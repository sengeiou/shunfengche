package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.utils.StateButton;

/**
 * 描述：提交申请提现成功后的页面
 * author:cyq
 * 2018-03-06
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WithDrawSuccessActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private StateButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_success);
        initView();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        btn_back = (StateButton) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(this);
        toolbar_iv_back.setVisibility(View.GONE);
        toolbar_iv_title.setText("提交成功");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
