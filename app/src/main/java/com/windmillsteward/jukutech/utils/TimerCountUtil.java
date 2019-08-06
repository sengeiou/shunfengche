package com.windmillsteward.jukutech.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * 倒计时功能
 */
public class TimerCountUtil extends CountDownTimer {

    private TextView btn;//按钮控件
    private TimerChangeListner timeLister;//时间剩余监听

    private static final String STATE_FINISH = "finish";//正常状态下

    /**
     * @param millisInFuture    延时时长，单位毫秒
     * @param countDownInterval 时间变化 ，单位毫秒
     * @param btn               按钮
     */
    public TimerCountUtil(long millisInFuture, long countDownInterval,
                          TextView btn) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
    }

    public TimerCountUtil(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * @param listner 状态监听
     */
    public void setTimeSurListner(TimerChangeListner listner) {
        this.timeLister = listner;
    }

    @Override
    public void onFinish() {
        btn.setClickable(true);
        if (timeLister != null) {
            timeLister.onChangeFinish(STATE_FINISH);
        }
    }

    @Override
    public void onTick(long arg0) {
        btn.setClickable(false);
        if (timeLister != null) {
            timeLister.onChange(arg0);
        }
    }

    /**
     * 状态监听
     */
    public interface TimerChangeListner {
        /**
         * @param minis 毫秒
         */
        void onChange(long minis);

        /**
         * @param state 等态的状态
         */
        void onChangeFinish(String state);
    }

}
