package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：编辑描述界面
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class CarOwnerDescActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private EditText et_desc;
    private String initData="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carowner_desc);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            initData = extras.getString(Define.INTENT_DATA,"");
        }
        initView();
        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("描述");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("完成");
        toolbar_tv_right.setOnClickListener(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_desc = (EditText) findViewById(R.id.et_desc);

        et_desc.setText(initData);
    }

    private void submit() {
        // validate
        String desc = et_desc.getText().toString().trim();
        if (TextUtils.isEmpty(desc) || desc.length()<10) {
            Toast.makeText(this, "请输入描述，至少10个字", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA,desc);
        data.putExtras(bundle);
        setResult(200, data);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_tv_right:
                submit();
                break;
        }
    }
}
