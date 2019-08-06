package com.windmillsteward.jukutech.activity.newpage.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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
import com.windmillsteward.jukutech.activity.mine.activity.MyCouponActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.bean.MyWalletBean;
import com.windmillsteward.jukutech.bean.event.NotifyPayComplete;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.MathUtils;
import com.windmillsteward.jukutech.utils.PayResult;
import com.windmillsteward.jukutech.utils.ResUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 收银台
 */
public class NewPayActivity extends BaseInitActivity {
    public static final int REQUEST_CODE = 1025;
    public static final int RESULT_CODE_SUCCESS = 250;
    public static final int SDK_PAY_FLAG = 2;
    public static final String USE_COUPON = "use_coupon";
    public static final int CAN_USE_COUPON = 0;
    public static final int CANT_USE_COUPON = 1;
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
    private int coupon_id;//优惠券id
    private String title;
    private String msg;
    private String money;
    private String old_price;//原价
    private int no_use_coupon;//0能用1不能用
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
     * @param msg 智慧生活抢单成功后的提示文字
     */
    public static void go(Activity activity, int type, int relate_id, String money, String title, int no_use_coupon,String msg) {
        Intent intent = new Intent(activity, NewPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        bundle.putInt("no_use_coupon", no_use_coupon);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 传入CommonPayPresenter对应的type即可
     *
     * @param activity
     * @param type
     * @param relate_id
     */
    public static void go(Activity activity, int type, int relate_id, String money, String title, int no_use_coupon) {
        Intent intent = new Intent(activity, NewPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putInt("no_use_coupon", no_use_coupon);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void go(Fragment fragment, int type, int relate_id, String money, String title, int no_use_coupon) {
        Intent intent = new Intent(fragment.getActivity(), NewPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putInt("no_use_coupon", no_use_coupon);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void go(Fragment fragment, int type, int relate_id, String money, String title, int no_use_coupon,String msg) {
        Intent intent = new Intent(fragment.getActivity(), NewPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putInt("no_use_coupon", no_use_coupon);
        bundle.putString("msg", msg);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void go(Fragment fragment, int type,int qz_id, int relate_id, String money, String title, int no_use_coupon) {
        Intent intent = new Intent(fragment.getActivity(), NewPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("qz_id", qz_id);
        bundle.putInt("relate_id", relate_id);
        bundle.putString("money", money);
        bundle.putString("title", title);
        bundle.putInt("no_use_coupon", no_use_coupon);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type", 0);
            relate_id = extras.getInt("relate_id", 0);
            money = extras.getString("money");
            old_price = money;
            title = extras.getString("title");
            msg = extras.getString("msg","");
            no_use_coupon = extras.getInt("no_use_coupon");
            if (no_use_coupon == 0) {
                llYouhuiquan.setVisibility(View.VISIBLE);
            } else {
                llYouhuiquan.setVisibility(View.GONE);
            }
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

        getCouPonNum();
        getYuE();

    }

    /**
     * 获取数据
     */
    private void getData() {
        showDialog();
        ((CommonPayPresenter) presenter).loadPayInfoData(type, pay_type,relate_id, coupon_id, new CommonPayPresenter.DataCallBack<PayInfoModel>() {
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
                                PayTask alipay = new PayTask(NewPayActivity.this);
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
                        final IWXAPI msgApi = WXAPIFactory.createWXAPI(NewPayActivity.this, null);
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
                        setResult(RESULT_CODE_SUCCESS);
                        Intent intent = new Intent(NewPayActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                        finish();
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
        ((CommonPayPresenter) presenter).walletPay(type, pay_type, relate_id, coupon_id, new CommonPayPresenter.DataCallBack<String>() {
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
                        publishSuccess(type);
                        setResult(RESULT_CODE_SUCCESS);
                        Intent intent = new Intent(NewPayActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    //获取优惠券信息
    private void getCouPonNum() {
        addCall(new NetUtil().setUrl(APIS.URL_MY_COUPON)
                .addParams("page", 1 + "")
                .addParams("page_count", 10 + "")
                .addParams("coupon_status", 1 + "")//可用的优惠券
                .setCallBackData(new BaseNewNetModelimpl<MyCouponBean>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<MyCouponBean> respnse, String source) {
                        if (respnse.getData() != null && respnse.getData().getList() != null) {
                            int totalRow = respnse.getData().getTotalRow();
                            if (totalRow > 0) {
                                llYouhuiquan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(NewPayActivity.this, MyCouponActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt(USE_COUPON, 1);
                                        intent.putExtras(bundle);
                                        startActivityForResult(intent, NewPayActivity.REQUEST_CODE);
                                    }
                                });
                                tvYouhuiquan.setText(new SpanUtils()
                                        .append("您有")
                                        .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                                        .append(" " + totalRow)
                                        .setForegroundColor(ResUtils.getCommRed())
                                        .append(" 张优惠券可以使用")
                                        .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                                        .create());
                            } else {
                                tvYouhuiquan.setText("暂无可用的优惠券");
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<MyCouponBean>>() {
                        }.getType();
                    }
                }).buildPost()
        );
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
                if (type == CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI){
                    smartPayNeedPost();
                }else{
                    if (pay_type == CommonPayPresenter.PAY_TYPE.CARD) {
                        getWalletData();
                    } else {
                        getData();
                    }
                }
                break;
        }
    }

    /**
     * 智慧生活抢单需要先调用这个
     */
    private void smartPayNeedPost(){
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_CAN_ROB_REQUIRE)
                .addParams("require_id", relate_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        if (pay_type == CommonPayPresenter.PAY_TYPE.CARD) {
                            getWalletData();
                        } else {
                            getData();
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

    /**
     * 使用了优惠券之后
     *
     * @param coupon_id
     */
    private void useCoupon(int coupon_id) {
        addCall(new NetUtil().setUrl(APIS.URL_USE_COUPON)
                .addParams("type", type + "")
                .addParams("relate_id", relate_id + "")
                .addParams("receive_id", coupon_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        String data = respnse.getData().toString();
                        try {
                            JSONObject object = new JSONObject(data);
                            money = object.getString("order_price");//后台计算后的结果，优惠券-原价 = money   x - old = 0.01
                            //设置属性
//                            tvMoney.setText(new SpanUtils().append("订单金额：")
//                                    .append("￥" + money).setForegroundColor(Color.parseColor("#ffa647"))
//                                    .create());
//                            tvYouhuiquan.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            String sub = MathUtils.sub(old_price, money, 2);
                            tvYouhuiquan.setText(new SpanUtils()
                                    .append("-￥" + sub).setForegroundColor(Color.parseColor("#ff3434"))
                                    .create());
//                            tvYouhuiquanNew.setText( " "+money );
                            tvWechatMoney.setText("¥" + money);
                            tvAlipayMoney.setText("¥" + money);
                            tvYueMoney.setText("¥" + money);
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                        if (respnse.getData() != null ) {
                            MyWalletBean data = (MyWalletBean)respnse.getData();
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
                        showTips("支付成功");
                        publishSuccess(type);
                        setResult(RESULT_CODE_SUCCESS);
                        finish();
                        Intent intent = new Intent(NewPayActivity.this, PaySuccessActivity.class);
                        intent.putExtra("type",CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI);
                        intent.putExtra("relate_id",relate_id);
                        startActivity(intent);

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

    /**
     * 发布成功后的吐司，根据type判断
     *
     * @param type
     */
    private void publishSuccess(int type) {
        //发布成功
        if (CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI == type) {
            showTips(msg);
        }else if (CommonPayPresenter.TYPE_LAOGONG_XINXI == type || //5.劳务中介-发布劳务工信息费用
                CommonPayPresenter.TYPE_TEZHONG_XINXI == type || //7.特种工- 发布特种工信息费用
                CommonPayPresenter.TYPE_BAOMU_XINXI == type || //9.发布我要当保姆信息费用
                CommonPayPresenter.TYPE_YUESAO_XINXI == type || //11.发布我要当月嫂信息费用
                CommonPayPresenter.TYPE_YUER_XINXI == type || //13.发布我当育儿嫂信息费用
                CommonPayPresenter.TYPE_JIAJIAO_XINXI == type || //15.发布我要当家教信息费用
                CommonPayPresenter.TYPE_QIUZHI == type || // 21.发布我要求职信息费
                CommonPayPresenter.TYPE_ZHONGDIANGONG == type  //23.发布钟点工信息费
        ){
            showTips("您的个人简历已经提交到人才驿站，匹配成功后，我们会发送通知消息给您，您还可以在“订单-已完成”中查看雇主信息。");
        }else if (CommonPayPresenter.TYPE_YINYUAN_DUIXIANG == type  ){//19.姻缘服务-发布我要找对象信息费用
            showTips("您的个人信息已经提交到人才驿站，匹配成功后，我们会发送通知消息给您，您还可以在“订单-已完成”中查看姻缘信息。");
        }
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
            publishSuccess(type);
            setResult(RESULT_CODE_SUCCESS);
            finish();
        } else if (event.getCode() == -1) {
            //失败

        } else if (event.getCode() == -2) {
            //取消

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE_SUCCESS) {
            coupon_id = data.getIntExtra("coupon_id", 0);
            useCoupon(coupon_id);
        }
    }


}
