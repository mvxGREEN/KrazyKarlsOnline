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

public class PageEntreeFragment extends Fragment {
    private static final String TAG = "PizzaFragment";
    private static final String SECTION_NUMBER_KEY = "section_number";

    RecyclerView pizzaRecycler;
    EntreeRecyclerAdapter entreeRecyclerAdapter;

    public PageEntreeFragment() {

    }

    // Returns new instance of this fragment for the given section number.
    public static PageEntreeFragment newInstance(int sectionNumber) {
        PageEntreeFragment fragment = new PageEntreeFragment();
        Bundle args = new Bundle();
        args.putInt(SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = 0;
        if (getArguments() != null) {
            sectionNumber = getArguments().getInt(SECTION_NUMBER_KEY);
        }

        Log.i(TAG, "Inflating layout #" + sectionNumber);
        View rootView = inflater.inflate(R.layout.fragment_menu_pizzas, container,
                false);

        pizzaRecycler = rootView.findViewById(R.id.entree_recycler);
        pizzaRecycler.setHasFixedSize(true);
        pizzaRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        final ArrayList<ItemEntree> entreeList = new ArrayList<>();
        entreeRecyclerAdapter = new EntreeRecyclerAdapter(entreeList, rootView.getContext());
        entreeRecyclerAdapter.setOnItemClickListener(new EntreeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemEntree clickedEntree = entreeList.get(position);
                Log.i(TAG, "clicked item: " + clickedEntree.name);
            }
        });
        pizzaRecycler.setAdapter(entreeRecyclerAdapter);

        return rootView;
    }
}
