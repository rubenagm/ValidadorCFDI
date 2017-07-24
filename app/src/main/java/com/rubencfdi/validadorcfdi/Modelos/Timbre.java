package com.rubencfdi.validadorcfdi.Modelos;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ruben on 27/04/2017
 */

public class Timbre {

    /*
    1.- Válido
    2.- Inválido
    3.- No se pudo verificar
     */
    private int id;
    private String uuid;
    private String rfcReceptor;
    private String rfcEmisor;
    private String monto;
    private int estatus;
    private String mensaje;
    private String fechaVerificacion;
    private String estado;

    public static final String VIGENTE = "Vigente";

    public static final int VALIDO = 1;
    public static final int INVALIDO = 2;
    public static final int NO_DISPONIBLE = 3;

    public static final String LLAVE_JSON_EMISOR = "rfcEmisor";
    public static final String LLAVE_JSON_RECEPTOR = "rfcReceptor";
    public static final String LLAVE_JSON_TOTAL = "total";
    public static final String LLAVE_JSON_ID = "id";
    public static final String LLAVE_JSON_ESTATUS = "estatus";
    public static final String LLAVE_JSON_ESTADO = "estado";
    public static final String LLAVE_JSON_FECHA_VERIFICACION = "fechaVerificacion";

    public Timbre()
    {}

    public Timbre(String json)
    {
        try {
            JSONObject jsonObject = new JSONObject(json);

            rfcEmisor = jsonObject.getString(LLAVE_JSON_EMISOR);
            rfcReceptor = jsonObject.getString(LLAVE_JSON_RECEPTOR);
            monto = jsonObject.getString(LLAVE_JSON_TOTAL);
            mensaje = jsonObject.getString(LLAVE_JSON_ESTATUS);
            uuid = jsonObject.getString(LLAVE_JSON_ID);
            estado = jsonObject.getString(LLAVE_JSON_ESTADO);
            rfcEmisor = jsonObject.getString(LLAVE_JSON_EMISOR);
            fechaVerificacion = jsonObject.getString(LLAVE_JSON_FECHA_VERIFICACION);

            if (estado.equals(VIGENTE))
                estatus = VALIDO;
            else
                estatus = INVALIDO;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Timbre(int id, String uuid, String rfcReceptor, String rfcEmisor, String monto, int estatus, String mensaje, String fechaVerificacion, String estado)
    {
        this.id = id;
        this.uuid = uuid;
        this.rfcReceptor = rfcReceptor;
        this.rfcEmisor = rfcEmisor;
        this.monto = monto;
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.fechaVerificacion = fechaVerificacion;
        this.estado = estado;
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
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_valido)).setText("Vigente");
            ((ImageView) linearLayout.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_valido);
            linearLayout.findViewById(R.id.item_timbre_text_linea_valido).setBackgroundResource(R.color.colorValido);
        }
        else {
            ((TextView) linearLayout.findViewById(R.id.item_timbre_text_valido)).setText("Inválido");
            ((ImageView) linearLayout.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_invalido);
            linearLayout.findViewById(R.id.item_timbre_text_linea_valido).setBackgroundResource(R.color.colorInvalido);
        }

        return linearLayout;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
