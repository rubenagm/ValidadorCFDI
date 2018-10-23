package com.rubencfdi.validadorcfdi.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rubencfdi.validadorcfdi.Activitys.ActivityCamara;
import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.Adaptadores.ArrayAdapterTimbres;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.util.ArrayList;

/**
 * Created by Rubén on 01/06/2017
 */

public class FragmentPrincipal extends Fragment {

    private View view;
    private MainActivity mainActivity;
    private LinearLayout linearLayoutListadoTimbres;
    private TextView textViewConsultados;
    private TextView textViewValidos;
    private TextView textViewInvalidos;
    private ArrayList<Timbre> timbres;
    private ImageView imageViewCamara;
    private ImageView imageViewInfo;
    private ListView listViewTimbres;
    private ViewPager viewPager;
    private RelativeLayout relativeLayoutSinTimbres;

    private LinearLayout linearLayoutQrLeidos;
    private LinearLayout linearLayoutQrValidos;
    private LinearLayout linearLayoutQrInvalidos;

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

    public void cargarTimbresGuardados() {
        BaseDatos baseDatos = new BaseDatos(mainActivity);
        timbres = baseDatos.consultarTimbres();
        textViewConsultados.setText(Integer.toString(timbres.size()));
        ArrayAdapterTimbres arrayAdapterTimbres = new ArrayAdapterTimbres(mainActivity, timbres);
        listViewTimbres.setDivider(null);
        listViewTimbres.setAdapter(arrayAdapterTimbres);

        //Se limpian los datos de pantalla
        textViewValidos.setText("0");
        textViewInvalidos.setText("0");

        if (timbres.size() > 0) {

            for (Timbre timbre : timbres) {

                if (timbre.getEstatus() == Timbre.VALIDO)
                    textViewValidos.setText((Integer.parseInt(textViewValidos.getText().toString()) + 1) + "");
                else
                    textViewInvalidos.setText((Integer.parseInt(textViewInvalidos.getText().toString()) + 1) + "");
            }
        } else {
            relativeLayoutSinTimbres.setVisibility(View.VISIBLE);
        }
    }

    private void inicializarEventos() {
        imageViewCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mainActivity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    inicializarCamaraActivity();
                } else {
                    mainActivity.requestPermissionCamera();
                }
            }
        });

        imageViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        linearLayoutQrLeidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivity, "QR's leídos", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutQrValidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivity, "QR's leídos (Válidos)", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutQrInvalidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivity, "QR's leídos (Inválidos o cancelados)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inicializarCamaraActivity() {
        Intent intent = new Intent(mainActivity, ActivityCamara.class);
        mainActivity.overridePendingTransition(0, 0);
        mainActivity.startActivityForResult(intent, ACTIVITY_CAMARA);
    }

    private void inicializarObjetos() {
        textViewConsultados = (TextView) view.findViewById(R.id.fragment_principal_text_total_consultados);
        textViewValidos = (TextView) view.findViewById(R.id.fragment_principal_text_validos);
        textViewInvalidos = (TextView) view.findViewById(R.id.fragment_principal_text_invalidos);
        imageViewCamara = (ImageView) view.findViewById(R.id.fragment_principal_boton_camara);
        imageViewInfo = (ImageView) view.findViewById(R.id.fragment_principal_image_boton_info);
        relativeLayoutSinTimbres = (RelativeLayout) view.findViewById(R.id.fragment_principal_relative_sin_timbres);
        listViewTimbres = (ListView) view.findViewById(R.id.fragment_principal_list_elementos);
        linearLayoutQrLeidos = (LinearLayout) view.findViewById(R.id.fragment_principal_layout_qr_leidos);
        linearLayoutQrValidos = (LinearLayout) view.findViewById(R.id.fragment_principal_layout_qr_validos);
        linearLayoutQrInvalidos = (LinearLayout) view.findViewById(R.id.fragment_principal_layout_qr_invalidos);
    }

    public FragmentPrincipal setActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        return this;
    }

    public FragmentPrincipal setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        return this;
    }
}
