package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.AddAccountPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.utils.StateButton;

/**
 * 描述：添加支付宝
 * author:cyq
 * 2018-03-06
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class AddWithdrawAccountActivity extends BaseActivity implements View.OnClickListener, AddAccountView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private EditText et_name;
    private EditText et_account;
    private StateButton btn_add;

    private AddAccountPresenter presenter;

    private int account_type = 1;//1支付宝2银行卡

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_withdraw_account);
        initView();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        et_account = (EditText) findViewById(R.id.et_account);
        btn_add = (StateButton) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("添加支付宝");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请如实填写", Toast.LENGTH_SHORT).show();
            return;
        }

        String account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入支付宝账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (presenter == null) {
            presenter = new AddAccountPresenter(this);
        }
        presenter.addAccount(getAccessToken(),account_type, name, account);

    }

    @Override
    public void addAccountSuccess() {
        showTips("添加成功", 1);
        finish();
    }

    @Override
    public void addAccountFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
