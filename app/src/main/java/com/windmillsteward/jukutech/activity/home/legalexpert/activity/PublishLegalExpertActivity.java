package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.PublishFinancingActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.presenter.PublishLegalExpertActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.bean.LegalDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2018/4/10 0010.
 */

public class PublishLegalExpertActivity extends BaseActivity implements View.OnClickListener, PublishLegalExpertActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_title;
    private TextView tv_legal_expert_type;
    private EditText et_description;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishLegalExpertActivityPresenter presenter;
    private int legal_expert_type;
    private String legal_expert_name;
    private int third_area_id;

    private LegalDetailBean bean;
    private int type;
    private int initType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_legalexpert);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (LegalDetailBean) extras.getSerializable(Define.INTENT_DATA);

            initType = extras.getInt(Define.INTENT_DATA_TWO);
            legal_expert_type = extras.getInt(Define.INTENT_DATA_THREE);
            legal_expert_name = extras.getString(Define.INTENT_DATA_FOUR);
        }
        initView();
        initToolbar();
        presenter = new PublishLegalExpertActivityPresenter(this);

        if (type==1 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        et_title.setText(bean.getTitle());
        tv_legal_expert_type.setText(bean.getLegal_expert_type_name());
        legal_expert_type = bean.getLegal_expert_type();
        et_description.setText(bean.getDescription());
        et_contacts.setText(bean.getContact_person());
        et_phone.setText(bean.getContact_mobile_phone());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("填写需求");
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode == 200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                legal_expert_type = extras.getInt(Define.INTENT_DATA);
                legal_expert_name = extras.getString(Define.INTENT_DATA_TWO);
                tv_legal_expert_type.setText(legal_expert_name);
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.publish(getAccessToken(),getCurrCityId(),third_area_id,legal_expert_type,et_contacts.getText().toString().trim(),
//                    et_phone.getText().toString().trim(),et_title.getText().toString().trim(),et_description.getText().toString().trim());
//        }
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_title = (EditText) findViewById(R.id.et_title);
        tv_legal_expert_type = (TextView) findViewById(R.id.tv_legal_expert_type);
        et_description = (EditText) findViewById(R.id.et_description);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        tv_legal_expert_type.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);

        if (initType!=0) {
            tv_legal_expert_type.setText(legal_expert_name);
        }
    }

    private void submit() {
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请填写标题", Toast.LENGTH_SHORT).show();
            return;
        }

        String description = et_description.getText().toString().trim();
        if (TextUtils.isEmpty(description)) {
            Toast.makeText(this, "输入您要描述的需求", Toast.LENGTH_SHORT).show();
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (legal_expert_type==0) {
            showTips("请选择类型",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }
        if (type==0) {
            presenter.isCharge(getAccessToken(),90,0);
        } else {
            if (bean!=null) {
                presenter.edit(getAccessToken(),bean.getLegal_expert_id(),getCurrCityId(),third_area_id,legal_expert_type,et_contacts.getText().toString().trim(),
                        et_phone.getText().toString().trim(),et_title.getText().toString().trim(),et_description.getText().toString().trim());
            }
        }
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.tv_legal_expert_type:
                startAtvDonFinishForResult(LegalExpertClassActivity.class,100);
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
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
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
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.publish(getAccessToken(),getCurrCityId(),third_area_id,legal_expert_type,et_contacts.getText().toString().trim(),
                    et_phone.getText().toString().trim(),et_title.getText().toString().trim(),et_description.getText().toString().trim());
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
        startAtvAndFinish(PublishSuccessActivity.class);
    }
}