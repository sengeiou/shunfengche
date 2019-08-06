package com.windmillsteward.jukutech.activity.home.insurance.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.InsuranceDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 保险详情
 * Created by Administrator on 2018/4/8 0008.
 */

public class InsuranceDetailActivity extends BaseActivity implements View.OnClickListener, InsuranceDetailActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_company_name;
    private TextView tv_insurance_type_name;
    private TextView tv_contact_person;
    private TextView tv_features;
    private View view_features;
    private FrameLayout fl_features;
    private TextView tv_notes;
    private View view_notes;
    private FrameLayout fl_notes;
    private TextView tv_text;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;

    private int insurance_id;
    private InsuranceDetailActivityPresenter presenter;
    private InsuranceDetailBean detailBean;
    private boolean collect_type;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            insurance_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFeatures_or_notes();
        initFlyBanner();

        presenter = new InsuranceDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),insurance_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(),insurance_id);
//        }
    }

    private void initFlyBanner() {

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height =  575*layoutParams.width/1080;
        flyBanner.setLayoutParams(layoutParams);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) detailBean.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION,position);
                    startAtvDonFinish(PhotoViewActivity.class,bundle);
                }
            }
        });
    }

    private void initFeatures_or_notes() {
        fl_features.setOnClickListener(this);
        fl_notes.setOnClickListener(this);

        tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
        view_features.setVisibility(View.VISIBLE);
        tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
        view_notes.setVisibility(View.INVISIBLE);
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
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_company_name = (TextView) findViewById(R.id.tv_company_name);
        tv_insurance_type_name = (TextView) findViewById(R.id.tv_insurance_type_name);
        tv_contact_person = (TextView) findViewById(R.id.tv_contact_person);
        tv_features = (TextView) findViewById(R.id.tv_features);
        view_features = (View) findViewById(R.id.view_features);
        fl_features = (FrameLayout) findViewById(R.id.fl_features);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        view_notes = (View) findViewById(R.id.view_notes);
        fl_notes = (FrameLayout) findViewById(R.id.fl_notes);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);

        linear_collection.setOnClickListener(this);
        linear_call.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 20);
        layoutParams.height = screenWH / 4 + GraphicUtil.dp2px(this, 5);
        flyBanner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        flyBanner.setImages(images);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_features:
                if (detailBean!=null) {
                    tv_text.setText(detailBean.getInsurance_introduce());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_features.setVisibility(View.VISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_notes.setVisibility(View.INVISIBLE);
                break;
            case R.id.fl_notes:
                if (detailBean!=null) {
                    tv_text.setText(detailBean.getInsurance_information());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_features.setVisibility(View.INVISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_notes.setVisibility(View.VISIBLE);
                break;
            case R.id.linear_call:
                if (isLogin()) {
                    if (detailBean!=null)
                    presenter.isCharge(getAccessToken(),104,insurance_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (detailBean!=null) {
                        if (collect_type){
                            presenter.cancelCollect(insurance_id,getAccessToken());
                        } else {
                            presenter.collect(insurance_id,getAccessToken());
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void initDataSuccess(InsuranceDetailBean bean) {
        this.detailBean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null && pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getTitle());
        tv_publish_time.setText("发布："+DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_read_times.setText("浏览："+bean.getView_num()+"次");
        tv_company_name.setText("承保公司："+bean.getCompany_name());
        tv_insurance_type_name.setText("险种："+bean.getInsurance_type_name());
        tv_contact_person.setText("联系人："+bean.getContact_person());
        tv_text.setText(detailBean.getInsurance_introduce());
        tv_hosted_id.setText(bean.getHosting_show());
        GlideUtil.show(this,bean.getUser_avatar_url(),civ_header,R.mipmap.icon_big_head_male,R.mipmap.icon_big_head_male);
        tv_username.setText(bean.getNickname());
        if (bean.getIs_collect()==0) {
            tv_collection.setText("收藏");
            collect_type = false;
        } else {
            tv_collection.setText("已收藏");
            collect_type = true;
        }

        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + detailBean.getContact_mobile_phone());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    @Override
    public void collectSuccess() {
        tv_collection.setText("已收藏");
        collect_type = true;
    }

    @Override
    public void cancelCollectSuccess() {
        tv_collection.setText("收藏");
        collect_type = false;
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + this.detailBean.getContact_mobile_phone());
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
                        }
                    })
                    .show();
        }
    }
}
