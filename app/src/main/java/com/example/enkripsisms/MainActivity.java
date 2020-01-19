package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BuatPesanMasuk;
    Button PesanMasuk;
    Button PesanKeluar;
    Button About;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BuatPesanMasuk = findViewById(R.id.TombolBuat);
        PesanMasuk = findViewById(R.id.TombolMasuk);
        PesanKeluar = findViewById(R.id.TombolPesanKeluar);
        About = findViewById(R.id.TombolAbout);

        BuatPesanMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, BuatPesan.class);
                startActivity(pindah);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, About.class);
                startActivity(pindah);
            }
        });

        PesanMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, DataPesanMasuk.class);
                startActivity(pindah);
            }
        });

        PesanKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataPesanKeluar.class);
                startActivity(intent);
            }
        });


    }
}
