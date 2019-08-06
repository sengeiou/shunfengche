package com.windmillsteward.jukutech.activity.newpage.yizhan;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;

import java.io.IOException;

public class VoiceUtils {

    private AnimationDrawable voiceAnimation;
    private MediaPlayer mediaPlayer;
    private String filePath;
    ImageView ivVoice;
    private boolean isPlaying = false;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public VoiceUtils(ImageView ivVoice) {
        this.ivVoice = ivVoice;

    }

    public void getTime(TextView tvlength){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            int duration = mediaPlayer.getDuration();
            tvlength.setText(duration/1000+"s");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {

            try {
                mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址
                mediaPlayer.setDataSource(filePath);
                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        isPlaying = true;
                        mediaPlayer.start();
                        ivVoice.setImageResource(R.anim.voice_to_icon);
                        voiceAnimation = (AnimationDrawable) ivVoice.getDrawable();
                        voiceAnimation.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPlaying = false;
                        stop();
                        ivVoice.setImageResource(R.drawable.ease_chatto_voice_playing);
                    }
                });
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        isPlaying = false;
                        ivVoice.setImageResource(R.drawable.ease_chatto_voice_playing);
                        return false;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * 暂停
     */
    public void pause() {
        if (isPlaying){
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                ivVoice.setImageResource(R.drawable.ease_chatto_voice_playing);
            }
        }else{
            mediaPlayer.start();
            ivVoice.setImageResource(R.anim.voice_to_icon);
            voiceAnimation = (AnimationDrawable) ivVoice.getDrawable();
            voiceAnimation.start();
        }
    }

    /**
     * 重新播放
     */
    protected void replay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            return;
        }
        play();
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            ivVoice.setImageResource(R.drawable.ease_chatto_voice_playing);
            isPlaying = false;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }



}