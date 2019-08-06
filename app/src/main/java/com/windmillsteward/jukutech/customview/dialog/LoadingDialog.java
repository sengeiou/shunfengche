package com.windmillsteward.jukutech.customview.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;


/**
 * 加载动画
 *
 * @time 2016/10/10 14:29
 */
public class LoadingDialog {
    private Context context;
    private Dialog dialog;
    private TextView tvContent;
    private ImageView loadImageView;//加载图片的背景
    private AnimationDrawable anim;//动画资源
    private RelativeLayout layout_rl_dialog_bg;//背景色值
    private Handler handler;

    public LoadingDialog(Context context) {
        this.context = context;
        initDialog();
    }

    /**
     * 监听back键
     */
    OnKeyListener keylistener = new OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                dialog.dismiss();
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * 初始化对话框
     */
    private void initDialog() {
        try {
            handler = new Handler();
            dialog = new Dialog(context, R.style.style_loading);
            dialog.setContentView(R.layout.layout_show_loading);
            dialog.setOnKeyListener(keylistener);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            loadImageView = (ImageView) dialog.findViewById(R.id.setting_Loading_ImageView);//加载图片背景
            layout_rl_dialog_bg = (RelativeLayout) dialog.findViewById(R.id.layout_rl_dialog_bg);//背景外框色值
            tvContent = (TextView) dialog.findViewById(R.id.tvContent);//文字提示
            anim = (AnimationDrawable) loadImageView.getBackground();//获取动画资源

//			layout_rl_dialog_bg.getBackground().setAlpha(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启动画
     */
    Runnable runStart = new Runnable() {
        @Override
        public void run() {
            anim.start();
            if (context!= null && dialog != null) {
                dialog.show();
            }
        }
    };

    /**
     * 停止动画
     */
    Runnable runStop = new Runnable() {
        @Override
        public void run() {
            if (isShow()) {
                anim.stop();
                if (context!= null && dialog != null) {
                    if (isShow()) {
                        dialog.dismiss();
                    }
                }
            }
        }
    };

    /**
     * 显示dialog
     *
     * @param content 显示的文字
     */
    public void showLoading(String content) {
        try {
            if (isShow()) {
                tvContent.setText(TextUtils.isEmpty(content) ? "" : content);
            } else {
                tvContent.setText(TextUtils.isEmpty(content) ? "" : content);
                handler.post(runStart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消对话框
     */
    public void dismiss() {
        if (dialog != null && loadImageView != null) {
            handler.postDelayed(runStop, 300);
        }
    }

    /**
     * @return 当前对话框是否正在弹窗
     */
    public boolean isShow() {
        if (dialog != null)
            return dialog.isShowing();
        return false;
    }

}
