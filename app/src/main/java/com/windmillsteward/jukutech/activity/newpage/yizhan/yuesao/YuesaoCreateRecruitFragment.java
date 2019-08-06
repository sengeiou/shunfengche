package com.windmillsteward.jukutech.activity.newpage.yizhan.yuesao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
 * 月嫂-发布需求
 */
public class YuesaoCreateRecruitFragment extends BaseInitFragment {
    public static final String TAG = "YuesaoCreateRecruitFragment";
    @Bind(R.id.tv_leixing_tips)
    TextView tvLeixingTips;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_neirong_tips)
    TextView tvNeirongTips;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_xueli_tips)
    TextView tvXueliTips;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_jingyan_tips)
    TextView tvJingyanTips;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_gzshiijan_tips)
    TextView tvGzshiijanTips;
    @Bind(R.id.tv_gzshiijan)
    TextView tvGzshiijan;
    @Bind(R.id.tv_yuding_tips)
    TextView tvYudingTips;
    @Bind(R.id.tv_yuding1)
    TextView tvYuding1;
    @Bind(R.id.tv_yuding2)
    TextView tvYuding2;
    @Bind(R.id.tv_daiyu_tips)
    TextView tvDaiyuTips;
    @Bind(R.id.ed_daiyu1)
    EditText edDaiyu1;
    @Bind(R.id.ed_daiyu2)
    EditText edDaiyu2;
    @Bind(R.id.tv_diqu_tips)
    TextView tvDiquTips;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_dizhi_tips)
    TextView tvDizhiTips;
    @Bind(R.id.ed_dizhi)
    EditText edDizhi;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.tv_user_tips)
    TextView tvUserTips;
    @Bind(R.id.ed_user)
    EditText edUser;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.tv_money_info)
    TextView tvMoneyInfo;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_yuesao_create_recruit;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布需求");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.tv_kemu, R.id.tv_sex, R.id.tv_age, R.id.tv_xueli, R.id.tv_jingyan, R.id.tv_gzshiijan, R.id.tv_yuding1, R.id.tv_yuding2, R.id.tv_diqu, R.id.iv_location, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_kemu:
                break;
            case R.id.tv_sex:
                break;
            case R.id.tv_age:
                break;
            case R.id.tv_xueli:
                break;
            case R.id.tv_jingyan:
                break;
            case R.id.tv_gzshiijan:
                break;
            case R.id.tv_yuding1:
                break;
            case R.id.tv_yuding2:
                break;
            case R.id.tv_diqu:
                break;
            case R.id.iv_location:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
