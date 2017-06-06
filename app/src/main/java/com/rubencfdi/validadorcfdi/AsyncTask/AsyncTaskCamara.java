package com.rubencfdi.validadorcfdi.AsyncTask;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.view.SurfaceHolder;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

/**
 * Created by Ruben on 05/06/2017
 */

public class AsyncTaskCamara extends AsyncTask<Void, Void, CameraSource> {

    private BarcodeDetector barcodeDetector;
    private Activity activity;
    private SurfaceHolder surfaceHolder;
    private CameraSource cameraSource;

    public AsyncTaskCamara(BarcodeDetector barcodeDetector, Activity activity, SurfaceHolder surfaceHolder, CameraSource cameraSource) {
        this.barcodeDetector = barcodeDetector;
        this.activity = activity;
        this.surfaceHolder = surfaceHolder;
        this.cameraSource = cameraSource;
    }

    @Override
    protected CameraSource doInBackground(Void... params) {
        CameraSource cameraSource = new CameraSource.Builder(activity, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1280, 720)
                .build();

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        try {
            cameraSource.start(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cameraSource;
    }

    @Override
    protected void onPostExecute(CameraSource cameraSource) {
        this.cameraSource = cameraSource;
        super.onPostExecute(cameraSource);
    }

    public void pararCamara() {
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }
}
