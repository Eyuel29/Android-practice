package com.joel.services_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent classicServiceIntent,jobIntentServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classicServiceIntent = new Intent(this,ClassicDemo.class);
        jobIntentServiceIntent = new Intent(this,JobIntentServiceDemo.class);
    }

    public void csStart(View view){
        startService(classicServiceIntent);
    }

    public void csStop(View view){
        stopService(classicServiceIntent);
    }

    public void jisStart(View view){
        JobIntentServiceDemo.myBackgroundService(getApplicationContext(),jobIntentServiceIntent);
    }

    public void jisStop(View view){

    }

}