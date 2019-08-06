package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;

/**
 * 描述：
 * 时间：2018/1/8
 * 作者：xjh
 */

public class BottomDialog {

    private Dialog dialog;

    public BottomDialog(Context context, final OnSelectListener selectListener) {
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);

        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_bottomdialog, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.tv_choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.tv_takePhoto);
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onChoosePhoto();
                dialog.dismiss();
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onTakePhoneClick();
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);

        dialog.show();
    }

    public interface OnSelectListener{
        void onTakePhoneClick();
        void onChoosePhoto();
    }
}
