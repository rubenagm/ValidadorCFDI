package com.rubencfdi.validadorcfdi.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rubencfdi.validadorcfdi.Modelos.Timbre;
import com.rubencfdi.validadorcfdi.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public LinearLayout linearLayoutListadoTimbres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarObjetos();
        cargarTimbresGuardados();
    }

    private void cargarTimbresGuardados() {
        ArrayList<Timbre> timbres = new ArrayList<>();
        timbres.add(new Timbre());
        timbres.add(new Timbre());
        timbres.add(new Timbre());
        timbres.add(new Timbre());
        timbres.add(new Timbre());
        timbres.add(new Timbre());

        for (Timbre timbre : timbres) {
            LinearLayout linearLayoutItem = (LinearLayout) getLayoutInflater().inflate(R.layout.item_timbre_validado, null);
            linearLayoutListadoTimbres.addView(linearLayoutItem);
        }
    }

    private void inicializarObjetos() {
        linearLayoutListadoTimbres = (LinearLayout) findViewById(R.id.linearLayoutListadoTimbres);
    }
}
