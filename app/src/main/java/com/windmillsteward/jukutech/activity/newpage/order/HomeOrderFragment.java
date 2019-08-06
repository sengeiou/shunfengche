package com.windmillsteward.jukutech.activity.newpage.order;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;
import com.windmillsteward.jukutech.bean.event.NotifyOrderUnReadCount;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 订单页面
 */
public class HomeOrderFragment extends BaseInitFragment {
    public static final String TAG = "HomeOrderFragment";
    //    @Bind(R.id.magic_indicator)
//    MagicIndicator magicIndicator;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_left_hongdian)
    TextView tvLeftHongdian;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_right_hongdian)
    TextView tvRightHongdian;
    @Bind(R.id.ll_indicator_container)
    LinearLayout llIndicatorContainer;
//    @Bind(R.id.appBarLayout)
//    AppBarLayout appBarLayout;

    private List<String> mTitleDataList;
    private TabAdapter adapter;
    private List<BaseInitFragment> fragments;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home_order;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        showContentView();

        setMainTitle("订单");
        hidBackTv();
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                }
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 1) {
                    viewPager.setCurrentItem(1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvLeft.setTextColor(Color.parseColor("#3172f4"));
                    tvRight.setTextColor(Color.parseColor("#333333"));
                    tvLeft.setBackgroundResource(R.mipmap.icon_rencai_home_tab_s);
                    tvRight.setBackgroundResource(R.mipmap.icon_rencai_home_tab_n);
                } else {
                    tvLeft.setTextColor(Color.parseColor("#333333"));
                    tvRight.setTextColor(Color.parseColor("#3172f4"));
                    tvRight.setBackgroundResource(R.mipmap.icon_rencai_home_tab_s);
                    tvLeft.setBackgroundResource(R.mipmap.icon_rencai_home_tab_n);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        mTitleDataList = new ArrayList<>();
//        mTitleDataList.add("进行中");
//        mTitleDataList.add("已完成");
//        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
//        commonNavigator.setAdjustMode(true);
//        commonNavigator.setSmoothScroll(true);
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//
//            @Override
//            public int getCount() {
//                return mTitleDataList == null ? 0 : mTitleDataList.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
//                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#333333"));
//                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#3172f4"));
//                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
//                colorTransitionPagerTitleView.setTextSize(16);
//                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        viewPager.setCurrentItem(index);
//                    }
//                });
//                return colorTransitionPagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                indicator.setColors(Color.parseColor("#3172f4"));
//                indicator.setLineWidth(10f);
//                return indicator;
//            }
//        });
//        magicIndicator.setNavigator(commonNavigator);
//        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(OrderListFragment.newInstance(1));
        fragments.add(OrderListFragment.newInstance(2));
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean isNeedEventBus() {
        return true;
    }

    //刷新订单未读状态红点
    @Subscribe
    public void notifyPageRefresh(NotifyOrderUnReadCount data) {
        if (data.getType() == 1) {
            if (data.getUnReadCount() > 0) {
                if (data.getUnReadCount() > 99) {
                    tvLeftHongdian.setText(99 + "");
                } else {
                    tvLeftHongdian.setText(data.getUnReadCount() + "");
                }
                tvLeftHongdian.setVisibility(View.VISIBLE);
            } else {
                tvLeftHongdian.setVisibility(View.GONE);
            }
        } else if (data.getType() == 2) {
            if (data.getUnReadCount() > 0) {
                if (data.getUnReadCount() > 99) {
                    tvRightHongdian.setText(99 + "");
                } else {
                    tvRightHongdian.setText(data.getUnReadCount() + "");
                }
                tvRightHongdian.setVisibility(View.VISIBLE);
            } else {
                tvRightHongdian.setVisibility(View.GONE);
            }
        }
    }

    public void refresh() {
        if (fragments != null) {
            for (BaseInitFragment fragment : fragments) {
                ((OrderListFragment) fragment).refresh();
            }
        }
    }

    @Override
    protected void refreshPageData() {
        for (BaseInitFragment fragment : fragments) {
            ((OrderListFragment) fragment).refresh();
        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

}
