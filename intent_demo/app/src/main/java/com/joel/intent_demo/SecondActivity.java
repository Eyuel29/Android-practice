package com.joel.intent_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button firstActivity = findViewById(R.id.returnToActivity1);

        firstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("message","Activity2 onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("message","Activity2 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message","Activity2 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("message","Activity2 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("message","Activity2 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message","Activity2 onDestroy");
    }
}