package com.example.locationtracker;
import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnToggleRecording;
    private EditText etFileName;
    private LocationUpdateService.LTBinder ltBinder;
    private LocationUpdateService locationUpdateService;
    private boolean isRecording = false;

    private boolean isBound = false;
    private String fileName;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ltBinder = (LocationUpdateService.LTBinder) iBinder;
            locationUpdateService = ltBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    private final int REQUEST_PERMISSION = 123;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Requiring permission!");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        } else {
            Log.d(TAG, "You have already permission!");
        }

        btnToggleRecording = findViewById(R.id.btnToggleRecording);
        etFileName = findViewById(R.id.etFileName);
        btnToggleRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    toggleRecording();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void checkPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    private void toggleRecording() {
        if (!isRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        fileName = etFileName.toString();
        if (!fileName.isEmpty()) {
            try {
                btnToggleRecording.setText("Stop");
                etFileName.setEnabled(false); // Set the EditText to readonly mode

                Intent serviceIntent = new Intent(MainActivity.this, LocationUpdateService.class);
                bindService(serviceIntent,connection, Context.BIND_AUTO_CREATE);

                isRecording = true;
            } catch (Exception e) {
                Toast.makeText(this, "Exception " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Exception", "Exception " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Enter a valid file name", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        try {
            unbindService(connection);
            isBound = false;
            isRecording = false;
            btnToggleRecording.setText("Start Tracking");
            etFileName.setEnabled(true);
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}