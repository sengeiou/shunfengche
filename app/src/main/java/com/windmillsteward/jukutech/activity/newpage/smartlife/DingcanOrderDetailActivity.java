package com.windmillsteward.jukutech.activity.newpage.smartlife;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.SmartLifeDetailModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingcanOrderDetailActivity extends BaseInitActivity {

    @Bind(R.id.tv_pipei_status)
    TextView tvPipeiStatus;
    @Bind(R.id.tv_store_name)
    TextView tvStoreName;
    @Bind(R.id.tv_store_user_name)
    TextView tvStoreUserName;
    @Bind(R.id.tv_store_phone)
    TextView tvStorePhone;
    @Bind(R.id.lay_ll_store_phone)
    LinearLayout layLlStorePhone;
    @Bind(R.id.tv_store_address)
    TextView tvStoreAddress;
    @Bind(R.id.lay_ll_store_address)
    LinearLayout layLlStoreAddress;
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_xiadansj)
    TextView tvXiadansj;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.lay_ll_phone)
    LinearLayout layLlPhone;
    @Bind(R.id.tv_yudingsj)
    TextView tvYudingsj;
    @Bind(R.id.tv_num_tips)
    TextView tvNumTips;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_remark)
    TextView tvRemark;
    @Bind(R.id.tv_no)
    TextView tvNo;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.lay_ll_button)
    LinearLayout layLlButton;
    private int type;//不传也可以
    private int relate_id;

    public static void go(Activity activity, int relate_id, int type) {
        Intent intent = new Intent(activity, DingcanOrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("relate_id", relate_id);
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void initView(View view) {
        setMainTitle("订单详情");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type");
            relate_id = extras.getInt("relate_id");
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dingcan_order_detail;
    }

    @Override
    protected void initData() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_REQUIRE_ORDER_DETAIL)
                .addParams("require_id", relate_id + "")
                .setCallBackData(new BaseNewNetModelimpl<SmartLifeDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<SmartLifeDetailModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        if (respnse.getData() != null) {
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<SmartLifeDetailModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        initData();
    }


    private void setData(final SmartLifeDetailModel data) {
        int my_role = data.getMy_role();
        if (my_role == 1) {
            layLlButton.setVisibility(View.GONE);
        } else if (my_role == 2) {
            layLlButton.setVisibility(View.VISIBLE);
        }
        if (data.getStatus() == 2) {//待接单
            tvPipeiStatus.setTextColor(Color.parseColor("#3172f4"));
            if (my_role == 1) {
                layLlButton.setVisibility(View.GONE);
            } else if (my_role == 2) {
                layLlButton.setVisibility(View.VISIBLE);
            }
        } else if (data.getStatus() == 3) {//已完成
            tvPipeiStatus.setTextColor(Color.parseColor("#FF0000"));
            layLlButton.setVisibility(View.GONE);
        } else if (data.getStatus() == 4) {//已取消
            tvPipeiStatus.setTextColor(Color.parseColor("#239491"));
            layLlButton.setVisibility(View.GONE);
        }
        tvPipeiStatus.setText(StringUtil.notEmptyBackValue(data.getStatus_name()));
        //商家信息
        tvStoreName.setText(StringUtil.notEmptyBackValue(data.getService_name()));
        tvStoreUserName.setText(StringUtil.notEmptyBackValue(data.getBoss_name()));
        if (TextUtils.isEmpty(data.getBoss_mobile_phone())) {
            layLlStorePhone.setVisibility(View.GONE);
        } else {
            layLlStorePhone.setVisibility(View.VISIBLE);
            tvStorePhone.setText(StringUtil.notEmptyBackValue(data.getBoss_mobile_phone()));
            tvStorePhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final BaseDialog baseDialog = new BaseDialog(DingcanOrderDetailActivity.this);
                    baseDialog.showTwoButton("提示", "是否拨打电话?\n" + data.getBoss_name() + "\n" + data.getBoss_mobile_phone()
                            , "拨打", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (baseDialog != null) {
                                        baseDialog.dismiss();
                                    }
                                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                    if (checkPermission(permissions)) {
                                        PhoneUtils.dial(data.getBoss_mobile_phone());
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
        if (TextUtils.isEmpty(data.getAddress())) {
            layLlStoreAddress.setVisibility(View.GONE);
        } else {
            layLlStoreAddress.setVisibility(View.VISIBLE);
            tvStoreAddress.setText(StringUtil.notEmptyBackValue(data.getAddress()));
            if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                tvStoreAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MapNaviUtils.showDaoHangDialog(DingcanOrderDetailActivity.this, Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), data.getAddress());
                    }
                });
            }
        }
        //用户信息
        tvBianhao.setText(StringUtil.notEmptyBackValue(data.getOrder_sn()));
        tvXiadansj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvUserName.setText(StringUtil.notEmptyBackValue(data.getUser_name()));
        if (TextUtils.isEmpty(data.getBoss_mobile_phone())) {
            layLlPhone.setVisibility(View.GONE);
        } else {
            layLlPhone.setVisibility(View.VISIBLE);
            tvPhone.setText(StringUtil.notEmptyBackValue(data.getMobile_phone()));
            tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final BaseDialog baseDialog = new BaseDialog(DingcanOrderDetailActivity.this);
                    baseDialog.showTwoButton("提示", "是否拨打电话?\n" + data.getUser_name() + "\n" + data.getMobile_phone()
                            , "拨打", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (baseDialog != null) {
                                        baseDialog.dismiss();
                                    }
                                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                    if (checkPermission(permissions)) {
                                        PhoneUtils.dial(data.getMobile_phone());
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
        tvYudingsj.setText(DateUtil.StampTimeToDate(data.getBooking_time() + "", "yyyy-MM-dd HH:mm:ss"));
        if (data.getIndex_type() == 1) {
            tvNumTips.setText("订餐人数");
            tvNum.setText(data.getPeople_num() + "人");
        } else if (data.getIndex_type() == 2) {
            tvNumTips.setText("订房间数");
            tvNum.setText(data.getPeople_num() + "间");
        } else if (data.getIndex_type() == 3) {
            tvNumTips.setText("订票张数");
            tvNum.setText(data.getPeople_num() + "张");
        }
        tvRemark.setText(StringUtil.notEmptyBackValue(data.getRemark()));
    }

    @OnClick({R.id.tv_no, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_no:
                refuse();
                break;
            case R.id.tv_confirm:
                confirm();
                break;
        }
    }

    private void refuse() {
        if (relate_id == 0) {
            return;
        }
        final BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.showTwoButton("提示", "是否拒绝接单", "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                addCall(new NetUtil().setUrl(APIS.URL_DINGCAN_REFUSE_ORDER)
                        .addParams("require_id", relate_id + "")
                        .setCallBackData(new BaseNewNetModelimpl<String>() {
                            @Override
                            protected void onFail(int type, String msg) {
                                dismiss();
                                showTips(msg);
                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
                                dismiss();
                                baseDialog.dismiss();
                                if (respnse.getData() != null) {
                                    showTips("操作成功");
                                    refreshPageData();
                                }
                            }

                            @Override
                            protected Type getType() {
                                return new TypeToken<BaseResultInfo<String>>() {
                                }.getType();
                            }
                        }).buildPost()
                );
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });

    }

    private void confirm() {
        if (relate_id == 0) {
            return;
        }
        final BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.showTwoButton("提示", "是否确认接单", "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
                showDialog();
                addCall(new NetUtil().setUrl(APIS.URL_DINGCAN_CONFIRM_ORDER)
                        .addParams("require_id", relate_id + "")
                        .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                            @Override
                            protected void onFail(int type, String msg) {
                                dismiss();
                                showTips(msg);
                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                                dismiss();
                                baseDialog.dismiss();
                                if (respnse.getData() != null) {
                                    PublicResultModel data = respnse.getData();
                                    NewPayActivity.go(DingcanOrderDetailActivity.this, CommonPayPresenter.TYPE_SMART_DINGCAN,
                                            data.getRelate_id(), data.getOrder_price() + "", data.getOrder_name(), NewPayActivity.CAN_USE_COUPON, respnse.getMsg());
                                }
                            }

                            @Override
                            protected Type getType() {
                                return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                                }.getType();
                            }
                        }).buildPost()
                );
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            finish();
        }
    }
}
