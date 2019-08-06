package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.windmillsteward.jukutech.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class CommonRefreshLayout extends PtrClassicFrameLayout {

    public CommonRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public CommonRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

        // the following are umeng_push_notification_default_sound.mp3otification_default_sound.mp3 settings
        setResistance(1.7f);
        setRatioOfHeaderHeightToRefresh(1.2f);
        setDurationToClose(200);
        setDurationToCloseHeader(1000);
        // umeng_push_notification_default_soundsh_notification_default_sound.mp3 is false
        setPullToRefresh(false);
        // umeng_push_notification_default_soundsh_notification_default_sound.mp3 is true
        setKeepHeaderWhenRefresh(true);
        disableWhenHorizontalMove(true);

        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("REFRESH");
        header.setPadding(0, dp2px(15), 0, dp2px(15));
        header.setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_them, null));

//        setHeaderView(header);
//        addPtrUIHandler(header);
    }

    private int dp2px(int value) {
        return (int) TypedValue.applyDimension(1, (float) value, getResources().getDisplayMetrics());
    }

}
