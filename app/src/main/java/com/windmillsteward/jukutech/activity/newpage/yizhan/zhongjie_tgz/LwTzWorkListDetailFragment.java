package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;

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
import com.windmillsteward.jukutech.activity.newpage.model.LwTzWorkListDetailModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
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

public class LwTzWorkListDetailFragment extends BaseInitFragment {

    public static final String TAG = "LwTzWorkListDetailFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_gongzhong)
    TextView tvGongzhong;
    @Bind(R.id.tv_xinzi_tips)
    TextView tvXinziTips;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_gzsj)
    TextView tvGzsj;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_fbsj)
    TextView tvFbsj;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_zprenshu)
    TextView tvZprenshu;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_no)
    TextView tvNo;

    private int roleType;
    private int recruitment_id;


    public static LwTzWorkListDetailFragment newInstance(int recruitment_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("recruitment_id", recruitment_id);
        args.putInt("roleType", roleType);
        LwTzWorkListDetailFragment fragment = new LwTzWorkListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_lw_tz_work_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
            recruitment_id = getArguments().getInt("recruitment_id");
            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                setMainTitle("劳务中介-招聘详情");
            } else {
                setMainTitle("特种工-招聘详情");
            }
        }

    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_LW_TZ_WORK_INFO_LIST_DETAIL)
                .addParams("recruitment_id", recruitment_id + "")
                .setCallBackData(new BaseNewNetModelimpl<LwTzWorkListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LwTzWorkListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            LwTzWorkListDetailModel record = respnse.getData();
                            if (record != null) {
                                //设置内容
                                setData(record);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LwTzWorkListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(LwTzWorkListDetailModel record) {
        tvFbsj.setText(DateUtil.StampTimeToDate(record.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvBianhao.setText(StringUtil.notEmptyBackValue(record.getOrder_sn()));
        tvBianti.setText(StringUtil.notEmptyBackValue(record.getTitle()));
        tvZprenshu.setText(record.getTotal_match_num() + "人");
        tvSex.setText(StringUtil.notEmptyBackValue(record.getSex_name()));
        tvAge.setText(StringUtil.notEmptyBackValue(record.getAge_name()));
        tvCompany.setText(StringUtil.notEmptyBackValue(record.getEnterprise_name()));
        if (record.getWork_type_name_list() != null) {
            StringBuilder result = new StringBuilder();
            for (LwTzWorkListDetailModel.WorkTypeNameListBean workTypeListBean : record.getWork_type_name_list()) {
                if (!TextUtils.isEmpty(workTypeListBean.getOther_work_type())) {
                    result.append(workTypeListBean.getOther_work_type() + "  " + workTypeListBean.getNum() + "人  ￥" + workTypeListBean.getPrice() + "/人\n");
                } else {
                    result.append(workTypeListBean.getName() + "  " + workTypeListBean.getNum() + "人  ￥" + workTypeListBean.getPrice() + "/人\n");
                }
            }
            if (!TextUtils.isEmpty(result)) {
                String str = result.substring(0, result.length() - "\n".length());
                tvGongzhong.setText(StringUtil.notEmptyBackValue(str));
            }
        }
        if (record.getSalary_type() == 1) {
            tvXinzi.setText("市场定价");
        } else if (record.getSalary_type() == 2) {

            tvXinzi.setText(record.getSalary_fee() + "-" + record.getEnd_salary_fee() + "元/天");

        } else {
            tvXinzi.setText("面议");
        }
        if (StringUtil.isAllNotEmpty(record.getWork_second_area_name(), record.getWork_third_area_name())) {
            tvDiqu.setText(record.getWork_second_area_name() + record.getWork_third_area_name());
        }
        tvDate.setText(StringUtil.notEmptyBackValue(record.getWork_date()));
        int work_hour = record.getWork_hour();
        if (work_hour == 0) {
            tvGzsj.setText("长期");
        } else {
            tvGzsj.setText(work_hour + "小时");
        }
        tvDesc.setText(StringUtil.notEmptyBackValue(record.getWork_info()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity) getActivity()).removeFragment();
            }
        });

        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("recruitment_id") !=0){
                    qzPiPei();
                }
            }
        });
    }

    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_LW_TZ_QZ_PIPEI_GONGZUO)
                .addParams("recruitment_id", recruitment_id + "")
                .addParams("type", (roleType == PageConfig.TYPE_ZHONGJIE ? 1 : 2) + "")
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
                            if (roleType == PageConfig.TYPE_ZHONGJIE){
                                type = CommonPayPresenter.TYPE_LAOGONG_XINXI;
                            }else if (roleType == PageConfig.TYPE_TEZHONGGONG){
                                type = CommonPayPresenter.TYPE_TEZHONG_XINXI;
                            }
                            PublicResultModel data = respnse.getData();
                            NewPayActivity.go(LwTzWorkListDetailFragment.this, type, data.getRelate_id(), data.getOrder_price() + "",
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
