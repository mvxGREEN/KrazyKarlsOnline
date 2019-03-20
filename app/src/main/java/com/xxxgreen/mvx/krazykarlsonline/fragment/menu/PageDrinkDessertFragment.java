package com.xxxgreen.mvx.krazykarlsonline.fragment.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxxgreen.mvx.krazykarlsonline.R;

public class PageDrinkDessertFragment extends Fragment {
    private static final String TAG = "PageDrinkDessertFrag";
        private static final String SECTION_NUMBER_KEY = "section_number";

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
            View rootView = inflater.inflate(R.layout.fragment_menu_pizzas, container,
                    false);

            //TODO: inflate recycler

            return rootView;
        }
}
