package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong;

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
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZdgWorkListDetailModel;
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

public class ZdgWorkListDetailFragment extends BaseInitFragment {

    public static final String TAG = "ZdgWorkListDetailFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_fbsj)
    TextView tvFbsj;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_service_content)
    TextView tvServiceContent;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_gzsj)
    TextView tvGzsj;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_apply)
    TextView tvApply;
    @Bind(R.id.tv_zprenshu)
    TextView tvZprenshu;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_xinzi_tips)
    TextView tvXinziTips;
    @Bind(R.id.tv_no)
    TextView tvNo;

    public static ZdgWorkListDetailFragment newInstance(int lookfor_bell_worker_id) {
        Bundle args = new Bundle();
        args.putInt("lookfor_bell_worker_id", lookfor_bell_worker_id);
        ZdgWorkListDetailFragment fragment = new ZdgWorkListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zdg_work_list_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("钟点工-招聘方详情");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_ZDG_WORK_INFO_LIST_DETAIL)
                .addParams("lookfor_bell_worker_id", getArguments().getInt("lookfor_bell_worker_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZdgWorkListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZdgWorkListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            ZdgWorkListDetailModel record = respnse.getData();
                            if (record != null) {
                                //设置内容
                                setData(record);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZdgWorkListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void setData(ZdgWorkListDetailModel data) {
        tvFbsj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvBianti.setText(StringUtil.notEmptyBackValue(data.getTitle()));
        tvCompany.setText(StringUtil.notEmptyBackValue(data.getEnterprise_name()));
        tvZprenshu.setText(data.getRecruit_number() + "人");
        tvAge.setText(StringUtil.notEmptyBackValue(data.getAge_name()));
        tvSex.setText(StringUtil.notEmptyBackValue(data.getSex_name()));
        StringBuffer sb = new StringBuffer();
        if (data.getService_ids() != null) {
            for (ZdgWorkListDetailModel.ServiceIdsBean model : data.getService_ids()) {
                sb.append(model.getName() + ",");
            }
            String result = sb.toString();
            if (!TextUtils.isEmpty(result)) {
                tvServiceContent.setText(result.substring(0, result.length() - 1));
            }
        }
        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        tvDate.setText(StringUtil.notEmptyBackValue(data.getWork_date()));
        tvGzsj.setText(StringUtil.notEmptyBackValue(data.getWork_hour_name()));
        tvXinzi.setText(StringUtil.notEmptyBackValue(data.getSalary()));
        tvDesc.setText(StringUtil.notEmptyBackValue(data.getJob_describe()));
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity) getActivity()).removeFragment();
            }
        });
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BackFragmentActivity) getActivity()).removeFragment();
            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("lookfor_bell_worker_id") != 0) {
                    qzPiPei();
                }
            }
        });
    }

    /**
     * 满意工作，请求成功后，发布当钟点工
     */
    private void qzPiPei() {
        showDialog();
        addCall(new NetUtil()
                .setUrl(APIS.URL_ZDG_QZ_PIPEI_GONGZUO)
                .addParams("lookfor_bell_worker_id", getArguments().getInt("lookfor_bell_worker_id") + "")
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
                            NewPayActivity.go(ZdgWorkListDetailFragment.this, CommonPayPresenter.TYPE_ZHONGDIANGONG, data.getRelate_id(), data.getOrder_price() + "",
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
