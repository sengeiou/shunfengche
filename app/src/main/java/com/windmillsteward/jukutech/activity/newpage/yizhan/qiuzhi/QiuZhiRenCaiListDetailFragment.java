package com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhaopinRenCaiDetailModel;
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

public class QiuZhiRenCaiListDetailFragment extends BaseInitFragment {

    public static final String TAG = "QiuZhiRenCaiListDetailFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_my_publish_fabusj)
    TextView tvMyPublishFabusj;
    @Bind(R.id.tv_zhaopin_gangwei)
    TextView tvZhaopinGangwei;
    @Bind(R.id.tv_zhaopin_sex)
    TextView tvZhaopinSex;
    @Bind(R.id.tv_zhaopin_age)
    TextView tvZhaopinAge;
    @Bind(R.id.tv_zhaopin_education)
    TextView tvZhaopinEducation;
    @Bind(R.id.tv_zhaopin_jingyan)
    TextView tvZhaopinJingyan;
    @Bind(R.id.tv_zhaopin_diqu)
    TextView tvZhaopinDiqu;
    @Bind(R.id.tv_xinzi_tips)
    TextView tvXinziTips;
    @Bind(R.id.tv_zhaopin_xinzi)
    TextView tvZhaopinXinzi;
    @Bind(R.id.tv_zhaopin_fuli)
    TextView tvZhaopinFuli;
    @Bind(R.id.tv_zhaopin_desc)
    TextView tvZhaopinDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_zhaopin_name)
    TextView tvZhaopinName;
    @Bind(R.id.tv_no)
    TextView tvNo;


    public static QiuZhiRenCaiListDetailFragment newInstance(int resume_id) {
        Bundle args = new Bundle();
        args.putInt("resume_id", resume_id);
        QiuZhiRenCaiListDetailFragment fragment = new QiuZhiRenCaiListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qiuzhi_zhaopin_rencai_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("求职招聘-应聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_QIUZHI_RENCAI_LIST_DETAIL)
                .addParams("resume_id", getArguments().getInt("resume_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZhaopinRenCaiDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZhaopinRenCaiDetailModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            final ZhaopinRenCaiDetailModel record = respnse.getData();
                            if (record != null) {
                                setData(record);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZhaopinRenCaiDetailModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );

    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(ZhaopinRenCaiDetailModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvMyPublishFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvZhaopinName.setText(StringUtil.notEmptyBackValue(data.getTrue_name()));
        tvZhaopinGangwei.setText(StringUtil.notEmptyBackValue(data.getJob_name()));
        String sex = "";
        if (data.getSex() == 0) {
            sex = "不限";
        } else if (data.getSex() == 1) {
            sex = "男";
        } else if (data.getSex() == 2) {
            sex = "女";
        }
        tvZhaopinSex.setText(sex);
        tvZhaopinAge.setText(StringUtil.notEmptyBackValue(data.getAge() + ""));
        tvZhaopinEducation.setText(StringUtil.notEmptyBackValue(data.getEducation_background_name()));
        tvZhaopinJingyan.setText(StringUtil.notEmptyBackValue(data.getWork_year_name()));
        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvZhaopinDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        int salary_type = data.getSalary_type();
        if (salary_type == 1) {
            tvZhaopinXinzi.setText("市场定价");
        } else if (salary_type == 2) {
            if (StringUtil.isAllNotEmpty(data.getSalary_fee(), data.getEnd_salary_fee())) {
                tvZhaopinXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元/月");
            }
        } else if (salary_type == 3) {
            tvZhaopinXinzi.setText("面议");
        }
        tvZhaopinFuli.setText(StringUtil.notEmptyBackValue(data.getBenefit_name()));
        tvZhaopinDesc.setText(StringUtil.notEmptyBackValue(data.getSelf_intro()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity)getActivity()).removeFragment();
            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("resume_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    /**
     * 满意人才，请求成功后，发布求职招聘
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_QIUZHI_QZ_PIPEI_RENCAI)
                .addParams("resume_id", getArguments().getInt("resume_id") + "")
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
                            NewPayActivity.go(QiuZhiRenCaiListDetailFragment.this, CommonPayPresenter.TYPE_ZHAOPIN, data.getRelate_id(), data.getOrder_price() + "",
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
}
