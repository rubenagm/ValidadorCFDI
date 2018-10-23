package com.rubencfdi.validadorcfdi.Librerias;

/**
 * Created by Rubén on 14/06/2017
 */

public class OperacionesQR {

    public static int validarFacturaQR(String qr) {

        if (qr.matches(".*re=[A-Z a-z 0-9]+&rr=.+&tt=[0-9 .]+&id=.+"))
            return 1;
        if(qr.contains("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?"))
            return 2;

        return 0;
    }
}
