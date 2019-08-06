package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.FeedBackPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.utils.StateButton;

/**
 * 描述：留言反馈
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener, FeedBackView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private EditText et_content;
    private EditText et_phone;
    private StateButton btn_commit;

    private FeedBackPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        et_content = (EditText) findViewById(R.id.et_content);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_commit = (StateButton) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
    }

    public void initData() {
        toolbar_iv_title.setText("留言反馈");
        String account = Hawk.get("account");
        et_phone.setText(TextUtils.isEmpty(account)?"":account);
        presenter = new FeedBackPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String content = et_content.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    showTips("请输入你的意见", 1);
                    return;
                }
                String phone = et_phone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showTips("请输入你的联系电话", 1);
                    return;
                }
                presenter.feedback(getAccessToken(),content, phone);
                break;
        }
    }

    @Override
    public void Success() {
        showTips("提交成功", 1);
        finish();
    }

    @Override
    public void Failed(int code, String msg) {
        showTips(msg, 1);
    }
}
