package com.rubencfdi.validadorcfdi.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.rubencfdi.validadorcfdi.Adaptadores.SliderAdapterMain;
import com.rubencfdi.validadorcfdi.Dialogos.DialogoLeyendoFactura;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;
import com.rubencfdi.validadorcfdi.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarObjetos();
    }

    private void inicializarObjetos() {
        viewPager = (ViewPager) findViewById(R.id.activity_main_slider);
        SliderAdapterMain sliderAdapterMain = new SliderAdapterMain(getSupportFragmentManager(), this, viewPager, getSupportFragmentManager());
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
                dialogoLeyendoFactura.setActivity(this);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(dialogoLeyendoFactura, "dialogo_leyendo_factura");
                transaction.commitAllowingStateLoss();
            }
        }
    }
}
