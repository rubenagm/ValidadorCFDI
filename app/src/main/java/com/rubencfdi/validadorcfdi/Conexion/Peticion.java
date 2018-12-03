package com.rubencfdi.validadorcfdi.Conexion;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;

import com.rubencfdi.validadorcfdi.Librerias.Fecha;
import com.rubencfdi.validadorcfdi.Librerias.OperacionesQR;
import com.rubencfdi.validadorcfdi.Modelos.Constantes;
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

        private RequestAsyncTask(String qr, ValidacionFactura validacionFactura) {
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

            String[] list = null;
            Timbre timbre = new Timbre();

            result = result.replace("ConsultaResponse{ConsultaResult=anyType{", "").replace("; }; }", "");

            if (OperacionesQR.validarFacturaQR(qr) == Constantes.FACTURA_CFDI_3_3) {
                list = qr.replace("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?", "").split("&");
            }

            list = qr.split("&");
            String[] SATkeys = new String[]{"id=" , "re=", "rr=","tt="};

            for (String element:
                 list) {
                if (element.contains(SATkeys[0])) {
                    timbre.setUuid(element.replace(SATkeys[0], ""));
                }
                else if(element.contains(SATkeys[1])){
                    timbre.setRfcEmisor(element.replace(SATkeys[1], ""));
                }
                else if(element.contains(SATkeys[2])) {
                    timbre.setRfcReceptor(element.replace(SATkeys[2], ""));
                }
                else if(element.contains(SATkeys[3])) {
                    timbre.setMonto(element.replace(SATkeys[3], ""));
                }
            }

            timbre.setCadenaQR(qr);
            timbre.setMensaje(result);
            timbre.setFechaVerificacion( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            if (result.toUpperCase().contains("ESTADO=VIGENTE")) {
                timbre.setEstatus(Timbre.VALIDO);
                timbre.setEstado("Vigente");
                validacionFactura.facturaValida(timbre);
            }
            else if(result.toUpperCase().contains("ESTADO=CANCELADO")) {
                timbre.setEstatus(Timbre.CANCELADO);
                timbre.setEstado("Cancelado");
                validacionFactura.facturaInvalida(timbre);
            }
            else  {
                timbre.setEstatus(Timbre.INVALIDO);
                timbre.setEstado("Inválido");
                validacionFactura.facturaInvalida(timbre);
            }
        }
    }
}
