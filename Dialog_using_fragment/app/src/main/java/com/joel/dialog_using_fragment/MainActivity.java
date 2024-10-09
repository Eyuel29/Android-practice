package com.joel.dialog_using_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewUsername;
    TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewUsername = findViewById(R.id.usernameView);
        textViewPassword = findViewById(R.id.passwordView);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(fragmentManager,"DialogFragment");

            }
        });

    }

    public void setData(String username, String password){
        textViewPassword.setText(password);
        textViewUsername.setText(username);
    }

}