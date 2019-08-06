package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishBuyHouseActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentInActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentOutActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishSellHouseActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditHouseDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.StaticData;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：卖房详情
 * 时间：2018/2/5
 * 作者：xjh
 */

public class EditHouseDetailActivity extends BaseActivity implements EditHouseDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String CLASS_TYPE = "CLASS_TYPE";
    public static final String POSITION = "POSITION";
    public static final String PUBLISH_STATUS = "PUBLISH_STATUS";
    private static final int DELETE_CODE = 104;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_add_time;
    private TextView tv_read_times;
    private TextView tv_house_type;
    private TextView tv_price;
    private TextView tv_community;
    private TextView tv_floor_area;
    private TextView tv_floor;
    private TextView tv_cx;
    private TextView tv_decoration;
    private TextView tv_type;
    private TextView tv_property;
    private TextView tv_degree;
    private TextView tv_developer;
    private TextView tv_contact;
    private TextView tv_address;
    private ExpandTextView expand_desc;
    private TextView tv_hosted_id;
    private LinearLayout linear_rentout;
    private TextView tv_total_price;
    private LinearLayout linear_out;
    private FlowLayout fll_house_config;
    private TextView tv_price_type;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditHouseDetailPresenter presenter;
    private HouseDetailBeam detailBeam;
    private int detail_id;
    private int type;
    private int position;
    private int publish_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sellhousedetail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detail_id = extras.getInt(DETAIL_ID);
            type = extras.getInt(CLASS_TYPE);
            position = extras.getInt(POSITION);
            publish_status = extras.getInt(PUBLISH_STATUS);
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();
        initFlyBanner();
        presenter = new EditHouseDetailPresenter(this);
        presenter.initData(detail_id, getAccessToken());
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
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add_time = (TextView) findViewById(R.id.tv_add_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_house_type = (TextView) findViewById(R.id.tv_house_type);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_community = (TextView) findViewById(R.id.tv_community);
        tv_floor_area = (TextView) findViewById(R.id.tv_floor_area);
        tv_floor = (TextView) findViewById(R.id.tv_floor);
        tv_cx = (TextView) findViewById(R.id.tv_cx);
        tv_decoration = (TextView) findViewById(R.id.tv_decoration);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_property = (TextView) findViewById(R.id.tv_property);
        tv_degree = (TextView) findViewById(R.id.tv_degree);
        tv_developer = (TextView) findViewById(R.id.tv_developer);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_address = (TextView) findViewById(R.id.tv_address);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        linear_rentout = (LinearLayout) findViewById(R.id.linear_rentout);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        linear_out = (LinearLayout) findViewById(R.id.linear_out);
        fll_house_config = (FlowLayout) findViewById(R.id.fll_house_config);
        tv_price_type = (TextView) findViewById(R.id.tv_price_type);
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


        if (type == 1) {  // 卖房
            fll_house_config.setVisibility(View.GONE);
            linear_out.setVisibility(View.VISIBLE);
            tv_total_price.setVisibility(View.GONE);
            linear_rentout.setVisibility(View.GONE);
            tv_price_type.setText("售价");
        } else if (type == 2) {  // 买房
            fll_house_config.setVisibility(View.GONE);
            flyBanner.setVisibility(View.GONE);
            tv_total_price.setVisibility(View.VISIBLE);
            linear_rentout.setVisibility(View.GONE);
            linear_out.setVisibility(View.GONE);

            tv_degree.setVisibility(View.GONE);
            tv_contact.setVisibility(View.GONE);
            tv_address.setVisibility(View.GONE);
        } else if (type == 3) {
            fll_house_config.setVisibility(View.VISIBLE);
            tv_total_price.setVisibility(View.GONE);
            tv_contact.setVisibility(View.GONE);
            tv_address.setVisibility(View.GONE);
            tv_price_type.setText("租金");
        } else if (type == 4) {
            fll_house_config.setVisibility(View.GONE);
            flyBanner.setVisibility(View.GONE);
            tv_total_price.setVisibility(View.VISIBLE);
            linear_rentout.setVisibility(View.GONE);
            linear_out.setVisibility(View.GONE);

            tv_type.setVisibility(View.GONE);
            tv_property.setVisibility(View.GONE);
            tv_degree.setVisibility(View.GONE);
            tv_developer.setVisibility(View.GONE);
            tv_contact.setVisibility(View.GONE);
            tv_address.setVisibility(View.GONE);
        }

        linear_out.requestLayout();
    }

    @Override
    public void initDataSuccess(HouseDetailBeam beam) {

        int orientation = beam.getOrientation();
        String[] orientation_text = StaticData.getOrientation_text();
        int decoration = beam.getDecoration();
        String[] decoration_text = StaticData.getDecoration_text();
        int property_right = beam.getProperty_right();
        int rent_deposit_type = beam.getRent_deposit_type();
        String[] rent_deposit_text = StaticData.getRent_deposit_text();
        int school_degree_type = beam.getSchool_degree_type();
        String[] school_degree_text = StaticData.getSchool_degree_text();
        String add_time = DateUtil.StampTimeToDate(String.valueOf(beam.getAdd_time()), "yyyy-MM-dd");
        if (type == 1) {
            List<String> pic_urls = beam.getPic_urls();
            if (pic_urls != null && pic_urls.size() > 0) {
                flyBanner.setImagesUrl(pic_urls);
            } else {
                flyBanner.setVisibility(View.GONE);
            }
            presenter.loadRentTypeDataF(2);
            tv_title.setText(beam.getTitle());
            tv_add_time.setText("发布于：" + add_time);
            tv_read_times.setText("浏览：" + beam.getView_num() + "次");
            tv_house_type.setText(beam.getHouse_rooms() + "房" + beam.getHouse_parlor() + "厅");
            tv_price.setText(beam.getTotal_price() + "万元");
            tv_community.setText("小区：" + beam.getCommunity_name());
            tv_floor_area.setText("面积：" + beam.getFloor_area() + "m²");
            tv_floor.setText("楼层：" + beam.getFloor() + "楼");
            tv_cx.setText("朝向：" + orientation_text[orientation - 1]);
            tv_decoration.setText("装修：" + decoration_text[decoration - 1]);
            tv_property.setText("产权：" + property_right + "年");

            tv_degree.setText("学位：" + school_degree_text[school_degree_type - 1]);
            tv_developer.setText("开发商：" + beam.getDevelopers_name());
            tv_contact.setText("联系人：" + beam.getContact_person());
            tv_address.setText("地址：" + beam.getHouse_third_name() + " " + beam.getHouse_fourth_name());
            expand_desc.setContent(beam.getDescription());
//            tv_hosted_id.setText(beam.getHosting_show());
        } else if (type == 2) {
            tv_title.setText(beam.getTitle());
            tv_total_price.setText(beam.getTotal_price() + "万元");
            tv_add_time.setText("发布于：" + add_time);
            tv_read_times.setText("浏览：" + beam.getView_num() + "次");

            tv_community.setText("户型：" + beam.getHouse_rooms() + "房" + beam.getHouse_parlor() + "厅");
            tv_floor_area.setText("面积：" + beam.getFloor_area() + "m²");
            tv_floor.setText("楼层：" + beam.getFloor() + "楼");
            tv_cx.setText("朝向：" + orientation_text[orientation - 1]);
            tv_decoration.setText("装修：" + decoration_text[decoration - 1]);
            tv_type.setText("产权：" + property_right + "年");
            tv_property.setText("联系人：" + beam.getContact_person());
            tv_developer.setText("购买区域" + beam.getHouse_third_name() + " " + beam.getHouse_fourth_name());

            expand_desc.setContent(beam.getDescription());
//            tv_hosted_id.setText(beam.getHosting_show());
        } else if (type == 3) {
            List<String> pic_urls = beam.getPic_urls();
            if (pic_urls != null && pic_urls.size() > 0) {
                flyBanner.setImagesUrl(pic_urls);
            } else {
                flyBanner.setVisibility(View.GONE);
            }
            presenter.loadRentTypeDataF(1);
            tv_title.setText(beam.getTitle());
            tv_add_time.setText("发布于：" + add_time);
            tv_read_times.setText("浏览：" + beam.getView_num() + "次");
            tv_house_type.setText(beam.getHouse_rooms() + "房" + beam.getHouse_parlor() + "厅");
            tv_price.setText(beam.getTotal_price() + "元/月");
            tv_community.setText("小区：" + beam.getCommunity_name());
            tv_floor_area.setText("面积：" + beam.getFloor_area() + "m²");
            tv_floor.setText("楼层：" + beam.getFloor() + "楼");
            tv_cx.setText("朝向：" + orientation_text[orientation - 1]);
            tv_decoration.setText("装修：" + decoration_text[decoration - 1]);
            tv_property.setText("押金：" + rent_deposit_text[rent_deposit_type - 1]);
            tv_degree.setText("联系人：" + beam.getContact_person());
            tv_developer.setText("房屋地址：" + beam.getHouse_third_name() + " " + beam.getHouse_fourth_name());

            expand_desc.setContent(beam.getDescription());
//            tv_hosted_id.setText(beam.getHosting_show());
//            List<HouseDetailBeam.HouseConfigIdsBean> house_config_ids = beam.getHouse_config_ids();

            LayoutInflater inflater = LayoutInflater.from(this);
//            if (house_config_ids != null) {
//                for (HouseDetailBeam.HouseConfigIdsBean config_id : house_config_ids) {
//                    View view = inflater.inflate(R.layout.view_house_config, null);
//                    TextView tv_house_config = (TextView) view.findViewById(R.id.tv_house_config);
//                    tv_house_config.setText(config_id.getName());
//                    ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
//                    x.image().bind(iv_icon,config_id.getImg());
//                    fll_house_config.addView(view);
//                }
//            }
        } else if (type == 4) {
            tv_title.setText(beam.getTitle());
            tv_total_price.setText(beam.getTotal_price() + "元/月");
            tv_add_time.setText("发布于：" + add_time);
            tv_read_times.setText("浏览：" + beam.getView_num() + "次");

            tv_community.setText("户型：" + beam.getHouse_rooms() + "房" + beam.getHouse_parlor() + "厅");
            tv_floor_area.setText("面积：" + beam.getFloor_area() + "m²");
            tv_floor.setText("楼层：" + beam.getFloor() + "楼");
            tv_cx.setText("联系人：" + beam.getContact_person());
            tv_decoration.setText("求租区域：" + beam.getHouse_third_name() + " " + beam.getHouse_fourth_name());

            expand_desc.setContent(beam.getDescription());
//            tv_hosted_id.setText(beam.getHosting_show());
        }

        detailBeam = beam;
    }

    @Override
    public void loadRentTypeDataSuccessF(HouseMoreBean bean) {

//        if (detailBeam!=null) {
//            int house_type = detailBeam.getHouse_type();
//            List<HouseMoreBean.HouseTypeListBean> house_type_list = bean.getHouse_type_list();
//            if (house_type_list!=null) {
//                for (HouseMoreBean.HouseTypeListBean listBean : house_type_list) {
//                    if (house_type==listBean.getHouse_type_id()) {
//                        tv_type.setText("类型：" + listBean.getHouse_type_name());
//                    }
//                }
//            }
//        }
    }

    @Override
    public void deleteSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION,position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBeam != null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) detailBeam.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                    startAtvDonFinish(PhotoViewActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_delete:
                if (detailBeam!=null) {
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
                                    presenter.delete(getAccessToken(),detail_id);
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (detailBeam==null) {
                    return;
                }
                Bundle bundle = new Bundle();
                switch (type){
                    case 1:
                        bundle.putInt(PublishSellHouseActivity.TYPE, 1);
                        bundle.putSerializable(PublishSellHouseActivity.DATA, detailBeam);
                        startAtvDonFinish(PublishSellHouseActivity.class, bundle);
                        break;
                    case 2:
                        bundle.putInt(PublishBuyHouseActivity.TYPE, 1);
                        bundle.putSerializable(PublishBuyHouseActivity.DATA, detailBeam);
                        startAtvDonFinish(PublishBuyHouseActivity.class, bundle);
                        break;
                    case 3:
                        bundle.putInt(PublishRentOutActivity.TYPE, 1);
                        bundle.putSerializable(PublishRentOutActivity.DATA, detailBeam);
                        startAtvDonFinish(PublishRentOutActivity.class, bundle);
                        break;
                    case 4:
                        bundle.putInt(PublishRentInActivity.TYPE, 1);
                        bundle.putSerializable(PublishRentInActivity.DATA, detailBeam);
                        startAtvDonFinish(PublishRentInActivity.class, bundle);
                        break;
                }

                break;
        }
    }
}
