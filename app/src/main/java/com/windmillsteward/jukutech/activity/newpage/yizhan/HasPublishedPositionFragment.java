package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;
import com.windmillsteward.jukutech.utils.ResUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * 公共已发布职位
 */
public class HasPublishedPositionFragment extends BaseInitFragment {
    public static final String TAG = "HasPublishedPositionFragment";
    @Bind(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private List<String> mTitleDataList;
    private TabAdapter adapter;
    private List<BaseInitFragment> fragments;

    private int roleType;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_has_published_position;
    }

    public static HasPublishedPositionFragment newInstance(int roleType) {
        Bundle args = new Bundle();
        args.putInt("roleType", roleType);
        HasPublishedPositionFragment fragment = new HasPublishedPositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("已发布职位");

        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
        }

        if (roleType == PageConfig.TYPE_ZHONGJIE) {
            setMainTitle("已发布职位");
        } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            setMainTitle("已发布职位");
        }

        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("进行中");
        mTitleDataList.add("已完成");
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setSmoothScroll(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#9399a5"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#394043"));
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setTextSize(16);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.parseColor("#3172f4"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        showContentView();
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(HasPublishedPositionChildFragment.newInstance(roleType, 1));
        fragments.add(HasPublishedPositionChildFragment.newInstance(roleType, 2));
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void refreshPageData() {

    }
}
