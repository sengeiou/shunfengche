package com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.JiaJiaoWorkListDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JiaJiaoWorkListDetailFragment extends BaseInitFragment {

    public static final String TAG = "JiaJiaoWorkListDetailFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_fabusj)
    TextView tvFabusj;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_ydsj)
    TextView tvYdsj;
    @Bind(R.id.tv_fdsj)
    TextView tvFdsj;
    @Bind(R.id.tv_xinzi_tips)
    TextView tvXinziTips;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_no)
    TextView tvNo;

    public static JiaJiaoWorkListDetailFragment newInstance(int look_for_tutor_id) {
        Bundle args = new Bundle();
        args.putInt("look_for_tutor_id", look_for_tutor_id);
        JiaJiaoWorkListDetailFragment fragment = new JiaJiaoWorkListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jiajiao_work_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("家教-招聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_JIAJIAO_WORK_LIST_DETAIL)
                .addParams("look_for_tutor_id", getArguments().getInt("look_for_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<JiaJiaoWorkListDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JiaJiaoWorkListDetailsModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            JiaJiaoWorkListDetailsModel record = respnse.getData();
                            if (record != null) {
                                tvBianhao.setText(StringUtil.notEmptyBackValue(record.getOrder_sn()));
                                tvBianti.setText(StringUtil.notEmptyBackValue(record.getTitle()));

                                tvFabusj.setText(DateUtil.StampTimeToDate(record.getAdd_time() + "",
                                        "yyyy-MM-dd HH:mm:ss"));
                                tvCompany.setText(StringUtil.notEmptyBackValue(record.getEnterprise_name()));
                                tvKemu.setText(StringUtil.notEmptyBackValue(record.getCoach_subject_name()));
                                tvNianji.setText(StringUtil.notEmptyBackValue(record.getCoach_grade_name()));
                                if (record.getSex() == 0) {
                                    tvSex.setText("不限");
                                } else {
                                    tvSex.setText(record.getSex() == 1 ? "男" : "女");
                                }
                                tvAge.setText(StringUtil.notEmptyBackValue(record.getAge_name()));
                                tvEducation.setText(StringUtil.notEmptyBackValue(record.getEducation_background_name()));
                                if (StringUtil.isAllNotEmpty(record.getSecond_area_name(), record.getThird_area_name())) {
                                    tvDiqu.setText(record.getSecond_area_name() + record.getThird_area_name());
                                }
                                tvYdsj.setText(DateUtil.StampTimeToDate(record.getStart_booking_time() + "", "yyyy-MM-dd")
                                        + "至" + DateUtil.StampTimeToDate(record.getEnd_booking_time() + "", "yyyy-MM-dd"));
                                tvDesc.setText(StringUtil.notEmptyBackValue(record.getWork_info()));
                                int salary_type = record.getSalary_type();
                                if (salary_type == 1) {
                                    tvXinzi.setText("市场定价");
                                } else if (salary_type == 2) {
                                    if (StringUtil.isAllNotEmpty(record.getStart_salary(), record.getEnd_salary())) {
                                        tvXinzi.setText(record.getStart_salary() + "-" + record.getEnd_salary());
                                    }
                                } else {
                                    tvXinzi.setText("面议");
                                }
                                String coach_time = record.getCoach_time();
                                StringBuffer sb = new StringBuffer();
                                if (coach_time.contains("1")) {
                                    sb.append("周末,");
                                }
                                if (coach_time.contains("2")) {
                                    sb.append("工作日,");
                                }
                                String result = sb.toString();
                                if (!TextUtils.isEmpty(result)) {
                                    tvFdsj.setText(result.substring(0, result.length() - 1));
                                }
                                tvNo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ((BackFragmentActivity) getActivity()).removeFragment();
                                    }
                                });
                                tvApply.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (getArguments().getInt("look_for_tutor_id") != 0) {
                                            qzPiPei();
                                        }
                                    }
                                });
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JiaJiaoWorkListDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    /**
     * 满意工作，请求成功后，发布当家教
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_JIAJIAO_QZ_PIPEI_GONGZUO)
                .addParams("look_for_tutor_id", getArguments().getInt("look_for_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);

                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            dismiss();
                            PublicResultModel data = respnse.getData();
                            NewPayActivity.go(JiaJiaoWorkListDetailFragment.this, CommonPayPresenter.TYPE_JIAJIAO_XINXI, data.getRelate_id(), data.getOrder_price() + "",
                                    data.getOrder_name(), NewPayActivity.CAN_USE_COUPON);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            getActivity().finish();
        }
    }


}
