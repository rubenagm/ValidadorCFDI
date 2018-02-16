package com.rubencfdi.validadorcfdi.Modelos;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.DeflaterOutputStream;

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
    private String cadenaQR;

    public static final String VIGENTE = "Vigente";

    public static final int VALIDO = 1;
    public static final int INVALIDO = 2;
    public static final int NO_DISPONIBLE = 3;

    public static final String LLAVE_JSON_EMISOR = "rfcEmisor";
    public static final String LLAVE_JSON_RECEPTOR = "rfcReceptor";
    public static final String LLAVE_JSON_TOTAL = "total";
    public static final String LLAVE_JSON_ID = "id";
    public static final String LLAVE_JSON_CODIGO_ESTATUS = "CodigoEstatus";
    public static final String LLAVE_JSON_ESTADO = "estado";
    public static final String LLAVE_JSON_FECHA_VERIFICACION = "fechaVerificacion";
    //public static final String LLAVE_JSON_CADENA_QR = "cadenaQR";

    public Timbre()
    {}

    public Timbre(String json)
    {
        /*
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
        */
    }

    public Timbre(int id, String uuid, String rfcReceptor, String rfcEmisor, String monto, int estatus, String mensaje, String fechaVerificacion, String estado, String cadenaQR)
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
        this.cadenaQR = cadenaQR;
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

    public String getMonto()
    {
        return this.monto;
    }

    public String getMontoString() {

        String montoString = "";

        try {
            montoString = String.format("%,.2f", Double.parseDouble(monto));
        }
        catch (NumberFormatException e)
        {}

        return montoString;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCadenaQR() {
        return cadenaQR;
    }

    public void setCadenaQR(String cadenaQR) {
        this.cadenaQR = cadenaQR;
    }
}
