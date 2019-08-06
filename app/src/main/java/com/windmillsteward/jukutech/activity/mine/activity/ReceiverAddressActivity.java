package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.utils.StateButton;

/**
 * 描述：收货地址
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ReceiverAddressActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private RecyclerView recycleView_content;
    private StateButton btn_creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_address);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        recycleView_content = (RecyclerView) findViewById(R.id.recycleView_content);
        btn_creat = (StateButton) findViewById(R.id.btn_creat);

        btn_creat.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("收货地址");
    }

    private void initData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_creat:

                break;
        }
    }
}
