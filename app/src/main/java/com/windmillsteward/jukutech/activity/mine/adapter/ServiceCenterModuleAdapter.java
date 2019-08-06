package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ModuleIntroduceBean;

import java.util.List;

/**
 * 描述：服务中心模块适配器
 * 时间：2018/3/12/
 * 作者：cyq
 */
public class ServiceCenterModuleAdapter extends BaseQuickAdapter<ModuleIntroduceBean, BaseViewHolder> {


    public ServiceCenterModuleAdapter(@Nullable List<ModuleIntroduceBean> data) {
        super(R.layout.item_service_center_module, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ModuleIntroduceBean item) {
        if (item != null) {
            helper.setText(R.id.tv_service_module_name, item.getName());
        }
    }
}
