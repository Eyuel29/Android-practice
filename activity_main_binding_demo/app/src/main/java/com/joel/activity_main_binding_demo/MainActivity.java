package com.joel.activity_main_binding_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.joel.activity_main_binding_demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = mainBinding.getRoot();

        setContentView(rootView);

        mainBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mainBinding.inputPassword.getText().toString();
                String password = mainBinding.inputUserName.getText().toString();

                if (!userName.equals("") ||
                    !password.equals("")){
                    mainBinding.textViewUserInfo.setText("User-name " + userName + "\nPassword "+ password);
                }else{
                    Toast.makeText(MainActivity.this,"No valid input",Toast.LENGTH_LONG).show();
                }
            }
        });

        mainBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBinding.inputPassword.setText("");
                mainBinding.inputUserName.setText("");
                mainBinding.textViewUserInfo.setText("User Info");
            }
        });
    }
}