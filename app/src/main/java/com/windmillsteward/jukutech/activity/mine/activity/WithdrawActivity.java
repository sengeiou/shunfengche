package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.ApplyWithdrawPresenter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyWalletPresenter;
import com.windmillsteward.jukutech.activity.mine.presenter.WithdrawAccountPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.MyWalletBean;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;
import com.windmillsteward.jukutech.bean.WithdrawBean;
import com.windmillsteward.jukutech.customview.SingleSelectPopupWindow;
import com.windmillsteward.jukutech.utils.RegexChkUtil;
import com.windmillsteward.jukutech.utils.StateButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：提现页面
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WithdrawActivity extends BaseActivity implements MyWalletView, View.OnClickListener, ApplyWithdrawView,
        WithdrawAccountView, SingleSelectPopupWindow.DataCallBack, TextWatcher {


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView iv_pic;
    private TextView tv_pay_type;
    private TextView tv_account;
    private RelativeLayout lay_rl_account;
    private RelativeLayout lay_rl_data;
    private LinearLayout lay_ll_root;
    private EditText et_money;
    private TextView tv_money;
    private TextView tv_all_withdraw;
    private TextView tv_add;
    private StateButton btn_apply_withdraw;

    private MyWalletPresenter presenter;
    private ApplyWithdrawPresenter applyWithdrawPresenter;
    private WithdrawAccountPresenter withdrawAccountPresenter;

    private ArrayList<PublicSelectInfo> publicSelectList = new ArrayList<>();//提现列表
    private int account_id;//提现账号id

    private SingleSelectPopupWindow singleSelectPopupWindow;
    private double current_fee;//当前所能提取的金钱


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        initView();

    }


    private void initData() {
        presenter = new MyWalletPresenter(this);
        presenter.getBalance(getAccessToken());
        withdrawAccountPresenter = new WithdrawAccountPresenter(this);
        withdrawAccountPresenter.getWithdrawAccountList(getAccessToken());
    }


    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_pay_type = (TextView) findViewById(R.id.tv_pay_type);
        tv_account = (TextView) findViewById(R.id.tv_account);
        lay_ll_root = (LinearLayout) findViewById(R.id.lay_ll_root);
        lay_rl_account = (RelativeLayout) findViewById(R.id.lay_rl_account);
        lay_rl_data = (RelativeLayout) findViewById(R.id.lay_rl_data);
        et_money = (EditText) findViewById(R.id.et_money);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_all_withdraw = (TextView) findViewById(R.id.tv_all_withdraw);
        btn_apply_withdraw = (StateButton) findViewById(R.id.btn_apply_withdraw);

        btn_apply_withdraw.setOnClickListener(this);
        tv_all_withdraw.setOnClickListener(this);
        lay_rl_account.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("提现");

        et_money.addTextChangedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apply_withdraw:
                if (applyWithdrawPresenter == null) {
                    applyWithdrawPresenter = new ApplyWithdrawPresenter(this);
                }
                if (publicSelectList.size() == 0 || publicSelectList == null) {
                    showTips("请添加提现账号", 1);
                    return;
                }
                if (account_id == 0) {
                    return;
                }
                String money = et_money.getText().toString();

                if (RegexChkUtil.isNumeric(money)) {//只能输入整数和小数
                    if ("0".equals(money) || "0.0".equals(money) || "0.00".equals(money)) {
                        showTips("请输入正确的金额", 1);
                        return;
                    }
                    if (current_fee >= Double.parseDouble(money)) {
                        applyWithdrawPresenter.applyWithdraw(getAccessToken(), account_id, money);
                    } else {
                        showTips("请输入正确的金额", 1);
                    }
                } else {
                    showTips("请输入正确的金额", 1);
                }

                break;
            case R.id.lay_rl_account:
                if (publicSelectList != null) {
                    singleSelectPopupWindow = new SingleSelectPopupWindow(this, publicSelectList, this);
                    singleSelectPopupWindow.showAtLocation(lay_ll_root, Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.tv_all_withdraw:
                et_money.setText(tv_money.getText().toString());
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable edt) {
        String temp = edt.toString();
        int posDot = temp.indexOf(".");//只允许输入两位小数
        if (posDot <= 0) return;
        if (temp.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, posDot + 4);
        }
    }

    @Override
    public void click_item(int position, int id, int type, String values) {
        if (position == publicSelectList.size() - 1) {
            startAtvDonFinish(AddWithdrawAccountActivity.class);
        } else if (position == publicSelectList.size() - 2) {
            startAtvDonFinish(AddBankAccountActivity.class);
        } else {
            this.account_id = id;
            tv_account.setText(TextUtils.isEmpty(values) ? "" : values);
            if (type == 1) {
                iv_pic.setImageResource(R.mipmap.icon_new_round_zfb);
                tv_pay_type.setText("支付宝");
            } else {
                iv_pic.setImageResource(R.mipmap.icon_new_bank);
                tv_pay_type.setText("银行卡");
            }
        }
    }

    @Override
    public void getDataSuccess(MyWalletBean bean) {
        current_fee = bean.getCurrent_fee();
        tv_money.setText("" + current_fee);
    }

    @Override
    public void getDataFailed(int code, String msg) {
        showTips(msg, 1);
    }


    @Override
    public void applyWithdrawSuccess() {
        showTips("申请提现成功", 1);
        startAtvAndFinish(WithDrawSuccessActivity.class);
    }

    @Override
    public void applyWithdrawFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getWithdrawAccountListSuccess(List<WithdrawBean> list) {
        if (list == null) {
            return;
        }
        publicSelectList.clear();
        WithdrawBean aa = new WithdrawBean();
        aa.setAccount("请添加支付宝账号");
        aa.setType(0);
        WithdrawBean bb = new WithdrawBean();
        bb.setAccount("请添加银行卡账号");
        bb.setType(0);
        if (list.size() == 0) {
            tv_account.setText(" 请添加提现账号");
            lay_rl_data.setVisibility(View.INVISIBLE);
            tv_add.setText("请添加提现账号");
            iv_pic.setImageResource(0);
            list.add(list.size(), aa);
            list.add(list.size() - 1, bb);
            for (WithdrawBean listBean : list) {
                PublicSelectInfo info = new PublicSelectInfo();
                info.setType(listBean.getType());
                info.setId(listBean.getAccount_id());
                info.setName(listBean.getAccount());
                publicSelectList.add(info);
            }
        } else {
            list.add(list.size(), aa);
            list.add(list.size() - 1, bb);
            lay_rl_data.setVisibility(View.VISIBLE);
            tv_add.setText("提现到");
            for (WithdrawBean listBean : list) {
                PublicSelectInfo info = new PublicSelectInfo();
                info.setId(listBean.getAccount_id());
                info.setName(listBean.getAccount());
                info.setType(listBean.getType());
                publicSelectList.add(info);
            }
            String account = list.get(0).getAccount();
            int type = list.get(0).getType();
            tv_account.setText(TextUtils.isEmpty(account) ? "" : account);
            account_id = list.get(0).getAccount_id();
            if (type == 1) {
                iv_pic.setImageResource(R.mipmap.icon_payzhifub);
            } else {
                iv_pic.setImageResource(R.mipmap.icon_bank);
            }

        }
    }

    @Override
    public void getWithdrawAccountListFailed(String msg) {
        showTips(msg, 1);
    }
}
