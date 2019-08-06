package com.windmillsteward.jukutech.activity.home.insurance.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MyApplication;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.BigHealthListBean;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：大健康列表适配器
 * 时间：2018年8月2日 16:16:45
 * 作者：lc
 */
public class BigHealthListFragmentAdapter extends BaseQuickAdapter<BigHealthListBean.ListBean,BaseViewHolder> {

    public BigHealthListFragmentAdapter(@Nullable List<BigHealthListBean.ListBean> data) {
        super(R.layout.item_big_health_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BigHealthListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_money,"￥" + item.getPrice())
                    .setText(R.id.tv_content,item.getSecond_title());
            ImageView iv_content =  helper.getView(R.id.iv_content);
            GlideUtil.show(mContext,item.getCover_url(),iv_content,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);
//            Glide.with(MyApplication.instance).load(item.getCover_url()).error(R.mipmap.icon_default_pic).into(iv_content);
        }

    }
}
