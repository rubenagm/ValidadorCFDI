package com.rubencfdi.validadorcfdi.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;

import com.rubencfdi.validadorcfdi.Librerias.Fecha;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ruben on 17/06/2017
 */

public class Peticion {
    private final static String URL = "https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc?singleWsdl";
    private final static String METODO = "Consulta";
    private final static String ACCION = "http://tempuri.org/IConsultaCFDIService/Consulta";
    private final static String NAMESPACE = "http://tempuri.org/";


    public interface ValidacionFactura {
        void facturaValida(Timbre timbre);
        void facturaInvalida(Timbre timbre);
        void error();
    }

    public static void validarFactura (final String qr, final ValidacionFactura validacionFactura, Activity activity) {

        RequestAsyncTask requestAsyncTask = new RequestAsyncTask(qr, validacionFactura);
        requestAsyncTask.execute();
    }

    public static class RequestAsyncTask extends AsyncTask<Void, Void, String> {

        private String qr;
        private ValidacionFactura validacionFactura;

        public RequestAsyncTask(String qr, ValidacionFactura validacionFactura) {
            this.qr = qr;
            this.validacionFactura = validacionFactura;
        }

        @Override
        protected String doInBackground(Void... voids) {
            SoapObject soapObject = new SoapObject(NAMESPACE, METODO);
            soapObject.addProperty("expresionImpresa", qr);

            SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapSerializationEnvelope.dotNet = true;
            soapSerializationEnvelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            try {
                httpTransportSE.call(ACCION, soapSerializationEnvelope);

                return soapSerializationEnvelope.bodyIn.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (NetworkOnMainThreadException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            result = result.replace("ConsultaResponse{ConsultaResult=anyType{", "").replace("; }; }", "");

            String[] list = qr.split("&");

            Timbre timbre = new Timbre();
            timbre.setCadenaQR(qr);
            timbre.setRfcEmisor(list[0].replace("re=", "").replace("?", ""));
            timbre.setRfcReceptor(list[1].replace("rr=", "").replace("?", ""));
            timbre.setMonto(list[2].replace("tt=", ""));
            timbre.setMensaje(result);
            timbre.setUuid(list[3].replace("id=", ""));
            timbre.setFechaVerificacion( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            if (result.contains("Estado=Vigente")) {
                timbre.setEstatus(1);
                timbre.setEstado("Vigente");
                validacionFactura.facturaValida(timbre);
            }
            else {
                timbre.setEstatus(2);
                timbre.setEstado("Invalido");
                validacionFactura.facturaInvalida(timbre);
            }
        }
    }
}
