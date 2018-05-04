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
import com.example.danceciliochua.pesbuk.Adapters.AlbumsAdapter;
import com.example.danceciliochua.pesbuk.Adapters.CommentsAdapter;
import com.example.danceciliochua.pesbuk.Data.Albums;
import com.example.danceciliochua.pesbuk.Data.Comments;
import com.example.danceciliochua.pesbuk.Data.Todos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodosFragment extends Fragment {


    Bundle mArgs;
    View mView;
    RecyclerView mRecyclerView;

    ArrayList<Todos> mTodos;
    TodosAdapter mTodosAdapter;

    @Override
    public void setArguments(@Nullable Bundle args) {
        this.mArgs = args;
        super.setArguments(args);
    }

    public TodosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_todos, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.recyclerView);

        loadTodos();

        return mView;

    }

    public void loadTodos() {
        APIClient client = ((APIBuild) getContext().getApplicationContext()).getClient();


        client.listTodos(mArgs.getInt("id")).enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {
                mTodos = new ArrayList<>();
                mTodos.addAll(response.body());
                mTodosAdapter = new TodosAdapter(getActivity().getApplicationContext(), mTodos);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(mTodosAdapter);
            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {

            }
        });
    }

}
