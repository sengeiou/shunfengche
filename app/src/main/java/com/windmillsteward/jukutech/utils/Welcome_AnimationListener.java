package com.windmillsteward.jukutech.utils;

import android.view.animation.Animation;

/**
 * 动画监听器
 * Created by lc on 2016/9/10.
 */
public class Welcome_AnimationListener implements Animation.AnimationListener {
    AnimationCallback mAnimationCallback;
    public Welcome_AnimationListener(AnimationCallback mAnimationCallback) {
        this.mAnimationCallback = mAnimationCallback;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mAnimationCallback.onComplete();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public interface AnimationCallback {
        /**
         * 动画结束回调
         */
        void onComplete();
    }
}
