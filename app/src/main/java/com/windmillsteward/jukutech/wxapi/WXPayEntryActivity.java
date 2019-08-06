package com.windmillsteward.jukutech.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.event.NotifyPayComplete;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ResUtils;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    private ImageView iv_pay;
    private TextView tv_pay;
    private TextView tv_time;
    private TextView tv_back;
    private TextView tv_back_btn;

    private BaseResp currResp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        setContentView(R.layout.pay_result);

        iv_pay = findViewById(R.id.iv_pay);
        tv_pay = findViewById(R.id.tv_pay);
        tv_back = findViewById(R.id.tv_back);
        tv_time = findViewById(R.id.tv_time);
        tv_back_btn = findViewById(R.id.tv_back_btn);

        api = WXAPIFactory.createWXAPI(this, Define.WX_APP_ID);
        api.handleIntent(getIntent(), this);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currResp != null && currResp.errCode == 0) {
                    EventBus.getDefault().post(new NotifyPayComplete(currResp.errCode, "恭喜您支付成功"));
                    finish();
                } else
                    finish();
            }
        });

        tv_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currResp != null && currResp.errCode == 0) {
                    EventBus.getDefault().post(new NotifyPayComplete(currResp.errCode, "恭喜您支付成功"));
                    finish();
                } else
                    finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            tv_time.setText(new SpanUtils().append(value + "s")
                    .setForegroundColor(ResUtils.getCommRed())
                    .append(" 后自动关闭页面")
                    .create());
        }

        @Override
        public void onFinish() {
            if (currResp != null && currResp.errCode == 0) {
                EventBus.getDefault().post(new NotifyPayComplete(currResp.errCode, "恭喜您支付成功"));
                finish();
            } else
                finish();
        }
    };


    @Override
    public void onReq(BaseReq req) {

    }

    private String msg;

    @Override
    public void onResp(final BaseResp resp) {
        currResp = resp;
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                //成功
                msg = "恭喜您支付成功";
                tv_pay.setText("恭喜您支付成功");
                iv_pay.setImageResource(R.mipmap.pay_success);
            } else if (resp.errCode == -1) {
                //支付失败
                msg = "很抱歉，支付失败";
                tv_pay.setText("很抱歉，支付失败");
                iv_pay.setImageResource(R.mipmap.pay_fail);
            } else if (resp.errCode == -2) {
                //取消支付
                msg = "支付已被取消";
                tv_pay.setText("支付已被取消");
                iv_pay.setImageResource(R.mipmap.pay_fail);
            }

            tv_time.setVisibility(View.VISIBLE);
            if (countDownTimer != null) {
                countDownTimer.start();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}