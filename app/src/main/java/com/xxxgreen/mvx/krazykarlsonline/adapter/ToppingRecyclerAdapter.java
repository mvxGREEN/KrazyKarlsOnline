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

import java.util.List;

public class ToppingRecyclerAdapter extends RecyclerView.Adapter<ToppingRecyclerAdapter.ItemEntreeHolder> {
    private final String TAG = "ToppingRecyclerAdapter";

    private List<ItemEntree> pizzaList;
    private OnItemClickListener itemClickListener;
    Context context;

    public ToppingRecyclerAdapter(List<ItemEntree> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;

        // Fill list with pizza data
        DatabaseManager dbm = DatabaseManager.getInstance(this.context);
    }

    public class ItemEntreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView i_icon;
        public TextView t_title, t_subtitle;

        public ItemEntreeHolder(View itemView) {
            super(itemView);
            this.i_icon = itemView.findViewById(R.id.icon_pizza);
            this.t_title = itemView.findViewById(R.id.title_item);
            this.t_subtitle = itemView.findViewById(R.id.toppings_item);
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
        View view = inflater.inflate(R.layout.menu_item, parent, false);

        return new ItemEntreeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemEntreeHolder holder, int position) {
        final ItemEntree pizza = pizzaList.get(position);
        Context ctx = holder.i_icon.getContext();
        Resources res = ctx.getResources();     // Get resources to access drawables

        holder.i_icon.setBackground(res.getDrawable(R.drawable.ic_specialty_pizza_white_128dp));
        holder.t_title.setText(pizza.name);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public ItemEntree getItem(int index) {
        if (index > -1 && index < getItemCount()) {
            return pizzaList.get(index);
        } else {
            return null;
        }
    }

    private void fillList(Cursor cursor) {
        if (cursor.isClosed()) {
            Log.e(TAG, "ERROR! fillList: cursor is closed!");
        } else if (cursor.moveToLast()) {
            do {
                ItemEntree p = new ItemEntree(cursor);
                this.pizzaList.add(p);
            } while(cursor.moveToPrevious());
        } else {
            Log.e(TAG, "cursor failed to move to last");
        }
    }

    // Click listener callback & setter
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
