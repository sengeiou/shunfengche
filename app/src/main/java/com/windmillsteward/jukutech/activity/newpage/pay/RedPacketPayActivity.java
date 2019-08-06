package com.windmillsteward.jukutech.activity.newpage.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.EaseConstant;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.MyWalletBean;
import com.windmillsteward.jukutech.bean.RedPacketPayModel;
import com.windmillsteward.jukutech.bean.event.NotifyPayComplete;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.PayResult;
import com.windmillsteward.jukutech.utils.ResUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 红包--收银台
 */
public class RedPacketPayActivity extends BaseInitActivity {
    public static final int REQUEST_CODE = 1025;
    public static final int RESULT_CODE_SUCCESS = 250;
    public static final int SDK_PAY_FLAG = 2;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_details)
    TextView tvDetails;
    @Bind(R.id.tv_youhuiquan)
    TextView tvYouhuiquan;
    @Bind(R.id.ll_youhuiquan)
    LinearLayout llYouhuiquan;
    @Bind(R.id.iv_alipay_flag)
    ImageView ivAlipayFlag;
    @Bind(R.id.tv_alipay_money)
    TextView tvAlipayMoney;
    @Bind(R.id.ll_alipay_container)
    LinearLayout llAlipayContainer;
    @Bind(R.id.iv_wechat_flag)
    ImageView ivWechatFlag;
    @Bind(R.id.tv_wechat_money)
    TextView tvWechatMoney;
    @Bind(R.id.ll_weichat_container)
    LinearLayout llWeichatContainer;
    @Bind(R.id.iv_yue_flag)
    ImageView ivYueFlag;
    @Bind(R.id.tv_yue_money)
    TextView tvYueMoney;
    @Bind(R.id.ll_yue_container)
    LinearLayout llYueContainer;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_youhuiquan_new)
    TextView tvYouhuiquanNew;
    @Bind(R.id.tv_wallet_money)
    TextView tv_wallet_money;

    //类型 与Presenter里面的对应
    private int type;
    private int relate_id;
    private String title;
    private String packet_desc;
    private String money;
    private RedPacketPayModel resultModel;

    private CommonPayPresenter.PAY_TYPE pay_type;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shouyintai;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View view) {
        setMainTitle("收银台");

        showContentView();
        llYouhuiquan.setVisibility(View.GONE);
    }

    @Override
    public BasePresenter getPresenter() {
        return new CommonPayPresenter(this);
    }

    /**
     * 传入CommonPayPresenter对应的type即可
     *
     * @param activity
     * @param type
     * @param relate_id
     */
    public static void go(Activity activity, int type, int relate_id, String money, String title, String packet_desc, RedPacketPayModel resultModel) {
        Intent intent = new Intent(activity, RedPacketPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putSerializable("resultModel",resultModel);
        bundle.putString("packet_desc", packet_desc);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 获取数据
     */
    private void getData() {
        showDialog();
        ((CommonPayPresenter) presenter).loadPayInfoData(type, pay_type, relate_id, 0, new CommonPayPresenter.DataCallBack<PayInfoModel>() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
                dismiss();
            }

            @Override
            public void onSucess(int code, final BaseResultInfo<PayInfoModel> response, String source) {
                dismiss();
                if (response.getData() != null) {
                    if (pay_type == CommonPayPresenter.PAY_TYPE.ALI_PAY) {
                        //支付宝
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(RedPacketPayActivity.this);
                                Map<String, String> result = alipay.payV2(response.getData().getAlipay(), true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();

                    } else if (pay_type == CommonPayPresenter.PAY_TYPE.WECHAT) {
                        final IWXAPI msgApi = WXAPIFactory.createWXAPI(RedPacketPayActivity.this, null);
                        msgApi.registerApp(Define.WX_APP_ID);

                        PayInfoModel payInfoModel = response.getData();
                        PayReq req = new PayReq();
                        req.appId = Define.WX_APP_ID;
                        req.partnerId = payInfoModel.getPartnerid();
                        req.prepayId = payInfoModel.getPrepayid();
                        req.nonceStr = payInfoModel.getNoncestr();
                        req.timeStamp = payInfoModel.getTimestamp();
                        req.packageValue = payInfoModel.getPackageX();
                        req.sign = payInfoModel.getSign();

                        msgApi.sendReq(req);
                    } else if (pay_type == CommonPayPresenter.PAY_TYPE.CARD) {
                        //钱包
                        //成功
                      paySuccess();
                    }
                }
            }
        });
    }

    /**
     * 获取钱包数据
     */
    private void getWalletData() {
        showDialog();
        ((CommonPayPresenter) presenter).walletPay(type, pay_type, relate_id, 0, new CommonPayPresenter.DataCallBack<String>() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
                dismiss();
            }

            @Override
            public void onSucess(int code, final BaseResultInfo<String> response, String source) {
                dismiss();
                if (response.getData() != null) {
                    if (pay_type == CommonPayPresenter.PAY_TYPE.CARD) {
                        //钱包
                        //成功
                        paySuccess();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type", 0);
            relate_id = extras.getInt("relate_id", 0);
            money = extras.getString("money");
            title = extras.getString("title");
            packet_desc = extras.getString("packet_desc");
            resultModel =(RedPacketPayModel) extras.getSerializable("resultModel");
        }

        //设置属性
        tvMoney.setText(new SpanUtils().append("订单金额：")
                .append("￥" + money).setForegroundColor(Color.parseColor("#ffa647"))
                .create());
        tvDetails.setText("订单名称: " + title);
        tvWechatMoney.setText("¥" + money);
        tvAlipayMoney.setText("¥" + money);
        tvYueMoney.setText("¥" + money);
        tvWechatMoney.setVisibility(View.GONE);
        tvYueMoney.setVisibility(View.GONE);

        pay_type = CommonPayPresenter.PAY_TYPE.ALI_PAY;

        getYuE();
    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.ll_alipay_container, R.id.ll_weichat_container, R.id.ll_yue_container, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_alipay_container:
                pay_type = CommonPayPresenter.PAY_TYPE.ALI_PAY;
                reset();
                tvAlipayMoney.setVisibility(View.VISIBLE);
                ivAlipayFlag.setImageResource(R.mipmap.select);
                break;
            case R.id.ll_weichat_container:
                pay_type = CommonPayPresenter.PAY_TYPE.WECHAT;
                reset();
                tvWechatMoney.setVisibility(View.VISIBLE);
                ivWechatFlag.setImageResource(R.mipmap.select);
                break;
            case R.id.ll_yue_container:
                pay_type = CommonPayPresenter.PAY_TYPE.CARD;
                reset();
                tvYueMoney.setVisibility(View.VISIBLE);
                ivYueFlag.setImageResource(R.mipmap.select);
                break;
            case R.id.tv_submit:
                if (pay_type == CommonPayPresenter.PAY_TYPE.CARD) {
                    getWalletData();
                } else {
                    getData();
                }
                break;
        }
    }


    /**
     * 获取钱包余额
     */
    private void getYuE() {
        addCall(new NetUtil().setUrl(APIS.URL_MY_WALLET)
                .setCallBackData(new BaseNewNetModelimpl<MyWalletBean>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<MyWalletBean> respnse, String source) {
                        if (respnse.getData() != null) {
                            MyWalletBean data = (MyWalletBean) respnse.getData();
                            tv_wallet_money.setText(new SpanUtils()
                                    .append("余额支付(当前余额: ").append(" " + data.getCurrent_fee())
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .append(")")
                                    .setForegroundColor(ResUtils.getColor(R.color.color_666666))
                                    .create());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<MyWalletBean>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked") PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /** 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为结束的通知。 */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus(); // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //成功
                        paySuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showTips("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    private void paySuccess() {
        Intent intent = new Intent();
//        intent.putExtra(EaseConstant.RED_PACKET_ID,relate_id);
//        intent.putExtra(EaseConstant.PACKET_DESC,packet_desc);
        if (resultModel != null){
            intent.putExtra(EaseConstant.RED_PACKET_RESULT_MODEL,resultModel);
        }
        showTips("支付成功");
        setResult(RESULT_CODE_SUCCESS,intent);
        finish();
    }


    //重置按钮
    private void reset() {
        ivAlipayFlag.setImageResource(R.mipmap.unselect);
        ivWechatFlag.setImageResource(R.mipmap.unselect);
        ivYueFlag.setImageResource(R.mipmap.unselect);
        tvAlipayMoney.setVisibility(View.GONE);
        tvWechatMoney.setVisibility(View.GONE);
        tvYueMoney.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notifyPayComplete(NotifyPayComplete event) {
        showTips(event.getMsg());
        if (event.getCode() == 0) {
            //成功
            paySuccess();
        } else if (event.getCode() == -1) {
            //失败

        } else if (event.getCode() == -2) {
            //取消

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
