package com.windmillsteward.jukutech.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.customview.ImageCycleViewGuidance;

import java.util.ArrayList;
import java.util.List;


/**
 * 程序引导页
 */
public class GuidanceActivity extends BaseActivity {
    private ImageCycleViewGuidance vp_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
        setContentView(R.layout.activity_guidance);
        initView();
    }

    private void initView() {
        vp_home = (ImageCycleViewGuidance) findViewById(R.id.vp_home);
        banner();
    }

    /**
     * 轮播图
     */
    public void banner() {
        final List<ImageCycleViewGuidance.ImageInfo> viewList = new ArrayList<>();
        viewList.add(new ImageCycleViewGuidance.ImageInfo(R.mipmap.icon_new_guide_one, "", ""));
        viewList.add(new ImageCycleViewGuidance.ImageInfo(R.mipmap.icon_new_guide_two, "", ""));
        viewList.add(new ImageCycleViewGuidance.ImageInfo(R.mipmap.icon_new_guide_three, "", ""));
        viewList.add(new ImageCycleViewGuidance.ImageInfo(R.mipmap.icon_new_guide_four, "", ""));

        vp_home.setAutoCycle(false); //关闭自动播放
        vp_home.setInfiniteCycle(false); //关闭无限播放
        vp_home.hideIndication(false);
//        vp_home.setIndicationStyle(ImageCycleViewGuidance.IndicationStyle.IMAGE,
//                R.mipmap.icon_new_guide_point_s,R.mipmap.icon_new_guide_point_n,0f);
        vp_home.setOnPageClickListener(new ImageCycleViewGuidance.OnPageClickListener() {
            public void onClick(View imageView, ImageCycleViewGuidance.ImageInfo imageInfo, int position) {
                if (position == (viewList.size() - 1)) {// 如果是最后一页
                   startAtvAndFinish(LoginActivity.class);
                }
            }
        });
        vp_home.loadData(viewList, new ImageCycleViewGuidance.LoadImageCallBack() {
            public ImageView loadAndDisplay(ImageCycleViewGuidance.ImageInfo imageInfo) {
                // 本地图片
                ImageView imageView = new ImageView(GuidanceActivity.this);
                imageView.setImageResource(Integer.parseInt(imageInfo.image
                        .toString()));
                return imageView;
            }
        });
    }


}
