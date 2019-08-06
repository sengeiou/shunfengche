package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.RentCarListBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class RentCarListFragmentAdapter extends BaseQuickAdapter<RentCarListBean.ListBean,BaseViewHolder> {


    public RentCarListFragmentAdapter(@Nullable List<RentCarListBean.ListBean> data) {
        super(R.layout.item_rentcar_list, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, RentCarListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_go_off, DateUtil.StampTimeToDate(String.valueOf(item.getGo_off()),"yyyy-MM-dd HH:mm"))
                    .setText(R.id.tv_vehicle_module_name,item.getVehicle_module_name())
                    .setText(R.id.tve_unoccupied_num,"剩余"+item.getUnoccupied_num()+"位")
                    .setText(R.id.tv_departure_place_name,item.getDeparture_place_name())
                    .setText(R.id.tv_destination_place_name,item.getDestination_place_name())
                    .addOnClickListener(R.id.iv_call);
            if (item.getType()==1) {  // 车主
                LinearLayout linear_type = (LinearLayout) helper.getView(R.id.linear_type);
                linear_type.setVisibility(View.VISIBLE);
            } else if (item.getType()==2) {
                LinearLayout linear_type = (LinearLayout) helper.getView(R.id.linear_type);
                linear_type.setVisibility(View.GONE);
            }
        }
    }
}
