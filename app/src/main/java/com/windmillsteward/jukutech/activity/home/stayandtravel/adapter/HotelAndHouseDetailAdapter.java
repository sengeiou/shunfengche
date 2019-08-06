package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseDetailAdapter extends BaseQuickAdapter<HotelAndHouseDetailBean.RoomListBean,BaseViewHolder> {

    private Context context;

    public HotelAndHouseDetailAdapter(Context context,@Nullable List<HotelAndHouseDetailBean.RoomListBean> data) {
        super(R.layout.item_hotelandhouse_detail, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotelAndHouseDetailBean.RoomListBean item) {

        ((TextView)helper.getView(R.id.tv_raw_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tv_room_name,item.getRoom_type_name())
                .setText(R.id.tv_true_price,"￥"+item.getDiscount_price())
                .setText(R.id.tv_raw_price,"￥"+item.getOrig_price())
                .addOnClickListener(R.id.tv_reserve);
        TextView tv_reserve = (TextView) helper.getView(R.id.tv_reserve);
        // 房间数量
        tv_reserve.setText("预订");
        tv_reserve.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_btn_reserve_bg));
    }
}
