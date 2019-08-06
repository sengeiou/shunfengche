package com.windmillsteward.jukutech.activity.newpage.common.popwindow;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.adapter.CommonSinglePopupAdapter;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;

import java.util.List;

/**
 * 描述：共用的popwindow
 * author:JK
 * 2019/3/4
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */
public class CommonPopwindow<T> {

    private Context context;
    private LimitHeightListView listView;
    private EasyPopup mCirclePop;
    private ViewGroup view;

    private List<T> list;
    private TextView tv_title;
    private CommonSinglePopupAdapter adapter;

    public CommonPopwindow(Context context,ViewGroup view,List<T> list) {
        this.context = context;
        this.view = view;
        this.list = list;
        initView();
    }

    private void initView(){
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_popup_single_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);
        tv_title =(TextView) inflate.findViewById(R.id.tv_title);

        inflate.findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCirclePop != null)
                    mCirclePop.dismiss();
            }
        });


        mCirclePop = new EasyPopup(context)
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(view)
                .createPopup();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mCirclePop != null)
//                    mCirclePop.dismiss();
//            }
//        });
    }

    public LimitHeightListView getListView(){
        return listView;
    }

    public EasyPopup getCirclePop(){
        return mCirclePop;
    }

    public void setTitle(String title){
        if (tv_title!=null){
            tv_title.setText(TextUtils.isEmpty(title)?"选择":title);
        }
    }

    public void bindAdapter(){
        adapter = new CommonSinglePopupAdapter(context,list);
        listView.setAdapter(adapter);
    }

    public void updateAdapter(){
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

}
