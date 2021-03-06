package com.xxxgreen.mvx.krazykarlsonline.data.sqlite;

import android.provider.BaseColumns;

/**
 * Created by MVX on 12/22/2018.
 */

public final class DatabaseSchema {
    // constructor is private to prevent instantiation
    private DatabaseSchema() {}

    // PIZZA
    public static class PizzaSchema implements BaseColumns {
        public static final String PIZZA_SCHEMA = "pizzas";
        public static final String PIZZA_1 = "id";
        public static final String PIZZA_2 = "name";
        public static final String PIZZA_3 = "base";
        public static final String PIZZA_4 = "top1";
        public static final String PIZZA_5 = "top2";
        public static final String PIZZA_6 = "top3";
        public static final String PIZZA_7 = "top4";
        public static final String PIZZA_8 = "top5";
        public static final String PIZZA_9 = "top6";
        public static final String PIZZA_10 = "top7";
        public static final String PIZZA_11 = "top8";
        public static final String PIZZA_12 = "top9";
    }

    // SIDE
    public static class SidesSchema implements BaseColumns {
        public static final String SIDE_SCHEMA = "sides";
        public static final String SIDE_1 = "id";
        public static final String SIDE_2 = "name";
        public static final String SIDE_3 = "sizes";
        public static final String SIDE_4 = "notes";
    }

    // GRINDER
    public static class GrinderSchema implements BaseColumns {
        public static final String GRINDER_SCHEMA = "grinders";
        public static final String GRINDER_1 = "id";
        public static final String GRINDER_2 = "name";
        public static final String GRINDER_3 = "top1";
        public static final String GRINDER_4 = "top2";
        public static final String GRINDER_5 = "top3";
        public static final String GRINDER_6 = "top4";
        public static final String GRINDER_7 = "top5";
        public static final String GRINDER_8 = "top6";
        public static final String GRINDER_9 = "top7";
        public static final String GRINDER_10 = "top8";
        public static final String GRINDER_11 = "top9";
    }

    // SALAD
    public static class SaladSchema implements BaseColumns {
        public static final String SALAD_SCHEMA = "salads";
        public static final String SALAD_1 = "id";
        public static final String SALAD_2 = "name";
        public static final String SALAD_3 = "base";
        public static final String SALAD_4 = "top1";
        public static final String SALAD_5 = "top2";
        public static final String SALAD_6 = "top3";
        public static final String SALAD_7 = "top4";
        public static final String SALAD_8 = "top5";
        public static final String SALAD_9 = "top6";
        public static final String SALAD_10 = "top7";
        public static final String SALAD_11 = "top8";
        public static final String SALAD_12 = "top9";
    }

    // DRINK/DESSERT
    public static class DrinkDessertSchema implements BaseColumns {
        public static final String DRINK_DESSERT_SCHEMA = "drinks_desserts";
        public static final String DRINK_DESSERT_1 = "id";
        public static final String DRINK_DESSERT_2 = "name";
        public static final String DRINK_DESSERT_3 = "sizes";
        public static final String DRINK_DESSERT_4 = "notes";
    }

}
