package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.think.activity.PublishIdeaThinkActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditIdeaThinkDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class EditIdeaThinkDetailActivity extends BaseActivity implements EditIdeaThinkDetailView, View.OnClickListener {
    public static final String REQUIRE_ID = "REQUIRE_ID";
    public static final String POSITION = "POSITION";
    public static final String PUBLISH_STATUS = "PUBLISH_STATUS";
    private static final int DELETE_CODE = 104;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_add_area;
    private TextView tv_add_time;
    private TextView tv_read_times;
    private ExpandTextView expand_desc;
    private TextView tv_hosted_id;
    private TextView tv_contact;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditIdeaThinkDetailPresenter presenter;
    private int require_id;
    private int position;
    private int publish_status;
    private String phone;

    private IdeaThinkDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ideathinkdetail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            require_id = extras.getInt(REQUIRE_ID);
            position = extras.getInt(POSITION);
            publish_status = extras.getInt(PUBLISH_STATUS);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new EditIdeaThinkDetailPresenter(this);
        presenter.initData(getAccessToken(), require_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_add_area = (TextView) findViewById(R.id.tv_add_area);
        tv_add_time = (TextView) findViewById(R.id.tv_add_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        linear_delete = (LinearLayout) findViewById(R.id.linear_delete);
        linear_edit = (LinearLayout) findViewById(R.id.linear_edit);
        LinearLayout linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
        TextView tv_edit = (TextView) findViewById(R.id.tv_edit);

        if (publish_status==0) {
            linear_bottom.setVisibility(View.GONE);
        } else if (publish_status==1){
            linear_bottom.setVisibility(View.VISIBLE);
            tv_edit.setText("编辑");
        } else if (publish_status==2) {
            linear_bottom.setVisibility(View.VISIBLE);
            tv_edit.setText("重新编辑");
        }
        linear_delete.setOnClickListener(this);
        linear_edit.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(IdeaThinkDetailBean bean) {
        tv_title.setText(bean.getTitle());
        tv_price.setText("￥" + bean.getPrice());
        tv_add_area.setText("发布于" + bean.getArea_name());
        tv_add_time.setText(DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_contact.setText(bean.getContact_person());
        tv_read_times.setText("浏览：" + bean.getView_num() + "次");
        expand_desc.setContent(bean.getDescription());
        tv_hosted_id.setText(bean.getHosting_show());
        phone = bean.getContact_tel();

        this.bean = bean;
    }


    @Override
    public void deleteIdeaThinkSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION,position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_delete:
                if (bean!=null) {
                    new AlertDialog(this).builder()
                            .setTitle("提示")
                            .setMsg("确定删除吗")
                            .setCancelable(true)
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    presenter.deleteRequire(getAccessToken(),bean.getRequire_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(PublishIdeaThinkActivity.TYPE,1);
                    bundle.putSerializable(PublishIdeaThinkActivity.DATA,bean);
                    startAtvDonFinish(PublishIdeaThinkActivity.class, bundle);
                }
                break;
        }
    }
}
