package com.rubencfdi.validadorcfdi.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rubencfdi.validadorcfdi.Activitys.ActivityCamara;
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
    private ImageView imageViewCamara;
    private ViewPager viewPager;

    public static final int ACTIVITY_CAMARA = 28747;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_principal, null);
        inicializarObjetos();
        inicializarEventos();
        cargarTimbresGuardados();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void cargarTimbresGuardados() {
        BaseDatos baseDatos = new BaseDatos(activity);
        timbres = baseDatos.consultarTimbres();
        textViewConsultados.setText(Integer.toString(timbres.size()));

        //Se limpian los datos de pantalla
        linearLayoutListadoTimbres.removeAllViews();
        textViewValidos.setText("0");
        textViewInvalidos.setText("0");

        if (timbres.size() > 0) {
            linearLayoutListadoTimbres.setVisibility(View.VISIBLE);

            for (Timbre timbre : timbres) {

                if (timbre.getEstatus() == Timbre.VALIDO)
                    textViewValidos.setText((Integer.parseInt(textViewValidos.getText().toString()) + 1) + "");
                else
                    textViewInvalidos.setText((Integer.parseInt(textViewInvalidos.getText().toString()) + 1) + "");

                LinearLayout linearLayoutItem = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.item_timbre_validado, null);
                agregarClickLayout(linearLayoutItem);
                linearLayoutListadoTimbres.addView(Timbre.generarTimbre(linearLayoutItem, timbre));
            }
        }
    }

    private void agregarClickLayout(LinearLayout linearLayoutItem) {
        linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void inicializarEventos() {
        imageViewCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActivityCamara.class);
                activity.overridePendingTransition(0, 0);
                activity.startActivityForResult(intent, ACTIVITY_CAMARA);
            }
        });

    }

    private void inicializarObjetos() {
        linearLayoutListadoTimbres = (LinearLayout) view.findViewById(R.id.linearLayoutListadoTimbres);
        textViewConsultados = (TextView) view.findViewById(R.id.fragment_principal_text_total_consultados);
        textViewValidos = (TextView) view.findViewById(R.id.fragment_principal_text_validos);
        textViewInvalidos = (TextView) view.findViewById(R.id.fragment_principal_text_invalidos);
        imageViewCamara = (ImageView) view.findViewById(R.id.fragment_principal_boton_camara);
    }

    public FragmentPrincipal setActivity(Activity activity) {
        this.activity = activity;

        return this;
    }

    public FragmentPrincipal setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        return this;
    }
}
