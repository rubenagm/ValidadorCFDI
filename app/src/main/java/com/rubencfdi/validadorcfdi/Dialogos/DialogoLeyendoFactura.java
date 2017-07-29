package com.rubencfdi.validadorcfdi.Dialogos;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Conexion.Peticion;
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
    private Timbre timbre;

    private TextView textViewEmisor;
    private TextView textViewReceptor;
    private TextView textViewTotal;
    private TextView textViewId;
    private TextView textViewEstado;
    private TextView textViewEstatus;
    private TextView textViewFechaVerificacion;

    private LinearLayout linearLayoutAceptar;
    private LinearLayout linearLayoutRefrescar;
    private LinearLayout linearLayoutCompartir;
    private LinearLayout linearLayoutBorrar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogo_leyendo_factura, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        inicializarObjetos();
        inicializarEventos();

        if (cadenaQR != null)
            Peticion.validarFactura(cadenaQR, this, mainActivity);
        else if (timbre != null)
            mostrarTimbre();

        return view;
    }

    public void mostrarTimbre() {

        viewLeyendo.setVisibility(View.GONE);
        viewTimbre.setVisibility(View.VISIBLE);

        if (timbre != null) {
            textViewEmisor.setText(timbre.getRfcEmisor());
            textViewReceptor.setText(timbre.getRfcReceptor());
            textViewTotal.setText(timbre.getMonto());
            textViewId.setText(timbre.getUuid());
            textViewEstado.setText(timbre.getEstado());
            textViewEstatus.setText(timbre.getMensaje());
            textViewFechaVerificacion.setText(timbre.getFechaVerificacion());
        }
    }

    private void inicializarEventos() {
        textViewCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        linearLayoutAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        linearLayoutBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("Borrar timbre")
                        .setMessage("¿Deseas borrar el registro de la aplicación?")
                        .setPositiveButton("BORRAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        BaseDatos baseDatos = new BaseDatos(mainActivity);
                                        baseDatos.borrarTimbre(timbre);
                                        mainActivity.refrescarLista();
                                        getDialog().dismiss();
                                    }
                                })
                        .setNegativeButton("CANCELAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getDialog().dismiss();
                                    }
                                })
                        .create()
                        .show();
            }
        });
    }

    private void inicializarObjetos() {
        textViewCancelar          = (TextView) view.findViewById(R.id.dialogo_leyendo_factura_text_cancelar);
        viewLeyendo               = view.findViewById(R.id.dialogo_leyendo_factura_leyendo);
        viewTimbre                = view.findViewById(R.id.dialogo_leyendo_factura_timbre);
        viewSinRespuesta          = view.findViewById(R.id.dialogo_leyendo_factura_sin_respuesta);
        textViewEmisor            = (TextView) view.findViewById(R.id.layout_vigente_text_emisor);
        textViewReceptor          = (TextView) view.findViewById(R.id.layout_vigente_text_receptor);
        textViewTotal             = (TextView) view.findViewById(R.id.layout_vigente_text_total);
        textViewId                = (TextView) view.findViewById(R.id.layout_vigente_text_id);
        textViewEstado            = (TextView) view.findViewById(R.id.layout_vigente_text_estado);
        textViewEstatus           = (TextView) view.findViewById(R.id.layout_vigente_text_estatus);
        textViewFechaVerificacion = (TextView) view.findViewById(R.id.layout_vigente_text_fecha_verificacion);
        linearLayoutAceptar       = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_aceptar);
        linearLayoutRefrescar     = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_refrescar);
        linearLayoutBorrar        = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_borrar);
        linearLayoutCompartir     = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_compartir);
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void facturaValida(String json, String qr) {
        timbre = new Timbre(json);
        timbre.setCadenaQR(qr);
        mostrarTimbre();
        BaseDatos baseDatos = new BaseDatos(mainActivity);
        baseDatos.insertarTimbre(timbre);
        mainActivity.refrescarLista();
    }

    @Override
    public void facturaInvalida(String json, String qr) {

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
