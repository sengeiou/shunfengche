package com.windmillsteward.jukutech.activity.home.think.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.activity.CretePositionActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.PublishIdeaThinkPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;
import com.windmillsteward.jukutech.bean.PublishIdeaThinkResultBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class PublishIdeaThinkActivity extends BaseActivity implements PublishIdeaThinkView,View.OnClickListener {

    private static final int REQUEST_CLASS_CODE = 100;
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_class;
    private EditText et_title;
    private EditText et_price;
    private EditText et_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishIdeaThinkPresenter presenter;

    private int first_class_id;
    private int second_class_id;
    private int third_area_id;

    private int type;
    private IdeaThinkDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishideathink);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
            bean = (IdeaThinkDetailBean) extras.getSerializable(DATA);
        }

        initView();
        initToolbar();
        presenter = new PublishIdeaThinkPresenter(this);

        if (type!=0 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_class.setText(bean.getSecond_class_name());
        first_class_id = bean.getFirst_class_id();
        second_class_id = bean.getSecond_class_id();
        et_title.setText(bean.getTitle());
        et_price.setText(bean.getPrice());
        et_desc.setText(bean.getDescription());
        et_contacts.setText(bean.getContact_person());
        et_phone.setText(bean.getContact_tel());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CLASS_CODE && resultCode == ChoiceIdeaThinkClassActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                second_class_id = extras.getInt(ChoiceIdeaThinkClassActivity.RESULT_DATA_ID);
                first_class_id = extras.getInt(ChoiceIdeaThinkClassActivity.RESULT_DATA_FIRST_ID);
                tv_class.setText(extras.getString(ChoiceIdeaThinkClassActivity.RESULT_DATA_NAME));
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.publish(getAccessToken(),first_class_id,second_class_id,et_title.getText().toString().trim(),et_price.getText().toString().trim()
//                    ,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布需求");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_class = (TextView) findViewById(R.id.tv_class);
        et_title = (EditText) findViewById(R.id.et_title);
        et_price = (EditText) findViewById(R.id.et_price);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        tv_class.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        if (second_class_id==0) {
            showTips("请选择分类",0);
            return;
        }
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",0);
            return;
        }

        String price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            showTips("价格不能为空",0);
            return;
        }
        String desc = et_desc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            showTips("描述不能为空",0);
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入联系人",0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入联系电话",0);
            return;
        }

        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }

        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            if (bean!=null) {
                presenter.edit(bean.getRequire_id(),getAccessToken(),first_class_id,second_class_id,title,price,desc,contacts,phone,getCurrCityId(),third_area_id);
            }
        }
    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
            @Override
            public void onSelect(int id, int pos, String text) {
                third_area_id = id;
                tv_publish_area.setText(text);
            }
        }).show();
    }

    @Override
    public void publishSuccess(PublishIdeaThinkResultBean bean) {
        PublishSuccessActivity.go(this,3);
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.publish(getAccessToken(),first_class_id,second_class_id,et_title.getText().toString().trim(),et_price.getText().toString().trim()
                    ,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
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
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.tv_class:
                startAtvDonFinishForResult(ChoiceIdeaThinkClassActivity.class,REQUEST_CLASS_CODE);
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
