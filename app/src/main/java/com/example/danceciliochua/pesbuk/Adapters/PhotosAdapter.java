package com.example.danceciliochua.pesbuk.Adapters;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.danceciliochua.pesbuk.Data.Photos;
import com.example.danceciliochua.pesbuk.PhotoFragment;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private ArrayList<Photos> mPhotos;
    private Context mContext;
    private View mView;
    FragmentManager fm;

    /**
     * Constructor that passes in the sports data and the context
     * @param Photos ArrayList containing the sports data
     * @param context Context of the application
     */
    public PhotosAdapter(Context context, ArrayList<Photos> Photos, FragmentManager fm) {
        mPhotos = new ArrayList<>();
        this.mPhotos = Photos;
        this.mContext = context;
        this.fm = fm;

    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.ViewHolder holder, int position) {
        Photos currentPhoto = mPhotos.get(position);
        //Populate the textviews with data
        holder.bindTo(currentPhoto);
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotosAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_photos, parent, false));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private ImageView mPhotoThumb;
        private TextView mPhotoTitle;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mPhotoThumb = (ImageView) itemView.findViewById(R.id.photo_imageview);
            mPhotoTitle = (TextView) itemView.findViewById(R.id.photo_title);
            itemView.setOnClickListener(this);

        }

        void bindTo(Photos currentPhoto){
            //Populate the textviews with data
            mPhotoTitle.setText(currentPhoto.getTitle());
            Glide.with(mContext).load(currentPhoto.getThumbnailUrl()).into(mPhotoThumb);

        }

        @Override
        public void onClick(View view) {
            Photos currentPhoto = mPhotos.get(getAdapterPosition());
            PhotoFragment dialogFragment = new PhotoFragment(currentPhoto.getUrl());
            dialogFragment.show(fm,"");
        }
    }

}


