package com.example.danceciliochua.pesbuk;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danceciliochua.pesbuk.API.APIBuild;
import com.example.danceciliochua.pesbuk.API.APIClient;
import com.example.danceciliochua.pesbuk.Data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView mName;
    TextView mWork;
    TextView mEmail;
    TextView mPhone;
    TextView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Posts"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ProfileAdapter adapter = new ProfileAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        String id = toString().valueOf(getIntent().getIntExtra("id",0));
        Toast.makeText(this, id ,Toast.LENGTH_SHORT).show();
        loadProfile(Integer.parseInt(id));
    }

    public void loadProfile(int id) {
        mName = (TextView) findViewById(R.id.profile_name);
        mWork = (TextView) findViewById(R.id.profile_work);
        mEmail = (TextView) findViewById(R.id.profile_email);
        mPhone = (TextView) findViewById(R.id.profile_phone);
        mWeb = (TextView) findViewById(R.id.profile_web);

        APIClient client = ((APIBuild) this.getApplication()).getClient();

        client.profile(id).enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                for(Users profile : response.body()) {
                    mName.setText(profile.getName() + " (" + profile.getUsername() + ")");
                    mWork.setText(profile.getCompany().getName());
                    mEmail.setText(profile.getEmail());
                    mPhone.setText(profile.getPhone());
                    mWeb.setText(profile.getWebsite());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

}
