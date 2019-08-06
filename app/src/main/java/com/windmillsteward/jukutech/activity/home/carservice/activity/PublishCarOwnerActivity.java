package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.bigkoo.pickerview.TimePickerView;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.PublishCarOwnerActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BuyCarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/3.
 */

public class PublishCarOwnerActivity extends BaseActivity implements View.OnClickListener,PublishCarOwnerActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_departure_place;
    private TextView tv_destination_place;
    private TextView tv_go_off;
    private TextView tv_unoccupied_num;
    private EditText et_price;
    private TextView tv_vehicle_module_name;
    private TextView tv_pass_place_one;
    private TextView tv_pass_place_two;
    private TextView tv_remark;
    private EditText et_contact_person;
    private EditText et_contact_tel;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private String departure_longitude;
    private String departure_latitude;
    private String departure_place_name;
    private String departure_place_address;
    private String destination_longitude;
    private String destination_latitude;
    private String destination_place_name;
    private String destination_place_address;
    private int go_off;
    private int unoccupied_num;
    private String vehicle_module_name;
    private String pass_place_one;
    private String pass_place_two;
    private String remark;
    private int third_area_id;

    private PublishCarOwnerActivityPresenter presenter;

    private RentCarDetailBean bean;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_carowner);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (RentCarDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initTolbar();

        presenter = new PublishCarOwnerActivityPresenter(this);
        if (type==1 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_departure_place.setText(bean.getDeparture_place_title());
        departure_place_name = bean.getDeparture_place_title();
        departure_place_address = bean.getDeparture_place_name();
        departure_longitude = bean.getDeparture_longitude();
        departure_latitude = bean.getDeparture_latitude();
        tv_destination_place.setText(bean.getDestination_place_title());
        destination_place_name = bean.getDestination_place_title();
        destination_place_address = bean.getDestination_place_name();
        destination_longitude = bean.getDestination_longitude();
        destination_latitude = bean.getDestination_latitude();
        tv_go_off.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getGo_off()), "MM-dd HH:mm"));
        go_off = bean.getGo_off();
        tv_unoccupied_num.setText(String.valueOf(bean.getUnoccupied_num()));
        unoccupied_num = bean.getUnoccupied_num();
        et_price.setText(bean.getPrice());
        tv_vehicle_module_name.setText(bean.getVehicle_module_name());
        vehicle_module_name = bean.getVehicle_module_name();
        tv_pass_place_one.setText(bean.getPass_place_one());
        pass_place_one = bean.getPass_place_one();
        tv_pass_place_two.setText(bean.getPass_place_two());
        pass_place_two = bean.getPass_place_two();
        tv_remark.setText(bean.getRemark());
        remark = bean.getRemark();
        et_contact_person.setText(bean.getContact_person());
        et_contact_tel.setText(bean.getContact_tel());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_departure_place.setText(extras.getString(Define.INTENT_DATA));
                departure_place_name = extras.getString(Define.INTENT_DATA);
                departure_place_address = extras.getString(Define.INTENT_DATA_THREE);
                LatLng latLng = extras.getParcelable(Define.INTENT_DATA_TWO);
                if (latLng!=null) {
                    departure_longitude = String.valueOf(latLng.longitude);
                    departure_latitude = String.valueOf(latLng.latitude);
                }
            }
        } else if (requestCode==102 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_destination_place.setText(extras.getString(Define.INTENT_DATA));
                destination_place_name = extras.getString(Define.INTENT_DATA);
                destination_place_address = extras.getString(Define.INTENT_DATA_THREE);
                LatLng latLng = extras.getParcelable(Define.INTENT_DATA_TWO);
                if (latLng!=null) {
                    destination_longitude = String.valueOf(latLng.longitude);
                    destination_latitude = String.valueOf(latLng.latitude);
                }
            }
        } else if (requestCode==103 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_pass_place_one.setText(extras.getString(Define.INTENT_DATA));
                pass_place_one = extras.getString(Define.INTENT_DATA);
            }
        } else if (requestCode==104 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_pass_place_two.setText(extras.getString(Define.INTENT_DATA));
                pass_place_two = extras.getString(Define.INTENT_DATA);
            }
        } else if (requestCode==105 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_remark.setText(extras.getString(Define.INTENT_DATA));
                remark = extras.getString(Define.INTENT_DATA);
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.publish(getAccessToken(),departure_longitude,departure_latitude,departure_place_address,departure_place_name,destination_longitude,destination_latitude,destination_place_address,destination_place_name,
//                    go_off,unoccupied_num,et_price.getText().toString().trim(),vehicle_module_name,pass_place_one,pass_place_two,remark,
//                    et_contact_person.getText().toString().trim(),et_contact_tel.getText().toString().trim(),getCurrCityId(),third_area_id);
//        }
    }

    private void initTolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("我是车主");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_departure_place = (TextView) findViewById(R.id.tv_departure_place);
        tv_destination_place = (TextView) findViewById(R.id.tv_destination_place);
        tv_go_off = (TextView) findViewById(R.id.tv_go_off);
        tv_unoccupied_num = (TextView) findViewById(R.id.tv_unoccupied_num);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_vehicle_module_name = (TextView) findViewById(R.id.tv_vehicle_module_name);
        tv_pass_place_one = (TextView) findViewById(R.id.tv_pass_place_one);
        tv_pass_place_two = (TextView) findViewById(R.id.tv_pass_place_two);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        et_contact_person = (EditText) findViewById(R.id.et_contact_person);
        et_contact_tel = (EditText) findViewById(R.id.et_contact_tel);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_departure_place.setOnClickListener(this);
        tv_destination_place.setOnClickListener(this);
        tv_go_off.setOnClickListener(this);
        tv_unoccupied_num.setOnClickListener(this);
        tv_vehicle_module_name.setOnClickListener(this);
        tv_pass_place_one.setOnClickListener(this);
        tv_pass_place_two.setOnClickListener(this);
        tv_remark.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        String price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            showTips("请输入价格",0);
            return;
        }

        String person = et_contact_person.getText().toString().trim();
        if (TextUtils.isEmpty(person)) {
            showTips("请输入联系人",0);
            return;
        }

        String tel = et_contact_tel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            showTips("请输入联系电话",0);
            return;
        }
        if (TextUtils.isEmpty(departure_place_name)) {
            showTips("请选择出发地",0);
            return;
        }
        if (TextUtils.isEmpty(destination_place_name)) {
            showTips("请选择目的地",0);
            return;
        }
        if (go_off==0) {
            showTips("请选择出发时间",0);
            return;
        }
        if (unoccupied_num==0) {
            showTips("请选择空余座位数",0);
            return;
        }
        if (TextUtils.isEmpty(vehicle_module_name)) {
            showTips("请选择车辆类型",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }
        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.publish(getAccessToken(),departure_longitude,departure_latitude,departure_place_address,departure_place_name,destination_longitude,destination_latitude,destination_place_address,destination_place_name,
                    go_off,unoccupied_num,et_price.getText().toString().trim(),vehicle_module_name,pass_place_one,pass_place_two,remark,
                    et_contact_person.getText().toString().trim(),et_contact_tel.getText().toString().trim(),getCurrCityId(),third_area_id);
        }
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.tv_departure_place:
                Intent intent1 = new Intent(this, PoiIndexListActivity.class);
                startActivityForResult(intent1, 101);
                break;
            case R.id.tv_destination_place:
                Intent intent = new Intent(this, PoiIndexListActivity.class);
                startActivityForResult(intent, 102);
                break;
            case R.id.tv_go_off:
                TimePickerView compulsory = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        go_off = (int) (date.getTime()/1000);
                        String dd = DateUtil.getYY_MM_DD(date, "MM-dd HH:mm");
                        tv_go_off.setText(dd);
                    }
                })
                        .setType(new boolean[]{false, true, true, true, true, false})
                        .build();
                compulsory.setDate(Calendar.getInstance());
                compulsory.show();
                break;
            case R.id.tv_unoccupied_num:
                presenter.loadUnoccupiedNumData();
                break;
            case R.id.tv_vehicle_module_name:
                presenter.loadVehicleModuleName();
                break;
            case R.id.tv_pass_place_one:
                Intent intent2 = new Intent(this, PoiIndexListActivity.class);
                startActivityForResult(intent2, 103);
                break;
            case R.id.tv_pass_place_two:
                Intent intent4 = new Intent(this, PoiIndexListActivity.class);
                startActivityForResult(intent4, 104);
                break;
            case R.id.tv_remark:
                Intent intent5 = new Intent(this, CarOwnerDescActivity.class);
                Bundle extras = new Bundle();
                extras.putString(Define.INTENT_DATA,remark);
                intent5.putExtras(extras);
                startActivityForResult(intent5, 105);
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
    public void loadUnoccupiedNumDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_unoccupied_num.setText(text);
                        unoccupied_num = id;
                    }
                })
                .show();
    }

    @Override
    public void loadVehicleModuleNameSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_vehicle_module_name.setText(text);
                        vehicle_module_name = text;
                    }
                })
                .show();
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.publish(getAccessToken(),departure_longitude,departure_latitude,departure_place_address,departure_place_name,destination_longitude,destination_latitude,destination_place_address,destination_place_name,
                    go_off,unoccupied_num,et_price.getText().toString().trim(),vehicle_module_name,pass_place_one,pass_place_two,remark,
                    et_contact_person.getText().toString().trim(),et_contact_tel.getText().toString().trim(),getCurrCityId(),third_area_id);
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
        PublishSuccessActivity.go(this,12);
    }
}
