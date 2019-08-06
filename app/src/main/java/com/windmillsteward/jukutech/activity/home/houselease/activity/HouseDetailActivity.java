package com.windmillsteward.jukutech.activity.home.houselease.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.HouseDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.StaticData;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * 描述：卖房详情
 * 时间：2018/2/5
 * 作者：xjh
 */

public class HouseDetailActivity extends BaseActivity implements HouseDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String CLASS_TYPE = "CLASS_TYPE";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;

    private com.windmillsteward.jukutech.customview.FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_add_time;
    private TextView tv_read_times;
    private LinearLayout linear_out;
    private TextView tv_house_type;
    private TextView tv_price_type;
    private TextView tv_price;
    private TextView tv_community;
    private TextView tv_floor_area;
    private com.windmillsteward.jukutech.customview.ExpandTextView expand_desc;
    private com.windmillsteward.jukutech.customview.CircleImageView civ_header;
    private TextView tv_username;
    private TextView tv_fee;
    private LinearLayout linear_call;
    private LinearLayout ll_user_info;

    private HouseDetailPresenter presenter;
    private HouseDetailBeam detailBeam;
    private int detail_id;
    private boolean collect_type;
    private String phone;
    private int position;
    private boolean isCall;

    private CommonPayPresenter commonPayPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellhousedetail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detail_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(Define.POSITION);
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();
        initFlyBanner();
        presenter = new HouseDetailPresenter(this);
        commonPayPresenter = new CommonPayPresenter(this);
        presenter.initData(detail_id, getAccessToken());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            presenter.initData(detail_id, getAccessToken());
        }
    }

    private void initToolbar() {
        toolbar_iv_back.setOnClickListener(this);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_fee = (TextView) findViewById(R.id.tv_fee);
        flyBanner = (com.windmillsteward.jukutech.customview.FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add_time = (TextView) findViewById(R.id.tv_add_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        linear_out = (LinearLayout) findViewById(R.id.linear_out);
        tv_house_type = (TextView) findViewById(R.id.tv_house_type);
        tv_price_type = (TextView) findViewById(R.id.tv_price_type);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_community = (TextView) findViewById(R.id.tv_community);
        tv_floor_area = (TextView) findViewById(R.id.tv_floor_area);
        expand_desc = (com.windmillsteward.jukutech.customview.ExpandTextView) findViewById(R.id.expand_desc);
        civ_header = (com.windmillsteward.jukutech.customview.CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        ll_user_info = (LinearLayout) findViewById(R.id.ll_user_info);
        toolbar_iv_right.setVisibility(View.VISIBLE);
        linear_call.setOnClickListener(this);
        toolbar_iv_right.setOnClickListener(this);

        linear_out.requestLayout();

        ViewWrap.handlerFlyBanner(flyBanner,750,438);
    }

    @Override
    public void initDataSuccess(HouseDetailBeam beam) {
        int orientation = beam.getOrientation();
        String[] orientation_text = StaticData.getOrientation_text();
        int decoration = beam.getDecoration();
        String[] decoration_text = StaticData.getDecoration_text();
        int house_type = beam.getHouse_type();
        int property_right = beam.getProperty_right();
        int rent_deposit_type = beam.getRent_deposit_type();
        String[] rent_deposit_text = StaticData.getRent_deposit_text();
        int school_degree_type = beam.getSchool_degree_type();
        String[] school_degree_text = StaticData.getSchool_degree_text();
        String add_time = DateUtil.StampTimeToDate(String.valueOf(beam.getAdd_time()), "yyyy-MM-dd");
        List<String> pic_urls = beam.getPic_urls();
        if (pic_urls != null && pic_urls.size() > 0) {
            flyBanner.setImagesUrl(pic_urls);
        } else {
            flyBanner.setVisibility(View.GONE);
        }
        presenter.loadRentTypeDataF(2);
        tv_title.setText(beam.getTitle());
        tv_add_time.setText("发布于：" + add_time);
        tv_read_times.setText(beam.getView_num() + "次浏览");
        tv_house_type.setText(beam.getHouse_rooms() + "室" + beam.getHouse_parlor() + "厅");
        tv_price.setText(beam.getTotal_price() + "元");
        //拼接数据
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("小区：" + beam.getCommunity_name() + "\n");
        stringBuffer.append("楼层：" + beam.getFloor() + "楼" + "\n");
        stringBuffer.append("装修：" + decoration_text[decoration - 1] + "\n");
        stringBuffer.append("产权：" + property_right + "年" + "\n");
        stringBuffer.append("开发商：" + beam.getDevelopers_name() + "\n");
        stringBuffer.append("房屋地址：" + beam.getHouse_third_name() + " " + beam.getHouse_fourth_name());

        StringBuffer stringBufferRight = new StringBuffer();
        stringBufferRight.append("面积：" + beam.getFloor_area() + "m²" + "\n");
        stringBufferRight.append("朝向：" + orientation_text[orientation - 1] + "\n");
        stringBufferRight.append("类型：" + beam.getHouse_type_name() == null ? "" : beam.getHouse_type_name() + "\n");
        stringBufferRight.append("学位：" + school_degree_text[school_degree_type] + "\n");
        stringBufferRight.append("联系人：" + beam.getContact_person());
        tv_community.setText(stringBuffer.toString());
        tv_floor_area.setText(stringBufferRight.toString());
        expand_desc.setContent(beam.getDescription());

        x.image().bind(civ_header, beam.getUser_avatar_url(), ImageUtils.defaulHeader());
        tv_username.setText(beam.getContact_person());
        if (beam.getIs_collect() == 0) {
            toolbar_iv_right.setImageResource(R.mipmap.shoucang);
            collect_type = false;
        } else if (beam.getIs_collect() == 1) {
            toolbar_iv_right.setImageResource(R.mipmap.shoucang_red);
            collect_type = true;
        }

        phone = beam.getContact_tel();
        if ("0".equals(phone))
            phone = "";
        detailBeam = beam;

        if (TextUtils.isEmpty(phone)) {
            //没有支付
            ll_user_info.setVisibility(View.GONE);
            tv_fee.setVisibility(View.VISIBLE);
            tv_fee.setText(new SpanUtils()
                    .append("信息费")
                    .append("￥" + beam.getFee()).setForegroundColor(Color.parseColor("#fdbe44")).create());
        } else {
            ll_user_info.setVisibility(View.VISIBLE);
            tv_fee.setVisibility(View.GONE);
            tv_username.setText(beam.getContact_person());
        }

        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + phone);
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBeam != null) {
                    ViewWrap.showPicActivity(HouseDetailActivity.this, (ArrayList<String>) detailBeam.getPic_urls(), position);
                }
            }
        });
    }

    @Override
    public void collectSuccess() {
        toolbar_iv_right.setImageResource(R.mipmap.shoucang_red);
        collect_type = true;
    }

    @Override
    public void cancelCollectSuccess() {
        toolbar_iv_right.setImageResource(R.mipmap.shoucang);
        collect_type = false;
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge() == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + phone);
            intent.setData(uri);
            startActivity(intent);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("拨打电话需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            if (type == 1) {
//                                PayActivity.go(HouseDetailActivity.this, 53, detail_id);
//                            } else if (type == 2) {
//                                PayActivity.go(HouseDetailActivity.this, 54, detail_id);
//                            } else if (type == 3) {
//                                PayActivity.go(HouseDetailActivity.this, 57, detail_id);
//                            } else if (type == 4) {
//                                PayActivity.go(HouseDetailActivity.this, 58, detail_id);
//                            }
                        }
                    })
                    .show();
        }
    }

    @Override
    public void loadRentTypeDataSuccessF(HouseMoreBean bean) {
//        if (detailBeam != null) {
//            int house_type = detailBeam.getHouse_type();
//            List<HouseMoreBean.HouseTypeListBean> house_type_list = bean.getHouse_type_list();
//            if (house_type_list != null) {
//                for (HouseMoreBean.HouseTypeListBean listBean : house_type_list) {
//                    if (house_type == listBean.getHouse_type_id()) {
//                        tv_type.setText("类型：" + listBean.getHouse_type_name());
//                    }
//                }
//            }
//        }
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_back:
                final Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION, collect_type ? -1 : position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.toolbar_iv_right:
                if (isLogin()) {
                    if (detailBeam != null) {
                        if (collect_type) {
                            presenter.cancelCollect(detail_id);
                        } else {
                            presenter.collect(detail_id);
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_call:
                if (isLogin()) {
                    if (!TextUtils.isEmpty(phone)) {
                        //已经支付过 //提示打电话
                        final BaseDialog baseDialog = new BaseDialog(this);
                        baseDialog.showTwoButton("提示", "是否拨打以下电话\n" + phone, "确定", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                                getPermission();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                            }
                        });
                    } else {
                        //没有支付过
                        showDialog();
                        commonPayPresenter.loadHousePayInfoFeeData(CommonPayPresenter.FEE_TYPE_FANGWU,detail_id, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
                            @Override
                            public void onFail(int type, String msg) {
                                showTips(msg);
                                dismiss();
                            }

                            @Override
                            public void onSucess(int code, final BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                                dismiss();
                                if (respnse.getData() != null) {
                                    final BaseDialog baseDialog = new BaseDialog(HouseDetailActivity.this);
                                    baseDialog.showTwoButton("提示", "拨打电话需要支付费用，继续吗", "确定", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            baseDialog.dismiss();
                                            NewPayActivity.go(HouseDetailActivity.this, CommonPayPresenter.TYPE_FANGWU, detail_id, respnse.getData().getCharge_fee(), respnse.getData().getOrder_name(),0);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            baseDialog.dismiss();
                                        }
                                    });
                                }
                            }
                        });
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();
            Bundle extras = new Bundle();
            extras.putInt(Define.POSITION, collect_type ? -1 : position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(Define.ALL_JURISDICTION_APPLY)
    private void getPermission() {
        String[] perms = {android.Manifest.permission.CALL_PHONE};
        if (checkPermission(perms)) {
            PhoneUtils.dial(phone);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        getPermission();
    }
}
