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
import com.windmillsteward.jukutech.activity.newpage.model.LwTzRencaiListDetalModel;
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

/**
 * 劳务和特种-人才信息列表详情
 */
public class LwTzRencaiListDetailFragment extends BaseInitFragment {

    public static final String TAG = "LwTzRencaiListDetailFragment";

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
    @Bind(R.id.tv_gongzhong)
    TextView tvGongzhong;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_riqi)
    TextView tvRiqi;
    @Bind(R.id.tv_work_hour)
    TextView tvWorkHour;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_no)
    TextView tvNo;

    private int info_id;
    private int roleType;

    public static LwTzRencaiListDetailFragment newInstance(int info_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("info_id", info_id);
        args.putInt("roleType", roleType);
        LwTzRencaiListDetailFragment fragment = new LwTzRencaiListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_lw_tz_rencai_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
            info_id = getArguments().getInt("info_id");
            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                setMainTitle("劳务中介-应聘详情");
            } else {
                setMainTitle("特种工-应聘详情");
            }
        }
    }

    @Override
    protected void initData() {
        addCall(new NetUtil()
                .setUrl(APIS.URL_LW_TZ_RENCAI_INFO_LIST_DETAIL)
                .addParams("info_id", info_id + "")
                .setCallBackData(new BaseNewNetModelimpl<LwTzRencaiListDetalModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LwTzRencaiListDetalModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            showContentView();
                            LwTzRencaiListDetalModel data = respnse.getData();
                            setData(data);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LwTzRencaiListDetalModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(LwTzRencaiListDetalModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvName.setText(StringUtil.notEmptyBackValue(data.getName()));
        tvSex.setText(data.getSex() == 1 ? "男" : "女");
        tvAge.setText(data.getAge() + "岁");

        if (data.getWork_type_name_list() != null) {
            StringBuffer sb = new StringBuffer();
            for (LwTzRencaiListDetalModel.WorkTypeNameListBean model : data.getWork_type_name_list()) {
                String name = model.getName();
                sb.append(name + ",");
            }
            String result = sb.toString();
            if (!TextUtils.isEmpty(result)) {
                tvGongzhong.setText(result.substring(0, result.length() - 1));
            }
        }else{
            tvGongzhong.setText(StringUtil.notEmptyBackValue(data.getOther_work_type()));
        }

        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        tvRiqi.setText(StringUtil.notEmptyBackValue(data.getWork_date()));
        if (data.getWork_hour() == 0) {
            tvWorkHour.setText("长期");
        } else {
            tvWorkHour.setText(data.getWork_hour() + "小时");
        }
        if (data.getSalary_type() == 1) {
            tvXinzi.setText("市场定价");
        } else if (data.getSalary_type() == 2) {
            tvXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "");
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
                if (getArguments().getInt("info_id") !=0){
                    qzPiPei();
                }
            }
        });
    }

    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_LW_TZ_QZ_PIPEI_RENCAI)
                .addParams("info_id", info_id + "")
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
                                type = CommonPayPresenter.TYPE_LAOGONG_ZHAOPIN;
                            }else if (roleType == PageConfig.TYPE_TEZHONGGONG){
                                type = CommonPayPresenter.TYPE_TEZHONG_ZHAOPIN;
                            }
                            PublicResultModel data = respnse.getData();
                            NewPayActivity.go(LwTzRencaiListDetailFragment.this, type, data.getRelate_id(), data.getOrder_price() + "",
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
