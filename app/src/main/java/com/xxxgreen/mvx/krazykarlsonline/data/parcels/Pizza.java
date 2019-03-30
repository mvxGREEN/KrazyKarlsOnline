package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.util.Log;

public class Pizza extends ItemEntree {
    private static final String TAG = "ItemEntree (Pizza)";

    public enum TYPE {
        GRINDER, SMALL, SMALL_THIN, MEDIUM, MEDIUM_THIN, MEDIUM_GF, LARGE, LARGE_THIN
    }

    public Pizza(String name, String base, String top1, String top2, String top3,
                 String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Pizza(Cursor cursor) {
        super(cursor);
    }

    public double getPrice(Pizza.TYPE type) {
        double price = 0.00;
        switch (type) {
            case GRINDER:
            case SMALL:
            case SMALL_THIN:
                price = 4.99;
                break;
            case MEDIUM:
            case MEDIUM_THIN:
                price = 5.99;
                break;
            case MEDIUM_GF:
                price = 11.99;
                break;
            case LARGE:
            case LARGE_THIN:
                price = 7.99;
                break;
        }
        Log.i(TAG, "Generated price: " + price);
        return price;
    }
}
