package com.rubencfdi.validadorcfdi.Modelos;

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
}
