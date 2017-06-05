package com.rubencfdi.validadorcfdi.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.rubencfdi.validadorcfdi.R;

import java.io.IOException;

/**
 * Created by Rubén on 01/06/2017
 */

public class FragmentCamara extends Fragment implements SurfaceHolder.Callback {

    private View view;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private Activity activity;
    private BarcodeDetector barcodeDetector;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_camara, null);
        inicializarObjetos();
        inicializarEventos();

        return view;
    }

    private void inicializarEventos() {


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodeSparseArray = detections.getDetectedItems();

                if (barcodeSparseArray.size() > 0) {

                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0: {
                        /*if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            //Mensaje de error
                            return;
                        }
                        try {
                            cameraSource.start(surfaceView.getHolder());
                        } catch (IOException e) {
                            e.printStackTrace();
                            //Mensaje de error
                        }*/
                    }
                    default: {
                        cameraSource.stop();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void inicializarObjetos() {
        surfaceView = (SurfaceView) view.findViewById(R.id.fragment_camara_surface_camara);
        surfaceView.getHolder().addCallback(this);

        barcodeDetector = new BarcodeDetector.Builder(activity)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        // Si contiene la función de lector código QR barcodeDetector.isOperational()
        cameraSource = new CameraSource.Builder(activity, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(320, 240)
                .build();
    }

    public FragmentCamara setActivity(Activity activity) {
        this.activity = activity;

        return this;
    }

    public FragmentCamara setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        return this;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        try {
            cameraSource.start(holder);
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
}
