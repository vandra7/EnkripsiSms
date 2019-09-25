package com.example.enkripsisms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListMsgAdapter extends RecyclerView.Adapter<ListMsgAdapter.ListViewHolder> {
    private ArrayList<ThumbnailMsg>  listMsg;

    public ListMsgAdapter(ArrayList<ThumbnailMsg> list){
        this.listMsg = list;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg, viewGroup, false);
                return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ThumbnailMsg thumb = listMsg.get(position);
        holder.nama.setText(thumb.getNama());
        holder.pesan.setText(thumb.getPesan());
        holder.tanggal.setText(thumb.getTanggal());



    }

    @Override
    public int getItemCount() {
        return listMsg.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView nama, pesan, tanggal;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_contact);
            pesan = itemView.findViewById(R.id.isi_pesan);
            tanggal = itemView.findViewById(R.id.tgl);

        }
    }
}
