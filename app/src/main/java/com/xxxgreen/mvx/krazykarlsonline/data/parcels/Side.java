package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;

public class Side extends MainItem {

    public Side(String name, String base, String top1, String top2, String top3,
                String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Side(Cursor cursor) {
        super(cursor);
    }
}
