package com.gotogyms.gtogapp;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class UserCheckin extends AppCompatActivity {

    Camera camera;
    SurfaceView camView;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    SurfaceHolder surfaceHolder;
    Button scanbutton;
    TextView textResult;
    final int REQUEST_CAMERA_PERMISSIOM_ID=1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case REQUEST_CAMERA_PERMISSIOM_ID:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSIOM_ID);
                        return;
                    }
                    try {
                        cameraSource.start(camView.getHolder());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_checkin);

        //  barcode scanner
        camView=(SurfaceView)findViewById(R.id.cameraView);
        textResult=(TextView)findViewById(R.id.checkintext);
        barcodeDetector=new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource=new CameraSource
                .Builder(this,barcodeDetector)
                .setRequestedPreviewSize(640,480)
                .build();
        camView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(UserCheckin.this,new String[]{android.Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSIOM_ID);
                    return;
                }
                try {
                    cameraSource.start(camView.getHolder());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>(){
            @Override
            public void release(){
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes=detections.getDetectedItems();
                if(qrcodes.size()!=0){
                    textResult.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(300);
                            textResult.setText(qrcodes.valueAt(0).displayValue);
                        }
                    });
                }

            }
        });

    }
}
