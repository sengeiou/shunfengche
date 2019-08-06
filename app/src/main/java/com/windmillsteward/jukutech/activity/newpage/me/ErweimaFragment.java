package com.windmillsteward.jukutech.activity.newpage.me;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.utils.GlideUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErweimaFragment extends BaseInitFragment {
    public static final String TAG = "ErweimaFragment";
    @Bind(R.id.tv_erweima)
    ImageView tvErweima;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;

    public static ErweimaFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        ErweimaFragment fragment = new ErweimaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_erweima;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.mImmersionBar.statusBarColor(R.color.color_394043).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).keyboardEnable(true).init();
        showContentView();
        setMainTitle("二维码邀请");
        setMainTitleColor(Color.WHITE);
        getTitleViewArea().setBackgroundColor(Color.parseColor("#394043"));
        hidLineDivider();
        ((TextView) getTitleViewArea().findViewById(R.id.tv_back)).setCompoundDrawablesRelativeWithIntrinsicBounds(
                getResources().getDrawable(R.mipmap.icon_new_arrow_white_back), null, null, null
        );

        //设置url
        if (getArguments() != null) {
            String url = getArguments().getString("url");
            GlideUtil.show(getActivity(), url, tvErweima);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }
}
