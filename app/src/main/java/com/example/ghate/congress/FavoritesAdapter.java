package com.example.ghate.congress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ghate on 11/19/2016.
 */

public class FavoritesAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"Legislators","Bills","Committees"};

    public FavoritesAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavoriteLegislators();
            case 1:
                return new FavoriteBills();

            case 2:
                return new FavoriteCommittees();

            default:
                break;
        }
        return null;
    }

    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
