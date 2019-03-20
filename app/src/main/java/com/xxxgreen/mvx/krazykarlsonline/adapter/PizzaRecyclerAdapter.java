package com.xxxgreen.mvx.krazykarlsonline.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseManager;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Pizza;

import java.util.ArrayList;

public class PizzaRecyclerAdapter extends RecyclerView.Adapter<PizzaRecyclerAdapter.PizzaHolder> {
    private final String TAG = "PizzaRecyclerAdapter";

    private ArrayList<Pizza> pizzaList;
    private OnItemClickListener itemClickListener;
    private Context context;

    public PizzaRecyclerAdapter(ArrayList<Pizza> pizzaList, Context context) {
        Log.i(TAG, "PizzaRecyclerAdapter.onCreate");
        this.pizzaList = pizzaList;
        this.context = context;

        DatabaseManager dbm = DatabaseManager.getInstance(this.context);
        Cursor cursor = dbm.queryAllPizzas();

        while (cursor.moveToNext()) {
            Pizza pizza = new Pizza(cursor);
            Log.i(TAG, pizza.name);
            pizzaList.add(pizza);
        }
        Log.i(TAG, "Pizza list length: " + pizzaList.size());
    }

    public class PizzaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView i_icon;
        public TextView t_title, t_toppings;

        public PizzaHolder(View itemView) {
            super(itemView);
            this.i_icon = itemView.findViewById(R.id.icon_pizza);
            this.t_title = itemView.findViewById(R.id.title_pizza);
            this.t_toppings = itemView.findViewById(R.id.toppings_pizza);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pizza, parent, false);

        return new PizzaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaHolder holder, int position) {
        final Pizza pizza = pizzaList.get(position);
        Context ctx = holder.i_icon.getContext();
        Resources res = ctx.getResources();     // Get resources to access drawables

        holder.i_icon.setBackground(res.getDrawable(R.drawable.ic_specialty_pizza_white_128dp));
        holder.t_title.setText(pizza.name);
        String toppings = pizza.base + ", " + pizza.top1 + ", " + pizza.top2 + ", " + pizza.top3;
        holder.t_toppings.setText(toppings);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public Pizza getPizza(int index) {
        if (index > -1 && index < getItemCount()) {
            return pizzaList.get(index);
        } else {
            return null;
        }
    }

    // Click listener callback & setter
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }
}
