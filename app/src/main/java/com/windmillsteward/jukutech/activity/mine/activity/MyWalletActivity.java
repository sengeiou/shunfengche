package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.MyWalletPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.bean.MyWalletBean;
import com.windmillsteward.jukutech.utils.StateButton;
import com.windmillsteward.jukutech.utils.UmengPushUtil;

/**
 * 描述：我的钱包-首页
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyWalletActivity extends BaseActivity implements View.OnClickListener, MyWalletView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private TextView tv_money;
    private StateButton btn_withdraw;
    private StateButton btn_manage;
    private StateButton btn_recharge;

    private MyWalletPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionBar.statusBarColor(R.color.bg_blue).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).keyboardEnable(true).init();

        setContentView(R.layout.activity_my_wallet);
        initView();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_money = (TextView) findViewById(R.id.tv_money);
        btn_withdraw = (StateButton) findViewById(R.id.btn_withdraw);
        btn_recharge = (StateButton) findViewById(R.id.btn_recharge);
        btn_manage = (StateButton) findViewById(R.id.btn_manage);

        btn_withdraw.setOnClickListener(this);
        btn_recharge.setOnClickListener(this);
        btn_manage.setOnClickListener(this);
        toolbar_tv_right.setOnClickListener(this);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("钱包");
        toolbar_tv_right.setText("明细");
        toolbar_tv_right.setVisibility(View.VISIBLE);
    }

    private void initData() {
        if (presenter == null){
            presenter = new MyWalletPresenter(this);
        }
        presenter.getBalance(getAccessToken());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_withdraw://提现
                startAtvDonFinish(WithdrawActivity.class);
                break;
            case R.id.btn_manage:
                startAtvDonFinish(ManageWithDrawActivity.class);
                break;
            case R.id.toolbar_tv_right:
                startAtvDonFinish(WalletListActivity.class);
                break;
            case R.id.btn_recharge://充值
                startAtvDonFinish(RechargeActivity.class);
                break;
        }
    }

    @Override
    public void getDataSuccess(MyWalletBean bean) {
        double current_fee = bean.getCurrent_fee();
        tv_money.setText(""+current_fee);
    }

    @Override
    public void getDataFailed(int code, String msg) {
        showTips(msg, 1);
    }


}
