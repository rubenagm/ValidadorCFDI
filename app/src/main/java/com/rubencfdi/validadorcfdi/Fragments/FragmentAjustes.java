package com.rubencfdi.validadorcfdi.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 03/06/2017
 */

public class FragmentAjustes extends Fragment {

    private View view;
    private ImageView imageViewVolver;
    private ViewPager viewPager;

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
                viewPager.setCurrentItem(1);
            }
        });
    }

    private void inicializarObjetos() {
        imageViewVolver = (ImageView) view.findViewById(R.id.fragment_ajustes_image_volver);
    }

    public FragmentAjustes setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        return this;
    }
}
