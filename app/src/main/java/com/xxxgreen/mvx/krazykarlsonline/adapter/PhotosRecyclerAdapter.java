package com.xxxgreen.mvx.krazykarlsonline.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xxxgreen.mvx.krazykarlsonline.R;

import java.util.ArrayList;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.PhotoHolder> {
    private final String TAG = "PhotosRecyclerAdapter";

    private ArrayList<Integer> photoList;
    private OnItemClickListener itemClickListener;
    Context context;

    public PhotosRecyclerAdapter(ArrayList<Integer> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img_photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            this.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_photo, parent, false);

        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        final Integer photoId = photoList.get(position);
        Context ctx = holder.img_photo.getContext();
        Resources res = ctx.getResources();
        holder.img_photo.setBackground(res.getDrawable(photoId));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    // Click listener callback & setter
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
