package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：首页banner外部链接H5介绍
 * author:cyq
 * 2018-05-19
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeBannerH5Activity extends BaseActivity   {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private WebView wv_content;


    public static void go(Context context, String html_url) {
        Intent intent = new Intent(context, HomeBannerH5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA, html_url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_banner_h5);
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


        toolbar_iv_title.setText("详情");

        wv_content.getSettings().setJavaScriptEnabled(true);
        wv_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }
        });
        wv_content.loadUrl(html_url);
    }

}
