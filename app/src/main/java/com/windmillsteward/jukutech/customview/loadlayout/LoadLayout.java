package com.windmillsteward.jukutech.customview.loadlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;


/**
 * author：   zp
 * date：     2015/9/2 10:22
 * version    1.0
 * description 这个Layout用于加载,可以显示各种状态的布局, 如加载中，加载成功, 加载失败, 无数据
 * modify by  ljy
 */
public class LoadLayout extends BaseLoadLayout {
    private Context context;
    private int mLoadingViewId = R.layout.layout_load_loading_view;
    private int mFailedViewId = R.layout.layout_load_failed_view;
    private int mNoDataViewId = R.layout.layout_load_no_data_view;

    public LoadLayout(Context context) {
        super(context);
        this.context = context;
    }

    public LoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public LoadLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected View createLoadingView() {
        mLoadingView = LayoutInflater.from(getContext()).inflate(mLoadingViewId, null);
        if (mLoadingView.findViewById(R.id.iv_loading_kk) != null) {
            AnimationDrawable animationDrawable = (AnimationDrawable) mLoadingView.findViewById(R.id.iv_loading_kk).getBackground();
            animationDrawable.start();
        }
        mLoadingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return mLoadingView;
    }

    @Override
    protected View createLoadFailedView() {
        mFailedView = LayoutInflater.from(getContext()).inflate(mFailedViewId, null);
        mFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });
        return mFailedView;
    }

    @Override
    protected View createNoDataView() {
        mNoDataView = LayoutInflater.from(getContext()).inflate(mNoDataViewId, null);
        mNoDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutState(State.LOADING);
            }
        });
        return mNoDataView;
    }

    public void setLoadingViewId(int loadingViewId) {
        this.mLoadingViewId = loadingViewId;
    }

    public void setFailedViewId(int failedViewId) {
        this.mFailedViewId = failedViewId;
    }

    public void setNoDataViewId(int noDataViewId) {
        this.mNoDataViewId = noDataViewId;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getFailedView() {
        return mFailedView;
    }

    public View getNoDataView() {
        return mNoDataView;
    }

}
