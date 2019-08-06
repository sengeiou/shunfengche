package com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhaopinPositionDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BmYsYesWorkListDetailFragment;
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

public class QiuZhiWorkListDetailFragment extends BaseInitFragment {

    public static final String TAG = "QiuZhiWorkListDetailFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_my_publish_fabusj)
    TextView tvMyPublishFabusj;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_my_publish_company)
    TextView tvMyPublishCompany;
    @Bind(R.id.tv_zhaopin_gangwei)
    TextView tvZhaopinGangwei;
    @Bind(R.id.tv_zhaopin_renshu)
    TextView tvZhaopinRenshu;
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
    @Bind(R.id.tv_no)
    TextView tvNo;


    public static QiuZhiWorkListDetailFragment newInstance(int job_id) {
        Bundle args = new Bundle();
        args.putInt("job_id", job_id);
        QiuZhiWorkListDetailFragment fragment = new QiuZhiWorkListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qiuzhi_zhaopin_work_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("求职招聘-招聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_QIUZHI_ZHAOPIN_INFO)
                .addParams("job_id", getArguments().getInt("job_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZhaopinPositionDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZhaopinPositionDetailsModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            final ZhaopinPositionDetailsModel record = respnse.getData();
                            if (record != null) {
                                setData(record);
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZhaopinPositionDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );

    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(ZhaopinPositionDetailsModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvMyPublishFabusj.setText(DateUtil.StampTimeToDate(data.getUpdate_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvBianti.setText(StringUtil.notEmptyBackValue(data.getTitle()));
        if (TextUtils.isEmpty(data.getCompany_name())) {
            tvMyPublishCompany.setText("个人");
        } else {
            tvMyPublishCompany.setText(data.getCompany_name());
        }
        tvZhaopinGangwei.setText(StringUtil.notEmptyBackValue(data.getJob_name()));
        tvZhaopinRenshu.setText(StringUtil.notEmptyBackValue(data.getRecruitment_num() + "人"));
        String sex = "";
        if (data.getSex() == 0) {
            sex = "不限";
        } else if (data.getSex() == 1) {
            sex = "男";
        } else if (data.getSex() == 2) {
            sex = "女";
        }
        tvZhaopinSex.setText(sex);
        tvZhaopinAge.setText(StringUtil.notEmptyBackValue(data.getAge_range_name()));
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
                tvZhaopinXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元");
            }
        } else if (salary_type == 3) {
            tvZhaopinXinzi.setText("面议");
        }
        tvZhaopinFuli.setText(StringUtil.notEmptyBackValue(data.getBenefit_name()));
        tvZhaopinDesc.setText(StringUtil.notEmptyBackValue(data.getDescription()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity)getActivity()).removeFragment();
            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("job_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    /**
     * 满意工作，请求成功后，发布我要求职
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_QIUZHI_QZ_PIPEI_GONGZUO)
                .addParams("job_id", getArguments().getInt("job_id") + "")
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
                            NewPayActivity.go(QiuZhiWorkListDetailFragment.this, CommonPayPresenter.TYPE_QIUZHI, data.getRelate_id(), data.getOrder_price() + "",
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
