package com.windmillsteward.jukutech.activity.newpage.yizhan.yuesao;


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
 * 月嫂-个人信息
 */
public class YuesaoAddUserInfoFragment extends BaseInitFragment {
    public static final String TAG = "YuesaoAddUserInfoFragment";
    @Bind(R.id.tv_name_tips)
    TextView tvNameTips;
    @Bind(R.id.ed_name)
    EditText edName;
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
    @Bind(R.id.tv_neirong_tips)
    TextView tvNeirongTips;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_jingyan_tips)
    TextView tvJingyanTips;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_leixing_tips)
    TextView tvLeixingTips;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_shiijan_tips)
    TextView tvShiijanTips;
    @Bind(R.id.tv_shiijan)
    TextView tvShiijan;
    @Bind(R.id.tv_xinzi_tips)
    TextView tvXinziTips;
    @Bind(R.id.ed_xinzi)
    EditText edXinzi;
    @Bind(R.id.tv_diqu_tips)
    TextView tvDiquTips;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_yuesao_add_user_info;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("个人信息");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.tv_sex, R.id.tv_age, R.id.tv_xueli, R.id.tv_neirong, R.id.tv_jingyan, R.id.tv_leixing, R.id.tv_shiijan, R.id.ed_xinzi, R.id.tv_diqu, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sex:
                break;
            case R.id.tv_age:
                break;
            case R.id.tv_xueli:
                break;
            case R.id.tv_neirong:
                break;
            case R.id.tv_jingyan:
                break;
            case R.id.tv_leixing:
                break;
            case R.id.tv_shiijan:
                break;
            case R.id.ed_xinzi:
                break;
            case R.id.tv_diqu:
                break;
            case R.id.tv_submit:
                ((BackFragmentActivity) getActivity()).addFragment(CommonYiZhanResultFragment.newInstance(CommonYizhanHomeActivity.TYPE_YUESAO), true, true);
                break;
        }
    }
}
