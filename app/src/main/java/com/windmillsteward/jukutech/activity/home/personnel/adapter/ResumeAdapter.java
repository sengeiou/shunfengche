package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ResumeListBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/10
 * 作者：xjh
 */

public class ResumeAdapter extends BaseQuickAdapter<ResumeListBean.ListBean,BaseViewHolder> {

    private Context context;


    public ResumeAdapter(Context context, List<ResumeListBean.ListBean> list) {
        super(R.layout.item_resume,list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ResumeListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_name,item.getTrue_name())
                    .setText(R.id.iv_year,item.getAge()+"岁")
                    .setText(R.id.tv_edu,item.getEducation_name())
                    .setText(R.id.tv_work,item.getWork_year_name())
                    .setText(R.id.tv_salary,item.getSalary_show())
                    .setText(R.id.tv_position,item.getExpected_position())
                    .setText(R.id.tv_location,item.getCity_area_name());
            ImageView iv_sex = helper.getView(R.id.iv_sex);
            if (item.getSex()==1) {
                iv_sex.setImageResource(R.mipmap.icon_male);
            } else {
                iv_sex.setImageResource(R.mipmap.icon_famale);
            }
            x.image().bind(((CircleImageView) helper.getView(R.id.civ_header)), item.getUser_avatar_url(), ImageUtils.defaulHeader());

            Spanned html = Html.fromHtml("<font color=\"#787878\">"+item.getEducation_name()+" | "+item.getWork_year_name()+" | "+item.getSalary_show()+" | "+item.getExpected_position()+ "</font>");
            TextView tv_desc = helper.getView(R.id.tv_desc);
            tv_desc.setText(html);
            TextView tv_match_str = helper.getView(R.id.tv_match_str);
            tv_match_str.setText(item.getMatch_str());
            if (item.getIs_show_match()==1) {
                tv_match_str.setVisibility(View.VISIBLE);
            } else {
                tv_match_str.setVisibility(View.GONE);
            }
        }
    }
}
