package com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong;


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
 */
public class TezgInfoDetailsFragment extends BaseInitFragment {
    public static final String TAG = "TezgInfoDetailsFragment";
    @Bind(R.id.tv_dingdan)
    TextView tvDingdan;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
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
    @Bind(R.id.tv_pingjia)
    TextView tvPingjia;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tezg_info_details;
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

    @OnClick({R.id.tv_address, R.id.tv_location, R.id.tv_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                break;
            case R.id.tv_location:
                break;
            case R.id.tv_pingjia:
                break;
        }
    }
}
