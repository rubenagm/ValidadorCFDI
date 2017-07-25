package com.rubencfdi.validadorcfdi.Dialogos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Line;
import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Conexion.Peticion;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 06/06/2017
 */

public class DialogoLeyendoFactura extends DialogFragment implements Peticion.ValidacionFactura {

    private String cadenaQR;
    private View view;
    private TextView textViewCancelar;
    private MainActivity mainActivity;
    private View viewLeyendo;
    private View viewTimbre;
    private View viewSinRespuesta;
    private TextView textViewAceptar;
    private Timbre timbre;

    private TextView textViewEmisor;
    private TextView textViewReceptor;
    private TextView textViewTotal;
    private TextView textViewId;
    private TextView textViewEstado;
    private TextView textViewEstatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogo_leyendo_factura, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        inicializarObjetos();
        inicializarEventos();

        if (cadenaQR != null)
            Peticion.validarFactura(cadenaQR, this, mainActivity);

        return view;
    }

    public void mostrarTimbre(int valido) {

        viewLeyendo.setVisibility(View.GONE);
        viewTimbre.setVisibility(View.VISIBLE);

        if (timbre != null) {
            textViewEmisor.setText(timbre.getRfcEmisor());
            textViewReceptor.setText(timbre.getRfcReceptor());
            textViewTotal.setText(timbre.getMonto());
            textViewId.setText(timbre.getUuid());
            textViewEstado.setText(timbre.getEstado());
            textViewEstatus.setText(timbre.getMensaje());
        }
    }

    private void inicializarEventos() {
        textViewCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        textViewAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void inicializarObjetos() {
        textViewCancelar = (TextView) view.findViewById(R.id.dialogo_leyendo_factura_text_cancelar);
        viewLeyendo = view.findViewById(R.id.dialogo_leyendo_factura_leyendo);
        viewTimbre = view.findViewById(R.id.dialogo_leyendo_factura_timbre);
        textViewAceptar = (TextView) view.findViewById(R.id.dialogo_leyendo_factura_text_aceptar);
        viewSinRespuesta = view.findViewById(R.id.dialogo_leyendo_factura_sin_respuesta);
        textViewEmisor = (TextView) view.findViewById(R.id.layout_vigente_text_emisor);
        textViewReceptor = (TextView) view.findViewById(R.id.layout_vigente_text_receptor);
        textViewTotal = (TextView) view.findViewById(R.id.layout_vigente_text_total);
        textViewId = (TextView) view.findViewById(R.id.layout_vigente_text_id);
        textViewEstado = (TextView) view.findViewById(R.id.layout_vigente_text_estado);
        textViewEstatus = (TextView) view.findViewById(R.id.layout_vigente_text_estatus);
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void facturaValida(String json) {
        timbre = new Timbre(json);
        mostrarTimbre(Timbre.VALIDO);
        BaseDatos baseDatos = new BaseDatos(mainActivity);
        baseDatos.insertarTimbre(timbre);
        mainActivity.refrescarLista();
    }

    @Override
    public void facturaInvalida(String json) {

    }

    @Override
    public void error() {
        viewLeyendo.setVisibility(View.GONE);
        viewSinRespuesta.setVisibility(View.VISIBLE);
    }

    public void setTimbre(Timbre timbre) {
        this.timbre = timbre;
    }
}
