package com.rubencfdi.validadorcfdi.Librerias;

import com.rubencfdi.validadorcfdi.Modelos.Constantes;

/**
 * Created by Rubén on 14/06/2017
 */

public class OperacionesQR {

    public static int validarFacturaQR(String qr) {

        if (qr.matches(".*re=[A-Z a-z 0-9]+&rr=.+&tt=[0-9 .]+&id=.+"))
            return Constantes.FACTURA_CFDI_OLDER;
        if(qr.contains("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?"))
            return Constantes.FACTURA_CFDI_3_3 ;

        return 0;
    }
}
