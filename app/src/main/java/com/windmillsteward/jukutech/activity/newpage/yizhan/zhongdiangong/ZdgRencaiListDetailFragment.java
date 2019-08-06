package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong;

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
import com.windmillsteward.jukutech.activity.newpage.model.ZdgRencaiListDetailModel;
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

public class ZdgRencaiListDetailFragment extends BaseInitFragment {

    public static final String TAG = "ZdgRencaiListDetailFragment";
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
    @Bind(R.id.tv_service_content)
    TextView tvServiceContent;
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

    public static ZdgRencaiListDetailFragment newInstance(int when_bell_worker_id) {
        Bundle args = new Bundle();
        args.putInt("when_bell_worker_id", when_bell_worker_id);
        ZdgRencaiListDetailFragment fragment = new ZdgRencaiListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zdg_rencai_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("钟点工-应聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_ZDG_RENCAI_INFO_LIST_DETAIL)
                .addParams("when_bell_worker_id", getArguments().getInt("when_bell_worker_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZdgRencaiListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZdgRencaiListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            ZdgRencaiListDetailModel data = respnse.getData();
                            if (data != null) {
                                //设置内容
                                setData(data);
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZdgRencaiListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(ZdgRencaiListDetailModel data) {
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvName.setText(StringUtil.notEmptyBackValue(data.getName()));
        tvSex.setText(data.getSex() == 1 ? "男" : "女");
        tvAge.setText(data.getAge() + "岁");

        StringBuffer sb = new StringBuffer();
        for (ZdgRencaiListDetailModel.ServiceIdsBean model : data.getService_ids()) {
            String name = model.getName();
            sb.append(name + ",");
        }
        String result = sb.toString();
        tvServiceContent.setText(result.substring(0, result.length() - 1));

        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }

        tvRiqi.setText(StringUtil.notEmptyBackValue(data.getWork_date()));
        tvWorkHour.setText(StringUtil.notEmptyBackValue(data.getWork_hour_name()));

        if (data.getSalary_type() == 1) {
            tvXinzi.setText("市场定价");
        } else if (data.getSalary_type() == 2) {
            if (StringUtil.isAllNotEmpty(data.getSalary_fee(), data.getEnd_salary_fee())) {
                tvXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元/小时");
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
                if (getArguments().getInt("when_bell_worker_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    /**
     * 满意人才，请求成功后，发布找钟点工
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_ZDG_QZ_PIPEI_RENCAI)
                .addParams("when_bell_worker_id", getArguments().getInt("when_bell_worker_id") + "")
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
                            NewPayActivity.go(ZdgRencaiListDetailFragment.this, CommonPayPresenter.TYPE_ZHONGDIANGONG_ZHAOPIN, data.getRelate_id(), data.getOrder_price() + "",
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
