package com.xxxgreen.mvx.krazykarlsonline.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemEntree;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Pizza;

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_5;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_6;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_7;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_8;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_9;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_SCHEMA;

/**
 * Created by MVX on 12/22/2018.
 */

public class DatabaseManager {
    private static final String TAG = "DatabaseManager";

    private static DatabaseManager sInstance;
    private SQLiteDatabase mDatabase;

    public static synchronized DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper myDbHelper;

    private DatabaseManager(Context context) {
        myDbHelper = new DatabaseHelper(context);
    }

    public Cursor queryAllPizzas() {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + PIZZA_SCHEMA;
        Cursor result = db.rawQuery(query, null);
        Log.i(TAG, "query result count: " + result.getCount());

        if (result.getCount() == 0) {
            Log.i(TAG, "Result count: 0\nCalling mDbHelper.onCreate()");
        }

        return result;
    }
    public Cursor queryAllItems(String tableName) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + tableName;
        Cursor result = db.rawQuery(query, null);
        Log.i(TAG, "query result count: " + result.getCount());

        if (result.getCount() == 0) {
            Log.i(TAG, "Result count: 0\nCalling mDbHelper.onCreate()");
        }

        return result;
    }

    public void addPizza(Pizza pizza) {
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

        Log.i(TAG, "Saving Pizza: " + pizza.name);
        ContentValues values = new ContentValues();
        values.put(PIZZA_2, pizza.name);
        values.put(PIZZA_3, pizza.base);
        values.put(PIZZA_4, pizza.top1);
        values.put(PIZZA_5, pizza.top2);
        values.put(PIZZA_6, pizza.top3);
        values.put(PIZZA_7, pizza.top4);
        values.put(PIZZA_8, pizza.top5);
        values.put(PIZZA_9, pizza.top6);

        long result = mDatabase.insert(PIZZA_SCHEMA, null, values);
        mDatabase.close();
    }

    public void deleteTable() {
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

        myDbHelper.onUpgrade(mDatabase, 0, 1);
        mDatabase.close();
    }

    public void deletePizza(int position) {
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        myDbHelper.deletePizza(mDatabase, position);
        mDatabase.close();
    }

    public void open() throws SQLException {
        mDatabase = myDbHelper.getWritableDatabase();
    }

    public void close() {
        if (mDatabase.isOpen()) {
            mDatabase.close();
        }
        this.myDbHelper.close();
    }
}
