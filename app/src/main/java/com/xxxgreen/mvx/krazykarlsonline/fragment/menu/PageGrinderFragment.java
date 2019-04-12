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
import com.xxxgreen.mvx.krazykarlsonline.adapter.EntreeRecyclerAdapter;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemEntree;

import java.util.ArrayList;

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.GrinderSchema.GRINDER_SCHEMA;

public class PageGrinderFragment extends Fragment {
    private static final String TAG = "PageGrinderFragment";
    private static final String SECTION_NUMBER_KEY = "section_number";

    RecyclerView grinderRecycler;
    EntreeRecyclerAdapter grinderRecyclerAdapter;

    public PageGrinderFragment() {

    }


    // Returns new instance of this fragment for the given section number.
    public static PageGrinderFragment newInstance(int sectionNumber) {
        PageGrinderFragment fragment = new PageGrinderFragment();
        Bundle args = new Bundle();
        args.putInt(SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = 2;
        if (getArguments() != null) {
            sectionNumber = getArguments().getInt(SECTION_NUMBER_KEY);
        }

        Log.i(TAG, "Inflating layout #" + sectionNumber);
        View rootView = inflater.inflate(R.layout.fragment_menu_pizzas, container,
                false);

        grinderRecycler = rootView.findViewById(R.id.entree_recycler);
        grinderRecycler.setHasFixedSize(true);
        grinderRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        final ArrayList<ItemEntree> grinderList = new ArrayList<>();
        grinderRecyclerAdapter = new EntreeRecyclerAdapter(grinderList, rootView.getContext(), GRINDER_SCHEMA);
        grinderRecyclerAdapter.setOnItemClickListener(new EntreeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemEntree clickedEntree = grinderList.get(position);
                Log.i(TAG, "clicked grinder: " + clickedEntree.name);
            }
        });
        grinderRecycler.setAdapter(grinderRecyclerAdapter);

        return rootView;
    }
}
