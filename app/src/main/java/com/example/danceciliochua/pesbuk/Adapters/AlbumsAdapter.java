package com.example.danceciliochua.pesbuk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Data.Albums;
import com.example.danceciliochua.pesbuk.Data.Photos;
import com.example.danceciliochua.pesbuk.MainActivity;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private ArrayList<Albums> mAlbums;
    private Context mContext;
    private View mView;

    /**
     * Constructor that passes in the sports data and the context
     * @param Albums ArrayList containing the sports data
     * @param context Context of the application
     */
    public AlbumsAdapter(Context context, ArrayList<Albums> Albums) {
        mAlbums = new ArrayList<>();
        this.mAlbums = Albums;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.ViewHolder holder, int position) {
        Albums currentAlbum = mAlbums.get(position);
        //Populate the textviews with data
        holder.bindTo(currentAlbum);
    }

    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_albums, parent, false));
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private ImageView mAlbumThumb;
        private TextView mAlbumTitle;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mAlbumThumb = (ImageView) itemView.findViewById(R.id.albums_imageview);
            mAlbumTitle = (TextView) itemView.findViewById(R.id.album_title);

        }

        void bindTo(Albums currentAlbum){
            //Populate the textviews with data
            mAlbumTitle.setText(currentAlbum.getTitle());
            APIClient client = ((APIBuild) mContext.getApplicationContext()).getClient();

            client.albumThum(currentAlbum.getId()).enqueue(new Callback<List<Photos>>() {
                @Override
                public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                    for(Photos photos : response.body()) {
                        Glide.with(mContext).load(photos.getThumbnailUrl()).into(mAlbumThumb);
                    }
                }

                @Override
                public void onFailure(Call<List<Photos>> call, Throwable t) {

                }
            });

        }

        @Override
        public void onClick(View view) {
            Albums currentAlbum = mAlbums.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, MainActivity.class);
            detailIntent.putExtra("id", currentAlbum.getId());
            mContext.startActivity(detailIntent);
        }
    }

}

