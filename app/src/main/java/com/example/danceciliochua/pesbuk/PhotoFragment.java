package com.example.danceciliochua.pesbuk;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PhotoFragment extends DialogFragment {

    View mView;
    String mUrl;
    ImageView photo;

    @SuppressLint("ValidFragment")
    public PhotoFragment(String url) {
        // Required empty public constructor
        this.mUrl = url;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_photo, container, false);

        photo = (ImageView) mView.findViewById(R.id.dialog_photo);
        Glide.with(getContext()).load(mUrl).into(photo);
        getDialog().setTitle("Photo");

        return mView;
    }

}
