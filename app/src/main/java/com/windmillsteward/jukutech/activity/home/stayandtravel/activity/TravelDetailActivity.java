package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.TravelDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：住宿旅行详情
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelDetailActivity extends BaseActivity implements TravelDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_area;
    private FrameLayout fl_features;
    private FrameLayout fl_notes;
    private TextView tv_features;
    private View view_features;
    private TextView tv_notes;
    private View view_notes;
    private TextView tv_features_or_notes;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout ll_collect;
    private LinearLayout ll_call;

    private int detail_id;
    private int position;
    private int is_collect;
    private TravelDetailPresenter presenter;
    private TravelDetailBean bean;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayandtraveldetail);

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
        initFeatures_or_notes();
        initFlyBanner();
        presenter = new TravelDetailPresenter(this);
        presenter.initData(getAccessToken(),detail_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = false;
//            presenter.initData(getAccessToken(),detail_id);
//        }
    }

    /**
     * 初始化特色和公告
     */
    private void initFeatures_or_notes() {
        fl_features.setOnClickListener(this);
        fl_notes.setOnClickListener(this);

        tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
        view_features.setVisibility(View.VISIBLE);
        tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
        view_notes.setVisibility(View.INVISIBLE);
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
    private void initToolbar() {
        toolbar_iv_back.setOnClickListener(this);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_area = (TextView) findViewById(R.id.tv_area);
        fl_features = (FrameLayout) findViewById(R.id.fl_features);
        fl_notes = (FrameLayout) findViewById(R.id.fl_notes);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        ll_collect = (LinearLayout) findViewById(R.id.ll_collect);
        ll_call = (LinearLayout) findViewById(R.id.ll_call);
        tv_features_or_notes = (TextView) findViewById(R.id.tv_features_or_notes);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_features = (TextView) findViewById(R.id.tv_features);
        view_features = (View) findViewById(R.id.view_features);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        view_notes = (View) findViewById(R.id.view_notes);

        ll_collect.setOnClickListener(this);
        ll_call.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height =  575*layoutParams.width/1080;
        flyBanner.setLayoutParams(layoutParams);
    }

    @Override
    public void initDataSuccess(TravelDetailBean bean) {
        this.bean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls!=null && pic_urls.size()>0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getTitle());
        String price = "￥"+bean.getStart_price();
        tv_price.setText(price);
        tv_area.setText(bean.getGo_area_name());
        tv_features_or_notes.setText(bean.getDescription());
        x.image().bind(civ_header,bean.getUser_avatar_url(), ImageUtils.defaulHeader());
        tv_username.setText(bean.getContact_person());
        is_collect = bean.getIs_collect();
        if (is_collect==0) {
            tv_collection.setText("收藏");
        } else {
            tv_collection.setText("已收藏");
        }
        tv_hosted_id.setText(bean.getHosting_show());
        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + bean.getContact_tel());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    /**
     * 收藏成功
     */
    @Override
    public void collectTravelSuccess() {
        is_collect = 1;
        tv_collection.setText("已收藏");
    }

    @Override
    public void cancelCollectTravelSuccess() {
        is_collect = 0;
        tv_collection.setText("收藏");
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + this.bean.getContact_tel());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_back:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION,is_collect==1?-1:position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.fl_features:
                if (bean!=null) {
                    tv_features_or_notes.setText(bean.getDescription());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_features.setVisibility(View.VISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_notes.setVisibility(View.INVISIBLE);
                break;
            case R.id.fl_notes:
                if (bean!=null) {
                    tv_features_or_notes.setText(bean.getNotes());
                }
                tv_features.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
                view_features.setVisibility(View.INVISIBLE);
                tv_notes.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                view_notes.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_collect:
                if (isLogin()) {
                    if (bean!=null) {
                        if (is_collect==0) {
                            presenter.collectTravel(getAccessToken(),detail_id);
                        } else {
                            presenter.cancelCollectTravel(getAccessToken(),detail_id);
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }

                break;
            case R.id.ll_call:
                if (isLogin()) {
                    presenter.isContacCharge(getAccessToken(),detail_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();
            Bundle extras = new Bundle();
            extras.putInt(Define.POSITION,(is_collect==1)?-1:position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
