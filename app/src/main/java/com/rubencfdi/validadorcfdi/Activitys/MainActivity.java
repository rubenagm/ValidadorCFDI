package com.rubencfdi.validadorcfdi.Activitys;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

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
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() == 0 || viewPager.getCurrentItem() == 2)
            viewPager.setCurrentItem(1);
        else
            finish();
        //super.onBackPressed();
    }
}
