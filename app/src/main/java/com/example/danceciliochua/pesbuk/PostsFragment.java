package com.example.danceciliochua.pesbuk;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Adapters.PeopleAdapter;
import com.example.danceciliochua.pesbuk.Adapters.PostsAdapter;
import com.example.danceciliochua.pesbuk.Data.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    ArrayList<Posts> mPosts;
    PostsAdapter mPostAdapter;
    RecyclerView mRecyclerView;
    Bundle mArgs;
    View mView;


    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        this.mArgs = args;
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_posts, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.recyclerView);

        //Set the Layout Manager
        loadPosts();

        return mView;
    }

    public void loadPosts() {
        APIClient client = ((APIBuild) getActivity().getApplication()).getClient();

        client.posts(mArgs.getInt("id")).enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                mPosts = new ArrayList<>();
                mPosts.addAll(response.body());
                mPostAdapter = new PostsAdapter(getActivity().getApplicationContext(), mPosts);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(mPostAdapter);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }

}
