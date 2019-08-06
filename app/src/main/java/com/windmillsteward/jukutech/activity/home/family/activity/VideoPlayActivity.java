package com.windmillsteward.jukutech.activity.home.family.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.customview.VideoPlayerView;

/**
 * 描述：视频播放，直接播放，播放完毕显示开始播放按钮
 * 时间：2018/1/22
 * 作者：xjh
 */

public class VideoPlayActivity extends FragmentActivity {

    public static final String VIDEO_URL = "VIDEO_URL";
    private VideoPlayerView vp_video_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);

        initView();

        Bundle extras = getIntent().getExtras();
        if (extras==null) {
            finish();
        } else {
            String url = extras.getString(VIDEO_URL);
            if (!TextUtils.isEmpty(url)) {
                vp_video_play.setVideoUrl(url);
            }
        }
    }

    private void initView() {
        vp_video_play = (VideoPlayerView) findViewById(R.id.vp_video_play);
    }
}
