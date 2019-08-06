package com.windmillsteward.jukutech.activity.home.insurance.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.BigHealthDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.ShoppingOtherSuccessActivity;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.ShoppingPayOtherActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BigHealthDetailBuyBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 大健康详情
 * Created by Administrator on 2018/4/8 0008.
 */

public class BigHealthDetailActivity extends BaseActivity implements View.OnClickListener, BigHealthDetailView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_features;
    private View view_features;
    private FrameLayout fl_features;
    private TextView tv_notes;
    private TextView tv_money;
    private TextView tv_buy_now;
    private View view_notes;
    private FrameLayout fl_notes;
    private TextView tv_after_sale;
    private View view_after_sale;
    private FrameLayout fl_after_sale;
    private LinearLayout linear_call;
    private LinearLayout ll_cpjs;
    private LinearLayout ll_szfw;
    private LinearLayout ll_shfw;
    private WebView webView;

    private int insurance_id;
    private BigHealthDetailPresenter presenter;
    private InsuranceDetailBean detailBean;
    private boolean isCall;
    private String status;//  // 0：未购买，1：售中，2：售后


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_health_detail);
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
        initWebView();

        presenter = new BigHealthDetailPresenter(this);
        presenter.initData(getAccessToken(), insurance_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(), insurance_id);
//        } else
        if (requestCode == 101 && resultCode == 200) {
            startAtvAndFinish(ShoppingOtherSuccessActivity.class);
        }
    }

    private void initFlyBanner() {

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height = 575 * layoutParams.width / 1080;
        flyBanner.setLayoutParams(layoutParams);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) detailBean.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                    startAtvDonFinish(PhotoViewActivity.class, bundle);
                }
            }
        });
    }

    private void initFeatures_or_notes() {
        fl_notes.setOnClickListener(this);
        tv_notes.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
        view_notes.setVisibility(View.INVISIBLE);

        fl_features.setOnClickListener(this);
        tv_features.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
        view_features.setVisibility(View.VISIBLE);

        fl_after_sale.setOnClickListener(this);
        tv_after_sale.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
        view_after_sale.setVisibility(View.INVISIBLE);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        webView = (WebView) findViewById(R.id.webView);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_features = (TextView) findViewById(R.id.tv_features);
        view_features = (View) findViewById(R.id.view_features);
        fl_features = (FrameLayout) findViewById(R.id.fl_features);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        view_notes = (View) findViewById(R.id.view_notes);
        fl_notes = (FrameLayout) findViewById(R.id.fl_notes);
        tv_after_sale = (TextView) findViewById(R.id.tv_after_sale);
        view_after_sale = (View) findViewById(R.id.view_after_sale);
        fl_after_sale = (FrameLayout) findViewById(R.id.fl_after_sale);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        ll_cpjs = (LinearLayout) findViewById(R.id.ll_cpjs);
        ll_szfw = (LinearLayout) findViewById(R.id.ll_szfw);
        ll_shfw = (LinearLayout) findViewById(R.id.ll_shfw);

        linear_call.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 20);
        layoutParams.height = screenWH / 4 + GraphicUtil.dp2px(this, 5);
        flyBanner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        flyBanner.setImages(images);
    }

    private void initWebView() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setUseWideViewPort(false);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(false);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_features:// 产品说明
                tv_features.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
                view_features.setVisibility(View.VISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_notes.setVisibility(View.INVISIBLE);
                tv_after_sale.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_after_sale.setVisibility(View.INVISIBLE);
                lodWebView(detailBean.getHealth_introduce());
                break;
            case R.id.fl_notes:// 售中
                tv_features.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_features.setVisibility(View.INVISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
                view_notes.setVisibility(View.VISIBLE);
                tv_after_sale.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_after_sale.setVisibility(View.INVISIBLE);
                lodWebView(detailBean.getHealth_for_sale());
                break;
            case R.id.fl_after_sale:// 售后
                tv_features.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_features.setVisibility(View.INVISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this, R.color.color_text_999));
                view_notes.setVisibility(View.INVISIBLE);
                tv_after_sale.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
                view_after_sale.setVisibility(View.VISIBLE);
                lodWebView(detailBean.getHealth_after_sale());
                break;
            case R.id.linear_call:
                if (isLogin()) {
                    if (detailBean != null)
                        presenter.isCharge(getAccessToken(), 71, insurance_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.tv_buy_now:
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA, insurance_id + "");
                startAtvDonFinishForResult(ShoppingPayOtherActivity.class, 101, bundle);
                break;

        }
    }

    @Override
    public void initDataSuccess(InsuranceDetailBean bean) {
        this.detailBean = bean;
        status = bean.getStatus();
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls != null && pic_urls.size() > 0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getTitle());
        tv_publish_time.setText("发布：" + DateTimeFormatUtil.dateTimeFormatString(bean.getAdd_time()));
        tv_read_times.setText("浏览：" + bean.getView_num() + "次");
        tv_money.setText(bean.getPrice() + "元");

        String content = bean.getHealth_introduce();//后台接口返回的需要在WebView中显示的HTML代码
        lodWebView(content);

        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + detailBean.getContact_mobile_phone());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }


        if (status.equals("0")) { // 0：未购买，1：售中，2：售后
            ll_cpjs.setVisibility(View.VISIBLE);
            ll_szfw.setVisibility(View.INVISIBLE);
            ll_shfw.setVisibility(View.INVISIBLE);
            tv_buy_now.setVisibility(View.VISIBLE);
        } else if (status.equals("1")) { // 0：未购买，1：售中，2：售后
            ll_cpjs.setVisibility(View.VISIBLE);
            ll_szfw.setVisibility(View.VISIBLE);
            ll_shfw.setVisibility(View.INVISIBLE);
            tv_buy_now.setVisibility(View.GONE);
        } else if (status.equals("2")) { // 0：未购买，1：售中，2：售后
            ll_cpjs.setVisibility(View.VISIBLE);
            ll_szfw.setVisibility(View.VISIBLE);
            ll_shfw.setVisibility(View.VISIBLE);
            tv_buy_now.setVisibility(View.GONE);
        }
    }

    private void lodWebView(String content) {
        content = content.replace("<img", "<img style=\"display:        ;max-width:100%;\"");
        webView.loadData(content, "text/html;charset=utf-8", "UTF-8");
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge() == 0) {
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
    public void buy(BigHealthDetailBuyBean bean) {
    }
}
