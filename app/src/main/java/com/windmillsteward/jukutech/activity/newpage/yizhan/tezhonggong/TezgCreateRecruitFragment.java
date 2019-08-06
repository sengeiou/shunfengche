package com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 特种工-发布招聘
 */
public class TezgCreateRecruitFragment extends BaseInitFragment {
    public static final String TAG = "TezgCreateRecruitFragment";
    @Bind(R.id.tv_select_01)
    TextView tvSelect01;
    @Bind(R.id.et_num_01)
    EditText etNum01;
    @Bind(R.id.tv_money_01)
    TextView tvMoney01;
    @Bind(R.id.iv_tips_01)
    ImageView ivTips01;
    @Bind(R.id.tv_select_02)
    TextView tvSelect02;
    @Bind(R.id.et_num_02)
    EditText etNum02;
    @Bind(R.id.tv_money_02)
    TextView tvMoney02;
    @Bind(R.id.iv_tips_02)
    ImageView ivTips02;
    @Bind(R.id.tv_select_03)
    TextView tvSelect03;
    @Bind(R.id.et_num_03)
    EditText etNum03;
    @Bind(R.id.tv_money_03)
    TextView tvMoney03;
    @Bind(R.id.iv_tips_03)
    ImageView ivTips03;
    @Bind(R.id.tv_select_04)
    TextView tvSelect04;
    @Bind(R.id.et_num_04)
    EditText etNum04;
    @Bind(R.id.tv_money_04)
    TextView tvMoney04;
    @Bind(R.id.iv_tips_04)
    ImageView ivTips04;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.tv_sex_value)
    TextView tvSexValue;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.tv_age_value)
    EditText tvAgeValue;
    @Bind(R.id.tv_diqu_tips)
    TextView tvDiquTips;
    @Bind(R.id.tv_diqu_value)
    TextView tvDiquValue;
    @Bind(R.id.tv_address_tips)
    TextView tvAddressTips;
    @Bind(R.id.tv_address_value)
    EditText tvAddressValue;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.tv_riqi_tips)
    TextView tvRiqiTips;
    @Bind(R.id.tv_riqi_value)
    TextView tvRiqiValue;
    @Bind(R.id.tv_shijian_tips)
    TextView tvShijianTips;
    @Bind(R.id.tv_shijian_value)
    TextView tvShijianValue;
    @Bind(R.id.tv_miaoshu_tips)
    TextView tvMiaoshuTips;
    @Bind(R.id.et_miaoshu_value)
    EditText etMiaoshuValue;
    @Bind(R.id.tv_lianxiren_tips)
    TextView tvLianxirenTips;
    @Bind(R.id.tv_lianxiren_value)
    EditText tvLianxirenValue;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_phone_value)
    EditText tvPhoneValue;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tezg_create_recruit;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布招聘");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.tv_select_01, R.id.tv_money_01, R.id.tv_select_02, R.id.tv_money_02, R.id.tv_select_03, R.id.tv_money_03, R.id.tv_select_04, R.id.tv_money_04, R.id.tv_sex_value, R.id.tv_diqu_value, R.id.tv_address_value, R.id.iv_location, R.id.tv_riqi_value, R.id.tv_shijian_value, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_01:
                break;
            case R.id.tv_money_01:
                break;
            case R.id.tv_select_02:
                break;
            case R.id.tv_money_02:
                break;
            case R.id.tv_select_03:
                break;
            case R.id.tv_money_03:
                break;
            case R.id.tv_select_04:
                break;
            case R.id.tv_money_04:
                break;
            case R.id.tv_sex_value:
                break;
            case R.id.tv_diqu_value:
                break;
            case R.id.tv_address_value:
                break;
            case R.id.iv_location:
                break;
            case R.id.tv_riqi_value:
                break;
            case R.id.tv_shijian_value:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
