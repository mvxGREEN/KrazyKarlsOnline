package com.xxxgreen.mvx.krazykarlsonline.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.xxxgreen.mvx.krazykarlsonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_5;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_6;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_7;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_8;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_9;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_SCHEMA;

/**
 * Created by MVX on 12/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "KKsDatabase.db";
    private static final int DATABASE_VERSION = 23;

    private static final String SQL_CREATE_PIZZA_TABLE =
            "CREATE TABLE " + PIZZA_SCHEMA + " (" +
                    PIZZA_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PIZZA_2 + " TEXT," +
                    PIZZA_3 + " TEXT," +
                    PIZZA_4 + " TEXT," +
                    PIZZA_5 + " TEXT," +
                    PIZZA_6 + " TEXT," +
                    PIZZA_7 + " TEXT," +
                    PIZZA_8 + " TEXT," +
                    PIZZA_9 + " TEXT)";


    private static final String SQL_DELETE_PIZZAS =
            "DROP TABLE IF EXISTS " + PIZZA_SCHEMA;

    private static final String SQL_DELETE_PIZZA =
            "DELETE FROM " + PIZZA_SCHEMA + " WHERE ID = ";

    private Resources mResources;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "SQLiteDatabase.onCreate! " + DATABASE_NAME);
        db.execSQL(SQL_CREATE_PIZZA_TABLE);

        try {
            readPizzasFromResources(db, "krazy_classics");
            readPizzasFromResources(db, "krazy_signatures");
        } catch (Exception e) {
            Log.w(TAG, "Error storing pizza data" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "SQLiteDatabase.onUpgrade! " + DATABASE_NAME);
        db.execSQL(SQL_DELETE_PIZZAS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "SQLiteDatabase.onDowngrade! " + DATABASE_NAME);
        onUpgrade(db, oldVersion, newVersion);
    }

    public void readPizzasFromResources(SQLiteDatabase db, String tableName) throws IOException, JSONException {
        Log.i(TAG, "Reading pizza data from resources");
        StringBuilder builder = new StringBuilder();
        InputStream inputStream;
        if (tableName.equals("krazy_classics")) {
            inputStream = mResources.openRawResource(R.raw.krazy_classics);
        } else {
            inputStream = mResources.openRawResource(R.raw.krazy_signatures);
        }

        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bfReader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        // Parse JSON data and insert into the provided database instance
        final JSONObject obj = new JSONObject(rawJson);
        final JSONArray krazy_classics = obj.getJSONArray(tableName);
        final int n = krazy_classics.length();
        Log.i(TAG, "length of pizzas table (json): " + n);
        for (int i = 0; i < n; ++i) {
            final JSONObject pizza = krazy_classics.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(PIZZA_2, pizza.getString("name"));
            values.put(PIZZA_3, pizza.getString("base"));
            values.put(PIZZA_4, pizza.getString("top1"));
            values.put(PIZZA_5, pizza.getString("top2"));
            values.put(PIZZA_6, pizza.getString("top3"));
            values.put(PIZZA_7, pizza.getString("top4"));
            values.put(PIZZA_8, pizza.getString("top5"));
            values.put(PIZZA_9, pizza.getString("top6"));

            long result = db.insert(PIZZA_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading data");
            }
        }
    }

    protected void deletePizza(SQLiteDatabase db, int i) {
        db.execSQL(SQL_DELETE_PIZZA + i);
    }

}
