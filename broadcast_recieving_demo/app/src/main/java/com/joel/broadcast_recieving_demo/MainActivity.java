package com.joel.broadcast_recieving_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    IntentFilter filter;
    BroadcastDemo bDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         filter = new IntentFilter();
         bDemo = new BroadcastDemo();
         filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(bDemo,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(bDemo);
    }
}