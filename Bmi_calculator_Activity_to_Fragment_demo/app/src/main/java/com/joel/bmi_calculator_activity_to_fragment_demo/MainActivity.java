package com.joel.bmi_calculator_activity_to_fragment_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputHeight, inputWeight;
    Button buttonCalculate;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHeight = findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.inputWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
                buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputHeight.getText().equals("") ||
                    inputWeight.getText().equals("")){
                    Toast.makeText(MainActivity.this, "Please give valid input!", Toast.LENGTH_SHORT).show();
                }else{
                    Double height = Double.valueOf(inputHeight.getText().toString());
                    Double weight = Double.valueOf(inputWeight.getText().toString());

                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ResultFragment resultFragment = new ResultFragment();
                    Bundle bundle = new Bundle();

                    bundle.putDouble("height",height);
                    bundle.putDouble("weight",weight);
                    resultFragment.setArguments(bundle);

                    fragmentTransaction.add(R.id.frameLayoutForResult,resultFragment);
                    fragmentTransaction.commit();
                }
            }
        });

    }
}