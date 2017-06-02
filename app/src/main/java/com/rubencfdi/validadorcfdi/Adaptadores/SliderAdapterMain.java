package com.rubencfdi.validadorcfdi.Adaptadores;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rubencfdi.validadorcfdi.Fragments.FragmentCamara;
import com.rubencfdi.validadorcfdi.Fragments.FragmentPrincipal;

/**
 * Created by Rubén on 01/06/2017
 */

public class SliderAdapterMain extends FragmentStatePagerAdapter{

    private Activity activity;
    public SliderAdapterMain(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1 : {
                return new FragmentPrincipal().setActivity(activity);
            }
            case 0 : {
                return new FragmentCamara();
            }
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
