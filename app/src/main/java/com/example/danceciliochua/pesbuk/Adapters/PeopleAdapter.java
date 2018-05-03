package com.example.danceciliochua.pesbuk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danceciliochua.pesbuk.Data.Users;
import com.example.danceciliochua.pesbuk.MainActivity;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private ArrayList<Users> mUsers;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context
     * @param Usersd ArrayList containing the sports data
     * @param context Context of the application
     */
    public PeopleAdapter(Context context, ArrayList<Users> Usersd) {
        mUsers = new ArrayList<>();
        this.mUsers = Usersd;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_people, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(PeopleAdapter.ViewHolder holder, int position) {
        //Get current sport
        Users currentUser = mUsers.get(position);
        //Populate the textviews with data
        holder.bindTo(currentUser);

    }


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private TextView mUsername;
        private TextView mName;
        private TextView mEmail;
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mUsername = (TextView)itemView.findViewById(R.id.people_username);
            mName = (TextView)itemView.findViewById(R.id.people_name);
            mEmail = (TextView)itemView.findViewById(R.id.people_email);
            mSportsImage = (ImageView) itemView.findViewById(R.id.people_imageview);
            itemView.setOnClickListener(this);

        }

        void bindTo(Users currentUser){
            //Populate the textviews with data
            mUsername.setText(currentUser.getUsername());
            mName.setText(currentUser.getName());
            mEmail.setText(currentUser.getEmail());

        }

        @Override
        public void onClick(View view) {
            Users currentUser = mUsers.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, MainActivity.class);
            detailIntent.putExtra("id", currentUser.getId());
            detailIntent.putExtra("name", currentUser.getName());
            mContext.startActivity(detailIntent);
        }
    }

}
