package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.ModuleInstructionPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：模块介绍
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ModuleInstructionActivity extends BaseActivity implements ModuleInstructionView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private WebView wv_content;

    private ModuleInstructionPresenter presenter;

    public static void go(Context context, String name, String html_url) {
        Intent intent = new Intent(context, ModuleInstructionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA, name);
        bundle.putString(Define.INTENT_DATA_TWO, html_url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_instruction);
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
        String name = bundle.getString(Define.INTENT_DATA);
        String html_url = bundle.getString(Define.INTENT_DATA_TWO);
        toolbar_iv_title.setText(name);
        wv_content.loadData(html_url, "text/html;charset=utf-8", "UTF-8");

//        presenter = new ModuleInstructionPresenter(this);
//        presenter.getHtmlUrl(type);
    }

    @Override
    public void getHtmlUrlSuccess(String html_url) {
        wv_content.loadUrl(html_url);
    }

    @Override
    public void getHtmlUrlFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
