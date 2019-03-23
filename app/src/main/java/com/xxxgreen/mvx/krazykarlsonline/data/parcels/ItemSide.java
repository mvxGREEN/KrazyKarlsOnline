package com.xxxgreen.mvx.krazykarlsonline.data.parcels;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MVX on 12/22/2018.
 */

public class ItemSide implements Parcelable {
    public final int id;
    public final String name, types, notes;

    public ItemSide(String name, String types, String notes) {
        this.id = 0;
        this.name = name;
        this.types = types;
        this.notes = notes;
    }

    public ItemSide(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.types = cursor.getString(2);
        this.notes = cursor.getString(3);
    }

    public ItemSide(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.types = in.readString();
        this.notes = in.readString();
    }

    public static final Creator<ItemSide> CREATOR = new Creator<ItemSide>() {
        @Override
        public ItemSide createFromParcel(Parcel in) {
            return new ItemSide(in);
        }

        @Override
        public ItemSide[] newArray(int size) {
            return new ItemSide[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.types);
        dest.writeString(this.notes);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
