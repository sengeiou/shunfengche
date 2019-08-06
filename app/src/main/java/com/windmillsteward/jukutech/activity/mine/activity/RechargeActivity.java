package com.windmillsteward.jukutech.activity.mine.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.bean.event.NotifyPayComplete;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.PayResult;
import com.windmillsteward.jukutech.utils.StateButton;
import com.windmillsteward.jukutech.utils.UmengPushUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 描述：充值页面
 * author:cyq
 * 2018-11-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */
public class RechargeActivity extends BaseInitActivity {

    public static final int REQUEST_CODE = 1025;
    public static final int RESULT_CODE_SUCCESS = 250;
    public static final int SDK_PAY_FLAG = 2;
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
    @Bind(R.id.btn_recharge)
    StateButton btnRecharge;
    @Bind(R.id.et_price)
    EditText etPrice;

    private CommonPayPresenter.PAY_TYPE pay_type;


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
    public BasePresenter getPresenter() {
        return new CommonPayPresenter(this);
    }

    @Override
    protected void initData() {
        pay_type = CommonPayPresenter.PAY_TYPE.ALI_PAY;
    }


    @Override
    protected void initView(View view) {
        setMainTitle("充值");
        showContentView();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.ll_alipay_container, R.id.ll_weichat_container, R.id.btn_recharge})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_recharge:
                getData();
                break;
            case R.id.ll_weichat_container:
                pay_type = CommonPayPresenter.PAY_TYPE.WECHAT;
                reset();
                ivWechatFlag.setImageResource(R.mipmap.select);
                break;
            case R.id.ll_alipay_container:
                pay_type = CommonPayPresenter.PAY_TYPE.ALI_PAY;
                reset();
                ivAlipayFlag.setImageResource(R.mipmap.select);
                break;
        }
    }
    /**
     * 获取数据
     */
    private void getData() {
        String price = etPrice.getText().toString();
        if (TextUtils.isEmpty(price)){
            showTips("请输入充值金额");
            return;
        }
        showDialog();
        ((CommonPayPresenter) presenter).walletRecharge(price, pay_type, new CommonPayPresenter.DataCallBack<PayInfoModel>() {
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
                                PayTask alipay = new PayTask(RechargeActivity.this);
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
                        final IWXAPI msgApi = WXAPIFactory.createWXAPI(RechargeActivity.this, null);
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
                    }
                }
            }
        });
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
                        showTips("充值成功");
                        setResult(RESULT_CODE_SUCCESS);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showTips("充值失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    //重置按钮
    private void reset() {
        ivAlipayFlag.setImageResource(R.mipmap.unselect);
        ivWechatFlag.setImageResource(R.mipmap.unselect);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notifyPayComplete(NotifyPayComplete event) {
        showTips(event.getMsg());
        if (event.getCode() == 0) {
            //成功
            setResult(RESULT_CODE_SUCCESS);
            finish();
        } else if (event.getCode() == -1) {
            //失败

        } else if (event.getCode() == -2) {
            //取消

        }
    }

}
