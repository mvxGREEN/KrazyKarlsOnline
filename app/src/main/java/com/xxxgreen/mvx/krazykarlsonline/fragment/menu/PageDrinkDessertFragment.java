package com.xxxgreen.mvx.krazykarlsonline.fragment.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.adapter.SideRecyclerAdapter;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemSide;

import java.util.ArrayList;

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.DrinkDessertSchema.DRINK_DESSERT_SCHEMA;

public class PageDrinkDessertFragment extends Fragment {
    private static final String TAG = "PageDrinkDessertFrag";
        private static final String SECTION_NUMBER_KEY = "section_number";

    private RecyclerView drinkDessertRecycler;
    private SideRecyclerAdapter drinkDessertRecyclerAdapter;
    private ArrayList<ItemSide> drinkDessertList;

        public PageDrinkDessertFragment() {

        }


        // Returns new instance of this fragment for the given section number.
        public static PageDrinkDessertFragment newInstance(int sectionNumber) {
            PageDrinkDessertFragment fragment = new PageDrinkDessertFragment();
            Bundle args = new Bundle();
            args.putInt(SECTION_NUMBER_KEY, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int sectionNumber = 4;
            if (getArguments() != null) {
                sectionNumber = getArguments().getInt(SECTION_NUMBER_KEY);
            }

            Log.i(TAG, "Inflating layout #" + sectionNumber);
            View rootView = inflater.inflate(R.layout.fragment_menu_sides, container,
                    false);

            drinkDessertRecycler = rootView.findViewById(R.id.side_recycler);
            drinkDessertRecycler.setHasFixedSize(true);
            drinkDessertRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

            final ArrayList<ItemSide> sideList = new ArrayList<>();
            drinkDessertRecyclerAdapter = new SideRecyclerAdapter(sideList, rootView.getContext(),
                    DRINK_DESSERT_SCHEMA);
            drinkDessertRecyclerAdapter.setOnItemClickListener(new SideRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ItemSide clickedDrinkDessert = sideList.get(position);
                    Log.i(TAG, "clicked drink_dessert: " + clickedDrinkDessert.name);
                }
            });
            drinkDessertRecycler.setAdapter(drinkDessertRecyclerAdapter);

            return rootView;
        }
}
