package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Customer implements Parcelable {
    private static final String TAG = "Customer";
    public final int id;
    public final String name, phoneNumber, address, city, zip_code, notes;
    public final ArrayList favorites;

    protected Customer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
        city = in.readString();
        zip_code = in.readString();
        notes = in.readString();
        favorites = in.readArrayList(ArrayList.class.getClassLoader());
    }

    public Customer(String name, String phoneNumber, String address,
                       String city, String zip_code, String notes, ArrayList<ItemEntree> favorites) {
        this.id = 0;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.zip_code = zip_code;
        this.notes = notes;
        this.favorites = favorites;
    }

    public Customer(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.phoneNumber = cursor.getString(2);
        this.address = cursor.getString(3);
        this.city = cursor.getString(4);
        this.zip_code = cursor.getString(5);
        this.notes = cursor.getString(6);
        this.favorites = new ArrayList<>();
        for (int j = 0; j < cursor.getColumnCount(); j++) {
            this.favorites.add(cursor.getInt(j));
        }
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
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
        dest.writeString(this.phoneNumber);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.zip_code);
        dest.writeString(this.notes);
    }

    @Override
    public String toString() {
        String temp = id + "," + name + "," + phoneNumber + "," + address + "," +
                city + "," + zip_code + "," + notes;
        StringBuilder builder = new StringBuilder();
        builder.append(temp);
        for (int i = 0; i < favorites.size(); i++) {
            if (i == 0) {
                builder.append(",");
            }
            builder.append(favorites.get(i)).append(",");
        }
        return builder.toString();
    }
}
