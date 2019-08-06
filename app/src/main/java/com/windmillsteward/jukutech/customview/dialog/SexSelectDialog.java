package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;

/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class SexSelectDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout linear_dialog_bg;
    private TextView tv_male,tv_female;
    private Display display;

    public SexSelectDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SexSelectDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_dialog_sexselect, null);

        linear_dialog_bg = (LinearLayout) view.findViewById(R.id.linear_dialog_bg);
        tv_male = (TextView) view.findViewById(R.id.tv_male);
        tv_female = (TextView) view.findViewById(R.id.tv_female);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.IosAlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        linear_dialog_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public SexSelectDialog setSelectListener(final OnSelectListener listener) {

        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onSelect(1);
                }
                dialog.dismiss();
            }
        });
        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onSelect(2);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public interface OnSelectListener{
        void onSelect(int sex);
    }

    public void show() {
        dialog.show();
    }
}
