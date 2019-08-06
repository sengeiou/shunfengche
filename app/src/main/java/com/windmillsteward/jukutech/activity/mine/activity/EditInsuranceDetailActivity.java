package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.PublishInsuranceActivity;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.InsuranceDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.home.think.activity.PublishIdeaThinkActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditInsuranceDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class EditInsuranceDetailActivity extends BaseActivity implements View.OnClickListener, EditInsuranceDetailActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_company_name;
    private TextView tv_insurance_type_name;
    private TextView tv_contact_person;
    private TextView tv_features;
    private View view_features;
    private FrameLayout fl_features;
    private TextView tv_notes;
    private View view_notes;
    private FrameLayout fl_notes;
    private TextView tv_text;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private int insurance_id;
    private int publish_status;
    private EditInsuranceDetailActivityPresenter presenter;
    private InsuranceDetailBean detailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_insurance_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            insurance_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFeatures_or_notes();
        initFlyBanner();

        presenter = new EditInsuranceDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),insurance_id);
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) detailBean.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION,position);
                    startAtvDonFinish(PhotoViewActivity.class,bundle);
                }
            }
        });
    }

    private void initFeatures_or_notes() {
        fl_features.setOnClickListener(this);
        fl_notes.setOnClickListener(this);

        tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
        view_features.setVisibility(View.VISIBLE);
        tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
        view_notes.setVisibility(View.INVISIBLE);
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
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_company_name = (TextView) findViewById(R.id.tv_company_name);
        tv_insurance_type_name = (TextView) findViewById(R.id.tv_insurance_type_name);
        tv_contact_person = (TextView) findViewById(R.id.tv_contact_person);
        tv_features = (TextView) findViewById(R.id.tv_features);
        view_features = (View) findViewById(R.id.view_features);
        fl_features = (FrameLayout) findViewById(R.id.fl_features);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        view_notes = (View) findViewById(R.id.view_notes);
        fl_notes = (FrameLayout) findViewById(R.id.fl_notes);
        tv_text = (TextView) findViewById(R.id.tv_text);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_features:
                if (detailBean!=null) {
                    tv_text.setText(detailBean.getInsurance_introduce());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_features.setVisibility(View.VISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_notes.setVisibility(View.INVISIBLE);
                break;
            case R.id.fl_notes:
                if (detailBean!=null) {
                    tv_text.setText(detailBean.getInsurance_information());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_features.setVisibility(View.INVISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_notes.setVisibility(View.VISIBLE);
                break;
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
                                    presenter.delete(getAccessToken(),detailBean.getInsurance_id());
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
                    startAtvDonFinish(PublishInsuranceActivity.class, bundle);
                }
                break;
        }
    }

    @Override
    public void initDataSuccess(InsuranceDetailBean bean) {
        this.detailBean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null&& pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getTitle());
        tv_publish_time.setText("发布："+DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_read_times.setText("浏览："+bean.getView_num()+"次");
        tv_company_name.setText("承保公司："+bean.getCompany_name());
        tv_insurance_type_name.setText("险种："+bean.getInsurance_type_name());
        tv_contact_person.setText("联系人："+bean.getContact_person());
        tv_text.setText(detailBean.getInsurance_introduce());
        tv_hosted_id.setText(bean.getHosting_show());
    }

    @Override
    public void deleteIdeaThinkSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        data.putExtras(bundle);
        finish();
    }
}
