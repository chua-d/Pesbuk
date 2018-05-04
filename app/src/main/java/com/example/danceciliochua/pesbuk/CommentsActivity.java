package com.example.danceciliochua.pesbuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Adapters.CommentsAdapter;
import com.example.danceciliochua.pesbuk.Data.Comments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<Comments> mComments;
    CommentsAdapter mAdapter;

    TextView mCommentPostName;
    TextView mCommentPostTitle;
    TextView mCommentPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize TextViews
        mCommentPostName = (TextView) findViewById(R.id.comment_post_name);
        mCommentPostBody = (TextView) findViewById(R.id.comment_post_body);
        mCommentPostTitle = (TextView) findViewById(R.id.comment_post_title);

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCurrentPost();

        loadComments();


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

    public void loadCurrentPost() {
        mCommentPostName.setText(getIntent().getStringExtra("postName"));
        mCommentPostTitle.setText(getIntent().getStringExtra("postTitle"));
        mCommentPostBody.setText(getIntent().getStringExtra("postBody"));
    }

    public void loadComments() {
        APIClient client = ((APIBuild) this.getApplication()).getClient();


        client.comments(getIntent().getIntExtra("postId",0)).enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                mComments = new ArrayList<>();
                mComments.addAll(response.body());
                mAdapter = new CommentsAdapter(getApplicationContext(), mComments);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
    }

}
