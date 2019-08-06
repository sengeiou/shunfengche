package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.LogisticsInfoListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class LogisticsActivityAdapter extends BaseQuickAdapter<LogisticsInfoListBean.LogisticsInfoBean, BaseViewHolder> {

    public LogisticsActivityAdapter(@Nullable List<LogisticsInfoListBean.LogisticsInfoBean> data) {
        super(R.layout.item_logistics, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsInfoListBean.LogisticsInfoBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_time,item.getAcceptTime())
                    .setText(R.id.tv_info,item.getAcceptStation());
            ImageView iv_status = helper.getView(R.id.iv_status);
            View v_top_line = helper.getView(R.id.v_top_line);
            View v_bottom_lin = helper.getView(R.id.v_bottom_lin);
            if (helper.getAdapterPosition()-getHeaderLayoutCount()==getItemCount()-getHeaderLayoutCount()-1) {
                iv_status.setImageResource(R.mipmap.icon_start);
                v_top_line.setVisibility(View.VISIBLE);
                v_bottom_lin.setVisibility(View.INVISIBLE);
            } else {
                iv_status.setImageResource(R.mipmap.icon_step);
                v_top_line.setVisibility(View.VISIBLE);
                v_bottom_lin.setVisibility(View.VISIBLE);
                if (helper.getAdapterPosition()-getHeaderLayoutCount()==0) {
                    v_top_line.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

}
