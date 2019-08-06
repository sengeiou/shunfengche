package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.insurance.activity.PublishInsuranceActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.PublishLegalExpertActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditLegalExpertActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.LegalDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class EditLegalExpertActivity extends BaseActivity implements EditLegalExpertActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_title;
    private TextView tv_class;
    private TextView tv_time;
    private ExpandTextView expand_desc;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditLegalExpertActivityPresenter presenter;
    private int publish_status;
    private int legal_expert_id;
    private LegalDetailBean detailBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_legal);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            legal_expert_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();

        presenter = new EditLegalExpertActivityPresenter(this);
        presenter.initData(getAccessToken(),legal_expert_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("法律咨询详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_time = (TextView) findViewById(R.id.tv_time);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
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
    public void initDataSuccess(LegalDetailBean bean) {
        this.detailBean = bean;
        tv_title.setText(bean.getTitle());
        tv_class.setText("类别："+bean.getLegal_expert_type_name());
        tv_time.setText("发布："+DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        expand_desc.setContent(bean.getDescription());
        tv_hosted_id.setText(bean.getHosting_show());
    }

    @Override
    public void deleteIdeaThinkSuccess() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_delete:
                if (detailBean!=null) {
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
                                    presenter.delete(getAccessToken(),detailBean.getLegal_expert_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (detailBean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_TYPE,1);
                    bundle.putSerializable(Define.INTENT_DATA,detailBean);
                    startAtvDonFinish(PublishLegalExpertActivity.class, bundle);
                }
                break;
        }
    }
}
