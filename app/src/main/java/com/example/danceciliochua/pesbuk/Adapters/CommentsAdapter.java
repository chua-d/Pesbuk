package com.example.danceciliochua.pesbuk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danceciliochua.pesbuk.Data.Comments;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private ArrayList<Comments> mComments;
    private Context mContext;


    /**
     * Constructor that passes in the sports data and the context
     * @param Comments ArrayList containing the sports data
     * @param context Context of the application
     */
    public CommentsAdapter(Context context, ArrayList<Comments> Comments) {
        mComments = new ArrayList<>();
        this.mComments = Comments;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        Comments currentComment = mComments.get(position);
        //Populate the textviews with data
        holder.bindTo(currentComment);
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_comments, parent, false));
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mCommentName;
        private TextView mCommentTitle;
        private TextView mCommentBody;


        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mCommentName = (TextView)itemView.findViewById(R.id.comment_name);
            mCommentTitle = (TextView)itemView.findViewById(R.id.comment_title);
            mCommentBody = (TextView)itemView.findViewById(R.id.comment_body);

        }

        void bindTo(Comments currentComment){
            //Populate the textviews with data
            mCommentName.setText(currentComment.getEmail());
            mCommentTitle.setText(currentComment.getName());
            mCommentBody.setText(currentComment.getBody());

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