package com.windmillsteward.jukutech.activity.home.think.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.think.presenter.WisdomFireControlDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

/**
 * 描述：智慧消防详情
 * 时间：2018年8月1日 03:03:30
 * 作者：lc
 */

public class WisdomFireControlDetailActivity extends BaseActivity implements WisdomFireControlDetailView, View.OnClickListener {

    public static final String REQUIRE_ID = "REQUIRE_ID";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_title;
    private TextView tv_add_area;
    private TextView tv_add_time;
    private TextView tv_read_times;
    private ExpandTextView expand_desc;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;

    private WisdomFireControlDetailPresenter presenter;
    private int require_id;
    private int position;
    private boolean collect_type;
    private String phone;
    private IdeaThinkDetailBean bean;
    private boolean isCall;
    private TextView tv_area;// 面积
    private TextView tv_site;// 地址
    private TextView tv_floor;// 楼层
    private TextView tv_type;// 类型
    private TextView tv_test_items;// 检测项目

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisdomfirecontroldetail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            require_id = extras.getInt(REQUIRE_ID);
            position = extras.getInt(Define.POSITION);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new WisdomFireControlDetailPresenter(this);
        presenter.initData(getAccessToken(),require_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(),require_id);
//        }
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add_area = (TextView) findViewById(R.id.tv_add_area);
        tv_add_time = (TextView) findViewById(R.id.tv_add_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_site = (TextView) findViewById(R.id.tv_site);
        tv_floor = (TextView) findViewById(R.id.tv_floor);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_test_items = (TextView) findViewById(R.id.tv_test_items);

        linear_collection.setOnClickListener(this);
        linear_call.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(IdeaThinkDetailBean bean) {
        this.bean = bean;
        tv_title.setText(bean.getTitle());
        tv_add_area.setText("发布于:" + bean.getSecond_area_name() + bean.getThird_area_name());
        tv_add_time.setText("" + DateUtil.StampTimeToDate(String.valueOf(bean.getUpdate_time()),"yyyy-MM-dd"));
        tv_read_times.setText("浏览：" + bean.getView_num() + "次");
        expand_desc.setContent(bean.getDescription());
        x.image().bind(civ_header,bean.getUser_avatar_url(), ImageUtils.defaulHeader());
        tv_username.setText(bean.getTrue_name());
        if (bean.getIs_collect()==0) {
            tv_collection.setText("收藏");
            collect_type = false;
        } else if (bean.getIs_collect()==1) {
            tv_collection.setText("已收藏");
            collect_type = true;
        }
        phone = bean.getContact_tel();
        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + bean.getContact_tel());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
        tv_test_items.setText(bean.getCheck_items());
        tv_type.setText(bean.getConsumption_type_name());
        tv_floor.setText(bean.getFloor_num());
        tv_site.setText(bean.getAddress());
        tv_area.setText(bean.getFloor_area());
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
    public void isChargeResult(final ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            if (phone!=null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + phone);
                intent.setData(uri);
                startActivity(intent);
            }
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
        switch (view.getId()){
            case R.id.toolbar_iv_back:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION,collect_type?-1:position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (bean==null) {
                        return;
                    }
                    if (collect_type) {
                        presenter.cancelCollect(getAccessToken(),require_id);
                    } else {
                        presenter.collect(getAccessToken(),require_id);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_call:

                if (isLogin()) {
                    if (bean==null) {
                        return;
                    }
                    presenter.isContacCharge(getAccessToken(),require_id);
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
            extras.putInt(Define.POSITION,(collect_type)?-1:position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
