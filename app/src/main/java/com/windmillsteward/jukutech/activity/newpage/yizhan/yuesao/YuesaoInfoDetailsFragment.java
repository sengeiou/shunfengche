package com.windmillsteward.jukutech.activity.newpage.yizhan.yuesao;


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
 * 月嫂-查看信息
 */
public class YuesaoInfoDetailsFragment extends BaseInitFragment {
    public static final String TAG = "YuesaoInfoDetailsFragment";
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
        return R.layout.fragment_yuesao_info_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("查看信息");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

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
