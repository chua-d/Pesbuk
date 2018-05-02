package com.example.danceciliochua.pesbuk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Data.Users;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements OnMapReadyCallback {


    Bundle mArgs;
    private GoogleMap mMap;

    TextView mName;
    TextView mWork;
    TextView mEmail;
    TextView mPhone;
    TextView mWeb;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // MapFragment Setup
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // TextView Setup
        mName = (TextView) view.findViewById(R.id.profile_name);
        mWork = (TextView) view.findViewById(R.id.profile_work);
        mEmail = (TextView) view.findViewById(R.id.profile_email);
        mPhone = (TextView) view.findViewById(R.id.profile_phone);
        mWeb = (TextView) view.findViewById(R.id.profile_web);

        // Load Profile
        loadProfile(mArgs.getInt("id"));



        return view;


        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
    }

    public void loadProfile(int id) {


        APIClient client = ((APIBuild) getActivity().getApplication()).getClient();

        client.profile(id).enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                for(Users profile : response.body()) {
                    mName.setText(profile.getName() + " (" + profile.getUsername() + ")");
                    mWork.setText(profile.getCompany().getName());
                    mEmail.setText(profile.getEmail());
                    mPhone.setText(profile.getPhone());
                    mWeb.setText(profile.getWebsite());
                    String Address = profile.getAddress().getSuite() +
                            ", " + profile.getAddress().getStreet() +
                            ", " + profile.getAddress().getCity() +
                            ", " + profile.getAddress().getZipcode();

                    LatLng loc = new LatLng(Double.parseDouble(profile.getAddress().getGeo().getLat()),
                            Double.parseDouble(profile.getAddress().getGeo().getLng()));
                    mMap.addMarker(new MarkerOptions().position(loc).title(Address));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 17));
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
    }
}
