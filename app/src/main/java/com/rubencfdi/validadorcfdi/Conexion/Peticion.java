package com.rubencfdi.validadorcfdi.Conexion;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruben on 17/06/2017
 */

public class Peticion {
    public static final String URL_PETICION_SERVIDOR = "http://192.168.100.9/WebServiceValidacionFactura/validacion.php";

    public interface ValidacionFactura {
        void facturaValida(String json);
        void facturaInvalida(String json);
        void error();
    }

    public static void validarFactura (final String qr, final ValidacionFactura validacionFactura, Activity activity) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_PETICION_SERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Peticion", response);

                        //Si la factura fue generada correctamente
                        if (true)
                            validacionFactura.facturaValida(response);
                        else
                            validacionFactura.facturaInvalida(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        validacionFactura.error();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("qr", qr);

                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

    }
}
