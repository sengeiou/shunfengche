package com.windmillsteward.jukutech.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/11 0011.
 * 倒计时类
 */
public class CountDownUtils {

    private Timer timer;
    private TimerTaskRunnable timerTaskRunnable;
    private int count;
    private int delay;
    private int period;
    private int startNum = 0;//计数
    private OnCountDownUpdateListener listener;

    public CountDownUtils(int count, int delay, int period){
        this.count = count;
        this.delay = delay;
        this.period = period;
    }

    /** 启动倒计时 */
    public void startCountDown(){
        //logger.i(" startCountDown !!");
        stopCountDown();
        timer = new Timer();
        timerTaskRunnable = new TimerTaskRunnable();
        timer.schedule(timerTaskRunnable, delay*1000, period*1000);
    }

    /** 停止倒计时 */
    public void stopCountDown(){
        //logger.i(" stopCountDown !!");
        if (timer != null)
            timer.cancel();
        if (timerTaskRunnable != null)
            timerTaskRunnable.cancel();
        startNum = 0;
    }

    private class TimerTaskRunnable extends TimerTask {
        @Override
        public void run() {
            startNum++;
            boolean isEnd = startNum >= count;
            //logger.i(" TimerTaskRunnable : "+startNum +" ** End Flag : "+isEnd);
            if (isEnd)
                stopCountDown();
            if (listener != null) {
                int cutNum = count - startNum;
                listener.countDownUpdate(isEnd, cutNum < 0 ? 0 : cutNum);
            }
        }
    }

    public void setOnCountDownUpdateListener(OnCountDownUpdateListener listener){
        this.listener = listener;
    }

    public interface OnCountDownUpdateListener{

        void countDownUpdate(boolean isEnd, int number);
    }
}
