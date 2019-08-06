package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.MyPositionDetailPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.PositionDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.bean.PositionDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlowLayout;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：职位详情界面
 * 时间：2018/1/9
 * 作者：xjh
 */

public class MyPositionDetailActivity extends BaseActivity implements View.OnClickListener, MyPositionDetailView {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String POSITION = "POSITION";
    public static final int DELETE_CODE = 104;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private TextView tv_position_title;
    private TextView tv_price;
    private TextView tv_refresh_time;
    private TextView tv_read_times;
    private TextView tv_position_name;
    private TextView tv_work_year;
    private TextView tv_edu;
    private TextView tv_require;
    private FlowLayout fl_welfare_type;
    private TextView tv_work_area;
    private ExpandTextView expand_more;
    private TextView tv_company_name;
    private ImageView iv_approve_type;
    private TextView tv_contact;
    private TextView tv_call;
    private TextView tv_hosted_id;
    private ImageView toolbar_iv_right;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private int detailId;
    private int position;
    private int is_collect = -1;
    private PositionDetailBean detailBean;

    private MyPositionDetailPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypositiondetail);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        } else {
            detailId = extras.getInt(DETAIL_ID);
            position = extras.getInt(POSITION);
        }
        initView();
        initToolbar();

        presenter = new MyPositionDetailPresenter( this);
        presenter.initData(getAccessToken(), detailId);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
        toolbar_iv_right.setVisibility(View.VISIBLE);
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        toolbar_iv_right.setOnClickListener(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_position_title = (TextView) findViewById(R.id.tv_position_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_refresh_time = (TextView) findViewById(R.id.tv_refresh_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_position_name = (TextView) findViewById(R.id.tv_position_name);
        tv_work_year = (TextView) findViewById(R.id.tv_work_year);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_require = (TextView) findViewById(R.id.tv_require);
        fl_welfare_type = (FlowLayout) findViewById(R.id.fl_welfare_type);
        tv_work_area = (TextView) findViewById(R.id.tv_work_area);
        expand_more = (ExpandTextView) findViewById(R.id.expand_more);
        tv_company_name = (TextView) findViewById(R.id.tv_company_name);
        iv_approve_type = (ImageView) findViewById(R.id.iv_approve_type);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_call = (TextView) findViewById(R.id.tv_call);
        tv_call.setOnClickListener(this);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        linear_delete = (LinearLayout) findViewById(R.id.linear_delete);
        linear_edit = (LinearLayout) findViewById(R.id.linear_edit);

        linear_delete.setOnClickListener(this);
        linear_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_right:
                if (is_collect == 0) {   // 收藏
                    presenter.collection(getAccessToken(), detailId);
                } else if (is_collect == 1) {  // 取消收藏
                    presenter.cancelCollection(getAccessToken(), detailId);
                }
                break;
            case R.id.tv_call:
                if (detailBean != null) {
                    presenter.isContacCharge(getAccessToken(),detailId);
                }
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
                                    presenter.deletePosition(getAccessToken(),detailBean.getJob_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:

                break;

        }
    }

    @Override
    public void initDataSuccess(PositionDetailBean bean) {
        detailBean = bean;
        is_collect = bean.getIs_collect();
        tv_position_title.setText(bean.getTitle());
        tv_price.setText(bean.getSalary_show());
        tv_refresh_time.setText("更新：" + DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_read_times.setText("浏览：" + bean.getView_num()+"人");
        tv_position_name.setText("职位：" + bean.getJob_name());
        tv_work_year.setText("年限：" + bean.getWork_year_name());
        tv_edu.setText("学历：" + bean.getEducation_name());
        tv_require.setText("要求：" + bean.getRequire());
        List<String> list = bean.getBenefit_list();
        if (list != null) {
            for (String s : list) {
                View view = LayoutInflater.from(this).inflate(R.layout.view_welfare_type, fl_welfare_type, false);
                TextView tv_welfare_type = (TextView) view.findViewById(R.id.tv_welfare_type);
                tv_welfare_type.setText(s);
                fl_welfare_type.addView(view);
            }
        }
        tv_work_area.setText(bean.getWork_address());
        expand_more.setContent(bean.getDescription());
        tv_company_name.setText(bean.getCompany_name());
        if (bean.getIs_authent() == 0) {  // 未认证
            iv_approve_type.setImageResource(R.mipmap.icon_certified_n);
        } else {  // 认证过
            iv_approve_type.setImageResource(R.mipmap.icon_certified);
        }
        tv_contact.setText("联系人:" + bean.getContact_person());
        tv_hosted_id.setText(bean.getHosting_show());

        if (bean.getIs_collect() == 0) {  // 未收藏
            toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        } else {  // 收藏了
            toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
        }

    }

    /**
     * 收藏成功
     */
    @Override
    public void collectionSuccess() {
        is_collect = 1;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
    }

    /**
     * 取消收藏成功
     */
    @Override
    public void cancelCollectionSuccess() {
        is_collect = 0;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + detailBean.getContact_tel());
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void deletePositionSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION,position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }
}
