package com.rubencfdi.validadorcfdi.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.util.ArrayList;

import static com.rubencfdi.validadorcfdi.R.id.linearLayoutListadoTimbres;

/**
 * Created by Rubén on 01/06/2017
 */

public class FragmentPrincipal extends Fragment {

    private View view;
    private Activity activity;
    private LinearLayout linearLayoutListadoTimbres;
    private TextView textViewConsultados;
    private TextView textViewValidos;
    private TextView textViewInvalidos;
    private ArrayList<Timbre> timbres;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_principal, null);
        inicializarObjetos();
        inicializarEventos();
        cargarTimbresGuardados();
        return view;
    }

    private void cargarTimbresGuardados() {
        BaseDatos baseDatos = new BaseDatos(activity);
        timbres = baseDatos.consultarTimbres();
        textViewConsultados.setText(Integer.toString(timbres.size()));

        //Se limpian los datos de pantalla
        linearLayoutListadoTimbres.removeAllViews();
        textViewValidos.setText("0");
        textViewInvalidos.setText("0");

        for (Timbre timbre : timbres) {

            if (timbre.getEstatus() == 1)
                textViewValidos.setText((Integer.parseInt(textViewValidos.getText().toString()) + 1) + "");
            else
                textViewInvalidos.setText((Integer.parseInt(textViewInvalidos.getText().toString()) + 1) + "");

            LinearLayout linearLayoutItem = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.item_timbre_validado, null);
            linearLayoutListadoTimbres.addView(Timbre.generarTimbre(linearLayoutItem, timbre));
        }
    }

    private void inicializarEventos() {

    }

    private void inicializarObjetos() {
        linearLayoutListadoTimbres = (LinearLayout) view.findViewById(R.id.linearLayoutListadoTimbres);
        textViewConsultados = (TextView) view.findViewById(R.id.fragment_principal_text_total_consultados);
        textViewValidos = (TextView) view.findViewById(R.id.fragment_principal_text_validos);
        textViewInvalidos = (TextView) view.findViewById(R.id.fragment_principal_text_invalidos);
    }

    public FragmentPrincipal setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }
}
