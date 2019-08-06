package com.windmillsteward.jukutech.activity.newpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.model.GongzhongModel;
import com.windmillsteward.jukutech.activity.newpage.model.BenefitTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.CoachGradeListModel;
import com.windmillsteward.jukutech.activity.newpage.model.ServiceModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhongdgServiceModel;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/9
 * 作者：xjh
 */

public class CommonPopupAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> list;

    public CommonPopupAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PopupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_gongzhong, parent, false);
            holder = new PopupHolder();
            holder.tvPopupItem = (TextView) convertView.findViewById(R.id.tv_popupItem);
            holder.iv_flag = (ImageView) convertView.findViewById(R.id.iv_flag);
            convertView.setTag(holder);
        } else {
            holder = (PopupHolder) convertView.getTag();
        }

        if (list.get(position) instanceof GongzhongModel) {
            GongzhongModel bean = (GongzhongModel) list.get(position);
            if (bean != null) {
                holder.tvPopupItem.setText(bean.getName());

                if (bean.isSelect()) {
                    holder.iv_flag.setImageResource(R.mipmap.select);
                } else {
                    holder.iv_flag.setImageResource(R.mipmap.unselect);
                }
            }
        }
        if (list.get(position) instanceof ServiceModel) {
            ServiceModel bean = (ServiceModel) list.get(position);
            if (bean != null) {
                holder.tvPopupItem.setText(bean.getService_content_name());

                if (bean.isSelect()) {
                    holder.iv_flag.setImageResource(R.mipmap.select);
                } else {
                    holder.iv_flag.setImageResource(R.mipmap.unselect);
                }
            }
        }

        if (list.get(position) instanceof CoachGradeListModel) {
            CoachGradeListModel bean = (CoachGradeListModel) list.get(position);
            if (bean != null) {
                holder.tvPopupItem.setText(bean.getCoach_grade_name());

                if (bean.isSelect()) {
                    holder.iv_flag.setImageResource(R.mipmap.select);
                } else {
                    holder.iv_flag.setImageResource(R.mipmap.unselect);
                }
            }
        }

        if (list.get(position) instanceof BenefitTypeModel) {
            BenefitTypeModel bean = (BenefitTypeModel) list.get(position);
            if (bean != null) {
                holder.tvPopupItem.setText(bean.getWork_year_name());

                if (bean.isSelect()) {
                    holder.iv_flag.setImageResource(R.mipmap.select);
                } else {
                    holder.iv_flag.setImageResource(R.mipmap.unselect);
                }
            }
        }

        if (list.get(position) instanceof ZhongdgServiceModel) {
            ZhongdgServiceModel bean = (ZhongdgServiceModel) list.get(position);
            if (bean != null) {
                holder.tvPopupItem.setText(bean.getName());

                if (bean.isSelect()) {
                    holder.iv_flag.setImageResource(R.mipmap.select);
                } else {
                    holder.iv_flag.setImageResource(R.mipmap.unselect);
                }
            }
        }


        return convertView;
    }

    class PopupHolder {
        TextView tvPopupItem;
        ImageView iv_flag;
    }


}
