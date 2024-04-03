package com.example.gsb;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AjoutEchant extends Activity {
    private EditText editTextCode;
    private EditText editTextLibelle;
    private EditText editTextStock;

    private TextView textViewMessageAjouter;




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.ajout_echant);

        Button buttonAjouter = (Button)findViewById(R.id.ajoutButtonAjouter);
        Button buttonQuitter = (Button)findViewById(R.id.ajoutButtonQuitter);
        editTextCode = (EditText)findViewById(R.id.ajoutEditTextCode);
        editTextLibelle = (EditText)findViewById(R.id.ajoutEditTextLib);
        editTextStock = (EditText)findViewById(R.id.ajoutEditTextStock);
        textViewMessageAjouter=(TextView)findViewById(R.id.TextViewMessageAjouter);


        BdAdapter database = new BdAdapter(this);

        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                finish(); //fermeture de la fenêtre
            }
        });

        buttonAjouter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editTextCode.getText().toString();
                String lib = editTextLibelle.getText().toString();
                String stock = editTextStock.getText().toString();
                editTextLibelle = (EditText)findViewById(R.id.ajoutEditTextLib);


                database.open();
                Echantillon echant = new Echantillon(code,lib,stock);
                database.insererEchantillon(echant);
                textViewMessageAjouter.setText("Vous avez bien ajouter l'échantillon : "+lib);



                database.close();



            }
        }));




    }

}
