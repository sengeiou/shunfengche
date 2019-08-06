package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;

import java.util.Map;

/**
 * 描述：单一的列表弹窗
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class SimpleListDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout linear_dialog_bg;
    private ListView listView;
    private Display display;
    private OnSelectListener onSelectListener;

    public SimpleListDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SimpleListDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_dialog_list, null);

        linear_dialog_bg = (LinearLayout) view.findViewById(R.id.linear_dialog_bg);
        listView = (ListView) view.findViewById(R.id.listView);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.IosAlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        linear_dialog_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }


    public SimpleListDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public SimpleListDialog setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
        return this;
    }

    public SimpleListDialog setSelectListener(final OnSelectListener listener) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (listener!=null) {
                    if (adapterView.getAdapter() instanceof SimpleListDialogAdapter) {
                        Map<String, Object> item = (Map<String, Object>) (adapterView.getAdapter()).getItem(i);
                        listener.onSelect(((int) item.get("id")),i, (String) item.get("text"));
                    }
                }

                dialog.dismiss();
            }
        });

        return this;
    }

    public interface OnSelectListener{
        void onSelect(int id, int pos, String text);
    }

    public void show() {
        dialog.show();
    }
}
