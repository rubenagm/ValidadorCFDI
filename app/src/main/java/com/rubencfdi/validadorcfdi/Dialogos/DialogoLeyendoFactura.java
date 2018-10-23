package com.rubencfdi.validadorcfdi.Dialogos;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Conexion.Peticion;
import com.rubencfdi.validadorcfdi.Librerias.Compartir;
import com.rubencfdi.validadorcfdi.Librerias.Conexion;
import com.rubencfdi.validadorcfdi.Librerias.Fecha;
import com.rubencfdi.validadorcfdi.Librerias.OperacionesQR;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
    private View viewSinConexion;
    private Timbre timbre;

    private TextView textViewEmisor;
    private TextView textViewReceptor;
    private TextView textViewTotal;
    private TextView textViewId;
    private TextView textViewEstado;
    private TextView textViewEstatus;
    private TextView textViewFechaVerificacion;
    private TextView textViewSinConexionAceptar;
    private TextView textViewSinRespuestaCancelar;
    private TextView textViewSinRespuestaVolverIntentar;
    private TextView textViewTextoValido;
    private ImageView imageViewIconoValido;

    private LinearLayout linearLayoutAceptar;
    private LinearLayout linearLayoutRefrescar;
    private LinearLayout linearLayoutCompartir;
    private LinearLayout linearLayoutBorrar;

    private RelativeLayout relativeLayoutVerificarSAT;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogo_leyendo_factura, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        inicializarObjetos();
        inicializarEventos();

        if (cadenaQR != null)
            if (Conexion.hayInternet(mainActivity))
                Peticion.validarFactura(cadenaQR, this, mainActivity);
            else {
                viewLeyendo.setVisibility(View.GONE);
                viewSinConexion.setVisibility(View.VISIBLE);
            }
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
            textViewTotal.setText("$ " + timbre.getMontoString());
            textViewId.setText(timbre.getUuid());
            textViewEstado.setText(timbre.getEstado());
            textViewEstatus.setText(timbre.getMensaje());
            textViewFechaVerificacion.setText(Fecha.fechaDiaSemana(timbre.getFechaVerificacion()));

            switch (timbre.getEstatus()) {
                case Timbre.VALIDO : {
                    textViewTextoValido.setText("Vigente");
                    imageViewIconoValido.setImageResource(R.mipmap.icono_valido);
                    break;
                }
                case Timbre.INVALIDO : {
                    textViewTextoValido.setText("Inválido");
                    imageViewIconoValido.setImageResource(R.mipmap.icono_invalido);
                    break;
                }
                case Timbre.CANCELADO : {
                    textViewTextoValido.setText("Cancelado");
                    imageViewIconoValido.setImageResource(R.mipmap.ic_cancel);
                    break;
                }
            }

            if (OperacionesQR.validarFacturaQR(timbre.getCadenaQR()) == 2)
                relativeLayoutVerificarSAT.setVisibility(View.VISIBLE);

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

        linearLayoutCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    compartirView();
                } else {
                    mainActivity.requestPermissionStorage();
                }
            }
        });

        linearLayoutRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLeyendo.setVisibility(View.VISIBLE);
                viewTimbre.setVisibility(View.GONE);
                Peticion.validarFactura(timbre.getCadenaQR(), DialogoLeyendoFactura.this, mainActivity);
            }
        });

        textViewSinConexionAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewSinRespuestaCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewSinRespuestaVolverIntentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSinRespuesta.setVisibility(View.GONE);
                viewLeyendo.setVisibility(View.VISIBLE);
                Peticion.validarFactura(cadenaQR,DialogoLeyendoFactura.this, mainActivity);

            }
        });

        relativeLayoutVerificarSAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(timbre.getCadenaQR());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void compartirView() {
        (view.findViewById(R.id.layout_vigente_linear_botones_compartir)).setVisibility(View.GONE);
        (view.findViewById(R.id.dialogo_leyendo_factura_mensaje_compartir)).setVisibility(View.VISIBLE);

        Compartir.compartirView(mainActivity,
                view.findViewById(R.id.dialogo_leyendo_factura_principal));

        (view.findViewById(R.id.dialogo_leyendo_factura_mensaje_compartir)).setVisibility(View.GONE);
        (view.findViewById(R.id.layout_vigente_linear_botones_compartir)).setVisibility(View.VISIBLE);
    }

    private void inicializarObjetos() {
        textViewCancelar = (TextView) view.findViewById(R.id.dialogo_leyendo_factura_text_cancelar);
        viewLeyendo = view.findViewById(R.id.dialogo_leyendo_factura_leyendo);
        viewTimbre = view.findViewById(R.id.dialogo_leyendo_factura_timbre);
        viewSinRespuesta = view.findViewById(R.id.dialogo_leyendo_factura_sin_respuesta);
        viewSinConexion = view.findViewById(R.id.dialogo_leyendo_factura_mensaje_sin_internet);
        textViewEmisor = (TextView) view.findViewById(R.id.layout_vigente_text_emisor);
        textViewReceptor = (TextView) view.findViewById(R.id.layout_vigente_text_receptor);
        textViewTotal = (TextView) view.findViewById(R.id.layout_vigente_text_total);
        textViewId = (TextView) view.findViewById(R.id.layout_vigente_text_id);
        textViewEstado = (TextView) view.findViewById(R.id.layout_vigente_text_estado);
        textViewEstatus = (TextView) view.findViewById(R.id.layout_vigente_text_estatus);
        textViewFechaVerificacion = (TextView) view.findViewById(R.id.layout_vigente_text_fecha_verificacion);
        textViewSinConexionAceptar = (TextView) view.findViewById(R.id.dialogo_layout_sin_conexion_aceptar);
        textViewSinRespuestaCancelar = (TextView) view.findViewById(R.id.dialogo_layout_sin_respuesta_cancelar);
        textViewSinRespuestaVolverIntentar = (TextView) view.findViewById(R.id.dialogo_layout_sin_respuesta_volver_intentar);
        linearLayoutAceptar = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_aceptar);
        linearLayoutRefrescar = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_refrescar);
        linearLayoutBorrar = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_borrar);
        linearLayoutCompartir = (LinearLayout) view.findViewById(R.id.layout_vigente_layout_compartir);
        imageViewIconoValido = (ImageView) view.findViewById(R.id.layout_vigente_icono_vigente);
        textViewTextoValido = (TextView) view.findViewById(R.id.layout_vigente_texto_vigente);
        relativeLayoutVerificarSAT = (RelativeLayout) view.findViewById(R.id.layout_vigente_relative_layout_verificar_pagina);
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void facturaValida(Timbre timbre) {
        this.timbre = timbre;
        mostrarTimbre();
        BaseDatos baseDatos = new BaseDatos(mainActivity);

        if (baseDatos.existeTimbre(timbre))
            baseDatos.actualizarTimbre(timbre);
        else
            baseDatos.insertarTimbre(timbre);

        mainActivity.refrescarLista();
    }

    @Override
    public void facturaInvalida(Timbre timbre) {
        this.timbre = timbre;
        mostrarTimbre();
        BaseDatos baseDatos = new BaseDatos(mainActivity);

        if (baseDatos.existeTimbre(timbre))
            baseDatos.actualizarTimbre(timbre);
        else
            baseDatos.insertarTimbre(timbre);

        mainActivity.refrescarLista();
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
