package com.xxxgreen.mvx.krazykarlsonline.utils;

import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Topping;

import java.util.ArrayList;

public final class MenuUtils {
    //TODO: find prices for Daiya

    public static int CATEGORY_PIZZA = 0, CATEGORY_APPS_SIDES = 1, CATEGORY_GRINDERS = 2,
            CATEGORY_SALADS = 3, CATEGORY_DRINKS_DESSERTS = 4;
    public static String[] MENU_PAGE_TITLES = {
            "Pizza", "Apps & Sides", "Grinders", "Salads", "Drinks & Desserts"
    };
    private static int[][] sizes = {
        {10, 12, 14},   // Pizza (10, 12, or 14 inches)
        {1, 3, 5, 6, 7, 8, 10, 12, 14, 16},     // Apps/Sides (all)
        {10},           // Grinders (one-size)
        {1, 2, 8},     // Salads (Half, Full, Family)
        {1}             // Desserts/Drinks (one-size)
    };

    public static int[] getSizes(int category) {
        return sizes[category];
    }

    public static Topping getToppingByName(String name) {
        return new Topping(0, name, false); //TODO generate correct topping object
    }

    public static ArrayList<Topping> getToppings(String itemName) {
        return new ArrayList<>();
    }

}
