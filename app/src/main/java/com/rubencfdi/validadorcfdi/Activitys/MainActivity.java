package com.rubencfdi.validadorcfdi.Activitys;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rubencfdi.validadorcfdi.Adaptadores.SliderAdapterMain;
import com.rubencfdi.validadorcfdi.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarObjetos();
    }

    private void inicializarObjetos() {
        viewPager = (ViewPager) findViewById(R.id.activity_main_slider);
        SliderAdapterMain sliderAdapterMain = new SliderAdapterMain(getSupportFragmentManager(), this, viewPager);
        viewPager.setAdapter(sliderAdapterMain);
        viewPager.setCurrentItem(0);
    }
}
