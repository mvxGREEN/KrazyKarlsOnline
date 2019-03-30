package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import static com.xxxgreen.mvx.krazykarlsonline.data.parcels.Pizza.PRICE_MOD.SIGNATURE;

public class Pizza extends ItemEntree {
    private static final String TAG = "ItemEntree (Pizza)";

    public enum CRUST {
        GRINDER, SMALL, SMALL_THIN, MEDIUM, MEDIUM_THIN, MEDIUM_GF, LARGE, LARGE_THIN
    }
    public enum PRICE_MOD {
        BUILD_YOUR_OWN, SIGNATURE, BEAT_THE_CLOCK
    }

    public Pizza(String name, String base, String top1, String top2, String top3,
                 String top4, String top5, String top6) {
        super(name, base, top1, top2, top3, top4, top5, top6);
    }

    public Pizza(Cursor cursor) {
        super(cursor);
    }

    /*
     *  Params:
     *      - Crust
     *      - Toppings
     *      - Price Modifier
     *      - Modified Toppings (Nullable)
     *
     */
    public double getCrustPrice(Pizza.CRUST crust, ArrayList<String> toppings,
                        Pizza.PRICE_MOD mod, @Nullable ArrayList<String> modToppings) {
        double crustPrice = 0.00;

        if (mod == SIGNATURE) {
            // Signature price
            switch (crust) {
                case GRINDER:
                    crustPrice = 7.99;
                case SMALL:
                case SMALL_THIN:
                    crustPrice = 8.99;
                    break;
                case MEDIUM:
                case MEDIUM_THIN:
                    crustPrice = 5.99;
                    break;
                case MEDIUM_GF:
                    crustPrice = 11.99;
                    break;
                case LARGE:
                case LARGE_THIN:
                    crustPrice = 7.99;
                    break;
            }
        } else {
            // Base crust price
            switch (crust) {
                case GRINDER:
                    //TODO grinder BYcheck for accuracy
                    crustPrice = 3.99;
                    break;
                case SMALL:
                case SMALL_THIN:
                    crustPrice = 4.99;
                    break;
                case MEDIUM:
                case MEDIUM_THIN:
                    crustPrice = 5.99;
                    break;
                case MEDIUM_GF:
                    crustPrice = 11.99;
                    break;
                case LARGE:
                case LARGE_THIN:
                    crustPrice = 7.99;
                    break;
            }
        }


        Log.i(TAG, "Generated crust price: " + crustPrice);
        return crustPrice;
    }
}
