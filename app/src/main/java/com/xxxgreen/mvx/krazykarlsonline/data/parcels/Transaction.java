package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by MVX on 12/22/2018.
 */

public class Transaction implements Parcelable {
    public final int id;
    public final String name;
    public final int type;
    public final Time timeReceived, timeQuoted, timeSettled;
    public final Customer customer;
    public final ArrayList<Transaction> orderItemList;
    //public final double[] expenseList, paymentList;

    public final int TYPE_PICKUP = 43, TYPE_DELIVERY = 44;

    private final double DELIVERY_FEE = 2.99;
    private final double EST_TAX_RATE = 0.11;  //TODO: find average tax rate
    private double subtotal = 0.00, total = 0.00;


    public Transaction(String name, int type, Time timeReceived, Time timeQuoted, Time timeSettled,
                       Customer customer, ArrayList<Transaction> orderItemList, double[] expenseList, double[] paymentList) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.timeReceived = timeReceived;
        this.timeQuoted = timeQuoted;
        this.timeSettled = timeSettled;
        this.customer = customer;
        this.orderItemList = orderItemList;
    }

    // Construct Transaction object with Cursor
    public Transaction(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.type = cursor.getInt(2);
        this.timeReceived = Time.valueOf(cursor.getString(3));
        this.timeQuoted = Time.valueOf(cursor.getString(4));
        this.timeSettled = Time.valueOf(cursor.getString(5));
        this.customer = cursor.getExtras().getParcelable("customer");
        this.orderItemList = cursor.getExtras().getParcelableArrayList("item_list");
    }

    // Construct Transaction object lol
    public Transaction(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.type = in.readInt();
        this.timeReceived = in.readParcelable(Time.class.getClassLoader());
        this.timeQuoted = in.readParcelable(Time.class.getClassLoader());
        this.timeSettled = in.readParcelable(Time.class.getClassLoader());
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        this.orderItemList = in.readParcelable(ArrayList.class.getClassLoader());
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
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
        dest.writeInt(this.type);
        dest.writeString(this.timeReceived.toString());
        dest.writeString(this.timeQuoted.toString());
        dest.writeString(this.timeSettled.toString());
        dest.readBundle(getClass().getClassLoader()).putParcelable("customer", this.customer);
        dest.readBundle(getClass().getClassLoader()).putParcelableArrayList("item_list", this.orderItemList);
    }



    @Override
    public String toString() {
        return this.name;
    }

}
