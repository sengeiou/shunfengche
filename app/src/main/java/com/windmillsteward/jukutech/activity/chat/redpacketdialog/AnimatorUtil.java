package com.windmillsteward.jukutech.activity.chat.redpacketdialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;

/**
 * @Author CaiXi on  2016/12/9 00:00.
 * @Github: https://github.com/cxMax
 * @Description 动画Util
 */

public class AnimatorUtil {

    public void flipChange(final View view, final View view1) {
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, -5.0f);
        objectAnimator4.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.8f, 1.0f));
        objectAnimator4.setDuration(200);

        ObjectAnimator objectAnimatorFlip4 = ObjectAnimator.ofFloat(view1, "rotationY", -180.0f, -185.0f);
        objectAnimatorFlip4.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.8f, 1.0f));
        objectAnimatorFlip4.setDuration(200);

        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(view, "rotationY", -5.0f, -188.0f);
        objectAnimator5.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.4f, 1.0f));
        objectAnimator5.setDuration(300);
        objectAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                if (value < -90.0f) {
                    view.setVisibility(View.GONE);
                }
            }
        });
        ObjectAnimator objectAnimatorFlip5 = ObjectAnimator.ofFloat(view1, "rotationY", -185.0f, -368.0f);
        objectAnimatorFlip5.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.4f, 1.0f));
        objectAnimatorFlip5.setDuration(300);

        ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(view, "rotationY", -188.0f, -180.0f);
        objectAnimator6.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.7f, 1.0f));
        objectAnimator6.setDuration(200);

        ObjectAnimator objectAnimatorFlip6 = ObjectAnimator.ofFloat(view1, "rotationY", -368.0f, -360.0f);
        objectAnimatorFlip6.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.4f, 1.0f));
        objectAnimatorFlip6.setDuration(300);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator4).with(objectAnimatorFlip4);
        animatorSet.play(objectAnimatorFlip4).before(objectAnimator5);
        animatorSet.play(objectAnimator5).with(objectAnimatorFlip5);
        animatorSet.play(objectAnimatorFlip5).before(objectAnimator6);
        animatorSet.play(objectAnimator6).with(objectAnimatorFlip6);
        animatorSet.start();
    }

    public void cardChange(View view, Activity activity) {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.04f);
        objectAnimator1.setInterpolator(new PathInterpolator(0.1f, 0f, 0.45f, 1.0f));
        objectAnimator1.setDuration(200);

        ObjectAnimator objectAnimatorY1 = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.04f);
        objectAnimatorY1.setInterpolator(new PathInterpolator(0.1f, 0f, 0.45f, 1.0f));
        objectAnimatorY1.setDuration(200);

        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleX", 1.04f, 0.98f);
        objectAnimator2.setInterpolator(new PathInterpolator(0.25f, 0f, 0.7f, 1.0f));
        objectAnimator2.setDuration(200);

        ObjectAnimator objectAnimatorY2 = ObjectAnimator.ofFloat(view, "scaleY", 1.04f, 0.98f);
        objectAnimatorY2.setInterpolator(new PathInterpolator(0.25f, 0f, 0.7f, 1.0f));
        objectAnimatorY2.setDuration(200);

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "scaleX", 0.98f, 1.0f);
        objectAnimator3.setInterpolator(new PathInterpolator(0.3f, 0f, 0.6f, 1.0f));
        objectAnimator3.setDuration(200);

        ObjectAnimator objectAnimatorY3 = ObjectAnimator.ofFloat(view, "scaleY", 0.98f, 1.0f);
        objectAnimatorY3.setInterpolator(new PathInterpolator(0.3f, 0f, 0.6f, 1.0f));
        objectAnimatorY3.setDuration(200);

        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, -5.0f);
        objectAnimator4.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.8f, 1.0f));
        objectAnimator4.setDuration(200);

        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(view, "rotationY", -5.0f, 188.0f);
        objectAnimator5.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.4f, 1.0f));
        objectAnimator5.setDuration(300);

        ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(view, "rotationY", 180.0f, 188.0f);
        objectAnimator6.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.7f, 1.0f));
        objectAnimator6.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(bgChange(activity)).with(objectAnimator1).with(objectAnimatorY1);
        animatorSet.play(objectAnimatorY1).before(objectAnimator2);
        animatorSet.play(objectAnimator2).with(objectAnimatorY2);
        animatorSet.play(objectAnimatorY2).before(objectAnimator3);
        animatorSet.play(objectAnimator3).with(objectAnimatorY3);
        animatorSet.start();
    }

    public ObjectAnimator bgChange(final Activity activity) {
        final WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lp, "alpha", 1, 0.3f);
        objectAnimator.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.7f, 1.0f));
        objectAnimator.setDuration(200);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.alpha = (float) valueAnimator.getAnimatedValue();
                activity.getWindow().setAttributes(lp);
            }
        });
        return objectAnimator;
    }

}
