package com.gotogyms.gotogyms.gFragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.gotogyms.gotogyms.Manifest;
import com.gotogyms.gotogyms.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckinFragment extends Fragment {


    public CheckinFragment() {

        // Required empty public constructor
    }

    private static final int REQUEST_CODE=100;
    public static final int PERMISSION_REQUEST=200;
    SurfaceView cameraView;
    BarcodeDetector barcode;
    CameraSource cameraSource;
    SurfaceHolder surfaceHolder;
    TextView txtView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkin, container, false);
        txtView=(TextView)view.findViewById(R.id.txtContent);
        cameraView=(SurfaceView)view.findViewById(R.id.cameraView);
        cameraView.setZOrderMediaOverlay(true);
        surfaceHolder=cameraView.getHolder();

        final AppCompatActivity activity=(AppCompatActivity)getActivity();

        if(ContextCompat.checkSelfPermission(this.getActivity(), android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Button scanbutton=(Button)getView().findViewById(R.id.scanbutton);
        scanbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    barcode = new BarcodeDetector.Builder(getActivity())
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();

                    if (!barcode.isOperational()) {
                        Toast.makeText(getActivity().getApplicationContext(), "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show();
                        getActivity().finish();
                    }

                    cameraSource = new CameraSource.Builder(getActivity(), barcode)
                            .setFacing(CameraSource.CAMERA_FACING_BACK)
                            .setRequestedFps(24)
                            .setAutoFocusEnabled(true)
                            .setRequestedPreviewSize(1920, 1024)
                            .build();
                    cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                        @Override
                        public void surfaceCreated(SurfaceHolder holder) {
                            try {
                                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                    cameraSource.start(cameraView.getHolder());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                        }

                        @Override
                        public void surfaceDestroyed(SurfaceHolder holder) {

                        }
                    });

                    barcode.setProcessor(new Detector.Processor<Barcode>() {
                        @Override
                        public void release() {

                        }

                        @Override
                        public void receiveDetections(Detector.Detections<Barcode> detections) {
                            final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                            if (barcodes.size() > 0) {
                                Barcode barcodevalue = barcodes.valueAt(0);
                                txtView.setText(barcodevalue.displayValue);
                                getActivity().finish();
                            }
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
        /*
        TextView txtView=(TextView)view.findViewById(R.id.txtContent);
        final AppCompatActivity activity=(AppCompatActivity)getActivity();

        Button scanbutton=(Button)view.findViewById(R.id.scanbutton);
        if(ContextCompat.checkSelfPermission(this.getActivity(), android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView imageView=(ImageView)view.findViewById(R.id.imgview);


        Bitmap myBitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(), R.drawable.qrfile);
        imageView.setImageBitmap(myBitmap);
        BarcodeDetector detector=new BarcodeDetector.Builder(getActivity().getApplicationContext()).setBarcodeFormats(Barcode.DATA_MATRIX|Barcode.QR_CODE).build();
        if(!detector.isOperational())
        {
            txtView.setText("Could not set up the detector!");
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
            SparseArray<Barcode> barcodes = detector.detect(frame);
            Barcode thisCode = barcodes.valueAt(0);
            txtView.setText(thisCode.rawValue);
        }
        return view;
    }
}   */