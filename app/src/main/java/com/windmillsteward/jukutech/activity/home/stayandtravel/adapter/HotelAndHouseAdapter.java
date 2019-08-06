package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：酒店房源适配器
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class HotelAndHouseAdapter extends BaseQuickAdapter<HotelAndHouseBean.ListBean, BaseViewHolder> {

    public HotelAndHouseAdapter(@Nullable List<HotelAndHouseBean.ListBean> data) {
        super(R.layout.item_hotelandhouse, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotelAndHouseBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getHotel_name())
                    .setText(R.id.tv_type,item.getHotel_type_name())
                    .setText(R.id.tv_location,item.getSecond_area_name()+" "+item.getThird_area_name())
                    .setText(R.id.tv_price,item.getStart_price()+"");
            x.image().bind(((ImageView) helper.getView(R.id.iv_img)),item.getCover_url(), ImageUtils.defaulHeader());
        }
    }
}
