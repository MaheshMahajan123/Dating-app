package com.example.tinderchat.Add_images;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tinderchat.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Image_Upload> mUploads;
    private String TAG = "adapterTAG";
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<Image_Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.imagei_tem, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Image_Upload uploadCurrent = mUploads.get(position);
//        Picasso.with(mContext)
//            .load(uploadCurrent.getImageUrl())
//            .fit()
//                .centerCrop()
//                .into(holder.imageView);
        //  Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerInside().into(holder.imageView);
        Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        Log.d(TAG, "onBindViewHolder: "+ mUploads.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener
    {
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_upload);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("select Action");
            MenuItem doWhatever = contextMenu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = contextMenu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (menuItem.getItemId())
                    {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}