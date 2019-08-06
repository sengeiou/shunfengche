package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.PublishTravelActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailView;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.TravelDetailPresenter;
import com.windmillsteward.jukutech.activity.home.think.activity.PublishIdeaThinkActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditTravelDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：住宿旅行详情
 * 时间：2018/1/25
 * 作者：xjh
 */

public class EditTravelDetailActivity extends BaseActivity implements EditTravelDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String POSITION = "POSITION";
    public static final String PUBLISH_STATUS = "PUBLISH_STATUS";

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
    private TextView tv_edit;
    private ImageView iv_delete;
    private TextView tv_delete;
    private ImageView iv_edit;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;
    private LinearLayout linear_bottom;

    private int detail_id;
    private int position;
    private int publish_status;  // 0审核中 1审核通过 2审核不通过
    private int is_collect;
    private EditTravelDetailPresenter presenter;
    private TravelDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stayandtraveldetail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detail_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(POSITION);
            publish_status = extras.getInt(PUBLISH_STATUS);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFeatures_or_notes();
        initFlyBanner();
        presenter = new EditTravelDetailPresenter(this);
        presenter.initData(getAccessToken(),detail_id);
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
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_area = (TextView) findViewById(R.id.tv_area);
        fl_features = (FrameLayout) findViewById(R.id.fl_features);
        fl_notes = (FrameLayout) findViewById(R.id.fl_notes);
        tv_features_or_notes = (TextView) findViewById(R.id.tv_features_or_notes);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_features = (TextView) findViewById(R.id.tv_features);
        view_features = (View) findViewById(R.id.view_features);
        tv_notes = (TextView) findViewById(R.id.tv_notes);
        view_notes = (View) findViewById(R.id.view_notes);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        linear_delete = (LinearLayout) findViewById(R.id.linear_delete);
        linear_edit = (LinearLayout) findViewById(R.id.linear_edit);
        linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        iv_edit = (ImageView) findViewById(R.id.iv_edit);

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
        tv_hosted_id.setText(bean.getHosting_show());

    }

    @Override
    public void deleteIdeaThinkSuccess() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                                    presenter.deleteRequire(getAccessToken(),bean.getTravel_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(PublishTravelActivity.TYPE,1);
                    bundle.putSerializable(PublishTravelActivity.DATA,bean);
                    startAtvDonFinish(PublishTravelActivity.class, bundle);
                }
                break;
        }
    }
}
