package com.joel.intent_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button activity2Button,incrementButton;
    private TextView counterView;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = findViewById(R.id.counterView);
        activity2Button = findViewById(R.id.openActivity);
        incrementButton = findViewById(R.id.buttonIncrement);

        incrementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                count +=1;
                counterView.setText(String.valueOf(count));
            }
        });

        activity2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });


        Log.d("message","Activity1 onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("message","Activity1 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message","Activity1 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("message","Activity1 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("message","Activity1 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message","Activity1 onDestroy");
    }
}