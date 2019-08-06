package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.AboutUsBean;

import java.util.List;

/**
 * 描述：服务中心模块适配器
 * 时间：2018/11/20
 * 作者：cyq
 */
public class AboutUsAdapter extends BaseQuickAdapter<AboutUsBean, BaseViewHolder> {


    public AboutUsAdapter(@Nullable List<AboutUsBean> data) {
        super(R.layout.item_service_center_module, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AboutUsBean item) {
        if (item != null) {
            helper.setText(R.id.tv_service_module_name, item.getRemark());
        }
    }
}
