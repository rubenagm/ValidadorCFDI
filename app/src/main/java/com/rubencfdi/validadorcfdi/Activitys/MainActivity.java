package com.rubencfdi.validadorcfdi.Activitys;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    public LinearLayout linearLayoutListadoTimbres;
    public ImageView imageViewButtonQr;
    public TextView textViewConsultados;
    public TextView textViewValidos;
    public TextView textViewInvalidos;
    public ArrayList<Timbre> timbres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarObjetos();
        inicializarEventos();
        cargarTimbresGuardados();
    }

    private void cargarTimbresGuardados() {
        BaseDatos baseDatos = new BaseDatos(this);
        timbres = baseDatos.consultarTimbres();
        textViewConsultados.setText(timbres.size() + "");

        //Se limpian los datos de pantalla
        linearLayoutListadoTimbres.removeAllViews();
        textViewValidos.setText("0");
        textViewInvalidos.setText("0");

        for (Timbre timbre : timbres) {

            if (timbre.getEstatus() == 1)
                textViewValidos.setText((Integer.parseInt(textViewValidos.getText().toString()) + 1) + "");
            else
                textViewInvalidos.setText((Integer.parseInt(textViewInvalidos.getText().toString()) + 1) + "");

            LinearLayout linearLayoutItem = (LinearLayout) getLayoutInflater().inflate(R.layout.item_timbre_validado, null);
            linearLayoutListadoTimbres.addView(Timbre.generarTimbre(linearLayoutItem, timbre));
        }
    }

    private void inicializarObjetos() {
        linearLayoutListadoTimbres = (LinearLayout) findViewById(R.id.linearLayoutListadoTimbres);
        imageViewButtonQr = (ImageView) findViewById(R.id.main_boton_qr);
        textViewConsultados = (TextView) findViewById(R.id.main_text_total_consultados);
        textViewValidos = (TextView) findViewById(R.id.main_text_validos);
        textViewInvalidos = (TextView) findViewById(R.id.main_text_invalidos);
    }

    private void inicializarEventos()
    {
        imageViewButtonQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos = new BaseDatos(MainActivity.this);
                baseDatos.insertarTimbre(
                        new Timbre(1, "UUID", "RFCEMISOR78", "RFCRECEPTOR76", "$151.98", 1, "Timbre válido por el SAT", "02-02-1992"));

                cargarTimbresGuardados();
            }
        });
    }
}
