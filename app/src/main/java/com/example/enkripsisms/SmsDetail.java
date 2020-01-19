package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SmsDetail extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_detail);

       textView = findViewById(R.id.smsEncryp);


        Bundle bundle = getIntent().getExtras();
        String isi = bundle.getString("isi_sms");

        textView.setText(isi);
    }
}
