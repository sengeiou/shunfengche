package com.windmillsteward.jukutech.activity.home.capitalmanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.presenter.CapitalDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class LoanDetailActivity extends BaseActivity implements CapitalDetailActivityView, View.OnClickListener  {


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_minimum_amount;
    private TextView tv_yield_rate;
    private ImageView iv_capital_logo;
    private TextView tv_product_type_name;
    private TextView tv_title2;
    private TextView tv_introduction;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;

    private CapitalDetailActivityPresenter presenter;
    private int capital_id;
    private CapitalDetailBean detailBean;
    private boolean collect_type;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            capital_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFlyBanner();

        presenter = new CapitalDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),capital_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        tv_minimum_amount = (TextView) findViewById(R.id.tv_minimum_amount);
        tv_yield_rate = (TextView) findViewById(R.id.tv_yield_rate);
        iv_capital_logo = (ImageView) findViewById(R.id.iv_capital_logo);
        tv_product_type_name = (TextView) findViewById(R.id.tv_product_type_name);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        tv_introduction = (TextView) findViewById(R.id.tv_introduction);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);

        linear_collection.setOnClickListener(this);
        linear_call.setOnClickListener(this);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        flyBanner.setImages(images);
    }

    @Override
    public void initDataSuccess(CapitalDetailBean bean) {
        this.detailBean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null && pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_minimum_amount.setText(bean.getMinimum_amount());
        tv_yield_rate.setText(bean.getYield_rate()+"%");
        GlideUtil.show(this,bean.getCapital_logo(),iv_capital_logo,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);
        tv_product_type_name.setText(bean.getTitle());
        tv_title2.setText("最高"+bean.getMinimum_amount()+"，月利率低至"+bean.getYield_rate()+"%");
        tv_introduction.setText(bean.getIntroduction());
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_call:
                if (isLogin()) {
                    if (detailBean!=null) {
                        // 83.联系产品发布人 84.联系资金放贷人
                        presenter.isCharge(getAccessToken(),84,capital_id);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (detailBean!=null) {
                        if (collect_type){
                            presenter.cancelCollect(capital_id,getAccessToken());
                        } else {
                            presenter.collect(capital_id,getAccessToken());
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }
}
