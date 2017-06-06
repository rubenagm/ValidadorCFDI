package com.rubencfdi.validadorcfdi.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.rubencfdi.validadorcfdi.AsyncTask.AsyncTaskCamara;
import com.rubencfdi.validadorcfdi.R;

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
    private AsyncTaskCamara asyncTaskCamara;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_camara, null);

        return view;
    }

    @Override
    public void onResume() {
        inicializarObjetos();
        super.onResume();
    }

    private void inicializarObjetos() {
        surfaceView = (SurfaceView) view.findViewById(R.id.fragment_camara_surface_camara);
        surfaceView.getHolder().addCallback(this);
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

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(activity)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

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

        asyncTaskCamara = new AsyncTaskCamara(barcodeDetector, activity, holder, cameraSource);
        asyncTaskCamara.execute();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (asyncTaskCamara != null) {
            asyncTaskCamara.pararCamara();
            asyncTaskCamara = null;
        }
    }
}
