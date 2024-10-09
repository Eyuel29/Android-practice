package com.joel.send_data_activity_to_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText inputFname, inputLname, inputEmail, inputPassword;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFname = findViewById(R.id.inputUserFirstName);
        inputLname = findViewById(R.id.inputUserLastName);
        inputEmail = findViewById(R.id.inputUserEmail);
        inputPassword = findViewById(R.id.inputUserPassword);
        buttonSignup = findViewById(R.id.buttonSignUp);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String inpFname =  inputFname.getText().toString();
                String inpLname =  inputLname.getText().toString();
                String inpEmail =  inputEmail.getText().toString();
                String inpPassword =  inputPassword.getText().toString();

                intent.putExtra("userFirstName",inpFname);
                intent.putExtra("userLastName",inpLname);
                intent.putExtra("userEmail",inpEmail);
                intent.putExtra("userPassword",inpPassword);
                startActivity(intent);
                finish();
            }
        });

    }
}