package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.SpecialtyDetailBean;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class GoodsModelSelectDialog {

    private Context context;
    private Dialog dialog;
    private TextView tv_commodity_price;
    private TextView tv_commodity_inventory;
    private ImageView iv_pic;
    private ImageView iv_close;
    private TagFlowLayout commodity_model_name;
    private TextView iv_add;
    private TextView tv_number;
    private TextView iv_reduce;
    private TextView tv_add_car;
    private TextView tv_buy_now;

    public GoodsModelSelectDialog(Context context, final OnSelectListener selectListener) {
        this.context = context;
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_goods_moel, null);
        tv_commodity_price = inflate.findViewById(R.id.tv_commodity_price);
        tv_commodity_inventory = inflate.findViewById(R.id.tv_commodity_inventory);
        iv_pic = inflate.findViewById(R.id.iv_pic);
        iv_close = inflate.findViewById(R.id.iv_close);
        commodity_model_name = inflate.findViewById(R.id.commodity_model_name);
        iv_add = inflate.findViewById(R.id.iv_add);
        tv_number = inflate.findViewById(R.id.tv_number);
        iv_reduce = inflate.findViewById(R.id.iv_reduce);
        tv_add_car = inflate.findViewById(R.id.tv_add_car);
        tv_buy_now = inflate.findViewById(R.id.tv_buy_now);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) + 1;
                if (i==0) {
                    i=1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) - 1;
                if (i==0) {
                    i=1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        tv_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectListener!=null) {
                    selectListener.onAddCarClick();
                }
            }
        });
        tv_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectListener!=null) {
                    selectListener.onBuyNowClick();
                }
            }
        });

        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);

        dialog.show();
    }

    public void setData(List<SpecialtyDetailBean.CommodityModelListBean> data) {
        if (data==null || data.size()==0) {
            return;
        }
        SpecialtyDetailBean.CommodityModelListBean bean = data.get(0);
        tv_commodity_price.setText("￥"+bean.getCommodity_price());
        tv_commodity_inventory.setText("库存" + bean.getCommodity_inventory());
        GlideUtil.show(context,bean.getCommodity_model_image(),iv_pic);
        final LayoutInflater inflater = LayoutInflater.from(context);
        commodity_model_name.setAdapter(new TagAdapter<SpecialtyDetailBean.CommodityModelListBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, SpecialtyDetailBean.CommodityModelListBean o) {
                TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                view.setText(o.getCommodity_model_name());
                return view;
            }
        });
        dialog.show();
    }

    public interface OnSelectListener {
        void onAddCarClick();

        void onBuyNowClick();
    }
}
