package com.windmillsteward.jukutech.activity.home.insurance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentOutActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentOutView;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.PublishInsuranceActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.PublishEditTextActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/20/020
 * 作者：xjh
 */
public class PublishInsuranceActivity extends BaseActivity implements PublishInsuranceActivityView, View.OnClickListener {

    private static final int REQUEST_CODE_IMAGE = 100;
    private static final int REQUEST_CODE_NOTES = 101;
    private static final int REQUEST_CODE_DESC = 102;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_click_upload;
    private EditText tv_title;
    private TextView tv_insurance_type;
    private EditText et_insurance_company;
    private TextView tv_insurance_desc;
    private TextView tv_insurance_note;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishInsuranceActivityPresenter presenter;

    private ArrayList<String> pic_path = new ArrayList<>();
    private int insurance_type;
    private int third_area_id;
    private int type;
    private InsuranceDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_insurance);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (InsuranceDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initToolbar();
        presenter = new PublishInsuranceActivityPresenter(this);

        if (type==1 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_click_upload.setText("已选择"+bean.getPic_urls().size()+"张图片");
        pic_path.addAll(bean.getPic_urls());
        tv_title.setText(bean.getTitle());
        tv_insurance_type.setText(bean.getInsurance_type_name());
        insurance_type = bean.getInsurance_type();
        et_insurance_company.setText(bean.getCompany_name());
        tv_insurance_desc.setText(bean.getInsurance_introduce());
        tv_insurance_note.setText(bean.getInsurance_information());
        et_contacts.setText(bean.getContact_person());
        et_phone.setText(bean.getContact_mobile_phone());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布保险");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_click_upload = (TextView) findViewById(R.id.tv_click_upload);
        tv_title = (EditText) findViewById(R.id.tv_title);
        tv_insurance_type = (TextView) findViewById(R.id.tv_insurance_type);
        et_insurance_company = (EditText) findViewById(R.id.et_insurance_company);
        tv_insurance_desc = (TextView) findViewById(R.id.tv_insurance_desc);
        tv_insurance_note = (TextView) findViewById(R.id.tv_insurance_note);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        iv_pic.setOnClickListener(this);
        tv_insurance_type.setOnClickListener(this);
        tv_insurance_desc.setOnClickListener(this);
        tv_insurance_note.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        String title = tv_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",1);
            return;
        }
        if (insurance_type==0) {
            showTips("请选择险种",1);
            return;
        }
        String insurance_desc = tv_insurance_desc.getText().toString().trim();
        if (TextUtils.isEmpty(insurance_desc)) {
            showTips("请输保险介绍",1);
            return;
        }
        String insurance_note = tv_insurance_note.getText().toString().trim();
        if (TextUtils.isEmpty(insurance_note)) {
            showTips("请输投保须知",1);
            return;
        }
        String company = et_insurance_company.getText().toString().trim();
        if (TextUtils.isEmpty(company)) {
            showTips("请输入承保公司",1);
            return;
        }
        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入联系人",1);
            return;
        }
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输联系电话",1);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",1);
            return;
        }
        if (pic_path.size()==0) {
            showTips("请选择图片",1);
            return;
        }

        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.uploadPic(pic_path);
        }
    }

    @Override
    public void loadInsuranceListTypeSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_insurance_type.setText(text);
                        insurance_type = id;
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
    public void uploadPicSuccess(List<String> img_urls) {
        String title = tv_title.getText().toString().trim();
        String insurance_desc = tv_insurance_desc.getText().toString().trim();
        String insurance_note = tv_insurance_note.getText().toString().trim();
        String company = et_insurance_company.getText().toString().trim();
        String contacts = et_contacts.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        if (type==0) {
            presenter.publish(getAccessToken(),getCurrCityId(),third_area_id,insurance_type,contacts,phone,
                    title,insurance_desc,company,insurance_note,img_urls);
        } else {
            if (bean!=null) {
                presenter.edit(getAccessToken(),bean.getInsurance_id(),getCurrCityId(),third_area_id,insurance_type,contacts,phone,
                        title,insurance_desc,company,insurance_note,img_urls);
            }
        }
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.uploadPic(pic_path );
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布保险信息需要支付费用，继续吗")
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
        PublishSuccessActivity.go(this,8);
    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_pic:
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA,pic_path);
                startAtvDonFinishForResult(SelectPhotoActivity.class,REQUEST_CODE_IMAGE,bundle);
                break;
            case R.id.tv_insurance_type:
                presenter.loadInsuranceListTypeData();
                break;
            case R.id.tv_insurance_desc:
                PublishEditTextActivity.forResult(this,REQUEST_CODE_DESC,tv_insurance_desc.getText().toString().trim(),"保险介绍","请输入对保险产品的具体描述");
                break;
            case R.id.tv_insurance_note:
                PublishEditTextActivity.forResult(this,REQUEST_CODE_NOTES,tv_insurance_note.getText().toString().trim(),"投保须知","请输入投保须知的描述");
                break;
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:
                submit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode==REQUEST_CODE_IMAGE&&resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = intent.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList!=null && imageList.size()>0) {
                pic_path.clear();
                pic_path.addAll(imageList);
                tv_click_upload.setText("已选择"+imageList.size()+"张图片");
            } else {
                pic_path.clear();
                tv_click_upload.setText("点击上传图片");
            }
        } else if (requestCode==REQUEST_CODE_DESC&& resultCode == PublishEditTextActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras!=null) {
                tv_insurance_desc.setText(extras.getString(PublishEditTextActivity.RESULT_DATA));
            }
        } else if (requestCode==REQUEST_CODE_NOTES&& resultCode == PublishEditTextActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras!=null) {
                tv_insurance_note.setText(extras.getString(PublishEditTextActivity.RESULT_DATA));
            }
        }
//        else if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadPic(pic_path );
//        }
    }

}
