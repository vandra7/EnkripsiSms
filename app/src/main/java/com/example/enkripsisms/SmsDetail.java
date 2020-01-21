package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SmsDetail extends AppCompatActivity {
    TextView textView;
    TextView smsDecryt;
    EditText keyDe;
    Button btnDecrypt;
    String message;
    String key;
    String decryptPhase1;
    String decryptPhase2;
    String doubleColumnarTransposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_detail);

       textView = findViewById(R.id.smsEncryp);
       smsDecryt = findViewById(R.id.smsDecrypt);
       keyDe = findViewById(R.id.key_de);
       btnDecrypt = findViewById(R.id.btnDecrypt);

       Bundle bundle = getIntent().getExtras();
       String isi = bundle.getString("isi_sms");
       textView.setText(isi);


       btnDecrypt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               deSms();
           }
       });

    }

    protected void deSms(){
        message = textView.getText().toString();
        key = keyDe.getText().toString();

      try {

          decryptPhase1 = Decryption.decryptColumnarTransposition(key, doubleColumnarTransposition);
          decryptPhase2 = Decryption.decryptColumnarTransposition(key, decryptPhase1);
          Log.d("hasilDecrypt", decryptPhase2);
      }
      catch (Exception e){
          Log.d("hasilDecrypt", "gagal");
      }

    }
}
