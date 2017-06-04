package com.rubencfdi.validadorcfdi.Adaptadores;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rubencfdi.validadorcfdi.Fragments.FragmentAjustes;
import com.rubencfdi.validadorcfdi.Fragments.FragmentCamara;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;

/**
 * Created by Rubén on 01/06/2017
 */

public class SliderAdapterMain extends FragmentStatePagerAdapter{

    private Activity activity;
    private ViewPager viewPager;

    public SliderAdapterMain(FragmentManager fm, Activity activity, ViewPager viewPager) {
        super(fm);
        this.activity = activity;
        this.viewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1 : {
                return new FragmentPrincipal()
                        .setActivity(activity)
                        .setViewPager(viewPager);
            }
            case 0 : {
                return new FragmentCamara();
            }
            case 2 : {
                return new FragmentAjustes();
            }
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
