package com.windmillsteward.jukutech.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.interfaces.OnTitleAreaCliclkListener;
import com.windmillsteward.jukutech.customview.loadlayout.LoadFailedRetryListener;
import com.windmillsteward.jukutech.customview.loadlayout.LoadLayout;
import com.windmillsteward.jukutech.customview.loadlayout.State;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * @date: on 2018/10/4
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public abstract class BaseInitFragment extends BaseFragment  {
    public TextView tvMainTitle;
    private TextView tvMainBack;
    private ImageView ivMainRight;
    private TextView tvMainRight;
    private TextView tvMainBackBlack;

    public View lineDivider;
    private LinearLayout llMainTitle;
    private OnTitleAreaCliclkListener onTitleAreaCliclkListener;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_activity, null);
        titleViewArea = view.findViewById(R.id.ll_main_title);
        //自己消费点击事件 防止事件穿透
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        initHeadView(view);
        initContentView(view, savedInstanceState);
        initData();
        if (isNeedEventBus()) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    /**
     * 是否需要EventBus
     *
     * @return
     */
    public boolean isNeedEventBus() {
        return false;
    }

    /**
     * 跳转manager
     *
     * @param managerActvityClass
     * @param tag
     */
    public void startManagerActivity(Class managerActvityClass, String tag) {
        Intent intent = new Intent(getActivity(), managerActvityClass);
        intent.putExtra(BackFragmentActivity.TAG_FRAGMENT, tag);
        startActivity(intent);
    }

    /**
     * 跳转manager
     *
     * @param managerActvityClass
     * @param tag
     * @param bundle
     */
    public void startManagerActivity(Class managerActvityClass, String tag, Bundle bundle) {
        Intent intent = new Intent(getActivity(), managerActvityClass);
        intent.putExtra(BackFragmentActivity.TAG_FRAGMENT, tag);
        if (bundle != null)
            intent.putExtra(BaseFragmentActivity.ARGS, bundle);
        startActivity(intent);
    }

    private View titleViewArea;

    public View getTitleViewArea() {
        return titleViewArea;
    }

    public TextView getTvMainRight() {
        return tvMainRight;
    }

    //是否需要自动刷新 如果需要 返回true即可
    public boolean autoRefresh() {
        return false;
    }

    //是否是第一次进来
    private boolean isFirstIn = true;
    private boolean isFirstLoad = true;

    @Override
    public void onResume() {
        super.onResume();
        if (autoRefresh()) {
            if (isFirstIn) {
                isFirstIn = false;
            } else {
                refreshPageData();
            }
        }
    }

    private LoadLayout mLoadLayout;

    private void initContentView(View view, Bundle savedInstanceState) {
        FrameLayout container = (FrameLayout) view.findViewById(R.id.main_container);
        mLoadLayout = (LoadLayout) view.findViewById(R.id.ll_base);

        //设置页面为“加载”状态
        mLoadLayout.setLayoutState(State.LOADING);


        llMainTitle = (LinearLayout) view.findViewById(R.id.ll_main_title);
        lineDivider = view.findViewById(R.id.line_divider);
        if (getContentViewId() != 0) {
            View v = View.inflate(getActivity(), getContentViewId(), null);
            ButterKnife.bind(this, v);
            container.addView(v, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            initView(v, savedInstanceState);
        } else {
            throw new RuntimeException("请设置布局文件");
        }

        mLoadLayout.setLoadFailedListener(new LoadFailedRetryListener() {
            @Override
            public void onLoadFailed() {
                showProgressView();
                refreshPageData();
            }
        });
    }


    //隐藏title栏的分界线
    public void hidLineDivider() {
        if (lineDivider.getVisibility() == View.VISIBLE)
            lineDivider.setVisibility(View.GONE);
    }

    public View getHeadView() {
        return llMainTitle;
    }

    //展示主视图
    @Override
    public void showContentView() {
        //隐藏掉整个加载中和错误页面
        if (mLoadLayout != null){
            mLoadLayout.setLayoutState(State.SUCCESS);
        }
    }

    //显示正在加载视图
    public void showProgressView() {
        if (mLoadLayout != null){
            mLoadLayout.setLayoutState(State.LOADING);
        }
    }

    //隐藏头部控件
    public void hidTitleView() {
        if (llMainTitle != null){
            llMainTitle.setVisibility(View.GONE);
        }
    }

    //显示头部控件
    public void showTitleView() {
        if (llMainTitle != null){
            llMainTitle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (isNeedEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    //隐藏返回按钮
    public void hidBackTv() {
        if (tvMainBack != null && tvMainBack.getVisibility() == View.VISIBLE)
            tvMainBack.setVisibility(View.GONE);
    }

    //显示加载错误
    @Override
    public void showErrorView() {
        if (mLoadLayout!=null){
            mLoadLayout.setLayoutState(State.FAILED);
        }
    }

    private boolean leftBtnClickable = true;

    //移除左边按钮点击事件
    public void removeLeftListener() {
        leftBtnClickable = false;
    }

    //初始化头部视图
    private void initHeadView(View view) {
        if (view != null) {
            tvMainTitle = (TextView) view.findViewById(R.id.tv_title_info);
            tvMainBack = (TextView) view.findViewById(R.id.tv_back);
            tvMainBackBlack = (TextView) view.findViewById(R.id.tv_back_black);
            ivMainRight = (ImageView) view.findViewById(R.id.iv_right);
            tvMainRight = (TextView) view.findViewById(R.id.tv_right);
            tvMainBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (leftBtnClickable)
                        if (getActivity() instanceof BackFragmentActivity) {
                            ((BackFragmentActivity) getActivity()).removeFragment();
                        } else {
                            getActivity().finish();
                        }
                    if (onTitleAreaCliclkListener != null)
                        onTitleAreaCliclkListener.onTitleAreaClickListener(v);
                }
            });

            tvMainBackBlack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (leftBtnClickable)
                        if (getActivity() instanceof BackFragmentActivity) {
                            ((BackFragmentActivity) getActivity()).removeFragment();
                        } else {
                            getActivity().finish();
                        }
                    if (onTitleAreaCliclkListener != null)
                        onTitleAreaCliclkListener.onTitleAreaClickListener(v);
                }
            });
            ivMainRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTitleAreaCliclkListener != null)
                        onTitleAreaCliclkListener.onTitleAreaClickListener(v);
                }
            });
            tvMainRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTitleAreaCliclkListener != null)
                        onTitleAreaCliclkListener.onTitleAreaClickListener(v);
                }
            });
        }
    }

    protected abstract int getContentViewId();

    protected abstract void initView(View v, Bundle savedInstanceState);

    protected abstract void initData();

    /**
     * 将整个页面需要在刷新时加载的数据操作都放在这个里面 当出现无网络界面
     * 点击屏幕刷新数据的时候会调用里面的方法 在数据加载完请记得调用showContent方法
     */
    protected abstract void refreshPageData();

    //获取标题栏的几个控件 供用户自己设计
    public TextView getMainTitle() {
        return tvMainTitle;
    }

    public TextView getLeftBackView() {
        return tvMainBack;
    }

    public TextView getRightTv() {
        return tvMainRight;
    }

    public ImageView getRightIv() {
        return ivMainRight;
    }

    public void setOnTitleAreaCliclkListener(OnTitleAreaCliclkListener onTitleAreaCliclkListener) {
        this.onTitleAreaCliclkListener = onTitleAreaCliclkListener;
    }

    //设置白色背景标题栏
    protected void setTitleBgWhite(String title) {
        if (!TextUtils.isEmpty(title))
            tvMainTitle.setText(title);
        llMainTitle.setBackground(getResources().getDrawable(R.color.white));
        lineDivider.setVisibility(View.GONE);
        tvMainBack.setVisibility(View.GONE);
        tvMainBackBlack.setVisibility(View.VISIBLE);
        tvMainTitle.setTextColor(getResources().getColor(R.color.color_ff222222));
    }

    //设置Title
    protected void setMainTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tvMainTitle.setText(title);
    }

    //设置TitleColor
    protected void setMainTitleColor(String titleColor) {
        if (!TextUtils.isEmpty(titleColor))
            setMainTitleColor(Color.parseColor(titleColor));
    }

    //设置TitleColor
    protected void setMainTitleColor(int titleColor) {
        tvMainTitle.setTextColor(titleColor);
    }

    //设置右边TextView颜色
    protected void setMainTitleRightColor(int tvRightColor) {
        tvMainRight.setTextColor(tvRightColor);
    }

    //设置右边TextView颜色
    protected void setMainTitleRightColor(String tvRightColor) {
        if (!TextUtils.isEmpty(tvRightColor))
            setMainTitleRightColor(Color.parseColor(tvRightColor));
    }

    //设置右边TextView大小
    protected void setMainTitleRightSize(int size) {
        tvMainRight.setTextSize(size);
    }

    //设置右边TextView内容
    protected void setMainTitleRightContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            if (tvMainRight.getVisibility() == View.GONE)
                tvMainRight.setVisibility(View.VISIBLE);
            tvMainRight.setText(content);
        }
    }

    //设置又边ImageView资源
    protected void setMainRightIvRes(int res) {
        if (ivMainRight.getVisibility() == View.GONE)
            ivMainRight.setVisibility(View.VISIBLE);
        ivMainRight.setImageResource(res);
    }
}
