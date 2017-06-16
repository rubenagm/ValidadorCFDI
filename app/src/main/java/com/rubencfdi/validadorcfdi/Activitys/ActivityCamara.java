package com.rubencfdi.validadorcfdi.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.rubencfdi.validadorcfdi.AsyncTask.AsyncTaskCamara;
import com.rubencfdi.validadorcfdi.AsyncTask.AsyncTaskMensajeError;
import com.rubencfdi.validadorcfdi.AsyncTask.AsyncTaskResponse;
import com.rubencfdi.validadorcfdi.Dialogos.DialogoLeyendoFactura;
import com.rubencfdi.validadorcfdi.Librerias.OperacionesQR;
import com.rubencfdi.validadorcfdi.R;

public class ActivityCamara extends AppCompatActivity implements SurfaceHolder.Callback, Detector.Processor<Barcode> {

    private SurfaceView surfaceView;
    private AsyncTaskCamara asyncTaskCamara;
    private ImageView imageViewFlechaDerecha;
    public TextView textViewMensajeError;
    private RelativeLayout relativeLayoutVertical;
    private RelativeLayout relativeLayoutHorizontal;

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
        textViewMensajeError = (TextView) findViewById(R.id.activity_camara_text_mensaje_error);
        relativeLayoutHorizontal = (RelativeLayout) findViewById(R.id.activity_camara_linear_linea_horizontal);
        relativeLayoutVertical = (RelativeLayout) findViewById(R.id.activity_camara_linear_linea_vertical);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        final BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(ActivityCamara.this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        barcodeDetector.setProcessor(ActivityCamara.this);

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

    @Override
    public void release() {

    }

    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        final SparseArray<Barcode> barcodeSparseArray = detections.getDetectedItems();

        if (barcodeSparseArray.size() > 0) {
            String codigoQr = barcodeSparseArray.valueAt(0).displayValue;

            if (OperacionesQR.validarFacturaQR(codigoQr)) {
                Intent intent = new Intent();
                intent.putExtra("qr", codigoQr);
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                textViewMensajeError.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewMensajeError.setText("No es un código QR");
                        textViewMensajeError.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textViewMensajeError.setText("");
                            }
                        }, 3000);
                    }
                });
                relativeLayoutVertical.post(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayoutVertical.setBackgroundResource(R.color.colorRojo);
                        relativeLayoutVertical.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                relativeLayoutVertical.setBackgroundResource(R.color.colorWhite);
                            }
                        }, 3000);
                    }
                });
                relativeLayoutHorizontal.post(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayoutHorizontal.setBackgroundResource(R.color.colorRojo);
                        relativeLayoutHorizontal.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                relativeLayoutHorizontal.setBackgroundResource(R.color.colorWhite);
                            }
                        }, 3000);
                    }
                });
            }
        }
    }
}
