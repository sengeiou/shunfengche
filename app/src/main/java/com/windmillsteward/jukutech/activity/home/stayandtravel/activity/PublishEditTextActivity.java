package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.app.Activity;
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

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class PublishEditTextActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE = "TYPE";
    public static final String TEXT = "TEXT";
    public static final String MSG = "MSG";
    public static final String TITLE = "TITLE";
    public static final int RESULT_CODE = 200;
    public static final String RESULT_DATA = "RESULT_DATA";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_text;
    private int type;
    private String text;
    private String msg;
    private String title;

    /**
     * 跳转到编辑界面
     * @param activity activity
     */
    public static void forResult(Activity activity,int requestCode,String text,String title,String msg) {
        Intent intent = new Intent(activity, PublishEditTextActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(TEXT,text);
        bundle.putString(MSG,msg);
        bundle.putString(TITLE,title);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishedit);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
            text = extras.getString(TEXT, "");
            msg = extras.getString(MSG, "");
            title = extras.getString(TITLE, "");
        }
        initView();

        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText(title);
        toolbar_tv_right.setText("完成");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setOnClickListener(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_text = (EditText) findViewById(R.id.et_text);
        et_text.setText(text);
        et_text.setHint(msg);
    }

    private void submit() {
        String text = et_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            showTips("请输入内容",0);
            return;
        }

        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA,text);
        data.putExtras(bundle);
        setResult(RESULT_CODE, data);
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
