package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class sentSmsDetail extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_sms_detail);

        textView = findViewById(R.id.sentSmsEncryp);


        Bundle bundle = getIntent().getExtras();
        String isi = bundle.getString("isi_sms");

        textView.setText(isi);
    }
}
