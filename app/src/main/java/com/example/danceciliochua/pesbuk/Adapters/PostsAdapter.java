package com.example.danceciliochua.pesbuk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danceciliochua.pesbuk.Data.Posts;
import com.example.danceciliochua.pesbuk.MainActivity;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<Posts> mPosts;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context
     * @param Posts ArrayList containing the sports data
     * @param context Context of the application
     */
    public PostsAdapter(Context context, ArrayList<Posts> Posts) {
        mPosts = new ArrayList<>();
        this.mPosts = Posts;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Posts currentPost = mPosts.get(position);
        //Populate the textviews with data
        holder.bindTo(currentPost);
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_posts, parent, false));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mPostName;
        private TextView mPostTitle;
        private TextView mPostBody;
        private TextView mCommentButton;
        private int mPostId;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mPostName = (TextView)itemView.findViewById(R.id.post_name);
            mPostTitle = (TextView)itemView.findViewById(R.id.post_title);
            mPostBody = (TextView)itemView.findViewById(R.id.post_body);
            mCommentButton = (TextView)itemView.findViewById(R.id.post_comments_button);


        }

        void bindTo(Posts currentPost){
            //Populate the textviews with data
            mPostTitle.setText(currentPost.getTitle());
            mPostBody.setText(currentPost.getBody());
            mPostId = currentPost.getId();
            mCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mPostId,Toast.LENGTH_SHORT);
                }
            });

        }

        /**@Override
        public void onClick(View view) {
            Posts currentPost = mPosts.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, MainActivity.class);
            detailIntent.putExtra("id", currentPost.getId());
            mContext.startActivity(detailIntent);
        }**/
    }

}
