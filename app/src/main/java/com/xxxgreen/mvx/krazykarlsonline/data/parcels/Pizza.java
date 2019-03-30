package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class Pizza extends ItemEntree {

    public Pizza(String name, String base, String top1, String top2, String top3,
                 String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Pizza(Cursor cursor) {
        super(cursor);
    }
}
