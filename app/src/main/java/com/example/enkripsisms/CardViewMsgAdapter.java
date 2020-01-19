package com.example.enkripsisms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewMsgAdapter extends RecyclerView.Adapter<CardViewMsgAdapter.CardViewMsgViewHolder>{

    private ArrayList<ThumbnailMsg> listMsg;
    Context context;

    public CardViewMsgAdapter(ArrayList<ThumbnailMsg> listMsg, Context ctx) {
        this.listMsg = listMsg;
        this.context = ctx;
    }

    @NonNull
    @Override
    public CardViewMsgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_hero, viewGroup,false);
        return new CardViewMsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewMsgViewHolder holder, int position) {
        ThumbnailMsg thum = listMsg.get(position);

        holder.nama.setText(thum.getNama());
        //holder.nama.setVisibility(View.GONE);
        holder.pesan.setText(thum.getPesan());
        holder.tanggal.setText(thum.getTanggal());

        if(context instanceof DataPesanKeluar) {
            holder.nama.setVisibility(View.GONE);
        }



        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof  DataPesanKeluar){
                    Intent intent = new Intent(context, sentSmsDetail.class);
                    intent.putExtra("isi_sms", holder.pesan.getText().toString());
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, SmsDetail.class);
                    intent.putExtra("isi_sms", holder.pesan.getText().toString());
                    context.startActivity(intent);
                }

            }
        });


      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(v.getContext(),SmsDetail.class);
                pindah.putExtra("isi_pesan", holder.pesan.getText().toString());
                startActivity(pindah);

            }
        }); */

    }

    @Override
    public int getItemCount() {
        return listMsg.size();
    }

    class CardViewMsgViewHolder extends RecyclerView.ViewHolder {
        TextView nama, pesan, tanggal;
        RelativeLayout recyclerView;
        public CardViewMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_contact);
            pesan = itemView.findViewById(R.id.isi_pesan);
            tanggal = itemView.findViewById(R.id.tgl);
            recyclerView = itemView.findViewById(R.id.RLayout);
        }
    }
}
