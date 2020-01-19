package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class DataPesanKeluar extends AppCompatActivity {
    private RecyclerView rvPesan;
    private ArrayList<ThumbnailMsg> list = new ArrayList<>();
    TextView smsEncrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesan_keluar);

        smsEncrypt = findViewById(R.id.smsEncryp);

        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            Uri uriSms = Uri.parse("content://sms/sent");
            Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"}, null, null, null);

            cursor.moveToFirst();
            while  (cursor.moveToNext())
            {
                ThumbnailMsg msg = new ThumbnailMsg();

                msg.setNama(cursor.getString(1));
                msg.setPesan(cursor.getString(3));
                String millis = cursor.getString(2);
                msg.setTanggal(DateFormat.format("EEEE, MMMM dd, yyyy h:mm:ss aa", new Date(Long.parseLong(millis, 10))));

                list.add(msg);
            }

        }else{
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }

        rvPesan = findViewById(R.id.listpesan);
        rvPesan.setHasFixedSize(true);

        //list.addAll(SMSDummy.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvPesan.setLayoutManager(new LinearLayoutManager(this));

        CardViewMsgAdapter cardViewMsgAdapter =  new CardViewMsgAdapter(list, DataPesanKeluar.this);
        rvPesan.setAdapter(cardViewMsgAdapter);
    }
}
