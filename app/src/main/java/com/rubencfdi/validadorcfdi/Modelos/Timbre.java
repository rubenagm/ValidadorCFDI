package com.rubencfdi.validadorcfdi.Modelos;
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

    public static final int VALIDO = 1;
    public static final int INVALIDO = 2;
    public static final int CANCELADO = 3;

    public Timbre()
    {}

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

        String montoString = monto;

        try {
            montoString = String.format("%1$,.2f", Double.parseDouble(monto));
        }
        catch (NumberFormatException ignored)
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
