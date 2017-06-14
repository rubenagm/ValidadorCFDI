package com.rubencfdi.validadorcfdi.AsyncTask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Rubén on 14/06/2017
 */

public class AsyncTaskMensajeError extends AsyncTask<String, Void, String> {

    public TextView textView;

    public AsyncTaskMensajeError(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("El código QR no pertenece a una factura");
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[0] ;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        textView.setText(aVoid);
    }
}
