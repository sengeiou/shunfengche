package com.windmillsteward.jukutech.activity.shoppingcart.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.AddressListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddressListActivityAdapter extends BaseQuickAdapter<AddressListBean.ListBean,BaseViewHolder> {

    private int type;

    public AddressListActivityAdapter(@Nullable List<AddressListBean.ListBean> data,int type) {
        super(R.layout.item_address_list, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListBean.ListBean item) {
        if(item!=null) {
            helper.setText(R.id.tv_user_name,item.getUser_name())
                    .setText(R.id.tv_mobile_phone,item.getMobile_phone())
                    .setText(R.id.tv_address,item.getTotal_address_name())
                    .addOnClickListener(R.id.iv_select)
                    .addOnClickListener(R.id.tv_edit);
            ImageView iv_select = helper.getView(R.id.iv_select);
            if (item.isSelect()) {
                iv_select.setImageResource(R.mipmap.icon_select);
            } else {
                iv_select.setImageResource(R.mipmap.icon_select_n);
            }
            if (type == 1){
                helper.setVisible(R.id.tv_select,false);
                helper.setVisible(R.id.iv_select,false);
            }else{
                helper.setVisible(R.id.tv_select,true);
                helper.setVisible(R.id.iv_select,true);
            }
        }
    }
}
