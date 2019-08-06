package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishBuyCarActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditBuyCarDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BuyCarDetailBean;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

/**
 *
 * Created by Administrator on 2018/4/2 0002.
 */

public class EditBuyCarDetailActivity extends BaseActivity implements EditBuyCarDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_title;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_contact;
    private TextView tv_price;
    private ExpandTextView et_detail;
    private TextView tv_hosted_id;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;

    private EditBuyCarDetailActivityPresenter presenter;
    private BuyCarDetailBean detailBean;
    private int buy_car_id;
    private int publish_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buycar_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            buy_car_id = extras.getInt(Define.INTENT_DATA);
            publish_status = extras.getInt(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();

        presenter = new EditBuyCarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),buy_car_id);
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_price = (TextView) findViewById(R.id.tv_price);
        et_detail = (ExpandTextView) findViewById(R.id.et_detail);
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
    public void initDataSuccess(BuyCarDetailBean bean) {
        detailBean = bean;
        tv_title.setText(bean.getTitle());
        tv_publish_time.setText("发布："+ DateUtil.StampTimeToDate(String.valueOf(bean.getUpdate_time()),"yyyy-MM-dd"));
        tv_read_times.setText("阅读：" + bean.getView_num()+"次");
        tv_contact.setText("联系人：" + bean.getContact_person());
        tv_price.setText("预算：" + bean.getPrice()+"万");
        et_detail.setContent(bean.getDetails());
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
                                    presenter.delete(getAccessToken(),detailBean.getBuy_car_id());
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_edit:
                if (detailBean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_TYPE,1);
                    bundle.putSerializable(Define.INTENT_DATA,detailBean);
                    startAtvDonFinish(PublishBuyCarActivity.class, bundle);
                }
                break;
        }
    }
}
