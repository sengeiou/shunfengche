package com.windmillsteward.jukutech.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.TestItemsListBean;
import com.windmillsteward.jukutech.bean.WelfareBean;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;

import java.util.Set;


/**
 * 描述：
 * 时间：2018/1/16
 * 作者：xjh
 */

public class TestItemsListDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout linear_dialog_bg;
    private TagFlowLayout flowLayout;
    private TextView sure,cancel;
    private Display display;
    private Set<Integer> ids;

    public TestItemsListDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public TestItemsListDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_dialog_mult_choice, null);

        linear_dialog_bg = (LinearLayout) view.findViewById(R.id.linear_dialog_bg);
        flowLayout = (TagFlowLayout) view.findViewById(R.id.tagFlowLayout);
        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                ids = selectPosSet;
            }
        });
        sure = (TextView) view.findViewById(R.id.tv_sure);
        cancel = (TextView)view.findViewById(R.id.tv_cancel);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.IosAlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        linear_dialog_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public TestItemsListDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public TestItemsListDialog setAdapter(TagAdapter<TestItemsListBean> adapter) {
        flowLayout.setAdapter(adapter);
        return this;
    }

    public TestItemsListDialog setOnListener(final OnListener listener) {

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelect(flowLayout.getSelectedList());
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return this;
    }

    public interface OnListener{
        void onSelect(Set<Integer> ids);
    }

    public void show() {
        dialog.show();
    }
}
