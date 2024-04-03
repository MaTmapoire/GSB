package com.example.gsb;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {

    Button menuButtonAjoutEchantillon;
    Button menuButtonListeEchantillons;
    Button menuButtonMajEchantillons;

    Button menuButtonSupprimerDB;
    static final int VERSION_BDD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteDatabase("gsb.db");

        menuButtonAjoutEchantillon = (Button)findViewById(R.id.menuButtonAjoutEchantillon);
        menuButtonListeEchantillons = (Button)findViewById(R.id.menuButtonListeEchantillons);
        menuButtonMajEchantillons = (Button)findViewById(R.id.menuButtonMajEchantillons);


        menuButtonAjoutEchantillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AjoutEchant.class);
                startActivity(intent);

            }
        });

        menuButtonListeEchantillons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListeEchant.class);
                startActivity(intent);
            }
        });

        menuButtonMajEchantillons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MajEchant.class);
                startActivity(intent);
            }
        });








    }


}