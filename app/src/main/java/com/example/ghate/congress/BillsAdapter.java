package com.example.ghate.congress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ghate on 11/19/2016.
 */

public class BillsAdapter extends FragmentStatePagerAdapter{

    private String[] titles = {"Active","New"};

    public BillsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BillsActive();
            case 1:
                return new BillsNew();
            default:
                break;
        }
        return null;
    }

    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
