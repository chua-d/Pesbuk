package com.example.danceciliochua.pesbuk;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Adapters.AlbumsAdapter;
import com.example.danceciliochua.pesbuk.Data.Albums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    Bundle mArgs;
    View mView;
    RecyclerView mRecyclerView;

    ArrayList<Albums> mAlbums;
    AlbumsAdapter mAlbumsAdapter;

    @Override
    public void setArguments(@Nullable Bundle args) {
        this.mArgs = args;
        super.setArguments(args);
    }

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_albums, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.recyclerView);



        loadAlbums();

        return mView;
    }

    public void loadAlbums() {
        APIClient client = ((APIBuild) getActivity().getApplication()).getClient();

        client.listAlbums(mArgs.getInt("id")).enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                mAlbums = new ArrayList<>();
                mAlbums.addAll(response.body());
                mAlbumsAdapter = new AlbumsAdapter(getActivity().getApplicationContext(), mAlbums);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                mRecyclerView.setAdapter(mAlbumsAdapter);
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {

            }
        });
    }

}
