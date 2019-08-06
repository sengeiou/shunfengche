package com.windmillsteward.jukutech.activity.home.commons.video;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.windmillsteward.jukutech.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * 视频录制
 * Created by Administrator on 2018/1/7/007.
 */

public class VideoRecordingActivity extends FragmentActivity {

    private VideoFragment fragment;
    private PlayVideoFragment playVideoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{RECORD_AUDIO, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, INTERNET, CAMERA}, 1);
        }

        playVideoFragment = new PlayVideoFragment();
        fragment = new VideoFragment();

        // 默认录取视频
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new VideoFragment();
        transaction.replace(R.id.mainContent, fragment);
        transaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            }
        }
    }

    public void end(String path){
        if (path!=null) {
            // 默认录取视频
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            playVideoFragment.setPath(path);
            transaction.replace(R.id.mainContent, playVideoFragment);
            transaction.commit();
        }
    }

    public void reR(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new VideoFragment();
        transaction.replace(R.id.mainContent, fragment);
        transaction.commit();
    }

    public void send(String path){
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        data.putExtras(bundle);
        setResult(200, data);
        finish();
    }
}
