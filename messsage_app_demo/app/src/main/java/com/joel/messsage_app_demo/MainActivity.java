package com.joel.messsage_app_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements MessageSender{

    ListView messageView;
    Button createMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = findViewById(R.id.recyclerViewMessages);
        createMessageButton = findViewById(R.id.buttonCreateNewMessage);


        createMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED){
                    String[] permissions = new String[1];
                    permissions[0] = Manifest.permission.SEND_SMS;
                    ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
                }else{


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessageSender(MainActivity.this);
                    messageFragment.show(fragmentManager,"MessageFragment");

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("121212",null,"Test",null, null);
            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void sendMessage(String messageContent, String receiverAddress) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(receiverAddress,null,messageContent,null, null);
                Toast.makeText(MainActivity.this, "Message sent to " + receiverAddress, Toast.LENGTH_SHORT).show();
    }

}