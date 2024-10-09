package com.joel.send_data_activity_to_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView firstNameView, lastNameView, emailView, passwordView;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firstNameView = findViewById(R.id.userFirstNameView);
        lastNameView = findViewById(R.id.userLastNameView);
        emailView = findViewById(R.id.userEmailView);
        passwordView = findViewById(R.id.userPasswordView);
        goBack = findViewById(R.id.buttonGoBack);

        firstNameView.setText("Your first name : " + getIntent().getStringExtra("userFirstName"));
        lastNameView.setText("Your last name : " + getIntent().getStringExtra("userLastName"));
        emailView.setText("Your email : " + getIntent().getStringExtra("userEmail"));
        passwordView.setText("Your password : " + getIntent().getStringExtra("userPassword"));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}