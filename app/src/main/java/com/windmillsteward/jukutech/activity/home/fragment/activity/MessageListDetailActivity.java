package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.MessageDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.MessageDetailBean;
import com.windmillsteward.jukutech.utils.DateUtil;

/**
 * 描述：消息列表详情
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageListDetailActivity extends BaseActivity implements MessageDetailView {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_title;
    private TextView tv_sender;
    private TextView tv_date;
    private TextView tv_content;

    private MessageDetailPresenter presenter;

    private String msg_id = "";//消息id

    public static void go(Context context, String msg_id) {
        Intent intent = new Intent(context, MessageListDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DETAIL_ID, msg_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list_detail);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_sender = (TextView) findViewById(R.id.tv_sender);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_content = (TextView) findViewById(R.id.tv_content);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("通知详情");

    }

    private void initData() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            msg_id = extras.getString(DETAIL_ID);
        }

        if (presenter == null) {
            presenter = new MessageDetailPresenter(this);
        }
        presenter.getMessageDetail(getAccessToken(), msg_id);

    }

    @Override
    public void getMessageDetailSuccess(MessageDetailBean bean) {
        if (bean == null) {
            return;
        }
        //标题
        String title = bean.getTitle();
        tv_title.setText(TextUtils.isEmpty(title) ? "" : title);
        //发送人
        String admin_name = bean.getAdmin_name();
        tv_sender.setText(TextUtils.isEmpty(admin_name) ? "" : admin_name);
        //时间
        String add_time = bean.getAdd_time();
        tv_date.setText(TextUtils.isEmpty(add_time) ? "" : DateUtil.StampTimeToDate(add_time, "yyyy-MM-dd HH:mm"));
        //内容
        String content = bean.getContent();
        tv_content.setText(TextUtils.isEmpty(content) ? "" : content);
    }

    @Override
    public void getMessageDetailFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
