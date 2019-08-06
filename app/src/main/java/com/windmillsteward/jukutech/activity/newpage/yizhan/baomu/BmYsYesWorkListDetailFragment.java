package com.windmillsteward.jukutech.activity.newpage.yizhan.baomu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesWorkListDetailModel;
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

/**
 * 保姆月嫂育儿嫂工作列表详情
 */

public class BmYsYesWorkListDetailFragment extends BaseInitFragment {

    public static final String TAG = "BmYsYesWorkListDetailFragment";
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_fabusj)
    TextView tvFabusj;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_service_content)
    TextView tvServiceContent;
    @Bind(R.id.tv_work_type_tips)
    TextView tvWorkTypeTips;
    @Bind(R.id.tv_work_type)
    TextView tvWorkType;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_ydsj)
    TextView tvYdsj;
    @Bind(R.id.tv_work_date)
    TextView tvWorkDate;
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

    private int roleType;
    private int recruitment_id;

    public static BmYsYesWorkListDetailFragment newInstance(int recruitment_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("recruitment_id", recruitment_id);
        args.putInt("roleType", roleType);
        BmYsYesWorkListDetailFragment fragment = new BmYsYesWorkListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bm_ys_yes_work_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
            recruitment_id = getArguments().getInt("recruitment_id");
            switch (roleType) {
                case PageConfig.TYPE_BAOMU:
                    setMainTitle("保姆-招聘方详情");
                    tvWorkTypeTips.setText("保姆类型");
                    break;
                case PageConfig.TYPE_YUESAO:
                    setMainTitle("月嫂-招聘方详情");
                    tvWorkTypeTips.setText("月嫂类型");
                    break;
                case PageConfig.TYPE_YUERSAO:
                    setMainTitle("育儿嫂-招聘方详情");
                    tvWorkTypeTips.setText("育儿嫂类型");
                    break;
            }
        }
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_BM_YS_YES_WORK_LIST_DETAIL)
                .addParams("recruitment_id", getArguments().getInt("recruitment_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<BmYsYesWorkListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BmYsYesWorkListDetailModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        if (respnse.getData() != null) {
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BmYsYesWorkListDetailModel>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }

    private void setData(BmYsYesWorkListDetailModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvFabusj.setText(DateUtil.StampTimeToDate(data.getUpdate_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvBianti.setText(StringUtil.notEmptyBackValue(data.getTitle()));
        if (TextUtils.isEmpty(data.getEnterprise_name())) {
            tvCompany.setText("个人");
        } else {
            tvCompany.setText(data.getEnterprise_name());
        }
        StringBuffer sb = new StringBuffer();
        if (data.getService_content_ids() != null) {
            for (BmYsYesWorkListDetailModel.ServiceContentModel model : data.getService_content_ids()) {
                sb.append(model.getName() + ",");
            }
        }
        String result = sb.toString();
        if (!TextUtils.isEmpty(result)) {
            tvServiceContent.setText(result.substring(0, result.length() - 1));
        }
        tvWorkType.setText(StringUtil.notEmptyBackValue(data.getPerson_type_name()));
        String sex = "";
        if (data.getSex() == 0) {
            sex = "不限";
        } else if (data.getSex() == 1) {
            sex = "男";
        } else if (data.getSex() == 2) {
            sex = "女";
        }
        tvSex.setText(sex);
        tvAge.setText(StringUtil.notEmptyBackValue(data.getAge_name()));
        tvEducation.setText(StringUtil.notEmptyBackValue(data.getEducation_background_name()));
        tvJingyan.setText(StringUtil.notEmptyBackValue(data.getWork_experience_name()));
        if (StringUtil.isAllNotEmpty(data.getSecond_area_name(), data.getThird_area_name())) {
            tvDiqu.setText(data.getSecond_area_name() + data.getThird_area_name());
        }
        tvYdsj.setText(DateUtil.StampTimeToDate(data.getStart_booking_time() + "", "yyyy-MM-dd")
                + "至" + DateUtil.StampTimeToDate(data.getEnd_booking_time() + "", "yyyy-MM-dd"));
        tvWorkDate.setText(StringUtil.notEmptyBackValue(data.getWork_time_type_name()));
        int salary_type = data.getSalary_type();
        if (salary_type == 1) {
            tvXinzi.setText("市场定价");
        } else if (salary_type == 2) {
            tvXinzi.setText(data.getStart_salary() + "-" + data.getEnd_salary());
        } else {
            tvXinzi.setText("面议");
        }
        tvDesc.setText(StringUtil.notEmptyBackValue(data.getWork_info()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity) getActivity()).removeFragment();
            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("recruitment_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    /**
     * 满意工作，请求成功后，发布当保姆
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_BM_YS_YES_QZ_PIPEI_GONGZUO)
                .addParams("recruitment_id", getArguments().getInt("recruitment_id") + "")
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
                            int type = 0;
                            if (roleType == PageConfig.TYPE_BAOMU) {
                                type = CommonPayPresenter.TYPE_BAOMU_XINXI;
                            } else if (roleType == PageConfig.TYPE_YUESAO) {
                                type = CommonPayPresenter.TYPE_YUESAO_XINXI;
                            } else if (roleType == PageConfig.TYPE_YUERSAO) {
                                type = CommonPayPresenter.TYPE_YUER_XINXI;
                            }
                            PublicResultModel data = respnse.getData();
                            NewPayActivity.go(BmYsYesWorkListDetailFragment.this, type, data.getRelate_id(), data.getOrder_price() + "",
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
