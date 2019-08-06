package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.PublishFinancingActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.PublishInsuranceActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditCapitalDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class EditFinancingDetailActivity extends BaseActivity implements EditCapitalDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_yield_rate;
    private TextView tv_product_type_name;
    private TextView tv_minimum_amount;
    private TextView tv_deadline;
    private TextView tv_description;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditCapitalDetailActivityPresenter  presenter;
    private int capital_id;
    private int publish_status;
    private CapitalDetailBean detailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_financing_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            capital_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFlyBanner();
        presenter = new EditCapitalDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),capital_id);
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
        tv_yield_rate = (TextView) findViewById(R.id.tv_yield_rate);
        tv_product_type_name = (TextView) findViewById(R.id.tv_product_type_name);
        tv_minimum_amount = (TextView) findViewById(R.id.tv_minimum_amount);
        tv_deadline = (TextView) findViewById(R.id.tv_deadline);
        tv_description = (TextView) findViewById(R.id.tv_description);
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
    public void initDataSuccess(CapitalDetailBean bean) {
        this.detailBean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null && pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_yield_rate.setText(bean.getYield_rate()+"%");
        tv_product_type_name.setText(bean.getProduct_type_name());
        tv_minimum_amount.setText(bean.getMinimum_amount()+"元");
        tv_deadline.setText(bean.getDeadline()+"天");
        tv_description.setText(bean.getIntroduction());
        tv_hosted_id.setText(bean.getHosting_show());
    }

    @Override
    public void deleteSuccess() {
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                                    presenter.delete(getAccessToken(),detailBean.getCapital_id());
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
                    startAtvDonFinish(PublishFinancingActivity.class, bundle);
                }
                break;
        }
    }
}
