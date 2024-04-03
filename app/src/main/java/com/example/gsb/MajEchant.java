package com.example.gsb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MajEchant extends Activity {


    private EditText majEditTextQte;
    private EditText editTextLibelle;
    private EditText majEditTextCode;
    private TextView modifier;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.maj_echantillon);
        modifier=(TextView)findViewById(R.id.TextViewMessageModifier);
        Button buttonQuitter = (Button) findViewById(R.id.majButtonQuitterListe);
        Button buttonMajAjouter = (Button) findViewById(R.id.majButtonAjouter);
        Button buttonMajSupprimer = (Button) findViewById(R.id.majButtonSupprimer);
        majEditTextQte = (EditText) findViewById(R.id.majEditTextQte);
        majEditTextCode = (EditText) findViewById(R.id.majEditTextCode);

        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //fermeture de la fenêtre
            }
        });


        BdAdapter database = new BdAdapter(this);
        buttonMajAjouter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.open();

                try {
                    String code = majEditTextCode.getText().toString();
                    Integer quantite = Integer.parseInt(majEditTextQte.getText().toString());

                    Echantillon echant1 = database.getEchantillonWithLib(code);

                    if (echant1 != null) {
                        String lib1 = echant1.getLibelle();
                        Integer stock = Integer.parseInt(echant1.getQuantiteStock());
                        Integer newStock = stock + quantite;
                        String newStock1 = newStock.toString();

                        database.removeEchantillonWithCode(code);
                        database.insererEchantillon(new Echantillon(code, lib1, newStock1));
                        modifier.setText("Vous avez bien ajouter l'échantillon : "+lib1+"qui a pour quantite: "+newStock1);
                    } else {
                        // Gérer le cas où l'échantillon n'existe pas
                        Toast.makeText(getApplicationContext(), "Échantillon introuvable", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Gérer le cas où la conversion en entier échoue
                    Toast.makeText(getApplicationContext(), "La quantité doit être un nombre entier", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Gérer les autres exceptions
                    Toast.makeText(getApplicationContext(), "Une erreur s'est produite : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {

                    database.close();
                }
            }
        }));




        buttonMajSupprimer.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.open();

                try {
                    String code = majEditTextCode.getText().toString();
                    Integer quantite = Integer.parseInt(majEditTextQte.getText().toString());

                    Echantillon echant1 = database.getEchantillonWithLib(code);

                    if (echant1 != null) {
                        String lib1 = echant1.getLibelle();
                        Integer stock = Integer.parseInt(echant1.getQuantiteStock());
                        Integer newStock = stock - quantite;
                        String newStock1 = newStock.toString();

                        database.removeEchantillonWithCode(code);
                        database.insererEchantillon(new Echantillon(code, lib1, newStock1));
                        modifier.setText("Vous avez bien supprimer l'échantillon : "+lib1+"qui a pour quantite: "+newStock1);
                    } else {
                        // Gérer le cas où l'échantillon n'existe pas
                        Toast.makeText(getApplicationContext(), "Échantillon introuvable", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Gérer le cas où la conversion en entier échoue
                    Toast.makeText(getApplicationContext(), "La quantité doit être un nombre entier", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Gérer les autres exceptions
                    Toast.makeText(getApplicationContext(), "Une erreur s'est produite : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {

                    database.close();
                }
            }
        }));
    }}









