package com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.yizhan.CommonYiZhanResultFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.CommonYizhanHomeActivity;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 特种工-添加信息
 */
public class TezgAddUserInfoFragment extends BaseInitFragment {
    public static final String TAG = "TezgAddUserInfoFragment";
    @Bind(R.id.tv_name_tips)
    TextView tvNameTips;
    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.ed_sex)
    TextView edSex;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.ed_age)
    EditText edAge;
    @Bind(R.id.tv_type_tips)
    TextView tvTypeTips;
    @Bind(R.id.ed_type)
    TextView edType;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.tv_address_tips)
    TextView tvAddressTips;
    @Bind(R.id.ed_address)
    TextView edAddress;
    @Bind(R.id.tv_time_tips)
    TextView tvTimeTips;
    @Bind(R.id.ed_time)
    TextView edTime;
    @Bind(R.id.tv_pipeitime_tips)
    TextView tvPipeitimeTips;
    @Bind(R.id.ed_pipeitime)
    TextView edPipeitime;
    @Bind(R.id.tv_money_tips)
    TextView tvMoneyTips;
    @Bind(R.id.ed_money)
    EditText edMoney;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tezg_add_user_info;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("特种工信息");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.ed_sex, R.id.ed_type, R.id.ed_address, R.id.ed_time, R.id.ed_pipeitime, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_sex:
                break;
            case R.id.ed_type:
                break;
            case R.id.ed_address:
                break;
            case R.id.ed_time:
                break;
            case R.id.ed_pipeitime:
                break;
            case R.id.tv_submit:
                ((BackFragmentActivity) getActivity()).addFragment(CommonYiZhanResultFragment.newInstance(CommonYizhanHomeActivity.TYPE_TEZHONGGONG), true, true);
                break;
        }
    }
}
