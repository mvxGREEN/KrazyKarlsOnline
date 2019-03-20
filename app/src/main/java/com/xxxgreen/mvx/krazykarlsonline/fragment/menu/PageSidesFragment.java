package com.xxxgreen.mvx.krazykarlsonline.fragment.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxxgreen.mvx.krazykarlsonline.R;

public class PageSidesFragment extends Fragment {
    private static final String TAG = "PageSidesFragment";
    private static final String SECTION_NUMBER_KEY = "section_number";
    private RecyclerView appsRecycler;

    public PageSidesFragment() {

    }


    // Returns new instance of this fragment for the given section number.
    public static PageSidesFragment newInstance(int sectionNumber) {
        PageSidesFragment fragment = new PageSidesFragment();
        Bundle args = new Bundle();
        args.putInt(SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = 1;
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
