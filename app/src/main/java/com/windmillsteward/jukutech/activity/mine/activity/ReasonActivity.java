package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：审核不通过原因
 * 时间：2018/3/10/010
 * 作者：xjh
 */
public class ReasonActivity extends BaseActivity {

    public static final String TEXT = "TEXT";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);
        initView();
        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详细原因");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_reason = (TextView) findViewById(R.id.tv_reason);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            String text = extras.getString(TEXT);
            tv_reason.setText(text);
        } else {
            finish();
        }
    }
}
