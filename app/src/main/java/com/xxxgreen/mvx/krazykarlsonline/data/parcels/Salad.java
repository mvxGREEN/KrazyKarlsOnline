package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;

public class Salad extends ItemEntree {


    public Salad(String name, String base, String top1, String top2, String top3,
                 String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Salad(Cursor cursor) {
        super(cursor);
    }
}
