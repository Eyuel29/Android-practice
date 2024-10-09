package com.joel.services_demo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class JobIntentServiceDemo extends JobIntentService {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Job-intent-service-demo","Job intent service started");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i("Job-intent-service-demo","service is started in thread [" + Thread.currentThread().getName()+"]");
    }


    public static void myBackgroundService(Context context, Intent intent){
        enqueueWork(context,JobIntentServiceDemo.class,2929,intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Job-intent-service-demo","Job intent service stopped");
    }
}
