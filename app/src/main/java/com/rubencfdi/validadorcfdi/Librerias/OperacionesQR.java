package com.rubencfdi.validadorcfdi.Librerias;

/**
 * Created by Rubén on 14/06/2017
 */

public class OperacionesQR {

    public static boolean validarFacturaQR(String qr) {

        if (qr.matches(".*re=[A-Z a-z 0-9]+&rr=.+&tt=[0-9 .]+&id=.+"))
            return true;

        return false;
    }
}
