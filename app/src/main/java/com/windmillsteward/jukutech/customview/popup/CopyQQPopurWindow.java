package com.windmillsteward.jukutech.customview.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;

public class CopyQQPopurWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View ll_chat, ll_friend, ll_face;
    private DataCallBack dataCallBack;

    public CopyQQPopurWindow(Context context, DataCallBack dataCallBack) {
        super(context);
        this.context = context;
        this.dataCallBack = dataCallBack;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_copy_qq, null);
        ll_chat = view.findViewById(R.id.ll_chat);//发起群聊
        ll_friend = view.findViewById(R.id.ll_friend);//添加好友
        ll_face = view.findViewById(R.id.ll_face);//面对面
        ll_chat.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_face.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.35));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }



    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_chat:
                dismiss();
                dataCallBack.clickGroup();
                break;
            case R.id.ll_friend:
                dismiss();
                dataCallBack.clickAddFriend();
                break;
            case R.id.ll_face:
                dismiss();
                dataCallBack.clickFaceGroup();
                break;
            default:
                break;
        }
    }

    public interface DataCallBack {
        void clickGroup();

        void clickAddFriend();

        void clickFaceGroup();
    }
}
