package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishSellCarActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditCarDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CarDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public class EditCarDetailActivity extends BaseActivity implements EditCarDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_license_time;
    private TextView tv_mileage;
    private ExpandTextView expand_desc;
    private ExpandTextView expand_detail_info;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditCarDetailActivityPresenter presenter;
    private CarDetailBean bean;
    private int car_id;
    private int publish_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cardtail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            car_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFlyBanner();
        presenter = new EditCarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),car_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) bean.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION,position);
                    startAtvDonFinish(PhotoViewActivity.class,bundle);
                }
            }
        });
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_license_time = (TextView) findViewById(R.id.tv_license_time);
        tv_mileage = (TextView) findViewById(R.id.tv_mileage);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        expand_detail_info = (ExpandTextView) findViewById(R.id.expand_detail_info);
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
    public void initDataSuccess(CarDetailBean bean) {
        this.bean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null && pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getName());
        tv_price.setText(bean.getPrice());
        tv_publish_time.setText("发布:"+ DateUtil.StampTimeToDate(String.valueOf(bean.getUpdate_time()),"yyyy-MM-dd"));
        tv_read_times.setText("浏览:"+bean.getView_num()+"次");
        tv_license_time.setText(bean.getLicense_time());
        tv_mileage.setText(bean.getMileage());
        expand_desc.setContent("品牌："+bean.getBrand_name()+"\n\n"+"车系列："+bean.getSeries_name()+"\n\n"+"车型："+bean.getVehicle_module_name()
            +"\n\n"+"下次验车："+bean.getNext_validate_time()+"\n\n"+"交强险到期："+bean.getCompulsory_insurance_time()+"\n\n"+"商业险到期："+bean.getCommercial_insurance_time()
            +"\n\n"+"过户费："+bean.getTransfer_fee_name()+"\n\n"+"车辆颜色："+bean.getCar_color()+"\n\n"+"联系人："+bean.getContact_person()+"\n\n"+"交易地点："+bean.getDeal_area_name());
        expand_detail_info.setContent(bean.getDetails());
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
                if (bean!=null) {
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
                                    presenter.delete(getAccessToken(),bean.getCar_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_TYPE,1);
                    bundle.putSerializable(Define.INTENT_DATA,bean);
                    startAtvDonFinish(PublishSellCarActivity.class, bundle);
                }
                break;
        }
    }
}
