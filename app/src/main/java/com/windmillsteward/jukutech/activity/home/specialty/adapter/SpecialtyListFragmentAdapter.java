package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SpecialtyListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class SpecialtyListFragmentAdapter extends BaseQuickAdapter<SpecialtyListBean.ListBean,BaseViewHolder> {



    public SpecialtyListFragmentAdapter(@Nullable List<SpecialtyListBean.ListBean> data) {
        super(R.layout.item_specialty_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialtyListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_price,item.getCommodity_reserve_price())
                    .setText(R.id.tv_area,item.getThird_area_name());

            GlideUtil.show(mContext,item.getCommodity_cover_picture(),(ImageView) helper.getView(R.id.iv_pic));
        }
    }
}
