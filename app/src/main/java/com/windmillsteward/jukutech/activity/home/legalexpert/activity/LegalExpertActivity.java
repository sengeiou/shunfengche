package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.legalexpert.presenter.LegalExpertActivityPresenter;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 法律专家
 * Created by Administrator on 2018/4/10 0010.
 */

public class LegalExpertActivity extends BaseActivity implements LegalExpertActivityView, View.OnClickListener {


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_fill_request;

    private LegalExpertActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legalexpert);
        initView();
        initToolbar();
        presenter = new LegalExpertActivityPresenter(this);
        presenter.getBannerTopList(5);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("法律专家");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_fill_request = (TextView) findViewById(R.id.tv_fill_request);
        tv_fill_request.setOnClickListener(this);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH ;
        layoutParams.height = 575 * layoutParams.width / 1080;
        flyBanner.setLayoutParams(layoutParams);
    }

    @Override
    public void getTopBannerListSuccess(List<SliderPictureInfo> list) {
        List<String> img_urls = new ArrayList<>();
        for (SliderPictureInfo info : list) {
            String pic_url = info.getPic_url();
            img_urls.add(pic_url);
        }
        if (img_urls.size()>0) {
            flyBanner.setImagesUrl(img_urls);
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen()==1) {
            startAtvDonFinish(PublishLegalExpertActivity.class);
        } else if (bean.getIs_authen()==0){
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen()==2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息",1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fill_request:
                presenter.getAuthenState(getAccessToken());
                break;
        }
    }
}
