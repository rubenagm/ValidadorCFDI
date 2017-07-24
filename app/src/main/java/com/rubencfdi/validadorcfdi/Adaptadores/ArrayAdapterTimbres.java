package com.rubencfdi.validadorcfdi.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 23/07/2017
 */

public class ArrayAdapterTimbres extends ArrayAdapter<Timbre> {

    private ArrayList<Timbre> timbres;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private View view;

    public ArrayAdapterTimbres(Activity activity, ArrayList<Timbre> timbres) {
        super(activity, R.layout.item_timbre_validado, timbres);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        view = LayoutInflater.from(activity).inflate(R.layout.item_timbre_validado, parent, false);
        generarItem(position);

        return view;
    }

    private void generarItem(int position) {
        ((TextView) view.findViewById(R.id.item_timbre_text_rfc_emisor)).setText(getItem(position).getRfcEmisor());
        ((TextView) view.findViewById(R.id.item_timbre_text_rfc_receptor)).setText(getItem(position).getRfcReceptor());
        ((TextView) view.findViewById(R.id.item_timbre_text_monto)).setText(getItem(position).getMonto());

        if (getItem(position).getEstatus() == 1) {
            ((TextView) view.findViewById(R.id.item_timbre_text_valido)).setText("Vigente");
            ((ImageView) view.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_valido);
            view.findViewById(R.id.item_timbre_text_linea_valido).setBackgroundResource(R.color.colorValido);
        }
        else {
            ((TextView) view.findViewById(R.id.item_timbre_text_valido)).setText("Inválido");
            ((ImageView) view.findViewById(R.id.item_timbre_icono_valido)).setImageResource(R.mipmap.icono_invalido);
            view.findViewById(R.id.item_timbre_text_linea_valido).setBackgroundResource(R.color.colorInvalido);
        }
    }
}
