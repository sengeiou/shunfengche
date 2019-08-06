package com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao;


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
import com.windmillsteward.jukutech.activity.newpage.model.JiajiaoUserPersonModel;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 家教-我要当家教--用工明细
 */
public class JiajiaoUsePersonFragment extends BaseInitFragment {
    public static final String TAG = "JiajiaoUsePersonFragment";
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_gongzhong)
    TextView tvGongzhong;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_yuding)
    TextView tvYuding;
    @Bind(R.id.tv_daiyu)
    TextView tvDaiyu;
    @Bind(R.id.tv_zhoumo)
    TextView tvZhoumo;
    @Bind(R.id.tv_gongzhongri)
    TextView tvGongzhongri;
    @Bind(R.id.tv_feiyong)
    TextView tvFeiyong;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
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
    private JiajiaoUserPersonModel data;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jiajiao_use_person;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("用工明细");
    }

    public static JiajiaoUsePersonFragment newInstance(int look_for_tutor_id) {
        Bundle args = new Bundle();
        args.putInt("look_for_tutor_id", look_for_tutor_id);
        JiajiaoUsePersonFragment fragment = new JiajiaoUsePersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_LOOKFOR_TUTOR_DETAIL)
                .addParams("look_for_tutor_id", getArguments().getInt("look_for_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<JiajiaoUserPersonModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JiajiaoUserPersonModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            setData(data);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JiajiaoUserPersonModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 设置数据
     *
     * @param model
     */
    private void setData(JiajiaoUserPersonModel model) {
        tvBianhao.setText(model.getOrder_sn());
        tvKemu.setText(model.getCoach_subject_name());
        tvNianji.setText(model.getCoach_grade_name());
        tvAddress.setText(model.getAddress());
        tvGongzhong.setText(model.getArea_name());
        tvYuding.setText(model.getBooking_time());
        tvDaiyu.setText(model.getSalary());
        if (!TextUtils.isEmpty(data.getCoach_time())) {
            if (data.getCoach_time().contains("1")) {
                if (data.getCoach_time().contains("1"))
                    tvZhoumo.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
                if (data.getCoach_time().contains("2"))
                    tvGongzhongri.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
            }
        }
        //状态：2.已确认匹配 3.待发布方确认完成。 如果status=2,则显示“确认完成工作”按钮，其它状态则不显示按钮
        if (model.getStatus() == 2) {
            tvFinish.setVisibility(View.VISIBLE);
        } else {
            tvFinish.setVisibility(View.GONE);
        }


        layLlHeader.setVisibility(View.VISIBLE);
        String avatar_url = data.getAvatar_url();
        final String contact_person = data.getContact_person();
        final String contact_tel = data.getContact_tel();
        String match_value = data.getMatch_value();
        GlideUtil.show(getActivity(),avatar_url,ivAvatar);
        tvPhone.setText(TextUtils.isEmpty(contact_tel)?"":contact_tel);
        tvPipei.setText(new SpanUtils().append("匹配度：")
                .append(match_value + "%")
                .setForegroundColor(ResUtils.getCommRed())
                .create());
        tvName.setText(TextUtils.isEmpty(contact_person)?"":contact_person);
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
        initData();
    }

    @OnClick({R.id.tv_address, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                if (data != null) {
                    MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");
                }
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }

    //确认完成工作
    private void finish() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EMPLOYEE_CONFIRM_WORK_DONE_JIAJIAO)
                .addParams("look_for_tutor_id", getArguments().getInt("look_for_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        if (respnse.getCode() == 0) {
                            showTips("确认完成工作成功");
                            tvFinish.setVisibility(View.GONE);
                            initData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
