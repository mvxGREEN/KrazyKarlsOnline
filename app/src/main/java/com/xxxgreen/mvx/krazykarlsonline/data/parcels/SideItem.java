package com.xxxgreen.mvx.krazykarlsonline.data.parcels;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MVX on 12/22/2018.
 */

public class SideItem implements Parcelable {
    public final int id;
    public final String name, types, notes;

    public SideItem(String name, String types, String notes) {
        this.id = 0;
        this.name = name;
        this.types = types;
        this.notes = notes;
    }

    public SideItem(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.types = cursor.getString(2);
        this.notes = cursor.getString(3);
    }

    public SideItem(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.types = in.readString();
        this.notes = in.readString();
    }

    public static final Creator<SideItem> CREATOR = new Creator<SideItem>() {
        @Override
        public SideItem createFromParcel(Parcel in) {
            return new SideItem(in);
        }

        @Override
        public SideItem[] newArray(int size) {
            return new SideItem[size];
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
