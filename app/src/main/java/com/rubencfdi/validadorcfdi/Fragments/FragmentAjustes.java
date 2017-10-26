package com.rubencfdi.validadorcfdi.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.BaseDatos.BaseDatos;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 03/06/2017
 */

public class FragmentAjustes extends Fragment {

    private View view;
    private ImageView imageViewVolver;
    private ImageView imageViewBorrarHistorial;
    private ViewPager viewPager;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ajustes, null);
        inicializarObjetos();
        inicializarEventos();

        return view;
    }

    private void inicializarEventos() {
        imageViewVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        imageViewBorrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("Borrar timbre")
                        .setMessage("¿Deseas borrar el registro de la aplicación?")
                        .setPositiveButton("BORRAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        BaseDatos baseDatos = new BaseDatos(mainActivity);

                                        for(Timbre timbre : baseDatos.consultarTimbres()) {
                                            baseDatos.borrarTimbre(timbre);
                                        }

                                        mainActivity.refrescarLista();
                                    }
                                })
                        .setNegativeButton("CANCELAR",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .create()
                        .show();
            }
        });
    }

    private void inicializarObjetos() {
        imageViewVolver = (ImageView) view.findViewById(R.id.fragment_ajustes_image_volver);
        imageViewBorrarHistorial = (ImageView) view.findViewById(R.id.fragment_ajustes_image_borrar_historial);
    }

    public FragmentAjustes setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        return this;
    }

    public FragmentAjustes setActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        return this;
    }
}
