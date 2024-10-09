package com.joel.shared_perferences_data_storage_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText inputReceiver, inputMessage;
    CheckBox remember;
    String receiverName,messageBody;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.message);
        inputReceiver = findViewById(R.id.receiver);
        remember = findViewById(R.id.remember);

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    public void restoreData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE);
        String restoredMessage = sharedPreferences.getString("Message","");
        String restoredReceiver = sharedPreferences.getString("ReceiverInfo","");
        boolean isChecked = sharedPreferences.getBoolean("Remember",false);

        inputReceiver.setText(restoredReceiver);
        inputMessage.setText(restoredMessage);
        remember.setChecked(isChecked);
    }

    public void saveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE);

        receiverName = inputReceiver.getText().toString();
        messageBody = inputMessage.getText().toString();
        isChecked = remember.isChecked();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ReceiverInfo",receiverName);
        editor.putString("Message",messageBody);
        editor.putBoolean("Remember",isChecked);

        editor.apply();
    }
}