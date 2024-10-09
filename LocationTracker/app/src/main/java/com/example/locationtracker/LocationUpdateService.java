package com.example.locationtracker;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class LocationUpdateService extends Service implements LocationListener {

    private FileWriter fileWriter;
    private LocationManager locationManager;
    private final LTBinder ltBinder = new LTBinder();
    private String filename;
    private boolean isRecording = false;
    private String dataToWrite = "Timestamp,lat,lng\n";
    private static final long MIN_TIME_BW_UPDATES = 1000; // 1 second
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 0f; // 0 meters

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ltBinder;
    }

    public class LTBinder extends Binder{
        public LocationUpdateService getService(){
            return LocationUpdateService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.d("LocationUpdateService","onCreate");
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        requestLocationUpdates();
    }

    public void startRecording(String filename){
        this.filename = filename;
        filename = "Location_" + UUID.randomUUID().toString().substring(0, 10) + ".csv";
        if (dataToWrite != null && filename != null) {
            appendToFile(dataToWrite);
        }
    }

    public boolean isRecording(){
        return isRecording;
    }

    private void appendToFile(String data) {
        try {
            isRecording = true;
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File outputFile = new File(downloadsDir, filename);
            fileWriter = new FileWriter(outputFile, true);
            fileWriter.append(data);
            fileWriter.close();
        } catch (IOException e) {
            isRecording = false;
            e.printStackTrace();
        }
    }


    private void requestLocationUpdates() {
        Log.d("LocationUpdateService", "requestLocationUpdates");
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                this
        );
        Log.d("LocationUpdateService", "requestLocationUpdates end");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("LocationUpdateService", "GPS LocationChanged");
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Log.i("SAVED_FILE","latitude : "+lat+"longitude : "+lng);
        String currentTimeStamp = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
        ).format(new Date());

        String dataToWrite = currentTimeStamp + ", " + lat + ", " + lng + "\n";
        try {
            if (fileWriter != null) {
                fileWriter.append(dataToWrite);
            }
        } catch (IOException e) {
            isRecording = false;
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("LocationUpdateService", "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("LocationUpdateService", "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("LocationUpdateService", "onProviderDisabled");
    }

    @Override
    public void onDestroy() {
        if (fileWriter != null) {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
        locationManager.removeUpdates(this);
    }
}
