package com.study.android.team_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ChargePageAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ChargePageAdapter(FragmentManager fm , int NumofTabs) {
        super(fm);
        this.mNumOfTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ChargeFragment1 ();
            case 1:
                return new ChargeFragment2 ();
            default:
                    return  null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
