package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.os.Parcel;
import android.os.Parcelable;

public class Topping implements Parcelable {
    public final int category;
    public final String name;
    public final boolean isPremium;

    public Topping(int category, String name, boolean isPremium) {
        this.category = category;
        this.name = name;
        this.isPremium = isPremium;
    }

    protected Topping(Parcel in) {
        category = in.readInt();
        name = in.readString();
        isPremium = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(category);
        dest.writeString(name);
        dest.writeByte((byte) (isPremium ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Topping> CREATOR = new Creator<Topping>() {
        @Override
        public Topping createFromParcel(Parcel in) {
            return new Topping(in);
        }

        @Override
        public Topping[] newArray(int size) {
            return new Topping[size];
        }
    };
}
