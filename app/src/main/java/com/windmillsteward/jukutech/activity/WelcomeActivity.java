package com.windmillsteward.jukutech.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gyf.barlibrary.BarHide;
import com.hyphenate.chat.EMClient;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.home.fragment.activity.WelcomeView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.WelcomePresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.WelcomeBean;
import com.windmillsteward.jukutech.interfaces.Define;


/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity implements WelcomeView {
//    private ImageView iv_welcome;
//    private Animation mAnimation;// 动画
//
//    private String hash;
//    private int app_versioncode;

    private WelcomePresenter presenter;
    private boolean is_first;//是否第一次打开应用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
        mImmersionBar.fitsSystemWindows(true).transparentStatusBar().hideBar(BarHide.FLAG_HIDE_BAR).fullScreen(true).init();

        is_first = (boolean) Hawk.get(Define.IS_FIRST_OPEN, true);
        if (is_first) {//第一次直接进入引导页
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, GuidanceActivity.class));
                    Hawk.put(Define.IS_FIRST_OPEN, false);
                    finish();
                }
            }, 1500);
        } else {//不是第一次则请求网络，获取下一个欢迎页的图片url
            presenter = new WelcomePresenter(this);
            presenter.getWelcomePic();
        }
    }


    @Override
    public void getWelcomePicSuccess(WelcomeBean welcomeBean) {
        if (welcomeBean != null) {
            final String image = welcomeBean.getImage();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (is_first) {
                        startActivity(new Intent(WelcomeActivity.this, GuidanceActivity.class));
                        Hawk.put(Define.IS_FIRST_OPEN, false);
                    } else {
                        Intent intent = new Intent(WelcomeActivity.this, WelcomeTwoActivity.class);
                        intent.putExtra(WelcomeTwoActivity.IMAGE, image);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
//                        overridePendingTransition(0, 0);
                    }
                }
            }, 1500);
        }
    }


    @Override
    public void getWelcomePicFailed(int code, String msg) {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
//                overridePendingTransition(0, 0);
            }
        }, 5000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onCancel();
        }
    }
}
