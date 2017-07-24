package com.rubencfdi.validadorcfdi.Adaptadores;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rubencfdi.validadorcfdi.Fragments.FragmentAjustes;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;

/**
 * Created by Rubén on 01/06/2017
 */

public class SliderAdapterMain extends FragmentStatePagerAdapter{

    private Activity activity;
    private ViewPager viewPager;
    private FragmentPrincipal fragmentPrincipal;
    private FragmentAjustes fragmentAjustes;

    public SliderAdapterMain(FragmentManager fm, Activity activity, ViewPager viewPager, FragmentManager fragmentManager) {
        super(fm);
        this.activity = activity;
        this.viewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : {
                fragmentPrincipal = new FragmentPrincipal()
                        .setActivity(activity)
                        .setViewPager(viewPager);

                return fragmentPrincipal;
            }
            case 1 : {
                fragmentAjustes = new FragmentAjustes()
                        .setViewPager(viewPager);

                return fragmentAjustes;
            }

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void refrescarLista () {
        fragmentPrincipal.cargarTimbresGuardados();
    }
}
