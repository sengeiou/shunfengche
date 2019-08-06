package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.PositionListBean;
import com.windmillsteward.jukutech.customview.FlowLayout;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/8
 * 作者：xjh
 */

public class PositionAdapter extends BaseQuickAdapter<PositionListBean.ListBean,BaseViewHolder> {

    private Context context;

    public PositionAdapter(Context context, List<PositionListBean.ListBean> list) {
        super(R.layout.item_position,list);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, PositionListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_position_title,item.getTitle())
                    .setText(R.id.tv_position_salary,item.getSalary_show())
                    .setText(R.id.tv_position_area,item.getSecond_area_name()+item.getThird_area_name())
                    .setText(R.id.tv_position_company,item.getCompany_name())
                    .setText(R.id.tv_position_job,item.getJob_name());

            List<String> names = item.getBenefit_names();
            FlowLayout fl_welfare_type = helper.getView(R.id.fl_welfare_type);
            fl_welfare_type.removeAllViews();
            if (names!=null) {
                for (String name : names) {
                    View view = LayoutInflater.from(context).inflate(R.layout.view_welfare_type, fl_welfare_type, false);
                    TextView tv_welfare_type = (TextView) view.findViewById(R.id.tv_welfare_type);
                    tv_welfare_type.setText(name);
                    fl_welfare_type.addView(tv_welfare_type);
                }
            }
        }
    }
}
