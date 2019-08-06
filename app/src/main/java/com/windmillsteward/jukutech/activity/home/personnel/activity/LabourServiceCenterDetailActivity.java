package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.LabourServiceDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.LabourServiceDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;

/**
 * 描述：劳务详情
 * author:cyq
 * 2018-07-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class LabourServiceCenterDetailActivity extends BaseActivity implements LabourServiceDetailView,View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_position_title;
    private TextView tv_price;
    private TextView tv_refresh_time;
    private TextView tv_read_times;
    private TextView tv_position_name;
    private TextView tv_work_year;
    private TextView tv_edu;
    private TextView tv_require;
    private TextView tv_work_area;
    private ExpandTextView expand_more;
    private TextView tv_contact;
    private TextView tv_call;
    private TextView tv_hosted_id;
    private TextView tv_apply;

    private int detailId;
    private int position;
    private int is_collect=-1;
    private LabourServiceDetailBean detailBean;

    private boolean isCall;

    private LabourServiceDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_service_detail);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras==null) {
            finish();
        } else {
            detailId = extras.getInt(DETAIL_ID);
            position = extras.getInt(Define.POSITION,-1);
        }
        presenter= new LabourServiceDetailPresenter(this);
        presenter.initData(detailId);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_position_title = (TextView) findViewById(R.id.tv_position_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_refresh_time = (TextView) findViewById(R.id.tv_refresh_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_position_name = (TextView) findViewById(R.id.tv_position_name);
        tv_work_year = (TextView) findViewById(R.id.tv_work_year);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_require = (TextView) findViewById(R.id.tv_require);
        tv_work_area = (TextView) findViewById(R.id.tv_work_area);
        expand_more = (ExpandTextView) findViewById(R.id.expand_more);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_call = (TextView) findViewById(R.id.tv_call);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_apply = (TextView) findViewById(R.id.tv_apply);

        handleBackEvent(toolbar_iv_back);


    }

    private void initToolbar() {
        toolbar_iv_title.setText("详情");
        toolbar_iv_right.setVisibility(View.VISIBLE);
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        toolbar_iv_right.setOnClickListener(this);
        tv_call.setOnClickListener(this);
        tv_apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_iv_right:
                if (isLogin()) {
                    if (is_collect==0) {   // 收藏
                        presenter.collection(getAccessToken(),detailId);
                    } else if (is_collect == 1){  // 取消收藏
                        presenter.cancelCollection(getAccessToken(),detailId);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.tv_call:
                if (isLogin()) {
                    if (detailBean!=null) {
//                        presenter.isContacCharge(getAccessToken(),detailId);
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + detailBean.getContact_tel());
                        intent.setData(data);
                        startActivity(intent);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.tv_apply:
                if (isLogin()) {
                    if (detailBean!=null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(LabourServiceCenterDetailActivity.DETAIL_ID, detailId);
                        intent.putExtras(bundle);
                        startAtvDonFinish(ApplyLabourServiceActivity.class, bundle);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void initDataSuccess(LabourServiceDetailBean bean) {
        detailBean = bean;
        is_collect = bean.getIs_collect();
        tv_position_title.setText(bean.getTitle());
        tv_price.setText(bean.getStart_salary() +" ~ "+ bean.getEnd_salary()+"元");
        tv_refresh_time.setText("更新："+ DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_read_times.setText("浏览："+bean.getView_num()+"人");
        tv_position_name.setText("职位："+bean.getJob_name());
        tv_work_year.setText("年限："+bean.getWork_year_name());
        tv_edu.setText("学历："+bean.getEducation_name());
        tv_work_area.setText(bean.getAddress());
        expand_more.setContent(bean.getDescription());

        tv_contact.setText("联系人:"+bean.getContact_person());
//        tv_hosted_id.setText(bean.getHosting_show());

        if (bean.getIs_collect()==0) {  // 未收藏
            toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        } else {  // 收藏了
            toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
        }

        // 已经申请了该职位
        if (bean.getIs_application()==1) {
            tv_apply.setText("已申请");
            tv_apply.setEnabled(false);
        }
        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + detailBean.getContact_tel());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    @Override
    public void collectionSuccess() {
        showTips("收藏成功",1);
        is_collect = 1;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
    }

    @Override
    public void cancelCollectionSuccess() {
        showTips("取消收藏",1);
        is_collect = 0;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        // 直接拨打
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + detailBean.getContact_tel());
            intent.setData(data);
            startActivity(intent);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("拨打电话需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            PayActivity.go(PositionDetailActivity.this,23,detailId);
                        }
                    })
                    .show();
        }
    }
}
