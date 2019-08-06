package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HouseKeeperAlertsDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;

/**
 * 描述：管家快讯详情
 * author:cyq
 * 2018-04-10
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HouseKeeperAlertsDetailActivity extends BaseActivity implements HouseKeeperAlertsDetailView {

    public static final String NEWS_FLASH_URL = "NEWS_FLASH_URL";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private WebView wv_content;

    private HouseKeeperAlertsDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeper_alerts_detail);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        wv_content = (WebView) findViewById(R.id.wv_content);

        toolbar_iv_title.setText("快讯详情");
        handleBackEvent(toolbar_iv_back);
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        String url = extras.getString(NEWS_FLASH_URL);

//        if (presenter == null){
//            presenter = new HouseKeeperAlertsDetailPresenter(this);
//        }
//        presenter.getDataDetail(news_flash_id);

        wv_content.loadUrl(url);
    }

    @Override
    public void getDataSuccess(HouseKeeperDataQuickBean bean) {
        if (bean == null) {
            return;
        }
        String detail_url = bean.getContent();

        wv_content.loadUrl(detail_url);
    }

    @Override
    public void getDataFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
