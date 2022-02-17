package com.example.doantn.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.doantn.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView mScannerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int permission_camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permission_camera != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        mScannerView  = findViewById(R.id.scannerView);

    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }


    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(Result rawResult) {
        Intent intent = new Intent(this, Detail.class);
        intent.putExtra("id",rawResult.getText());
        startActivity(intent);
        mScannerView.resumeCameraPreview(this);

    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    }
}
