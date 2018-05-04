package com.example.danceciliochua.pesbuk;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Adapters.PhotosAdapter;
import com.example.danceciliochua.pesbuk.Data.Photos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    ArrayList<Photos> mPhotos;
    PhotosAdapter mPhotosAdapter;
    public FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra("albumName"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mFm = this.getSupportFragmentManager();

        loadPhotos();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadPhotos() {
        APIClient client = ((APIBuild) getApplication()).getClient();

        client.listPhotos(getIntent().getIntExtra("id",0)).enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                mPhotos = new ArrayList<>();
                mPhotos.addAll(response.body());
                mPhotosAdapter = new PhotosAdapter(getApplicationContext(), mPhotos, mFm);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                mRecyclerView.setAdapter(mPhotosAdapter);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }
}
