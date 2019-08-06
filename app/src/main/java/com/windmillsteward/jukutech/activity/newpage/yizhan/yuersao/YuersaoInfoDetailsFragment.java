package com.windmillsteward.jukutech.activity.newpage.yizhan.yuersao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 育儿嫂-查看详情
 */
public class YuersaoInfoDetailsFragment extends BaseInitFragment {
    public static final String TAG = "YuersaoInfoDetailsFragment";
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_yuersao_info_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("查看详情");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                break;
            case R.id.tv_right:
                break;
        }
    }
}
