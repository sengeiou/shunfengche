package com.windmillsteward.jukutech.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.windmillsteward.jukutech.base.BaseInitFragment;

import java.util.List;

/**
 * Created by cretin on 2018/3/17.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private List<BaseInitFragment> fragments;
    private List<String> tabTitle;

    public TabAdapter(FragmentManager fm, List<BaseInitFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void setTabTitle(List<String> tabTitle) {
        this.tabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
