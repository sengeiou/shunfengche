package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishCarOwnerActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishCarPassengerActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditRentCarDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.RentCarDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.DateUtil;

/**
 * 描述：
 * 时间：2018/4/3/003
 * 作者：xjh
 */
public class EditRentCarDetailActivity extends BaseActivity implements  EditRentCarDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_go_off;
    private TextView tv_vehicle_module_name;
    private TextView tve_unoccupied_num;
    private LinearLayout linear_type;
    private TextView tv_departure_place_name;
    private TextView tv_departure_place_address;
    private TextView tv_destination_place_name;
    private TextView tv_destination_place_address;
    private TextView tv_price;
    private TextView tv_pass_place_one;
    private TextView tv_pass_place_two;
    private TextView tv_remark;
    private TextView tv_update_time;
    private TextView tv_view_num;
    private LinearLayout linear_car_owner;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditRentCarDetailActivityPresenter presenter;
    private int car_rent_id;
    private RentCarDetailBean detailBean;
    private int publish_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rentcar_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            car_rent_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new EditRentCarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),car_rent_id);
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
        tv_go_off = (TextView) findViewById(R.id.tv_go_off);
        tv_vehicle_module_name = (TextView) findViewById(R.id.tv_vehicle_module_name);
        tve_unoccupied_num = (TextView) findViewById(R.id.tve_unoccupied_num);
        linear_type = (LinearLayout) findViewById(R.id.linear_type);
        tv_departure_place_name = (TextView) findViewById(R.id.tv_departure_place_name);
        tv_departure_place_address = (TextView) findViewById(R.id.tv_departure_place_address);
        tv_destination_place_name = (TextView) findViewById(R.id.tv_destination_place_name);
        tv_destination_place_address = (TextView) findViewById(R.id.tv_destination_place_address);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_pass_place_one = (TextView) findViewById(R.id.tv_pass_place_one);
        tv_pass_place_two = (TextView) findViewById(R.id.tv_pass_place_two);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        tv_view_num = (TextView) findViewById(R.id.tv_view_num);
        linear_car_owner = (LinearLayout) findViewById(R.id.linear_car_owner);
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
    public void initDataSuccess(RentCarDetailBean bean) {
        detailBean = bean;
        int type = bean.getType();
        if (type==1) {  // 车主
            linear_car_owner.setVisibility(View.VISIBLE);
            tv_pass_place_one.setText(bean.getPass_place_one());
            tv_pass_place_two.setText(bean.getPass_place_two());
            tv_price.setText("￥"+bean.getPrice());
            tv_vehicle_module_name.setText(bean.getVehicle_module_name());
            tve_unoccupied_num.setText("空余"+bean.getUnoccupied_num()+"座位");
        } else if (type==2) {  // 乘客
            linear_car_owner.setVisibility(View.GONE);
            linear_type.setVisibility(View.GONE);
            tv_price.setVisibility(View.GONE);
        }
        tv_go_off.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getGo_off()),"yyyy-MM-dd HH:mm"));
        tv_departure_place_name.setText(bean.getDeparture_place_title());
        tv_departure_place_address.setText(bean.getDeparture_place_name());
        tv_destination_place_name.setText(bean.getDestination_place_title());
        tv_destination_place_address.setText(bean.getDestination_place_name());
        tv_remark.setText(bean.getRemark());
        tv_update_time.setText(DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_view_num.setText(String.valueOf(bean.getView_num()));
        tv_hosted_id.setText(bean.getHosting_sn());

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
                                    presenter.delete(getAccessToken(),detailBean.getCar_rent_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (detailBean!=null) {
                    int type = detailBean.getType();
                    if (type==1) {  // 车主
                        Bundle bundle = new Bundle();
                        bundle.putInt(Define.INTENT_TYPE,1);
                        bundle.putSerializable(Define.INTENT_DATA,detailBean);
                        startAtvDonFinish(PublishCarOwnerActivity.class, bundle);
                    } else if (type==2) {  // 乘客
                        Bundle bundle = new Bundle();
                        bundle.putInt(Define.INTENT_TYPE,1);
                        bundle.putSerializable(Define.INTENT_DATA,detailBean);
                        startAtvDonFinish(PublishCarPassengerActivity.class, bundle);
                    }
                }
                break;
        }
    }
}
