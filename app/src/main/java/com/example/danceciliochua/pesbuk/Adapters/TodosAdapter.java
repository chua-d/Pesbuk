package com.example.danceciliochua.pesbuk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danceciliochua.pesbuk.Data.Todos;
import com.example.danceciliochua.pesbuk.R;

import java.util.ArrayList;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private ArrayList<Todos> mTodos;
    private Context mContext;


    /**
     * Constructor that passes in the sports data and the context
     * @param Todos ArrayList containing the sports data
     * @param context Context of the application
     */
    public TodosAdapter(Context context, ArrayList<Todos> Todos) {
        mTodos = new ArrayList<>();
        this.mTodos = Todos;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull TodosAdapter.ViewHolder holder, int position) {
        Todos currentTodo = mTodos.get(position);
        //Populate the textviews with data
        holder.bindTo(currentTodo);
    }

    @Override
    public TodosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodosAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_todos, parent, false));
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mTodoTitle;
        private TextView mTodoStatus;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTodoTitle = (TextView) itemView.findViewById(R.id.todo_title);
            mTodoStatus = (TextView) itemView.findViewById(R.id.todo_status);

        }

        void bindTo(Todos currentTodo){
            //Populate the textviews with data
            mTodoTitle.setText(currentTodo.getTitle());
            if(currentTodo.getCompleted()) {
                mTodoStatus.setTextColor(Color.GREEN);
                mTodoStatus.setText("Completed!");
            } else {
                mTodoStatus.setTextColor(Color.RED);
                mTodoStatus.setText("Incomplete");
            }

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