package com.rubencfdi.validadorcfdi.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubencfdi.validadorcfdi.Activitys.ActivityCamara;
import com.rubencfdi.validadorcfdi.Activitys.MainActivity;
import com.rubencfdi.validadorcfdi.Dialogos.DialogoLeyendoFactura;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 23/07/2017
 */

public class ArrayAdapterTimbres extends ArrayAdapter<Timbre> {

    private MainActivity mainActivity;
    private View view;

    public ArrayAdapterTimbres(MainActivity mainActivity, ArrayList<Timbre> timbres) {
        super(mainActivity, R.layout.item_timbre_validado, timbres);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        view = LayoutInflater.from(mainActivity).inflate(R.layout.item_timbre_validado, parent, false);
        generarItem(position);

        return view;
    }

    private void generarItem(final int position) {
        ((TextView) view.findViewById(R.id.item_timbre_text_rfc_emisor)).setText(getItem(position).getRfcEmisor());
        ((TextView) view.findViewById(R.id.item_timbre_text_rfc_receptor)).setText(getItem(position).getRfcReceptor());
        ((TextView) view.findViewById(R.id.item_timbre_text_monto)).setText("$" + getItem(position).getMontoString());

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

        ((LinearLayout) view.findViewById(R.id.item_timbre_linear_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoLeyendoFactura dialogoLeyendoFactura = new DialogoLeyendoFactura();
                dialogoLeyendoFactura.setMainActivity(mainActivity);
                dialogoLeyendoFactura.setTimbre(getItem(position));
                FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
                transaction.add(dialogoLeyendoFactura, "dialogo_leyendo_factura");
                transaction.commitAllowingStateLoss();

            }
        });
    }
}
