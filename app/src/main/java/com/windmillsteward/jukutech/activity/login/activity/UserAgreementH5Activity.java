package com.windmillsteward.jukutech.activity.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：注册协议H5介绍
 * author:cyq
 * 2018-11-14
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class UserAgreementH5Activity extends BaseActivity   {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private WebView wv_content;


    public static void go(Context context, String html_url,String title) {
        Intent intent = new Intent(context, UserAgreementH5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA, html_url);
        bundle.putString(Define.INTENT_DATA_TWO, title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_h5);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        wv_content = (WebView) findViewById(R.id.wv_content);

        handleBackEvent(toolbar_iv_back);

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        String html_url = bundle.getString(Define.INTENT_DATA);
        String title = bundle.getString(Define.INTENT_DATA_TWO);

        toolbar_iv_title.setText(title);
        wv_content.loadData(html_url, "text/html;charset=utf-8", "UTF-8");
    }

}
