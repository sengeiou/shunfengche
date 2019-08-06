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
import com.windmillsteward.jukutech.activity.newpage.model.AgeAreaModel;
import com.windmillsteward.jukutech.activity.newpage.model.CoachGradeListModel;
import com.windmillsteward.jukutech.activity.newpage.model.CoachSubjectListModel;
import com.windmillsteward.jukutech.activity.newpage.model.EducationModel;
import com.windmillsteward.jukutech.activity.newpage.model.PersonTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.SalaryTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkExperienceModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTimeModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkYearModel;
import com.windmillsteward.jukutech.bean.RequireClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/9
 * 作者：xjh
 */

public class CommonSinglePopupAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> list;

    public CommonSinglePopupAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ?0:list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        PopupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_single_select, parent, false);
            holder = new PopupHolder();
            holder.tvPopupItem = (TextView) convertView.findViewById(R.id.tv_popupItem);
            holder.iv_select = (ImageView) convertView.findViewById(R.id.iv_select);
            convertView.setTag(holder);
        } else {
            holder = (PopupHolder) convertView.getTag();
        }

        final T data = list.get(position);
        //劳务工工种
        if (data instanceof GongzhongModel){
            GongzhongModel model = (GongzhongModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getName());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //学历
        if (data instanceof EducationModel){
            EducationModel educationModel = (EducationModel) data;
            if (educationModel != null){
                holder.tvPopupItem.setText(educationModel.getEducation_background_name());
                if (educationModel.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //工作经验
        if (data instanceof WorkYearModel){
            WorkYearModel model = (WorkYearModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getWork_year_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //科目
        if (data instanceof CoachSubjectListModel){
            CoachSubjectListModel model = (CoachSubjectListModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getCoach_subject_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //年级
        if (data instanceof CoachGradeListModel){
            CoachGradeListModel model = (CoachGradeListModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getCoach_grade_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //保姆类型
        if (data instanceof PersonTypeModel){
            PersonTypeModel model = (PersonTypeModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getPerson_type_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }

        //保姆工作时间
        //钟点工工作小时
        if (data instanceof WorkTimeModel){
            WorkTimeModel model = (WorkTimeModel)data;
            if (model != null){
                holder.tvPopupItem.setText(model.getName());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //保姆从业经验
        if (data instanceof WorkExperienceModel){
            WorkExperienceModel model = (WorkExperienceModel)data;
            if (model != null){
                holder.tvPopupItem.setText(model.getWork_experience_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }

        //选择年龄
        if (data instanceof AgeAreaModel){
            AgeAreaModel model = (AgeAreaModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getName());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }

        //月收入
        if (data instanceof SalaryTypeModel){
            SalaryTypeModel model = (SalaryTypeModel) data;
            if (model != null){
                holder.tvPopupItem.setText(model.getSalary_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }
        //智慧生活分类
        if (data instanceof RequireClassBean){
            RequireClassBean model =(RequireClassBean)data;
            if (model != null){
                holder.tvPopupItem.setText(model.getClass_name());
                if (model.isSelect()){
                    holder.iv_select.setVisibility(View.VISIBLE);
                }else{
                    holder.iv_select.setVisibility(View.GONE);
                }
            }
        }

        return convertView;
    }

    class PopupHolder {
        TextView tvPopupItem;
        ImageView iv_select;
    }

    public interface OnPopItemClickListener{
        void  itemClickListener();
    }

}
