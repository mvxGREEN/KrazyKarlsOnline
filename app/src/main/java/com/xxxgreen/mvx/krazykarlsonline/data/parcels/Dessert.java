package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;

public class Dessert extends SideItem {

    public Dessert(String name, String types, String notes) {
        super(name, types, notes);
    }

    public Dessert(Cursor cursor) {
        super(cursor);
    }
}
