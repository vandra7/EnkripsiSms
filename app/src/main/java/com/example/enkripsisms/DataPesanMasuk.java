package com.example.enkripsisms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DataPesanMasuk extends AppCompatActivity {
    private RecyclerView rvPesan;
    private ArrayList<ThumbnailMsg> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesan_masuk);

        rvPesan = findViewById(R.id.listpesan);
        rvPesan.setHasFixedSize(true);

        list.addAll(SMSDummy.getListData());
        showRecyclerList();
    }


    private void showRecyclerList(){
        rvPesan.setLayoutManager(new LinearLayoutManager(this));
        ListMsgAdapter listMsgAdapter = new ListMsgAdapter((list));
        rvPesan.setAdapter(listMsgAdapter);
    }
}
