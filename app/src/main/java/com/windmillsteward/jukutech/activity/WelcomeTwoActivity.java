package com.windmillsteward.jukutech.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.customview.CountDownProgressView;

import org.xutils.x;


/**
 * 欢迎页
 */
public class WelcomeTwoActivity extends Activity {

    public static final String IMAGE = "image";

    private CountDownProgressView countDownProgressView;
    private ImageView iv_welcome;

    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        setContentView(R.layout.activity_welcome);

        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        countDownProgressView = (CountDownProgressView) findViewById(R.id.countdownProgressView);

        handler = new Handler();

        if (getIntent() != null) {
            String pic_url = getIntent().getStringExtra(IMAGE);
            if (TextUtils.isEmpty(pic_url)) {
                finish();
                return;
            } else {
                x.image().bind(iv_welcome, pic_url);
            }
        }
        countDownProgressView.setProgressListener(new CountDownProgressView.OnProgressListener() {
            @Override
            public void onProgress(int progress) {
            }

            @Override
            public void onTimeProgress(int time) {
                countDownProgressView.setText(time + "s");
                if (time <= 0) {
                    countDownProgressView.setText("跳过");
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownProgressView.start();
            }
        }, 500);

         runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeTwoActivity.this, MainActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable,4000);
        countDownProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownProgressView.stop();
                handler.removeCallbacksAndMessages(runnable);
                startActivity(new Intent(WelcomeTwoActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}
