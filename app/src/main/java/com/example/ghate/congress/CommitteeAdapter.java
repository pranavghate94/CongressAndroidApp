package com.example.ghate.congress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ghate on 11/19/2016.
 */

public class CommitteeAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"House","Senate","Joint"};

    public CommitteeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CommitteesHouse();
            case 1:
                return new CommitteesSenate();

            case 2:
                return new CommitteesJoint();

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
