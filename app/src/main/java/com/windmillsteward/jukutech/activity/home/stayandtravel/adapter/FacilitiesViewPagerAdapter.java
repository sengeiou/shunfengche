package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class FacilitiesViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FacilitiesViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
