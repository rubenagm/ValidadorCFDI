package com.rubencfdi.validadorcfdi.Activitys;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.rubencfdi.validadorcfdi.Adaptadores.SliderAdapterMain;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Dialogos.DialogoLeyendoFactura;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SliderAdapterMain sliderAdapterMain;
    public static final int ID_PERMISSION_CAMERA = 7637;
    public static final int ID_PERMISSION_WRITE_EXTERNAL_STORAGE = 7492;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarObjetos();
    }

    private void inicializarObjetos() {
        viewPager = (ViewPager) findViewById(R.id.activity_main_slider);
        sliderAdapterMain = new SliderAdapterMain(getSupportFragmentManager(), this, viewPager, getSupportFragmentManager());
        viewPager.setAdapter(sliderAdapterMain);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() == 1)
            viewPager.setCurrentItem(0);
        else
            finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FragmentPrincipal.ACTIVITY_CAMARA && resultCode == Activity.RESULT_OK) {

            if (data.hasExtra(ActivityCamara.CODIGO_ENVIAR_QR)) {
                DialogoLeyendoFactura dialogoLeyendoFactura = new DialogoLeyendoFactura();
                dialogoLeyendoFactura.setCadenaQR(data.getStringExtra(ActivityCamara.CODIGO_ENVIAR_QR));
                dialogoLeyendoFactura.setMainActivity(this);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(dialogoLeyendoFactura, "dialogo_leyendo_factura");
                transaction.commitAllowingStateLoss();
            }
        }
    }

    public void requestPermissionCamera() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                MainActivity.ID_PERMISSION_CAMERA);
    }

    public void requestPermissionStorage() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MainActivity.ID_PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    public void refrescarLista() {
        sliderAdapterMain.refrescarLista();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ID_PERMISSION_CAMERA: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    sliderAdapterMain.inicializarCamaraActivity();

                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("PERMISO CÁMARA")
                            .setMessage("Debes conceder el permiso a la cámara para poder leer los códigos QR")
                            .create()
                            .show();
                }
                break;
            }
            case ID_PERMISSION_WRITE_EXTERNAL_STORAGE : {

                if ((grantResults.length > 0)
                        && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("PERMISO STORAGE")
                            .setMessage("Debes conceder el permiso para poder guardar la imagen y compartir el contenido de esta factura.")
                            .create()
                            .show();
                }

                break;
            }
        }
    }
}
