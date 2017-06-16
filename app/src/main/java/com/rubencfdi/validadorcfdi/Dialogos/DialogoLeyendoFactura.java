package com.rubencfdi.validadorcfdi.Dialogos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 06/06/2017
 */

public class DialogoLeyendoFactura extends DialogFragment {

    private View view;
    private String cadenaQR;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogo_leyendo_factura, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }
}
