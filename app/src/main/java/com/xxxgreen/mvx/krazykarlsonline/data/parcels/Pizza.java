package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;

public class Pizza extends MainItem {
    public enum SIZE {
        GRINDER, SMALL, MEDIUM, LARGE,
    }
    public enum MODIFIER {
        THIN, GLUTEN_FREE
    }

    public Pizza(String name, String base, String top1, String top2, String top3,
                 String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Pizza(Cursor cursor) {
        super(cursor);
    }
}
