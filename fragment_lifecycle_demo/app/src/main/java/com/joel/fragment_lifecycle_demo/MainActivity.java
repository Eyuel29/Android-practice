package com.joel.fragment_lifecycle_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Activity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity","onDestroy");
    }
}