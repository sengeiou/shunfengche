package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.LabourServiceCenterListBean;

import java.util.List;

/**
 * 描述：劳务中心列表
 * 时间：2018/07/31
 * 作者：cyq
 */

public class LabourServiceCenterListAdapter extends BaseQuickAdapter<LabourServiceCenterListBean.ListBean, BaseViewHolder> {

    private Context context;

    public LabourServiceCenterListAdapter(Context context, List<LabourServiceCenterListBean.ListBean> list) {
        super(R.layout.item_labour_service_center, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LabourServiceCenterListBean.ListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_position_title, item.getTitle())
                    .setText(R.id.tv_position_salary, item.getStart_salary()+" - "+ item.getEnd_salary())
                    .setText(R.id.tv_position_area, item.getWork_second_area_name() + item.getWork_third_area_name())
                    .setText(R.id.tv_position_company, item.getTitle())
                    .setText(R.id.tv_position_job, item.getJob_name());
            int is_application = item.getIs_application();
            if (is_application == 1){
                helper.setText(R.id.tv_is_apply,"已申请");
            }else{
                helper.setText(R.id.tv_is_apply,"");
            }


        }
    }
}
