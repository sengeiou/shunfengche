package com.windmillsteward.jukutech.activity.home.commons.pay;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/26/026
 * 作者：xjh
 */
public class ChoiceCouponListAdapter extends BaseQuickAdapter<MyCouponBean.ListBean,BaseViewHolder> {

    public ChoiceCouponListAdapter(@Nullable List<MyCouponBean.ListBean> data) {
        super(R.layout.item_choicecoupon_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCouponBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_price,item.getCoupon_money()+"")
                    .setText(R.id.tv_title,item.getCoupon_name())
                    .setText(R.id.tv_time, DateUtil.StampTimeToDate(String.valueOf(item.getStart_time()),"yyyy-MM-dd")+"至"+DateUtil.StampTimeToDate(String.valueOf(item.getEnd_time()),"yyyy-MM-dd"))
                    .addOnClickListener(R.id.iv_select);
            ImageView iv_select = helper.getView(R.id.iv_select);
            if (item.isSelect()) {
                iv_select.setImageResource(R.mipmap.icon_select);
            } else {
                iv_select.setImageResource(R.mipmap.icon_select_n);
            }
        }
    }
}
