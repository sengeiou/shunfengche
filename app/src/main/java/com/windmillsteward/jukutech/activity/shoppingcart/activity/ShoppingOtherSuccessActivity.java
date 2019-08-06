package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.MyOrderActivity;
import com.windmillsteward.jukutech.activity.mine.activity.SpecialtyOrderListActivity;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class ShoppingOtherSuccessActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_back;
    private TextView tv_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_success);
        initView();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_order = (TextView) findViewById(R.id.tv_order);

        toolbar_iv_back.setVisibility(View.GONE);
        toolbar_iv_title.setText("支付成功");
        tv_back.setOnClickListener(this);
        tv_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_order:
                if (isLogin()) {
                    startAtvAndFinish(MyOrderActivity.class);
                }
                break;
        }
    }
}
