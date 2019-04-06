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

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_10;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_11;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_5;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_6;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_7;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_8;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_9;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_10;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_11;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_12;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_5;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_6;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_7;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_8;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_9;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_10;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_11;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_12;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_5;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_6;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_7;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_8;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_9;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SaladSchema.SALAD_SCHEMA;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_1;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_2;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_3;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_4;
import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_SCHEMA;

/**
 * Created by MVX on 12/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "KKsDatabase.db";
    private static final int DATABASE_VERSION = 28;

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
                    PIZZA_9 + " TEXT," +
                    PIZZA_10 + " TEXT," +
                    PIZZA_11 + " TEXT," +
                    PIZZA_12 + " TEXT)";

    private static final String SQL_CREATE_SIDE_TABLE =
            "CREATE TABLE " + SIDE_SCHEMA + " (" +
                    SIDE_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SIDE_2 + " TEXT," +
                    SIDE_3 + " TEXT," +
                    SIDE_4 + " TEXT)";

    private static final String SQL_CREATE_GRINDER_TABLE =
            "CREATE TABLE " + GRINDER_SCHEMA + " (" +
                    GRINDER_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    GRINDER_2 + " TEXT," +
                    GRINDER_3 + " TEXT," +
                    GRINDER_4 + " TEXT," +
                    GRINDER_5 + " TEXT," +
                    GRINDER_6 + " TEXT," +
                    GRINDER_7 + " TEXT," +
                    GRINDER_8 + " TEXT," +
                    GRINDER_9 + " TEXT," +
                    GRINDER_10 + " TEXT," +
                    GRINDER_11 + " TEXT)";

    private static final String SQL_CREATE_SALAD_TABLE =
            "CREATE TABLE " + SALAD_SCHEMA + " (" +
                    SALAD_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SALAD_2 + " TEXT," +
                    SALAD_3 + " TEXT," +
                    SALAD_4 + " TEXT," +
                    SALAD_5 + " TEXT," +
                    SALAD_6 + " TEXT," +
                    SALAD_7 + " TEXT," +
                    SALAD_8 + " TEXT," +
                    SALAD_9 + " TEXT)";

    private static final String SQL_CREATE_DRINK_DESSERT_TABLE =
            "CREATE TABLE " + DRINK_DESSERT_SCHEMA + " (" +
                    DRINK_DESSERT_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DRINK_DESSERT_2 + " TEXT," +
                    DRINK_DESSERT_3 + " TEXT," +
                    DRINK_DESSERT_4 + " TEXT)";


    private static final String SQL_DELETE_PIZZAS =
            "DROP TABLE IF EXISTS " + PIZZA_SCHEMA;

    private static final String SQL_DELETE_SIDES =
            "DROP TABLE IF EXISTS " + SIDE_SCHEMA;

    private static final String SQL_DELETE_GRINDERS =
            "DROP TABLE IF EXISTS " + GRINDER_SCHEMA;

    private static final String SQL_DELETE_SALADS =
            "DROP TABLE IF EXISTS " + SALAD_SCHEMA;

    private static final String SQL_DELETE_DRINKS_DESSERTS =
            "DROP TABLE IF EXISTS " + DRINK_DESSERT_SCHEMA;

    private static final String SQL_DELETE_PIZZA =
            "DELETE FROM " + PIZZA_SCHEMA + " WHERE ID = ";

    private static final String SQL_DELETE_SIDE =
            "DELETE FROM " + SIDE_SCHEMA + " WHERE ID = ";

    private static final String SQL_DELETE_GRINDER =
            "DELETE FROM " + GRINDER_SCHEMA + " WHERE ID = ";

    private static final String SQL_DELETE_SALAD =
            "DELETE FROM " + SALAD_SCHEMA + " WHERE ID = ";

    private static final String SQL_DELETE_DRINK_DESSERT =
            "DELETE FROM " + DRINK_DESSERT_SCHEMA + " WHERE ID = ";

    private Resources mResources;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "SQLiteDatabase.onCreate! " + DATABASE_NAME);
        db.execSQL(SQL_CREATE_PIZZA_TABLE);
        db.execSQL(SQL_CREATE_SIDE_TABLE);
        db.execSQL(SQL_CREATE_GRINDER_TABLE);
        db.execSQL(SQL_CREATE_SALAD_TABLE);
        db.execSQL(SQL_CREATE_DRINK_DESSERT_TABLE);

        try {
            readPizzasFromResources(db, "krazy_classics");
            readPizzasFromResources(db, "krazy_signatures");
            readSidesFromResources(db);
            readGrindersFromResources(db);
            readSaladsFromResources(db);
        } catch (Exception e) {
            Log.w(TAG, "Error storing data" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "SQLiteDatabase.onUpgrade! " + DATABASE_NAME);
        db.execSQL(SQL_DELETE_PIZZAS);
        db.execSQL(SQL_DELETE_SIDES);
        db.execSQL(SQL_DELETE_GRINDERS);
        db.execSQL(SQL_DELETE_SALADS);
        db.execSQL(SQL_DELETE_DRINKS_DESSERTS);
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
            values.put(PIZZA_10, pizza.getString("top7"));
            values.put(PIZZA_11, pizza.getString("top8"));
            values.put(PIZZA_12, pizza.getString("top9"));

            long result = db.insert(PIZZA_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading pizza data");
            }
        }
    }
    public void readSidesFromResources(SQLiteDatabase db) throws IOException, JSONException {
        Log.i(TAG, "Reading side data from resources");
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = mResources.openRawResource(R.raw.krazy_apps);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bfReader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        // Parse JSON data and insert into the provided database instance
        final JSONObject obj = new JSONObject(rawJson);
        final JSONArray krazy_sides = obj.getJSONArray("krazy_apps");
        final int n = krazy_sides.length();
        Log.i(TAG, "length of sides table (json): " + n);
        for (int i = 0; i < n; ++i) {
            final JSONObject side = krazy_sides.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(SIDE_2, side.getString("name"));
            values.put(SIDE_3, side.getString("types"));
            values.put(SIDE_4, side.getString("notes"));

            long result = db.insert(SIDE_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading side data");
            }
        }
    }
    public void readGrindersFromResources(SQLiteDatabase db) throws IOException, JSONException {
        Log.i(TAG, "Reading grinder data from resources");
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = mResources.openRawResource(R.raw.krazy_grinders);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bfReader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        // Parse JSON data and insert into the provided database instance
        final JSONObject obj = new JSONObject(rawJson);
        final JSONArray krazy_grinders = obj.getJSONArray("krazy_grinders");
        final int n = krazy_grinders.length();
        Log.i(TAG, "length of grinders table (json): " + n);
        for (int i = 0; i < n; ++i) {
            final JSONObject grinder = krazy_grinders.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(GRINDER_2, grinder.getString("name"));
            values.put(GRINDER_3, grinder.getString("top1"));
            values.put(GRINDER_4, grinder.getString("top2"));
            values.put(GRINDER_5, grinder.getString("top3"));
            values.put(GRINDER_6, grinder.getString("top4"));
            values.put(GRINDER_7, grinder.getString("top5"));
            values.put(GRINDER_8, grinder.getString("top6"));
            values.put(GRINDER_9, grinder.getString("top7"));
            values.put(GRINDER_10, grinder.getString("top8"));
            values.put(GRINDER_11, grinder.getString("top9"));

            long result = db.insert(SIDE_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading grinder data");
            }
        }
    }
    public void readSaladsFromResources(SQLiteDatabase db) throws IOException, JSONException {
        Log.i(TAG, "Reading grinder data from resources");
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = mResources.openRawResource(R.raw.krazy_grinders);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bfReader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        // Parse JSON data and insert into the provided database instance
        final JSONObject obj = new JSONObject(rawJson);
        final JSONArray krazy_salads = obj.getJSONArray("krazy_salads");
        final int n = krazy_salads.length();
        Log.i(TAG, "length of salads table (json): " + n);
        for (int i = 0; i < n; ++i) {
            final JSONObject salad = krazy_salads.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(SALAD_2, salad.getString("name"));
            values.put(SALAD_3, salad.getString("base"));
            values.put(SALAD_4, salad.getString("top1"));
            values.put(SALAD_5, salad.getString("top2"));
            values.put(SALAD_6, salad.getString("top3"));
            values.put(SALAD_7, salad.getString("top4"));
            values.put(SALAD_8, salad.getString("top5"));
            values.put(SALAD_9, salad.getString("top6"));
            values.put(SALAD_10, salad.getString("top7"));
            values.put(SALAD_11, salad.getString("top8"));
            values.put(SALAD_12, salad.getString("top9"));


            long result = db.insert(SIDE_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading salad data");
            }
        }
    }
    public void readDessertsDrinksResources(SQLiteDatabase db) throws IOException, JSONException {
        Log.i(TAG, "Reading drink/dessert data from resources");
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = mResources.openRawResource(R.raw.krazy_drinks_desserts);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bfReader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        // Parse JSON data and insert into the provided database instance
        final JSONObject obj = new JSONObject(rawJson);
        final JSONArray krazy_dds = obj.getJSONArray("krazy_drinks_desserts");
        final int n = krazy_dds.length();
        Log.i(TAG, "length of drink/desserts table (json): " + n);
        for (int i = 0; i < n; ++i) {
            final JSONObject side = krazy_dds.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(SIDE_2, side.getString("name"));
            values.put(SIDE_3, side.getString("types"));
            values.put(SIDE_4, side.getString("notes"));

            long result = db.insert(DRINK_DESSERT_SCHEMA, null, values);
            if (result == -1) {
                Log.i(TAG, "Error loading drink/dessert data");
            }
        }
    };

    protected void deletePizza(SQLiteDatabase db, int i) {
        db.execSQL(SQL_DELETE_PIZZA + i);
    }

}
