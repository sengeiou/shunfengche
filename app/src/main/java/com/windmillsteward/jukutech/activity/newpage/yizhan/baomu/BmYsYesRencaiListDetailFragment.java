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
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesRencaiListDetailModel;
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

public class BmYsYesRencaiListDetailFragment extends BaseInitFragment {

    public static final String TAG = "BmYsYesRencaiListDetailFragment";
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_fabusj)
    TextView tvFabusj;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_work_type_tips)
    TextView tvWorkTypeTips;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_service_content)
    TextView tvServiceContent;
    @Bind(R.id.tv_work_type)
    TextView tvWorkType;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_work_date)
    TextView tvWorkDate;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_no)
    TextView tvNo;


    private int roleType;
    private int require_id;

    public static BmYsYesRencaiListDetailFragment newInstance(int require_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("require_id", require_id);
        args.putInt("roleType", roleType);
        BmYsYesRencaiListDetailFragment fragment = new BmYsYesRencaiListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bm_ys_yes_rencai_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
            require_id = getArguments().getInt("require_id");
            switch (roleType) {
                case PageConfig.TYPE_BAOMU:
                    setMainTitle("保姆-应聘方详情");
                    tvWorkTypeTips.setText("保姆类型");
                    break;
                case PageConfig.TYPE_YUESAO:
                    setMainTitle("月嫂-应聘方详情");
                    tvWorkTypeTips.setText("月嫂类型");
                    break;
                case PageConfig.TYPE_YUERSAO:
                    setMainTitle("育儿嫂-应聘方详情");
                    tvWorkTypeTips.setText("育儿嫂类型");
                    break;
            }
        }
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_BM_YS_YES_RENCAI_LIST_DETAIL)
                .addParams("require_id", getArguments().getInt("require_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<BmYsYesRencaiListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BmYsYesRencaiListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BmYsYesRencaiListDetailModel>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(BmYsYesRencaiListDetailModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvName.setText(StringUtil.notEmptyBackValue(data.getUser_name()));
        tvSex.setText(data.getSex() == 1 ? "男" : "女");
        tvAge.setText(data.getAge() + "岁");
        tvEducation.setText(StringUtil.notEmptyBackValue(data.getEducation_background_name()));
        StringBuffer sb = new StringBuffer();
        if (data.getService_content_ids() != null) {
            for (BmYsYesRencaiListDetailModel.ServiceContentModel model : data.getService_content_ids()) {
                sb.append(model.getName() + ",");
            }
        }
        String result = sb.toString();
        if (!TextUtils.isEmpty(result)) {
            tvServiceContent.setText(result.substring(0, result.length() - 1));
        }
        tvWorkType.setText(StringUtil.notEmptyBackValue(data.getPerson_type_name()));
        tvJingyan.setText(StringUtil.notEmptyBackValue(data.getWork_experience_name()));
        if (StringUtil.isAllNotEmpty(data.getSecond_area_name(), data.getThird_area_name())) {
            tvDiqu.setText(data.getSecond_area_name() + data.getThird_area_name());
        }
        tvWorkDate.setText(StringUtil.notEmptyBackValue(data.getWork_time_type_name()));
        int salary_type = data.getSalary_type();
        if (salary_type == 1) {
            tvXinzi.setText("市场定价");
        } else if (salary_type == 2) {

            tvXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee());

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
                if (getArguments().getInt("require_id") != 0) {
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
                .setUrl(APIS.URL_BM_YS_YES_QZ_PIPEI_RENCAI)
                .addParams("require_id", getArguments().getInt("require_id") + "")
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
                            if (roleType == PageConfig.TYPE_BAOMU){
                                type = CommonPayPresenter.TYPE_BAOMU_ZHAOPIN;
                            }else if (roleType == PageConfig.TYPE_YUESAO){
                                type = CommonPayPresenter.TYPE_YUESAO_ZHAOPIN;
                            }else if ( roleType == PageConfig.TYPE_YUERSAO){
                                type = CommonPayPresenter.TYPE_YUER_ZHAOPIN;
                            }
                            PublicResultModel data = respnse.getData();
                            NewPayActivity.go(BmYsYesRencaiListDetailFragment.this, type, data.getRelate_id(), data.getOrder_price() + "",
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
