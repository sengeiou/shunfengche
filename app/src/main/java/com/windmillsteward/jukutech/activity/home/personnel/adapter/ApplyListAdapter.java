package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ApplyListBean;
import com.windmillsteward.jukutech.bean.EmployResumeListBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;


/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class ApplyListAdapter extends BaseQuickAdapter<EmployResumeListBean.ListBean,BaseViewHolder> {

    private Context context;

    public ApplyListAdapter(Context context, List<EmployResumeListBean.ListBean> list) {
        super(R.layout.item_apply,list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, EmployResumeListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_name,item.getTrue_name())
                    .setText(R.id.iv_year,item.getAge()+"岁")
                    .setText(R.id.tv_edu,item.getEducation_name())
                    .setText(R.id.tv_work,item.getWork_year_name())
                    .setText(R.id.tv_salary,item.getSalary_show())
                    .setText(R.id.tv_location,item.getCity_area_name())
                    .setText(R.id.tv_position,item.getJob_name());
            x.image().bind(((CircleImageView) helper.getView(R.id.civ_header)), item.getUser_avatar_url(), ImageUtils.defaulHeader());
            if (item.getIs_view()==1) {
                helper.setText(R.id.tv_look,"未查看");
                helper.setTextColor(R.id.tv_look,ContextCompat.getColor(context,R.color.color_text_78));
            } else if(item.getIs_view()==2) {
                helper.setText(R.id.tv_look,"已查看");
                helper.setTextColor(R.id.tv_look,ContextCompat.getColor(context,R.color.color_price));
            }

            if (item.getSex()==1) {
                ((ImageView) helper.getView(R.id.iv_sex)).setImageResource(R.mipmap.icon_male);
            } else {
                ((ImageView) helper.getView(R.id.iv_sex)).setImageResource(R.mipmap.icon_famale);
            }
        }
    }
}
