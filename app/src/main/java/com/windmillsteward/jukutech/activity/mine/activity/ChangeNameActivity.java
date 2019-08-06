package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.EditMyInfoImpl;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.List;
import java.util.Map;

/**
 * 描述：修改名字
 * author:cyq
 * 2018-03-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ChangeNameActivity extends BaseActivity implements View.OnClickListener, EditMyInfoView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private TextView tv_save;
    private EditText et_content;

    private String key;
    private String value;

    private EditMyInfoImpl editMyInfoImpl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_save = (TextView) findViewById(R.id.tv_save);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_content = (EditText) findViewById(R.id.et_content);

        toolbar_tv_right.setVisibility(View.GONE);

        handleBackEvent(toolbar_iv_back);

        tv_save.setOnClickListener(this);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString(Define.INTENT_DATA);
        value = bundle.getString(Define.INTENT_DATA_TWO);

        if (key.equals("nickname")) {
            toolbar_iv_title.setText("修改昵称");
        }
        et_content.setText(TextUtils.isEmpty(value) ? "" : value);
        et_content.setSelection(et_content.getText().toString().length());

        editMyInfoImpl = new EditMyInfoImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                String name = et_content.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    showTips("请输入名字", 1);
                    return;
                }
                editMyInfoImpl.editMyInfo(key, name);
                break;
        }
    }


    @Override
    public void editSuccess() {
        showTips("修改成功", 1);
        Intent intent = getIntent();
        intent.putExtra(Define.INTENT_DATA, et_content.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void editFailed(int code, String msg) {
        showTips(msg, 1);
    }


    @Override
    public void loadSexModuleSuccess(List<Map<String, Object>> maps) {
        //用不上
    }
}
