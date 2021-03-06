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

import static com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseSchema.PizzaSchema.PIZZA_SCHEMA;

public class PagePizzaFragment extends Fragment {
    private static final String TAG = "PizzaFragment";
    private static final String SECTION_NUMBER_KEY = "section_number";

    RecyclerView pizzaRecycler;
    EntreeRecyclerAdapter pizzaRecyclerAdapter;

    public PagePizzaFragment() {

    }

    // Returns new instance of this fragment for the given section number.
    public static PagePizzaFragment newInstance(int sectionNumber) {
        PagePizzaFragment fragment = new PagePizzaFragment();
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

        final ArrayList<ItemEntree> pizzaList = new ArrayList<>();
        pizzaRecyclerAdapter = new EntreeRecyclerAdapter(pizzaList, rootView.getContext(), PIZZA_SCHEMA);
        pizzaRecyclerAdapter.setOnItemClickListener(new EntreeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemEntree clickedEntree = pizzaList.get(position);
                Log.i(TAG, "clicked pizza: " + clickedEntree.name);
            }
        });
        pizzaRecycler.setAdapter(pizzaRecyclerAdapter);

        return rootView;
    }
}
