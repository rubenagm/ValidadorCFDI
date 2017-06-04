package com.rubencfdi.validadorcfdi.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rubencfdi.validadorcfdi.R;

/**
 * Created by Ruben on 03/06/2017
 */

public class FragmentAjustes extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ajustes, null);
        return view;
    }
}
