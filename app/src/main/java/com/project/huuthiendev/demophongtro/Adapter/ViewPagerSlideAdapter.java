package com.project.huuthiendev.demophongtro.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by HUUTHIENDEV on 10/16/2016.
 */

public class ViewPagerSlideAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;

    public ViewPagerSlideAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
