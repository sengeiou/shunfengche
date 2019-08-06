package com.windmillsteward.jukutech.activity.home.commons.video;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;

import java.io.File;
import java.io.IOException;

/**
 * 视频录制界面
 * Created by Administrator on 2018/1/7/007.
 */

public class VideoFragment extends Fragment {

    private CameraPreview mPreview;
    private CustomView custom;
    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private String path;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==200) {
                if (prepareVideoRecorder()) {
                    mMediaRecorder.start();
                } else {
                    releaseMediaRecorder();
                }
            } else if (msg.what==400) {
                // 视频录制成功
                VideoRecordingActivity activity = (VideoRecordingActivity) getActivity();
                activity.end(path);
                releaseMediaRecorder();
            } else if (msg.what==404){
                VideoRecordingActivity activity = (VideoRecordingActivity) getActivity();
                activity.end(null);
                releaseMediaRecorder();
                Toast.makeText(getContext(),"录制时间过短",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        FrameLayout content = (FrameLayout) view.findViewById(R.id.content);

        custom = (CustomView) view.findViewById(R.id.custom);
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(getContext(),mCamera);
        content.addView(mPreview);

        custom.setProgressListener(new CustomView.OnProgressListener() {
            @Override
            public void onStart() {
                handler.sendEmptyMessage(200);
            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onEnd(int totalTime) {
                if (isLongEnough(totalTime)) {
                    handler.sendEmptyMessage(400);
                } else {
                    handler.sendEmptyMessage(404);
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaRecorder();
        releaseCamera();
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    private boolean prepareVideoRecorder(){

        if (mCamera==null) return false;
        mMediaRecorder = new MediaRecorder();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_480P));
        mMediaRecorder.setOutputFile(getOutputMediaFile().toString());
        mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
        try {
            mMediaRecorder.setOrientationHint(90);
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;
    }


    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mCamera.lock();
        }
    }

    private Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    private File getOutputMediaFile(){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio/");
        file.mkdirs();

        try {
            File tempFile = File.createTempFile("recording", ".mp4", file);
            path = tempFile.getAbsolutePath();
            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isLongEnough(int time){
        return time > 2;
    }
}
