package com.example.enkripsisms;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BuatPesan extends AppCompatActivity {

    private EditText txtMsg;
    private EditText phone;
    private Button select;
    private Button sendBtn;
    private String phoneNo;
    private String message;
    private static final int RESULT_PICK_CONTACT =1;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesan);

        txtMsg = findViewById(R.id.txtMessage);
        sendBtn = findViewById(R.id.btnSendSms);
        phone = findViewById(R.id.txtphoneNo);
        select = findViewById(R.id.contact);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in, RESULT_PICK_CONTACT);

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });


    }

    //new method for encrypt data to send sms
    //1. buat sms
    //2. get data sms
    //3. panggil transencrypt
    //4. send sms

    protected void  sendSMS(){
        // Grabs phone number and message.
        phoneNo = phone.getText().toString();
        message = txtMsg.getText().toString();

        // Gets permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }

        } else{
            Toast.makeText(this, "Failed to pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:{
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplication(), "SMS sent.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "SMS failed, please try again.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    private void contactPicked(Intent data){
        Cursor cursor = null;

        try{
            String phoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null ,null,null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString(phoneIndex);

            phone.setText(phoneNo);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
