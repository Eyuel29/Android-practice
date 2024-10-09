package com.joel.appsjava.signinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button cancelButton;
    private Button signupButton;
    private EditText emailInput;
    private EditText passwordInput;
    private TextView emailView;
    private TextView passwordView;
    private TextView noticeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cancelButton = findViewById(R.id.buttonCancel);
        signupButton = findViewById(R.id.buttonSignin);
        emailInput = findViewById(R.id.textinputemail);
        passwordInput = findViewById(R.id.textinputpassword);
        emailView = findViewById(R.id.emailView);
        passwordView = findViewById(R.id.passwordView);
        noticeView = findViewById(R.id.noticeView);
    }

    public void showWarning(String warning){
            noticeView.setBackgroundColor(Color.parseColor("#FF2222"));
            noticeView.setTextColor(Color.parseColor("#EEEEFF"));
            noticeView.setText(warning);
    }

    public void cancelClicked(View view){
        emailInput.setText("");
        passwordInput.setText("");
        emailView.setText("");
        passwordView.setText("");
        noticeView.setText("");
        noticeView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        noticeView.setTextColor(Color.parseColor("#000000"));
    }

    public void signupClicked(View view){
         String userEmail = emailInput.getText().toString(),
                userPassword = passwordInput.getText().toString();

        if (!userEmail.equals("") && !userPassword.equals("")){
            emailView.setText(userEmail);
            passwordView.setText(userPassword);
            showWarning("Successfully registered");
            noticeView.setBackgroundColor(Color.parseColor("#22FF22"));
            noticeView.setTextColor(Color.parseColor("#EEEEFF"));
        }else{
            showWarning("Invalid information");
        }
    }
}