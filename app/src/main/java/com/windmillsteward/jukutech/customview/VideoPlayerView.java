package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.MediaHelper;

/**
 * 描述：
 * 时间：2018/1/22
 * 作者：xjh
 */

public class VideoPlayerView extends RelativeLayout {
    private static final String  TAG = "VideoPlayer";

    private TextureView videoView;
    private ProgressBar pb_video_play;
    private ImageView iv_video_play;
    private MediaPlayer mPlayer;
    private Surface mSurface;
    private String videoUrl;

    public boolean hasPlay;//是否播放了

    public VideoPlayerView(Context context) {
        this(context, null);
    }

    public VideoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化布局
    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_video_play, this);
        videoView = (TextureView) view.findViewById(R.id.tv_video_play);
        pb_video_play = (ProgressBar)view.findViewById(R.id.pb_video_play);
        iv_video_play = (ImageView)view.findViewById(R.id.iv_video_play);

        iv_video_play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                iv_video_play.setVisibility(GONE);
            }
        });
        //进行TextureView控件创建的监听
        videoView.setSurfaceTextureListener(surfaceTextureListener);
    }

    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {

        //创建完成  TextureView才可以进行视频画面的显示
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            mSurface = new Surface(surface);//连接对象（MediaPlayer和TextureView）
            play();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Log.i(TAG,"onSurfaceTextureSizeChanged");
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            // Log.i(TAG,"onSurfaceTextureDestroyed");
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            // Log.i(TAG,"onSurfaceTextureUpdated");
        }
    };

    //视频播放（视频的初始化）
    private void play(){
        try {
            pb_video_play.setVisibility(VISIBLE);
            mPlayer = MediaHelper.getInstance();
            mPlayer.reset();
            mPlayer.setDataSource(videoUrl);
            //让MediaPlayer和TextureView进行视频画面的结合
            mPlayer.setSurface(mSurface);
            //设置监听
            mPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
            mPlayer.setOnCompletionListener(onCompletionListener);
            mPlayer.setOnErrorListener(onErrorListener);
            mPlayer.setOnPreparedListener(onPreparedListener);
            mPlayer.setScreenOnWhilePlaying(true);//在视频播放的时候保持屏幕的高亮
            //异步准备
            mPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //准备完成监听
    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            //隐藏视频加载进度条
            pb_video_play.setVisibility(GONE);
            //进行视频的播放
            MediaHelper.play();
            hasPlay = true;

            int videoHeight = mPlayer.getVideoHeight();
            int videoWidth = mPlayer.getVideoWidth();

            ViewGroup.LayoutParams params = videoView.getLayoutParams();
            params.height = (getMeasuredWidth()*videoHeight) / videoWidth;
            videoView.setLayoutParams(params);
        }
    };

    //错误监听
    private MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            hasPlay = false;
            pb_video_play.setVisibility(GONE);
            return true;
        }
    };

    //完成监听
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //视频播放完成
            iv_video_play.setVisibility(VISIBLE);
            hasPlay = false;
        }
    };

    //缓冲的监听
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (!hasWindowFocus) {
            if (mPlayer!=null) {
                if (mPlayer.isPlaying()) {
                    MediaHelper.pause();
                }
            }
        } else {
            if (hasPlay) {
                if (mPlayer!=null) {
                    MediaHelper.play();
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        MediaHelper.pause();
        MediaHelper.release();
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
