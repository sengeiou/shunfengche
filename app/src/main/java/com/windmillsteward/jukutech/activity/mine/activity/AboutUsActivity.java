package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.AboutUsAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.AboutUsPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AboutUsBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：关于我们
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class AboutUsActivity extends BaseActivity implements AboutUsView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_version;
    private RecyclerView recycleView_content;
    private AboutUsPresenter presenter;
    private List<AboutUsBean> list;//关于我们数据源
    private AboutUsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_version = (TextView) findViewById(R.id.tv_version);
        recycleView_content = (RecyclerView) findViewById(R.id.recycleView_content);

        toolbar_iv_title.setText("关于我们");
        handleBackEvent(toolbar_iv_back);

        recycleView_content.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        adapter = new AboutUsAdapter(list);
        recycleView_content.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AboutUsBean aboutUsBean = list.get(position);
                String value = aboutUsBean.getValue();
                String remark = aboutUsBean.getRemark();
//                ModuleInstructionActivity.go(AboutUsActivity.this, remark, value);
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA,value);
                startAtvDonFinish(CommonWebActivity.class,bundle);
            }
        });

    }

    private void initData() {

        String versionName = ConfigUtil.getVersionName(this);
        tv_version.setText("版本号：" + "顺风车管家V" + versionName);

        presenter = new AboutUsPresenter(this);
        presenter.getAboutUsData();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void getAboutUsDataSuccess(List<AboutUsBean> list) {
        if (list == null) {
            return;
        }
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
//        String com_introduction = aboutUsBean.getApp_com_introduction();//公司介绍
//        String user_protocol = aboutUsBean.getApp_user_protocol();//用户协议
//        String law_disclaimer = aboutUsBean.getApp_law_disclaimer();//法律免责声明
    }

    @Override
    public void getAboutUsDataFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
