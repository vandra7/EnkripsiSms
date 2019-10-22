package com.example.enkripsisms;

import java.util.ArrayList;

public class SMSDummy {
    private static String[] pengirim = {
            "Dospem",
            "mama",
            "adek",
            "zufar",
            "zahran",
            "galang"
    };

    private static String[] pesanTerakhir = {
            "PI Kerjain oit",
            "beli tisu ya",
            "Eelllooo",
            "kermh",
            "ayookk",
            "okey"
    };

    private static String[] tanggal = {
            "20/20/20",
            "20/20/19",
            "20/20/20",
            "20/20/20",
            "20/20/19",
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
