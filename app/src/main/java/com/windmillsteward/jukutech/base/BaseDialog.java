package com.windmillsteward.jukutech.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;


/**
 * 弹框工具类,基类
 */
public class BaseDialog {
    private Dialog dialog;
    private Context context;
    private Display display;

    public BaseDialog(Context ctx) {
        this.context = ctx;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }
    /**
     * 带有两个选项按钮的对话框
     *
     * @param title                  标题
     * @param content                提示内容
     * @param confirmName            确认(左标题名字)
     * @param cancelName             取消(右标题名字)
     * @param onClickListenerConfirm 确认点击事件(左）
     * @param onClickListenerCancel  取消点击事件（右）
     * @return 对话框对象
     */
    public Dialog showTwoButton(String title, String content, String confirmName, String cancelName, View.OnClickListener onClickListenerConfirm, View.OnClickListener onClickListenerCancel) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_cancel, null);
        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
        Button btn_confirm = (Button) v.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
        tv_title.setText(title);
        tv_content.setText(content);
        btn_confirm.setText(confirmName);
        btn_cancel.setText(cancelName);
        btn_confirm.setOnClickListener(onClickListenerConfirm);
        btn_cancel.setOnClickListener(onClickListenerCancel);
        return initDialog(R.style.style_dialogTranslucent, v, false);
    }

    /**
     * 带有两个选项按钮的对话框
     *
     * @param title                  标题
     * @param content                提示内容
     * @param confirmName            确认(左标题名字)
     * @param cancelName             取消(右标题名字)
     * @param onClickListenerConfirm 确认点击事件(左）
     * @param onClickListenerCancel  取消点击事件（右）
     * @return 对话框对象
     */
    public Dialog showNewTwoButton(String title, CharSequence content, String confirmName, String cancelName, View.OnClickListener onClickListenerConfirm, View.OnClickListener onClickListenerCancel) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_cancel, null);
        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
        Button btn_confirm = (Button) v.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
        tv_title.setText(title);
        tv_content.setText(content);
        btn_confirm.setText(confirmName);
        btn_cancel.setText(cancelName);
        btn_confirm.setOnClickListener(onClickListenerConfirm);
        btn_cancel.setOnClickListener(onClickListenerCancel);
        return initDialog(R.style.style_dialogTranslucent, v, false);
    }
    /**
     * 带有三个选项按钮的对话框
     *
     * @param title                  标题
     * @param content                提示内容
     * @param confirmName            确认(左标题名字)
     * @param middleName             (中间标题名字)
     * @param cancelName             取消(右标题名字)
     * @param onClickListenerConfirm 确认点击事件(左）
     * @param onClickListenerMiddle 确认点击事件(中）
     * @param onClickListenerCancel  取消点击事件（右）
     * @return 对话框对象
     */
    public Dialog showThreeButton(String title, String content, String confirmName, String middleName,String cancelName, View.OnClickListener onClickListenerConfirm,View.OnClickListener onClickListenerMiddle, View.OnClickListener onClickListenerCancel) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_middle_cancel, null);
        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
        Button btn_confirm = (Button) v.findViewById(R.id.btn_confirm);
        Button btn_middle = (Button) v.findViewById(R.id.btn_middle);
        Button btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
        tv_title.setText(title);
        tv_content.setText(content);
        btn_confirm.setText(confirmName);
        btn_middle.setText(middleName);
        btn_cancel.setText(cancelName);
        btn_confirm.setOnClickListener(onClickListenerConfirm);
        btn_middle.setOnClickListener(onClickListenerMiddle);
        btn_cancel.setOnClickListener(onClickListenerCancel);
        return initDialog(R.style.style_dialogTranslucent, v, false);
    }


    /**
     * 展示列表
     *
     * @param title               标题
     * @param act                 基Activity
     * @param withDrawListAdapter 列表适配器
     * @param isHideTitle         是否隐藏标题
     * @return
     */
    public Dialog showListView(String title, BaseActivity act, BaseAdapter withDrawListAdapter, boolean isHideTitle) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
        if (title != null || !title.equals("")) {
            tv_title.setText(title);
        }
        ListView lv_content = (ListView) v.findViewById(R.id.lv_content);
        lv_content.setAdapter(withDrawListAdapter);
        if (isHideTitle) {
            tv_title.setVisibility(View.GONE);
        }
        return initDialog(R.style.style_dialogTranslucent, v, true);
    }

    /**
     * @param themeId    显示主题
     * @param view       对话框布局
     * @param cancelable 点外框是否取消
     * @return
     */
    public Dialog initDialog(int themeId, View view, boolean cancelable) {
        //设置宽度和高度
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        ViewGroup.LayoutParams layoutParams = ((Activity) context).getWindow().getAttributes();
        layoutParams.width = display.getWidth();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //主题
        dialog = new Dialog(context, themeId);
        dialog.setContentView(view, layoutParams);
        //设置监听事件
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.show();
        return dialog;
    }



    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

    public boolean isShown() {
        if (dialog == null)
            return false;
        return dialog.isShowing();
    }

}
