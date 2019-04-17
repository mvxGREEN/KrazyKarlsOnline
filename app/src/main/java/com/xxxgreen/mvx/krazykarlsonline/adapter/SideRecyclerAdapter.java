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
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.ItemSide;
import com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseManager;

import java.util.ArrayList;

public class SideRecyclerAdapter extends RecyclerView.Adapter<SideRecyclerAdapter.SideHolder> {
    private final String TAG = "SideRecyclerAdapter";

    private ArrayList<ItemSide> sideList;
    private OnItemClickListener itemClickListener;
    Context context;

    public SideRecyclerAdapter(ArrayList<ItemSide> sideList, Context context, String tableName) {
        this.sideList = sideList;
        this.context = context;

        DatabaseManager dbm = DatabaseManager.getInstance(this.context);
        Cursor pizzaCursor = dbm.queryAllItems(tableName);
        fillList(pizzaCursor);
    }

    public class SideHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView i_icon;
        public TextView t_title, t_subtitle;

        public SideHolder(View itemView) {
            super(itemView);
            this.i_icon = itemView.findViewById(R.id.icon_pizza);
            this.t_title = itemView.findViewById(R.id.title_pizza);
            this.t_subtitle = itemView.findViewById(R.id.toppings_pizza);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public SideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_item, parent, false);

        return new SideHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SideHolder holder, int position) {
        final ItemSide zide = sideList.get(position);
        Context ctx = holder.i_icon.getContext();
        Resources res = ctx.getResources();     // Get resources to access drawables

        //holder.i_icon.setBackground(res.getDrawable(R.drawable.ic_specialty_pizza_white_128dp));
        holder.t_title.setText(zide.name);
        holder.t_subtitle.setText(zide.types);
    }

    @Override
    public int getItemCount() {
        return sideList.size();
    }

    public ItemSide getItem(int index) {
        if (index > -1 && index < getItemCount()) {
            return sideList.get(index);
        } else {
            return null;
        }
    }

    private void fillList(Cursor cursor) {
        if (cursor.isClosed()) {
            Log.e(TAG, "ERROR! fillList: cursor is closed!");
        } else if (cursor.moveToLast()) {
            do {
                ItemSide item = new ItemSide(cursor);
                this.sideList.add(item);
            } while(cursor.moveToPrevious());
        } else {
            Log.e(TAG, "cursor failed to move to last");
        }
    }

    // Click listener callback & setter
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final SideRecyclerAdapter.OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }
}
