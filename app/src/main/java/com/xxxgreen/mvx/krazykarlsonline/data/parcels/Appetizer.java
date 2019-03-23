package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;

public class Appetizer extends SideItem {

    public Appetizer(String name, String types, String notes) {
        super(name, types, notes);
    }

    public Appetizer(Cursor cursor) {
        super(cursor);
    }
}
