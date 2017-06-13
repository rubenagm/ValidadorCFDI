package com.rubencfdi.validadorcfdi.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.rubencfdi.validadorcfdi.AsyncTask.AsyncTaskCamara;
import com.rubencfdi.validadorcfdi.Dialogos.DialogoLeyendoFactura;
import com.rubencfdi.validadorcfdi.R;

public class ActivityCamara extends AppCompatActivity implements SurfaceHolder.Callback  {

    private SurfaceView surfaceView;
    private AsyncTaskCamara asyncTaskCamara;
    private ImageView imageViewFlechaDerecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        inicializarObjetos();
        inicializarEventos();
    }

    private void inicializarEventos() {
        imageViewFlechaDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    private void inicializarObjetos() {
        surfaceView = (SurfaceView) findViewById(R.id.fragment_camara_surface_camara);
        surfaceView.getHolder().addCallback(this);
        imageViewFlechaDerecha = (ImageView) findViewById(R.id.fragment_camara_image_flecha_derecha);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        final BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(ActivityCamara.this)
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
                    //asyncTaskCamara.pararCamara();
                    Intent intent = new Intent();
                    intent.putExtra("qr", barcodeSparseArray.valueAt(0).displayValue);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        asyncTaskCamara = new AsyncTaskCamara(barcodeDetector, ActivityCamara.this, holder);
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
