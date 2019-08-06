package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SearchModuleBean;

import java.util.List;

/**
 * 描述：管家快讯列表适配器
 * author:cyq
 * 2018-04-10
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SearchSelectTypeAdapter extends BaseQuickAdapter<SearchModuleBean.ListBean,BaseViewHolder> {


    private Context context;
    private List<SearchModuleBean.ListBean> list;

    public SearchSelectTypeAdapter(Context context, List<SearchModuleBean.ListBean> list) {
        super(R.layout.item_search_select_type,list);
        this.context = context;
        this.list = list;
    }



    @Override
    protected void convert(BaseViewHolder helper, SearchModuleBean.ListBean item) {
            helper.setText(R.id.tv_module_name, TextUtils.isEmpty(item.getModule_name())?"":item.getModule_name());
            helper.setText(R.id.tv_module_number,item.getNum()+"条信息");
    }
}
