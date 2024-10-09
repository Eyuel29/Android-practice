package com.joel.chat_app_fragment_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button replaceWithFragmentOne, replaceWithFragmentTwo, replaceWithFragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        FragmentThree fragmentThree = new FragmentThree();

        replaceWithFragmentOne = findViewById(R.id.buttonFragmentOne);
        replaceWithFragmentTwo = findViewById(R.id.buttonFragmentTwo);
        replaceWithFragmentThree = findViewById(R.id.buttonFragmentThree);

        replaceWithFragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager secondFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = secondFragmentManager.beginTransaction();
                transaction.replace(R.id.layoutFrame,firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        replaceWithFragmentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager secondFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = secondFragmentManager.beginTransaction();
                transaction.replace(R.id.layoutFrame,secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        replaceWithFragmentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager thirdFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = thirdFragmentManager.beginTransaction();
                transaction.replace(R.id.layoutFrame,fragmentThree);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}