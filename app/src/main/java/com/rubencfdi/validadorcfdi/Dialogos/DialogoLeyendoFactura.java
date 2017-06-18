package com.rubencfdi.validadorcfdi.Dialogos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.Conexion.Peticion;
import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 06/06/2017
 */

public class DialogoLeyendoFactura extends DialogFragment implements Peticion.ValidacionFactura {

    private String cadenaQR;
    private View view;
    private TextView textViewCancelar;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogo_leyendo_factura, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        inicializarObjetos();
        inicializarEventos();

        //Peticion.validarFactura(cadenaQR, this, activity);
        return view;
    }

    private void inicializarEventos() {
        textViewCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void inicializarObjetos() {
        textViewCancelar = (TextView) view.findViewById(R.id.dialogo_leyendo_factura_text_cancelar);
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void facturaValida() {

    }

    @Override
    public void facturaInvalida() {

    }

    @Override
    public void error() {

    }
}
