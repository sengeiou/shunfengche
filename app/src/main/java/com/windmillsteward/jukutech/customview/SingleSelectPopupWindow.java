package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.SingleSelectAdapter;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;

import java.util.ArrayList;

/**
 * 描述：单选--Popwindow(居中)
 * author:cyq
 * 2017/10/30
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SingleSelectPopupWindow extends PopupWindow implements SingleSelectAdapter.DataCallBack, PopupWindow.OnDismissListener, View.OnTouchListener {

    private Context mContext;
    private LinearLayout lay_ll_top;
    private LinearLayout lay_ll_content;
    private ArrayList<PublicSelectInfo> publicSelectInfos;
    private DataCallBack dataCallBack;

    public SingleSelectPopupWindow(Context context, ArrayList<PublicSelectInfo> publicSelectInfos, DataCallBack dataCallBack) {
        this.mContext = context;
        this.publicSelectInfos = publicSelectInfos;
        this.dataCallBack = dataCallBack;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pop_single_select, null);
        this.setContentView(view);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setClippingEnabled(false);
//        int displayHeight = ((Activity) mContext).getWindowManager()
//                .getDefaultDisplay().getHeight();//获取屏幕高度
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);//根据线在屏幕的位置设置pop起点的位置


        lay_ll_top = (LinearLayout) view.findViewById(R.id.lay_ll_top);
        lay_ll_content = (LinearLayout) view.findViewById(R.id.lay_ll_content);
        ListView lv_popip_content = (ListView) view.findViewById(R.id.lv_popip_content);

        lay_ll_top.getBackground().setAlpha(150);

        SingleSelectAdapter singleSelectAdapter = new SingleSelectAdapter(mContext, publicSelectInfos, this);
        lv_popip_content.setAdapter(singleSelectAdapter);
        //添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(this);
        setOnDismissListener(this);
    }

//    public void setMargins() {
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lay_ll_content.getLayoutParams();
//        int statusBarHeight = Hawk.get(Define.STATUS_BAR_HIGH, 0);
//        int line_H = GraphicUtil.dp2px(mContext, 42f);
//        params.setMargins(0, (line_H + statusBarHeight), 0, 0);
//        lay_ll_content.setLayoutParams(params);
//    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            dismiss();
        }
        return true;
    }

    @Override
    public void onItemClick(int tagId, int id, int type, String values) {
        dataCallBack.click_item(tagId, id, type, values);
        dismiss();
    }

    @Override
    public void onDismiss() {
        dismiss();
    }

    public interface DataCallBack {
        void click_item(int position, int id, int type, String values);
    }
}
