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
import com.windmillsteward.jukutech.activity.newpage.model.JiaJiaoRencaiListDetailsModel;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JiaJiaoRencaiListDetailFragment extends BaseInitFragment {

    public static final String TAG = "JiaJiaoRencaiListDetailFragment";
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_fabusj)
    TextView tvFabusj;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
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

    public static JiaJiaoRencaiListDetailFragment newInstance(int when_tutor_id) {
        Bundle args = new Bundle();
        args.putInt("when_tutor_id", when_tutor_id);
        JiaJiaoRencaiListDetailFragment fragment = new JiaJiaoRencaiListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jiajiao_rencai_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("家教-应聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_JIAJIAO_RENCAI_LIST_DETAIL)
                .addParams("when_tutor_id", getArguments().getInt("when_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<JiaJiaoRencaiListDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JiaJiaoRencaiListDetailsModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            JiaJiaoRencaiListDetailsModel record = respnse.getData();
                            if (record != null) {
                                setData(record);
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JiaJiaoRencaiListDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
            initData();
    }

    private void setData(JiaJiaoRencaiListDetailsModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvName.setText(StringUtil.notEmptyBackValue(data.getUser_name()));
        tvSex.setText(data.getSex() == 1 ? "男" : "女");
        tvAge.setText(data.getAge() + "岁");
        tvEducation.setText(StringUtil.notEmptyBackValue(data.getEducation_background_name()));

        tvKemu.setText(StringUtil.notEmptyBackValue(data.getCoach_subject_name()));
        List<JiaJiaoRencaiListDetailsModel.CoachGradeListBean> coach_grade_list = data.getCoach_grade_list();
        StringBuffer gradeSb = new StringBuffer();
        for (JiaJiaoRencaiListDetailsModel.CoachGradeListBean model : coach_grade_list) {
            gradeSb.append(model.getName() + ",");
        }
        String gradeResult = gradeSb.toString();
        tvNianji.setText(StringUtil.notEmptyBackValue(gradeResult.substring(0, gradeResult.length() - 1)));
        if (StringUtil.isAllNotEmpty(data.getSecond_area_name(), data.getThird_area_name())) {
            tvDiqu.setText(data.getSecond_area_name() + data.getThird_area_name());
        }
        StringBuffer fdsjSb = new StringBuffer();
        if (!TextUtils.isEmpty(data.getCoach_time())) {
            if (data.getCoach_time().contains("1")) {
                fdsjSb.append("周末" + ",");
            }
            if (data.getCoach_time().contains("2")) {
                fdsjSb.append("工作日" + ",");
            }
            String fdsjResult = fdsjSb.toString();
            tvFdsj.setText(fdsjResult.substring(0, fdsjResult.length() - 1));
        }
        int salary_type = data.getSalary_type();
        if (salary_type == 1) {
            tvXinzi.setText("市场定价");
        } else if (salary_type == 2) {
            if (StringUtil.isAllNotEmpty(data.getSalary(), data.getEnd_salary_fee())) {
                tvXinzi.setText(data.getSalary() + "-" + data.getEnd_salary_fee());
            }
        } else {
            tvXinzi.setText("面议");
        }
        tvDesc.setText(StringUtil.notEmptyBackValue(data.getSelf_intro()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity) getActivity()).removeFragment();
            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("when_tutor_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    /**
     * 满意人才，请求成功后，发布找家教
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_JIAJIAO_QZ_PIPEI_RENCAI)
                .addParams("when_tutor_id", getArguments().getInt("when_tutor_id") + "")
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
                            NewPayActivity.go(JiaJiaoRencaiListDetailFragment.this, CommonPayPresenter.TYPE_JIAJIAO_ZHAOPIN, data.getRelate_id(), data.getOrder_price() + "",
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
