package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(String[] titles,List<Fragment> fragments,FragmentManager fm) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
