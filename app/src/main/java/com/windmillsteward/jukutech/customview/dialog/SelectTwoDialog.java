package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.windmillsteward.jukutech.R;


/**
 * 选择对话框(两个选项)
 *@author chenyuqiang
 *@date 2016/12/28
 * Created by  2016 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SelectTwoDialog extends Dialog implements View.OnClickListener {

    public static final int TAKE_PHOTO = 111;//拍照
    public static final int PHOTO_ALBUM = 222;//相册

    private Button btn_1;//第一个按钮
    private Button btn_2;//第二个按钮
    private Button btn_cancle;//取消;
    private View view_line;//1和2之间的线

    private String btn1_name;//第一个按钮名字
    private String btn2_name;//第二个按钮名字
    private String btn3_name;//第三个按钮名字

    private DialogListner callBack;//监听回调
    private int action;//标识

    /**
     * @param context
     * @param callBack  回调方法中的第二个参数代表着是按下的第几个按钮
     * @param action    标识
     * @param btn1_name 第一个按钮名字
     * @param btn2_name 第二个按钮名字
     * @param btn3_name 第三个按钮名字
     */
    public SelectTwoDialog(Context context, int action, DialogListner callBack, String btn1_name, String btn2_name, String btn3_name) {
        super(context, R.style.Mydialog);
        this.callBack = callBack;
        this.btn1_name = btn1_name;
        this.btn2_name = btn2_name;
        this.btn3_name = btn3_name;
        this.action = action;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_two_botton);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        view_line = (View) findViewById(R.id.view_line);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);

        btn_1.setText(btn1_name);
        btn_2.setText(btn2_name);
        btn_cancle.setText(btn3_name);
    }

    /**
     * 在底部展示
     */
    public void showAtBottom() {
        Window win = getWindow();
        win.setGravity(Gravity.BOTTOM);
        show();
    }

    /**
     * 在顶部展示
     */
    public void showAtTop() {
        Window win = getWindow();
        win.setGravity(Gravity.TOP);
        show();
    }

    public void hideOneButton(){
        btn_1.setVisibility(View.GONE);
        view_line.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1://按钮一
                dismiss();
                callBack.selectDialogIndex(action, 1);
                break;
            case R.id.btn_2://按钮二
                dismiss();
                callBack.selectDialogIndex(action, 2);
                break;
            case R.id.btn_cancle://取消
                callBack.selectDialogIndex(action, 3);
                dismiss();
                break;
        }
    }

    public interface DialogListner {
        /**
         * 所远的对话框的选项位置
         *
         * @param positon 位置从上往下选,1开始
         */
        void selectDialogIndex(int action, int positon);
    }
}
