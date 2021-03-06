package com.example.danceciliochua.pesbuk.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.danceciliochua.pesbuk.AlbumsFragment;
import com.example.danceciliochua.pesbuk.PostsFragment;
import com.example.danceciliochua.pesbuk.ProfileFragment;
import com.example.danceciliochua.pesbuk.TodosFragment;

public class ProfileAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    int mUserId;
    Bundle bundle;

    public ProfileAdapter(FragmentManager fm, int NumOfTabs, int Userid, String name) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mUserId = Userid;
        bundle = new Bundle();
        bundle.putInt("id", Userid);
        bundle.putString("name", name);

    }

    @Override
    public Fragment getItem(int position) {
        ProfileFragment profileFragment = new ProfileFragment();
        PostsFragment postsFragment = new PostsFragment();
        AlbumsFragment albumsFragment = new AlbumsFragment();
        TodosFragment todosFragment = new TodosFragment();
        switch (position) {
            case 0:
                profileFragment.setArguments(bundle);
                return profileFragment;

            case 1:
                postsFragment.setArguments(bundle);
                return postsFragment;

            case 2:
                albumsFragment.setArguments(bundle);
                return albumsFragment;

            case 3:
                todosFragment.setArguments(bundle);
                return todosFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
