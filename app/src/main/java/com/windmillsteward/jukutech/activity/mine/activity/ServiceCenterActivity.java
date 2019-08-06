package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.ServiceCenterModuleAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.ServicePhonePresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.ModuleIntroduceBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：服务中心
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ServiceCenterActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, ServicePhoneView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_feedback;
    private TextView tv_service_tel;
    private RecyclerView recycleView_content;

    private ServiceCenterModuleAdapter adapter;
    private ServicePhonePresenter servicePhonePresenter;

    private List<ModuleIntroduceBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_feedback = (TextView) findViewById(R.id.tv_feedback);
        tv_service_tel = (TextView) findViewById(R.id.tv_service_tel);
        recycleView_content = (RecyclerView) findViewById(R.id.recycleView_content);

        tv_feedback.setOnClickListener(this);
        tv_service_tel.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("服务中心");
    }

    private void initData() {
        recycleView_content.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        adapter = new ServiceCenterModuleAdapter(list);
        recycleView_content.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        if (servicePhonePresenter == null) {
            servicePhonePresenter = new ServicePhonePresenter(this);
        }
        servicePhonePresenter.getModuleIntroduce();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_feedback:
                startAtvDonFinish(FeedBackActivity.class);
                break;
            case R.id.tv_service_tel:
                if (servicePhonePresenter == null) {
                    servicePhonePresenter = new ServicePhonePresenter(this);
                }
                servicePhonePresenter.getServicePhone();
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ModuleIntroduceBean bean = (ModuleIntroduceBean) adapter.getData().get(position);
//        ModuleInstructionActivity.go(this, bean.getName(), bean.getContent());
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA,bean.getContent());
        startAtvDonFinish(CommonWebActivity.class,bundle);
    }

    /**
     * 打电话
     */
    private void callPhone(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void Success(String tel) {
        if (TextUtils.isEmpty(tel)) {
            return;
        }
        callPhone(tel);
    }

    @Override
    public void getModuleListSuccess(List<ModuleIntroduceBean> beanList) {
        if (beanList == null) {
            return;
        }
        list.addAll(beanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void Failed(int code, String msg) {
        showTips(msg, 1);
    }
}
