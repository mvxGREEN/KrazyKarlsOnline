package com.xxxgreen.mvx.krazykarlsonline.data.parcels;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import static com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemEntree.PRICE_MOD.SIGNATURE;

/**
 * Created by MVX on 12/22/2018.
 */

public class ItemTopping implements Parcelable {
    private static final String TAG = "ItemTopping";
    public final int id;
    public final String name, types, notes;

    public enum TYPE {
        MEAT, CHEESE, VEGGIE, SAUCE, OTHER
    }

    public enum PRICE_MOD {
        STANDARD, PREMIUM, FREE
    }

    public ItemTopping(String name, String types, String notes) {
        this.id = 0;
        this.name = name;
        this.types = types;
        this.notes = notes;
    }

    public ItemTopping(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.types = cursor.getString(2);
        this.notes = cursor.getString(3);
    }

    public ItemTopping(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.types = in.readString();
        this.notes = in.readString();
    }

    public static final Creator<ItemTopping> CREATOR = new Creator<ItemTopping>() {
        @Override
        public ItemTopping createFromParcel(Parcel in) {
            return new ItemTopping(in);
        }

        @Override
        public ItemTopping[] newArray(int size) {
            return new ItemTopping[size];
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

    /*
     *  Params:
     *      - Crust
     *      - Toppings
     *      - Price Modifier
     *      - Modified Toppings (Nullable)
     *
     */
    public double getCrustPrice(ItemEntree.CRUST crust, ItemTopping.TYPE type,
                            ItemTopping.PRICE_MOD mod) {
        double toppingPrice = 0.00;
        switch (type) {
            case MEAT:

            case VEGGIE:
            case CHEESE:
            case SAUCE:
            case OTHER:
        }

        Log.i(TAG, "Generated crust price: " + toppingPrice);
        return toppingPrice;
    }

}
