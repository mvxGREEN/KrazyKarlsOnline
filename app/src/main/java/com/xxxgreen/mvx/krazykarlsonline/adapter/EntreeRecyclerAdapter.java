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
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemEntree;

import java.util.ArrayList;

public class EntreeRecyclerAdapter extends RecyclerView.Adapter<EntreeRecyclerAdapter.ItemEntreeHolder> {
    private final String TAG = "EntreeRecyclerAdapter";

    private ArrayList<ItemEntree> entreeList;
    private OnItemClickListener itemClickListener;
    private Context context;

    public EntreeRecyclerAdapter(ArrayList<ItemEntree> entreeList, Context context) {
        Log.i(TAG, "ItemEntreeRecyclerAdapter.onCreate");
        this.entreeList = entreeList;
        this.context = context;

        DatabaseManager dbm = DatabaseManager.getInstance(this.context);
        Cursor cursor = dbm.queryAllPizzas();

        while (cursor.moveToNext()) {
            ItemEntree pizza = new ItemEntree(cursor);
            Log.i(TAG, pizza.name);
            entreeList.add(pizza);
        }
        Log.i(TAG, "ItemEntree list length: " + entreeList.size());
    }

    public class ItemEntreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView i_icon;
        public TextView t_title, t_toppings;

        public ItemEntreeHolder(View itemView) {
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
    public ItemEntreeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pizza, parent, false);

        return new ItemEntreeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemEntreeHolder holder, int position) {
        final ItemEntree pizza = entreeList.get(position);
        Context ctx = holder.i_icon.getContext();
        Resources res = ctx.getResources();     // Get resources to access drawables

        //holder.i_icon.setBackground(res.getDrawable(R.drawable.ic_specialty_pizza_white_128dp));
        holder.t_title.setText(pizza.name);
        String toppings = pizza.base + ", " + pizza.top1 + ", " + pizza.top2 + ", " + pizza.top3;
        holder.t_toppings.setText(toppings);
    }

    @Override
    public int getItemCount() {
        return entreeList.size();
    }

    public ItemEntree getItemEntree(int index) {
        if (index > -1 && index < getItemCount()) {
            return entreeList.get(index);
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
