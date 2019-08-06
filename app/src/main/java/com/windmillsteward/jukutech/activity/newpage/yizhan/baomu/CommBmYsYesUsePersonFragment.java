package com.windmillsteward.jukutech.activity.newpage.yizhan.baomu;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesPersonDetailsModel;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.ResUtils;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 保姆-应聘方查看信息
 */
public class CommBmYsYesUsePersonFragment extends BaseInitFragment {
    public static final String TAG = "CommBmYsYesUsePersonFragment";
    @Bind(R.id.tv_dingdan)
    TextView tvDingdan;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
    @Bind(R.id.tv_ydshijian)
    TextView tvYdshijian;
    @Bind(R.id.tv_daiyu)
    TextView tvDaiyu;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.tv_complete)
    TextView tvComplete;
    @Bind(R.id.tv_type_tips)
    TextView tv_type_tips;
    @Bind(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_pipei)
    TextView tvPipei;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;
    @Bind(R.id.lay_ll_header)
    LinearLayout layLlHeader;

    public static CommBmYsYesUsePersonFragment newInstance(int roleType, int recruitment_id) {
        Bundle args = new Bundle();
        args.putInt("recruitment_id", recruitment_id);
        args.putInt("roleType", roleType);
        CommBmYsYesUsePersonFragment fragment = new CommBmYsYesUsePersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_baomu_user_person;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("雇主信息");

        if (getArguments() != null) {
            int roleType = getArguments().getInt("roleType");
            if (roleType == PageConfig.TYPE_BAOMU) {
                tv_type_tips.setText("保姆类型");
            } else if (roleType == PageConfig.TYPE_YUESAO) {
                tv_type_tips.setText("月嫂类型");
            } else if (roleType == PageConfig.TYPE_YUERSAO) {
                tv_type_tips.setText("育儿嫂类型");
            }
        }
    }

    @Override
    protected void initData() {
        getData();
    }

    //获取信息
    private void getData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RECRUITMENT_DETAIL)
                .addParams("recruitment_id", getArguments().getInt("recruitment_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<BmYsYesPersonDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BmYsYesPersonDetailsModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        if (respnse.getData() != null)
                            setData(respnse.getData());
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BmYsYesPersonDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 设置数据
     *
     * @param data
     */
    private void setData(final BmYsYesPersonDetailsModel data) {
        tvComplete.setVisibility(View.GONE);
        tvDingdan.setText(data.getOrder_sn());
        tvJingyan.setText(data.getWork_experience_name());
        tvLeixing.setText(data.getPerson_type_name());
        tvShijian.setText(data.getWork_time_type_name());
        tvYdshijian.setText(data.getBooking_time());
        tvDaiyu.setText(data.getSalary());
        tvDiqu.setText(data.getArea_name());
        tvAddress.setText(data.getAddress());
        List<String> service_contents = data.getService_contents();
        StringBuilder stringBuilder = new StringBuilder();
        if (service_contents != null) {
            for (String service_content : service_contents) {
                stringBuilder.append(service_content + "、");
            }
            if (TextUtils.isEmpty(stringBuilder)) {
                tvNeirong.setText("");
            } else {
                tvNeirong.setText(stringBuilder.subSequence(0, stringBuilder.length() - 1));
            }
        } else {
            tvNeirong.setText("");
        }

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");

            }
        });

        //状态：2.已确认匹配 3.待发布方确认完成。 如果status=2,则显示“确认完成工作”按钮，其它状态则不显示按钮
        if (data.getStatus() == 2) {
            tvComplete.setVisibility(View.VISIBLE);
        } else {
            tvComplete.setVisibility(View.GONE);
        }
        layLlHeader.setVisibility(View.VISIBLE);
        String avatar_url = data.getAvatar_url();
        final String contact_person = data.getContact_person();
        final String contact_tel = data.getContact_tel();
        String match_value = data.getMatch_value();
        GlideUtil.show(getActivity(), avatar_url, ivAvatar);
        tvPhone.setText(TextUtils.isEmpty(contact_tel) ? "" : contact_tel);
        tvPipei.setText(new SpanUtils().append("匹配度：")
                .append(match_value + "%")
                .setForegroundColor(ResUtils.getCommRed())
                .create());
        tvName.setText(TextUtils.isEmpty(contact_person) ? "" : contact_person);
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BaseDialog baseDialog = new BaseDialog(getActivity());
                baseDialog.showTwoButton("提示", "是否拨打电话?\n" + contact_person + "\n" + contact_tel
                        , "拨打", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (baseDialog != null) {
                                    baseDialog.dismiss();
                                }
                                String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                if (checkPermission(permissions)) {
                                    PhoneUtils.dial(contact_tel);
                                }
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (baseDialog != null) {
                                    baseDialog.dismiss();
                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void refreshPageData() {
        showDialog();
        getData();
    }

    @OnClick({R.id.tv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_complete:
                complete();
                break;
        }
    }

    /**
     * 确定完成工作
     */
    private void complete() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EMPLOYEE_CONFIRM_WORK_DONE)
                .addParams("recruitment_id", getArguments().getInt("recruitment_id") + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            showTips("确认完成工作成功");
                            tvComplete.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }


}
