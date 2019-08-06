package com.windmillsteward.jukutech.activity.home.capitalmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.presenter.PublishPresenter;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishBuyCarActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.LegalDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class PublishFinancingActivity extends BaseActivity implements PublishView, View.OnClickListener {

    private static final int REQUEST_CODE_IMAGE = 100;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_click_upload;
    private EditText et_title;
    private TextView tv_product_type;
    private EditText et_yield_rate;
    private EditText et_minimum_amount;
    private EditText et_deadline;
    private EditText et_introduction;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishPresenter presenter;

    private ArrayList<String> pic_path = new ArrayList<>();
    private int product_type;
    private int third_area_id;

    private CapitalDetailBean bean;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_financing);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (CapitalDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initToolbar();

        presenter = new PublishPresenter(this);
        if (type==1 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_click_upload.setText("已选择"+bean.getPic_urls().size()+"张图片");
        pic_path.addAll(bean.getPic_urls());
        et_title.setText(bean.getTitle());
        tv_product_type.setText(bean.getProduct_type_name());
        product_type = bean.getProduct_type();
        et_yield_rate.setText(bean.getYield_rate());
        et_minimum_amount.setText(bean.getMinimum_amount());
        et_deadline.setText(bean.getDeadline());
        et_introduction.setText(bean.getIntroduction());
        et_contacts.setText(bean.getNickname());
        et_phone.setText(bean.getContact_mobile_phone());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_IMAGE&&resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = data.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList!=null && imageList.size()>0) {
                pic_path.clear();
                pic_path.addAll(imageList);
                tv_click_upload.setText("已选择"+imageList.size()+"张图片");
            } else {
                pic_path.clear();
                tv_click_upload.setText("点击上传图片");
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadPic(pic_path);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布产品");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_click_upload = (TextView) findViewById(R.id.tv_click_upload);
        et_title = (EditText) findViewById(R.id.et_title);
        tv_product_type = (TextView) findViewById(R.id.tv_product_type);
        et_yield_rate = (EditText) findViewById(R.id.et_yield_rate);
        et_minimum_amount = (EditText) findViewById(R.id.et_minimum_amount);
        et_deadline = (EditText) findViewById(R.id.et_deadline);
        et_introduction = (EditText) findViewById(R.id.et_introduction);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        iv_pic.setOnClickListener(this);
        tv_product_type.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        if (pic_path.size()==0) {
            showTips("请选择图片",0);
            return;
        }
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入产品名称",0);
            return;
        }

        String rate = et_yield_rate.getText().toString().trim();
        if (TextUtils.isEmpty(rate)) {
            showTips("请输入七日年华收益率",0);
            return;
        }

        String amount = et_minimum_amount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            showTips("请输入金额",0);
            return;
        }

        String deadline = et_deadline.getText().toString().trim();
        if (TextUtils.isEmpty(deadline)) {
            showTips("请输入天数",0);
            return;
        }

        String introduction = et_introduction.getText().toString().trim();
        if (TextUtils.isEmpty(introduction)) {
            showTips("输入对产品的描述",0);
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
        if (product_type==0) {
            showTips("请选择产品类型",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }
        if (type==0) {
            presenter.isCharge(getAccessToken(),81,0);
        } else {
            presenter.uploadPic(pic_path);
        }
    }

    @Override
    public void loadProductTypeDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_product_type.setText(text);
                        product_type = id;
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
    public void uploadPicSuccess(String pic_url) {

    }

    @Override
    public void uploadPicSuccess(List<String> img_urls) {
        if (type==0) {
            presenter.publish(getAccessToken(),1,et_title.getText().toString().trim(),getCurrCityId(),third_area_id,et_yield_rate.getText().toString().trim(),et_minimum_amount.getText().toString().trim(),
                    et_phone.getText().toString().trim(),et_contacts.getText().toString().trim(),product_type,et_deadline.getText().toString().trim(),"","",img_urls,et_introduction.getText().toString().trim());
        } else {
            presenter.edit(getAccessToken(),bean.getCapital_id(),1,et_title.getText().toString().trim(),getCurrCityId(),third_area_id,et_yield_rate.getText().toString().trim(),et_minimum_amount.getText().toString().trim(),
                    et_phone.getText().toString().trim(),et_contacts.getText().toString().trim(),product_type,et_deadline.getText().toString().trim(),"","",img_urls,et_introduction.getText().toString().trim());
        }
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.uploadPic(pic_path);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("该发布需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public void publishSuccess() {
        PublishSuccessActivity.go(this,9);
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.iv_pic:
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA,pic_path);
                startAtvDonFinishForResult(SelectPhotoActivity.class,REQUEST_CODE_IMAGE,bundle);
                break;
            case R.id.tv_product_type:
                presenter.loadProductTypeData();
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
