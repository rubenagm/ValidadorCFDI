package com.rubencfdi.validadorcfdi.Modelos;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 27/04/2017
 */

public class Timbre {

    private int id;
    private String uuid;
    private String rfcReceptor;
    private String rfcEmisor;
    private String monto;
    private int estatus;
    private String mensaje;
    private String fechaVerificacion;

    public Timbre()
    {}

    public Timbre(int id, String uuid, String rfcReceptor, String rfcEmisor, String monto, int estatus, String mensaje, String fechaVerificacion)
    {
        this.id = id;
        this.uuid = uuid;
        this.rfcReceptor = rfcReceptor;
        this.rfcEmisor = rfcEmisor;
        this.monto = monto;
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.fechaVerificacion = fechaVerificacion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaVerificacion() {
        return fechaVerificacion;
    }

    public void setFechaVerificacion(String fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static LinearLayout generarTimbre(LinearLayout linearLayout, Timbre timbre)
    {
        ((TextView) linearLayout.findViewById(R.id.item_timbre_text_rfc_emisor)).setText(timbre.getRfcEmisor());
        ((TextView) linearLayout.findViewById(R.id.item_timbre_text_rfc_receptor)).setText(timbre.getRfcReceptor());
        ((TextView) linearLayout.findViewById(R.id.item_timbre_text_monto)).setText(timbre.getMonto());

        if (timbre.getEstatus() == 1) {
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_valido)).setText("Válido");
            ((ImageView) linearLayout.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_valido);
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_linea_valido)).setBackgroundResource(R.color.colorValido);
        }
        else {
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_valido)).setText("Inválido");
            ((ImageView) linearLayout.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_invalido);
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_linea_valido)).setBackgroundResource(R.color.colorInvalido);
        }



        return linearLayout;
    }
}
