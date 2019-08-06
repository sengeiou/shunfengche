package com.windmillsteward.jukutech.activity.newpage.yizhan.utils;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderDialog;
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderUtils;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseInitActivity;

/**
 * 人才驿站发布页面的录音初始化
 */
public class RecorderUtils  {

    private HomeCommonPublishActivity context;
    private AudioRecoderDialog recoderDialog;
    private AudioRecoderUtils recoderUtils;
    private VoiceUtils voiceUtils;

    LinearLayout ll_root;
    private Button btn_luzhi;
    private ImageView iv_voice;

    private String voicePath;
    private boolean isCancel = false;//取消录制语音
    private long downT;//按下时的时间戳
    private CallBack callBack;


    public RecorderUtils(HomeCommonPublishActivity context,CallBack callBack) {
        this.callBack = callBack;
        this.context = context;
    }

    public void initView(LinearLayout ll_root,Button button,ImageView iv_voice){
        this.ll_root = ll_root;
        this.btn_luzhi = button;
        this.iv_voice = iv_voice;
        voiceUtils = new VoiceUtils(iv_voice);
        btn_luzhi.setOnTouchListener(onTouchListener);
        initData();
    }

    private void initData(){
        recoderDialog = new AudioRecoderDialog(context);
        recoderDialog.setShowAlpha(0.98f);
        recoderUtils = new AudioRecoderUtils();
        recoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            @Override
            public void onUpdate(double db) {
                if (null != recoderDialog) {
                    int level = (int) db;
                    recoderDialog.setLevel((int) db);
                    recoderDialog.setTime(System.currentTimeMillis() - downT);
                }
            }

            @Override
            public void onStop(String filePath, long length) {
                if (TextUtils.isEmpty(filePath)) {
                    return;
                }
                voicePath = filePath;
                callBack.luzhiFinish(filePath,length);
            }
        });
    }

    public void play(){
        if (TextUtils.isEmpty(voicePath)) {
            return;
        }
        voiceUtils.setFilePath(voicePath);
        boolean playing = voiceUtils.isPlaying();
        if (playing) {
            voiceUtils.stop();
        } else {
            voiceUtils.play();
        }
    }

    public void delete(){
        voicePath = "";
        boolean playing = voiceUtils.isPlaying();
        if (playing) {
            voiceUtils.stop();
        }
    }



    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            String[] permissions = {Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            float startY = 0;
            float endY = 0;
            if (context.checkPermission(permissions)) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = motionEvent.getY();
                        recoderUtils.startRecord();
                        downT = System.currentTimeMillis();
                        recoderDialog.showAtLocation(ll_root, Gravity.CENTER, 0, 0);
                        callBack.pressButton(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        endY = motionEvent.getY();
                        if (isCancel) {
                            recoderUtils.cancelRecord();//取消录音
                            callBack.pressButton(2);
                        } else {
                            recoderUtils.stopRecord();//停止录音，保存
                            callBack.pressButton(1);
                        }
                        recoderDialog.dismiss();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (isCancel) {
                            recoderUtils.cancelRecord();//取消录音
                            callBack.pressButton(2);
                        } else {
                            recoderUtils.stopRecord();//停止录音，保存
                            callBack.pressButton(1);
                        }
                        recoderDialog.dismiss();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = motionEvent.getY();
                        int instance = (int) Math.abs((moveY - startY));
                        if (instance > 100) {
                            recoderDialog.setStatus(true);
                            isCancel = true;
                        } else {
                            recoderDialog.setStatus(false);
                            isCancel = false;
                        }
                        break;
                }
            }
            return true;
        }
    };


    public interface CallBack{
       void  luzhiFinish(String filePath, long length);
       void pressButton(int status);
    }
}
