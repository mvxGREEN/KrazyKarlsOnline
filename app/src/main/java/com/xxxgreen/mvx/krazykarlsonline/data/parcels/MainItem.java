package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MVX on 12/22/2018.
 */

public class MainItem implements Parcelable {
    public final int id;
    public final String name, base, top1, top2, top3, top4, top5, top6;

    public MainItem(String name, String base, String top1, String top2, String top3,
                    String top4, String top5, String top6) {
        this.id = 0;
        this.name = name;
        this.base = base;
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
        this.top4 = top4;
        this.top5 = top5;
        this.top6 = top6;
    }

    public MainItem(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.base = cursor.getString(2);
        this.top1 = cursor.getString(3);
        this.top2 = cursor.getString(4);
        this.top3 = cursor.getString(5);
        this.top4 = cursor.getString(6);
        this.top5 = cursor.getString(7);
        this.top6 = cursor.getString(8);
    }

    public MainItem(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.base = in.readString();
        this.top1 = in.readString();
        this.top2 = in.readString();
        this.top3 = in.readString();
        this.top4 = in.readString();
        this.top5 = in.readString();
        this.top6 = in.readString();
    }

    public static final Creator<MainItem> CREATOR = new Creator<MainItem>() {
        @Override
        public MainItem createFromParcel(Parcel in) {
            return new MainItem(in);
        }

        @Override
        public MainItem[] newArray(int size) {
            return new MainItem[size];
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
        dest.writeString(this.base);
        dest.writeString(this.top1);
        dest.writeString(this.top2);
        dest.writeString(this.top3);
        dest.writeString(this.top4);
        dest.writeString(this.top5);
        dest.writeString(this.top6);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
