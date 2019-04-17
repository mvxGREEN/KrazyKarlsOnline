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

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.SidesSchema.SIDE_SCHEMA;

public class PageSidesFragment extends Fragment {
    private static final String TAG = "PageSidesFragment";
    private static final String SECTION_NUMBER_KEY = "section_number";

    private RecyclerView sideRecycler;
    private SideRecyclerAdapter sideRecyclerAdapter;
    private ArrayList<ItemSide> sideList;

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

        sideRecycler = rootView.findViewById(R.id.entree_recycler);
        sideRecycler.setHasFixedSize(true);
        sideRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        final ArrayList<ItemSide> sideList = new ArrayList<>();
        sideRecyclerAdapter = new SideRecyclerAdapter(sideList, rootView.getContext(), SIDE_SCHEMA);
        sideRecyclerAdapter.setOnItemClickListener(new SideRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemSide clickedSide = sideList.get(position);
                Log.i(TAG, "clicked side: " + clickedSide.name);
            }
        });
        sideRecycler.setAdapter(sideRecyclerAdapter);

        return rootView;
    }
}
