package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.customview.AssortPinyin.AssortPinyinList;
import com.windmillsteward.jukutech.customview.AssortPinyin.ComparatorMember;
import com.windmillsteward.jukutech.customview.AssortPinyin.LanguageComparator_CN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 城市选择适配器
 *
 * @author cyq
 */
public class SelectCityAdapter extends BaseExpandableListAdapter {

    private List<String> strList;// 字符串
    private AssortPinyinList assort = new AssortPinyinList();
    private LayoutInflater inflater;

    private List<CityBean> list;
    private ComparatorMember cnSort = new ComparatorMember(); // 中文排序比较器
    private LanguageComparator_CN keySort = new LanguageComparator_CN();// 首字比较器

    public SelectCityAdapter(Context context, List<CityBean> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        if (strList == null) {
            strList = new ArrayList<String>();
        }
        sort();// 排序
    }

    private void sort() {
        // 分类
        for (CityBean str : list) {
            assort.getMemberHashList().add(str);//
        }
        assort.getMemberHashList().sortKeyComparator(keySort);
        for (int i = 0, length = assort.getMemberHashList().size(); i < length; i++) {
            Collections.sort((assort.getMemberHashList().getValueListIndex(i)),
                    cnSort);
        }
    }

    public Object getChild(int group, int child) {
        return assort.getMemberHashList().getValueIndex(group, child);
    }

    public long getChildId(int group, int child) {
        return child;
    }

    public View getChildView(int group, int child, boolean arg2,
                             View contentView, ViewGroup arg4) {
        childViewHolder holder = null;
        if (contentView == null) {
            holder = new childViewHolder();
            contentView = inflater.inflate(R.layout.item_select_city_child, arg4, false);
            holder.tv_child_name = (TextView) contentView.findViewById(R.id.tv_child_name);

            contentView.setTag(holder);
        } else {
            holder = (childViewHolder) contentView.getTag();
        }

        holder.tv_child_name.setText(assort.getMemberHashList()
                .getValueIndex(group, child).getArea_name());

        return contentView;
    }

    public int getChildrenCount(int group) {
        int size = assort.getMemberHashList().getValueListIndex(group).size();
        return size;
    }

    public Object getGroup(int group) {
        return assort.getMemberHashList().getValueListIndex(group);
    }

    public int getGroupCount() {
        return list == null ? 0 : assort.getMemberHashList().size();
    }

    public long getGroupId(int group) {
        return group;
    }

    public View getGroupView(int group, boolean arg1, View contentView,
                             ViewGroup arg3) {
        parentViewHolder holder;
        if (contentView == null) {
            holder = new parentViewHolder();
            contentView = inflater.inflate(R.layout.item_select_city_group, arg3, false);
            contentView.setClickable(true);
            holder.tv_group_name = (TextView) contentView.findViewById(R.id.tv_group_name);
            contentView.setTag(holder);
        } else {
            holder = (parentViewHolder) contentView.getTag();
        }
        String name = assort.getFirstChar(assort.getMemberHashList()
                .getValueIndex(group, 0).getArea_name());
        holder.tv_group_name.setText(name);
        return contentView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    public AssortPinyinList getAssort() {
        return assort;
    }

    /**
     * 父控件的holder
     *
     * @author zhuxian
     */
    private class parentViewHolder {
        TextView tv_group_name;// 字母
    }

    /**
     * 子控件item的holder
     */
    private class childViewHolder {
        private TextView tv_child_name;// 名字
    }

    /**
     * 重新设置数值
     */
    public void setList(List<CityBean> list) {
        // 分类

        this.list.clear();
        this.list = list;
        assort.getMemberHashList().clear();
        notifyDataSetChanged();
        sort();// 排序
    }
}
