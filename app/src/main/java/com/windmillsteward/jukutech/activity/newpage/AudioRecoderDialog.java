package com.windmillsteward.jukutech.activity.newpage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BasePopupWindow;

/**
 * Created by MarioStudio on 2016/5/12.
 */

public class AudioRecoderDialog extends BasePopupWindow {

    private ImageView imageView;
    private TextView textView;
    private TextView tv_status;

    public AudioRecoderDialog(Context context) {
        super(context);
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_recoder_dialog, null);
        imageView = (ImageView) contentView.findViewById(android.R.id.progress);
        textView = (TextView) contentView.findViewById(android.R.id.text1);
        tv_status = (TextView) contentView.findViewById(R.id.tv_status);
        setContentView(contentView);
    }

    public void setLevel(int level) {
        Drawable drawable = imageView.getDrawable();
        drawable.setLevel(3000 + 6000 * level / 100);
    }

    public void setTime(long time) {
        textView.setText(ProgressTextUtils.getProgressText(time));
    }

    public void setStatus(boolean cancel){
        if (cancel){//取消发送，背景变蓝色
            tv_status.setText("松开手指,取消发送");
            tv_status.setBackgroundResource(R.drawable.shape_bg_common_5);
        }else{
            tv_status.setText("手指上滑,取消发送");
            tv_status.setBackground(null);

        }
    }
}
