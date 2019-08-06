package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.CarClassListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/2/002
 * 作者：xjh
 */
public class BrandListAdapter extends BaseQuickAdapter<CarClassListBean.SeriesListBean,BaseViewHolder> {

    public BrandListAdapter(@Nullable List<CarClassListBean.SeriesListBean> data) {
        super(R.layout.item_brand_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarClassListBean.SeriesListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_brand_name,item.getSeries_name());
        }
    }
}
