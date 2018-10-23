package com.rubencfdi.validadorcfdi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rubencfdi.validadorcfdi.Librerias.OperacionesQR;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.rubencfdi.validadorcfdi", appContext.getPackageName());

        int t1 = OperacionesQR.validarFacturaQR("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id=2c521834-768e-4051-8628-37fe666c12e8&re=CNM980114PI2&rr=GUMR920202RT1&tt=239.00&fe=HmLx8A==");
        int t2 = OperacionesQR.validarFacturaQR("?re=TRL070530CD7&rr=RAGR930514L31&tt=1160.000000&id=77E0DA5F-7E57-4851-9335-0E2642D45DEB");
        int t3 = OperacionesQR.validarFacturaQR("");
        int t4 = OperacionesQR.validarFacturaQR("");
        int t5 = OperacionesQR.validarFacturaQR("");
        int t6 = OperacionesQR.validarFacturaQR("");
        int t7 = OperacionesQR.validarFacturaQR("");
    }
}
