package com.example.enkripsisms;

import java.util.ArrayList;

public class SMSDummy {
    private static String[] pengirim = {
            "Dospem",
            "mama",
            "adek"
    };

    private static String[] pesanTerakhir = {
            "PI Kerjain anjing",
            "beli tisu ya",
            "Eelllooo"
    };

    private static String[] tanggal = {
            "20/20/20",
            "20/20/20",
            "20/20/20"
    };

    static ArrayList<ThumbnailMsg> getListData() {
        ArrayList<ThumbnailMsg> list = new ArrayList<>();
        for (int position = 0; position < pengirim.length; position++) {
            ThumbnailMsg thumb = new ThumbnailMsg();
            thumb.setNama(pengirim[position]);
            thumb.setPesan(pesanTerakhir[position]);
            thumb.setTanggal(tanggal[position]);
            list.add(thumb);
        }
        return list;
    }
}
