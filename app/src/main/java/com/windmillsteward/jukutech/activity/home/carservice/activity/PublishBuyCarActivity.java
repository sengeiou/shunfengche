package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.PublishBuyCarActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BuyCarDetailBean;
import com.windmillsteward.jukutech.bean.CarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class PublishBuyCarActivity extends BaseActivity implements PublishBuyCarActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_title;
    private EditText et_price;
    private TextView tv_deal_area;
    private EditText et_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishBuyCarActivityPresenter presenter;

    private int deal_third_area_id;
    private int third_area_id;

    private BuyCarDetailBean bean;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_buycar);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (BuyCarDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initToolbar();
        presenter = new PublishBuyCarActivityPresenter(this);

        if (type==1 && bean!=null) {
            initData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.publish(getAccessToken(),et_title.getText().toString().trim(),et_price.getText().toString().trim(),
//                    getCurrCityId(),deal_third_area_id,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),
//                    et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
//        }
    }

    private void initData() {
        et_title.setText(bean.getTitle());
        et_price.setText(bean.getPrice());
        tv_deal_area.setText(bean.getDeal_third_area_name());
        deal_third_area_id = bean.getDeal_third_area_id();
        et_desc.setText(bean.getDetails());
        et_contacts.setText(bean.getContact_person());
        et_phone.setText(bean.getContact_tel());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("我要买车");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_title = (EditText) findViewById(R.id.et_title);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_deal_area = (TextView) findViewById(R.id.tv_deal_area);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        tv_deal_area.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请填写标题",0);
            return;
        }

        String price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            showTips("请填写预算价格",0);
            return;
        }

        String desc = et_desc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            showTips("请填写描述",0);
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入姓名",0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入手机号",0);
            return;
        }
        if (deal_third_area_id==0) {
            showTips("请选择交易地区",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }

        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.edit(getAccessToken(),bean.getBuy_car_id(),et_title.getText().toString().trim(),et_price.getText().toString().trim(),
                    getCurrCityId(),deal_third_area_id,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),
                    et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
        }
    }

    @Override
    public void loadDealAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_deal_area.setText(text);
                        deal_third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_publish_area.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void publishSuccess() {
        PublishSuccessActivity.go(this,11);
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.publish(getAccessToken(),et_title.getText().toString().trim(),et_price.getText().toString().trim(),
                    getCurrCityId(),deal_third_area_id,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),
                    et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布需求需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            PayActivity.go(PublishBuyCarActivity.this,101,0);
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.tv_deal_area:
                presenter.loadDealAreaData(getCurrCityId());
                break;
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:
                submit();
                break;
        }
    }
}
