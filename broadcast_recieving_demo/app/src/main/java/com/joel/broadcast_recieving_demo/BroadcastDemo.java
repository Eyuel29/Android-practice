package com.joel.broadcast_recieving_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastDemo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean flightMode = intent.getBooleanExtra("state",false);

        if (flightMode){
            Toast.makeText(context,"Airplane mode is enabled!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Airplane mode is disabled!",Toast.LENGTH_LONG).show();
        }

    }
}