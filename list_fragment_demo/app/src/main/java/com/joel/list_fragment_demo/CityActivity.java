package com.joel.list_fragment_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class CityActivity extends AppCompatActivity {

    int[] photos = new int[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        int dataIndex = getIntent().getIntExtra("position",0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CityFragment cityFragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",dataIndex);
        cityFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.cityPhoto, cityFragment);
        fragmentTransaction.commit();
    }
}