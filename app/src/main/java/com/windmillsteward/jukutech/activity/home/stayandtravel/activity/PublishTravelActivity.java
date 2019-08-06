package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexCityActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.PublishTravelPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.PublishTravelResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：发布旅游
 * 时间：2018/1/26
 * 作者：xjh
 */

public class PublishTravelActivity extends BaseActivity implements PublishTravelView,View.OnClickListener {

    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private static final int REQUEST_CODE_IMAGE = 100;
    private static final int REQUEST_CODE_START_AREA = 101;
    private static final int REQUEST_CODE_PRODUCT_TC = 102;
    private static final int REQUEST_CODE_NOTES = 103;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private EditText et_title;
    private TextView tv_class;
    private TextView tv_travel_area;
    private TextView tv_out_area;
    private EditText et_price;
    private TextView tv_product_tc;
    private TextView tv_notes;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;
    private TextView tv_click_upload;

    private PublishTravelPresenter presenter;
    private ArrayList<String> imgs;
    private int travel_class_id=-1;
    private int travel_AREA_id=-1;
    private int start_area_id=-1;
    private String product_tc="";
    private String notes="";
    private int publish_area_id=-1;
    private String title;
    private String start_price;
    private String contact_tel;
    private String contact_person;

    private int type;
    private TravelDetailBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishtravel);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
            bean = (TravelDetailBean) extras.getSerializable(DATA);
        }
        initView();
        initToolbar();

        presenter = new PublishTravelPresenter(this);
        imgs = new ArrayList<>();
        if (type!=0) {
            initData();
        }
    }

    private void initData() {
        if (bean==null) {
            return;
        }
        tv_click_upload.setText("已选择"+bean.getPic_urls().size()+"张图片");
        imgs.addAll(bean.getPic_urls());
        et_title.setText(bean.getTitle());
        title = bean.getTitle();
        tv_class.setText(bean.getTravel_class_name());
        travel_class_id = bean.getTravel_class_id();
        tv_travel_area.setText(bean.getTravel_area_name());
        travel_AREA_id = bean.getTravel_area_id();
        tv_out_area.setText(bean.getGo_area_name());
        start_area_id = bean.getGo_area_id();
        et_price.setText(bean.getStart_price());
        start_price = bean.getStart_price();
        tv_product_tc.setText(bean.getDescription());
        product_tc = bean.getDescription();
        tv_notes.setText(bean.getNotes());
        notes = bean.getNotes();
        et_contacts.setText(bean.getContact_person());
        contact_person = bean.getContact_person();
        et_phone.setText(bean.getContact_tel());
        contact_tel = bean.getContact_tel();
        tv_publish_area.setText(bean.getThird_area_name());
        publish_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    private void initToolbar() {
        mImmersionBar.keyboardEnable(true).init();
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布旅游");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        et_title = (EditText) findViewById(R.id.et_title);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_travel_area = (TextView) findViewById(R.id.tv_travel_area);
        tv_out_area = (TextView) findViewById(R.id.tv_out_area);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_product_tc = (TextView) findViewById(R.id.tv_product_tc);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_click_upload = (TextView) findViewById(R.id.tv_click_upload);

        iv_pic.setOnClickListener(this);
        tv_class.setOnClickListener(this);
        tv_travel_area.setOnClickListener(this);
        tv_out_area.setOnClickListener(this);
        tv_product_tc.setOnClickListener(this);
        tv_notes.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        if (imgs==null || imgs.size()==0) {
            showTips("请选择图片",0);
            return;
        }
        title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",0);
            return;
        }
        start_price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(start_price)) {
            showTips("请输入价格",0);
            return;
        }
        contact_person = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contact_person)) {
            showTips("请输入联系人",0);
            return;
        }
        contact_tel = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(contact_tel)) {
            showTips("请输入联系电话",0);
            return;
        }
        if (travel_class_id==-1){
            showTips("请选择旅游类型",0);
            return;
        }
        if (travel_AREA_id==-1) {
            showTips("请选择旅游地区",0);
            return;
        }
        if (start_area_id==-1) {
            showTips("请选择出发城市",0);
            return;
        }
        if (TextUtils.isEmpty(product_tc)) {
            showTips("请输入产品特色",0);
            return;
        }
        if (TextUtils.isEmpty(notes)) {
            showTips("请输入须知",0);
            return;
        }
        if (publish_area_id==-1) {
            showTips("请选择发布地区",0);
            return;
        }
        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.uploadImages(imgs);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_IMAGE&&resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = data.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList!=null && imageList.size()>0) {
                imgs.clear();
                imgs.addAll(imageList);
                tv_click_upload.setText("已选择"+imageList.size()+"张图片");
            } else {
                imgs.clear();
                tv_click_upload.setText("点击上传图片");
            }
        } else if (requestCode==REQUEST_CODE_START_AREA && resultCode == QuickIndexCityActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                start_area_id = extras.getInt(QuickIndexCityActivity.ID);
                tv_out_area.setText(extras.getString(QuickIndexCityActivity.TEXT));
            }
        } else if (requestCode==REQUEST_CODE_PRODUCT_TC && resultCode == PublishEditTextActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                product_tc = extras.getString(PublishEditTextActivity.RESULT_DATA);
                tv_product_tc.setText(product_tc);
            }
        } else if (requestCode==REQUEST_CODE_NOTES && resultCode==PublishEditTextActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                notes = extras.getString(PublishEditTextActivity.RESULT_DATA);
                tv_notes.setText(notes);
            }
        }
//        else if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadImages(imgs);
//        }
    }

    @Override
    public void onClick(View v) {

        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.iv_pic:
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA,imgs);
                startAtvDonFinishForResult(SelectPhotoActivity.class,REQUEST_CODE_IMAGE,bundle);
                break;
            case R.id.tv_class:  // 选择旅游分类
                presenter.loadTravelClass();
                break;
            case R.id.tv_travel_area:
                presenter.loadTravelArea();
                break;
            case R.id.tv_out_area: // 跳转到出发地选择
                startAtvDonFinishForResult(QuickIndexCityActivity.class,REQUEST_CODE_START_AREA);
                break;
            case R.id.tv_product_tc:  // 跳转到产品特色
                PublishEditTextActivity.forResult(this,REQUEST_CODE_PRODUCT_TC,product_tc,"产品介绍","请输入产品介绍");
                break;
            case R.id.tv_notes:  // 跳转到须知
                PublishEditTextActivity.forResult(this,REQUEST_CODE_NOTES,notes,"须知","请输入详细须知事项");
                break;
            case R.id.tv_publish_area:  // 弹窗选择发布地区
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:  // 发布
                submit();
                break;
        }
    }

    @Override
    public void loadTravelClassSuccess(List<Map<String,Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_class.setText(text);
                        travel_class_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadTravelAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_travel_area.setText(text);
                        travel_AREA_id = id;
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
                        publish_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadFileSuccess(FileUploadResultBean bean) {
        List<String> fileUrls = bean.getFileUrls();
        if (!fileUrls.isEmpty()) {
            if (type==0) {
                presenter.publish(getAccessToken(),fileUrls,title,travel_class_id,travel_AREA_id,start_area_id,start_price,product_tc,notes,contact_tel,contact_person,getCurrCityId(),publish_area_id);
            } else {
                presenter.edit(this.bean.getTravel_id(),getAccessToken(),fileUrls,title,travel_class_id,travel_AREA_id,start_area_id,start_price,product_tc,notes,contact_tel,contact_person,getCurrCityId(),publish_area_id);
            }
        } else {
            showTips("提交失败！",0);
            dismiss();
        }
    }

    @Override
    public void publishSuccess(PublishTravelResultBean bean) {
        PublishSuccessActivity.go(this,7);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.uploadImages(imgs);
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
}
