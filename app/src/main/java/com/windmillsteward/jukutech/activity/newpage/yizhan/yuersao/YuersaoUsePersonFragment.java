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
 * 育儿嫂-用工明细
 */
public class YuersaoUsePersonFragment extends BaseInitFragment {
    public static final String TAG = "YuersaoUsePersonFragment";
    @Bind(R.id.tv_dingdan)
    TextView tvDingdan;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
    @Bind(R.id.tv_ydshijian)
    TextView tvYdshijian;
    @Bind(R.id.tv_daiyu)
    TextView tvDaiyu;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.tv_feiyong)
    TextView tvFeiyong;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_pipeidu)
    TextView tvPipeidu;
    @Bind(R.id.tv_location)
    TextView tvLocation;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_yuersao_user_person;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("用工明细");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick(R.id.tv_address)
    public void onViewClicked() {
    }
}
