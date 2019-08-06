package com.windmillsteward.jukutech.activity.home.houselease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexAreaActivity;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexStreetActivity;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.PublishBuyHousePresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.utils.StaticData;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.List;
import java.util.Map;

/**
 * 描述：发布买房
 * 时间：2018/2/1/001
 * 作者：xjh
 */
public class PublishBuyHouseActivity extends BaseActivity implements PublishBuyHouseView, View.OnClickListener {

    private static final int REQUEST_CODE_ADDRESS = 100;
    private static final int REQUEST_CODE_DESC = 101;
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_buyhouse_area;
    private EditText et_house_num;
    private EditText et_hall_num;
    private EditText et_floor_num;
    private EditText et_house_area;
    private EditText et_price;
    private TextView tv_cx;
    private TextView tv_fixture;
    private TextView tv_property_right;
    private EditText et_title;
    private TextView tv_house_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishBuyHousePresenter presenter;

    private int house_third_id;
    private int house_fourth_id;
    private String description;
    private int orientation = 1;
    private int decoration = 1;
    private int property_right = 70;
    private int third_area_id;

    private int type;
    private HouseDetailBeam bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishbuyhouse);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
            bean = ((HouseDetailBeam) extras.getSerializable(DATA));
        }
        initView();
        initToolbar();
        presenter = new PublishBuyHousePresenter(this);
        if (type != 0 && bean != null) {
            initData();
        }
    }

    private void initData() {
//        String[] cx = StaticData.getOrientation_text();
//        String[] fixture = StaticData.getDecoration_text();
//
//        tv_buyhouse_area.setText(bean.getHouse_third_name() + bean.getHouse_fourth_name());
//        house_third_id = bean.getHouse_third_id();
//        house_fourth_id = bean.getHouse_fourth_id();
//        et_house_num.setText(String.valueOf(bean.getHouse_rooms_num()));
//        et_hall_num.setText(String.valueOf(bean.getHouse_parlor_num()));
//        et_floor_num.setText(String.valueOf(bean.getFloor()));
//        et_house_area.setText(String.valueOf(bean.getFloor_area()));
//        et_price.setText(bean.getTotal_price());
//        tv_cx.setText(cx[bean.getOrientation() - 1]);
//        orientation = bean.getOrientation();
//        tv_fixture.setText(fixture[bean.getDecoration() - 1]);
//        decoration = bean.getDecoration();
//        tv_property_right.setText(bean.getProperty_right() + "年");
//        property_right = bean.getProperty_right();
//        et_title.setText(bean.getTitle());
//        tv_house_desc.setText(bean.getDescription());
//        description = bean.getDescription();
//        et_contacts.setText(bean.getContact_person());
//        et_phone.setText(bean.getContact_mobile_phone());
//        tv_publish_area.setText(bean.getThird_area_name());
//        third_area_id = bean.getThird_area_id();
//        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE_ADDRESS && resultCode == QuickIndexStreetActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                house_third_id = extras.getInt(QuickIndexStreetActivity.THIRD_ID);
                house_fourth_id = extras.getInt(QuickIndexStreetActivity.STREET_ID);
                String third_name = extras.getString(QuickIndexStreetActivity.THIRD_NAME);
                String street_name = extras.getString(QuickIndexStreetActivity.STREET_NAME);

                tv_buyhouse_area.setText(third_name + street_name);
            }
        } else if (requestCode == REQUEST_CODE_DESC && resultCode == HouseDescActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                description = extras.getString(HouseDescActivity.RESULT_DATA);
                tv_house_desc.setText(description);
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.publish(getAccessToken(), 2, getCurrCityId(), house_third_id, house_fourth_id, et_house_num.getText().toString().trim(), et_hall_num.getText().toString().trim(),
//                    et_floor_num.getText().toString().trim(), et_price.getText().toString().trim(),
//                    et_title.getText().toString().trim(), description, et_contacts.getText().toString().trim(), et_phone.getText().toString().trim(), getCurrCityId(), third_area_id,
//                    et_house_area.getText().toString().trim(), orientation, decoration, null, null, null,
//                    0, property_right, null, null, null, 0);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("求购");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_buyhouse_area = (TextView) findViewById(R.id.tv_buyhouse_area);
        et_house_num = (EditText) findViewById(R.id.et_house_num);
        et_hall_num = (EditText) findViewById(R.id.et_hall_num);
        et_floor_num = (EditText) findViewById(R.id.et_floor_num);
        et_house_area = (EditText) findViewById(R.id.et_house_area);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_cx = (TextView) findViewById(R.id.tv_cx);
        tv_fixture = (TextView) findViewById(R.id.tv_fixture);
        tv_property_right = (TextView) findViewById(R.id.tv_property_right);
        et_title = (EditText) findViewById(R.id.et_title);
        tv_house_desc = (TextView) findViewById(R.id.tv_house_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        tv_buyhouse_area.setOnClickListener(this);
        tv_cx.setOnClickListener(this);
        tv_fixture.setOnClickListener(this);
        tv_property_right.setOnClickListener(this);
        tv_house_desc.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        if (house_fourth_id == 0) {
            showTips("请选择购房区域", 0);
            return;
        }
        String house_num = et_house_num.getText().toString().trim();
        if (TextUtils.isEmpty(house_num)) {
            showTips("请输入房间数", 0);
            return;
        }

        String hall_num = et_hall_num.getText().toString().trim();
        if (TextUtils.isEmpty(hall_num)) {
            showTips("请输入客厅数", 0);
            return;
        }

        String floor_num = et_floor_num.getText().toString().trim();
        if (TextUtils.isEmpty(floor_num)) {
            showTips("请输入楼层数", 0);
            return;
        }

        String area = et_house_area.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            showTips("请输入面积", 0);
            return;
        }

        String price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            showTips("请输入价格", 0);
            return;
        }

        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题", 0);
            return;
        }
        if (title.length() < 8) {
            showTips("标题长度在8-28字之间", 0);
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入联系人", 0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入手机号", 0);
            return;
        }

        if (TextUtils.isEmpty(description)) {
            showTips("请输入描述", 0);
            return;
        }

        if (type == 0) {
            presenter.isCharge(getAccessToken(), 0);
        } else {
            if (bean != null) {
                presenter.edit(bean.getHouse_id(), getAccessToken(), 2, getCurrCityId(), house_third_id, house_fourth_id, house_num, hall_num, floor_num, price,
                        title, description, contacts, phone, getCurrCityId(), third_area_id, area, orientation, decoration, null, null, null,
                        0, property_right, null, null, null, 0);
            }
        }

    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.tv_buyhouse_area:
                startAtvDonFinishForResult(QuickIndexAreaActivity.class, REQUEST_CODE_ADDRESS);
                break;
            case R.id.tv_cx:
                presenter.loadCXData();
                break;
            case R.id.tv_fixture:
                presenter.loadFixtureData();
                break;
            case R.id.tv_property_right:
                presenter.loadPropertyRightData();
                break;
            case R.id.tv_house_desc:
                Bundle bundle = new Bundle();
                bundle.putString(HouseDescActivity.RESULT_DATA, description);
                startAtvDonFinishForResult(HouseDescActivity.class, REQUEST_CODE_DESC, bundle);
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
    public void loadCXDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_cx.setText(text);
                        orientation = id;
                    }
                })
                .show();
    }

    @Override
    public void loadFixtureDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_fixture.setText(text);
                        decoration = id;
                    }
                })
                .show();
    }

    @Override
    public void loadPropertyRightDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_property_right.setText(text);
                        property_right = id;
                    }
                })
                .show();
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
    public void publishSuccess(String data) {
        PublishSuccessActivity.go(this,14);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge() == 0) {
            presenter.publish(getAccessToken(), 2, getCurrCityId(), house_third_id, house_fourth_id, et_house_num.getText().toString().trim(), et_hall_num.getText().toString().trim(),
                    et_floor_num.getText().toString().trim(), et_price.getText().toString().trim(),
                    et_title.getText().toString().trim(), description, et_contacts.getText().toString().trim(), et_phone.getText().toString().trim(), getCurrCityId(), third_area_id,
                    et_house_area.getText().toString().trim(), orientation, decoration, null, null, null,
                    0, property_right, null, null, null, 0);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布买房需要支付费用，继续吗")
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
