package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.ApplySaleRefundActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;
import java.util.Map;

/**
 * 描述：待发货退款
 * 时间：2018/5/5/005
 * 作者：xjh
 */
public class ApplyRefundActivity extends BaseActivity implements ApplySaleRefundActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_title;
    private TextView tv_model;
    private TextView tv_price;
    private TextView tv_number;
    private TextView tv_reason;
    private TextView tv_refund_way;
    private EditText et_user_remark;
    private TextView tv_apply;

    private String order_sn;
    private int order_id;
    private SpecialtyOrderDetailBean.CommodityListBean bean;

    private ApplySaleRefundActivityPresenter presenter;
    private int reason_id;
    private int number;
    private int refund_way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applyrefund_activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String json = extras.getString(Define.INTENT_DATA);
            order_sn = extras.getString(Define.INTENT_DATA_TWO);
            order_id = extras.getInt(Define.INTENT_DATA_THREE);
            bean = JSON.parseObject(json, SpecialtyOrderDetailBean.CommodityListBean.class);
            if (bean == null) {
                finish();
                return;
            }
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initData();

        presenter = new ApplySaleRefundActivityPresenter(this);
    }

    private void initData() {
        GlideUtil.show(this,bean.getCommodity_price(),iv_pic);
        tv_title.setText(bean.getCommodity_title());
        tv_model.setText("型号："+bean.getCommodity_model_name());
        tv_price.setText("￥"+bean.getCommodity_price());
        tv_number.setText("X"+bean.getCommodity_num());
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("退款");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_model = (TextView) findViewById(R.id.tv_model);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_reason = (TextView) findViewById(R.id.tv_reason);
        tv_refund_way = (TextView) findViewById(R.id.tv_refund_way);
        et_user_remark = (EditText) findViewById(R.id.et_user_remark);
        tv_apply = (TextView) findViewById(R.id.tv_apply);

        tv_reason.setOnClickListener(this);
        tv_refund_way.setOnClickListener(this);
        tv_apply.setOnClickListener(this);
    }

    private void submit() {
        String remark = et_user_remark.getText().toString().trim();

        if (reason_id==0) {
            showTips("请选择售后原因",0);
            return;
        }
        if (refund_way==0) {
            showTips("请选择退款方式",0);
            return;
        }
        presenter.apply(getAccessToken(),order_sn,bean.getOrder_commodity_id(),bean.getCommodity_id(),bean.getCommodity_num(),1,reason_id,refund_way,remark, null);
    }

    @Override
    public void loadAplyReasonDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_reason.setText(text);
                        reason_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadNumberDataSuccess(List<Map<String, Object>> maps) {

    }

    @Override
    public void loadRefundWayDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_refund_way.setText(text);
                        refund_way = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadPicSuccess(String pic_url) {

    }

    @Override
    public void applySuccess() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reason:
                presenter.loadAplyReasonData();
                break;
            case R.id.tv_refund_way:
                presenter.loadRefundWayData();
                break;
            case R.id.tv_apply:
                submit();
                break;
        }
    }
}
