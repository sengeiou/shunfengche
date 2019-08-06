package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;

import java.util.List;

/**
 */

public class SingleSelectAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<PublicSelectInfo> data;
    private DataCallBack dataCallBack;

    public SingleSelectAdapter(Context context, List<PublicSelectInfo> data, DataCallBack dataCallBack) {
        this.context = context;
        this.data = data;
        this.dataCallBack = dataCallBack;
        addTagId();
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                data.get(i).setCheck(true);
            }
        }
    }

    public void refreshList(List<PublicSelectInfo> data){
        this.data = data;
        notifyDataSetChanged();
    }

    private void addTagId() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setTagId(i);
        }
    }

    /**
     * 重置状态
     */
    private void resetStatus() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setCheck(false);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PublicSelectInfo info = data.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_select, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.rb_content.setText(info.getName());

        viewHolder.rb_content.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
                notifyDataSetChanged();
            }
        });
        boolean isCheck = info.isCheck;
        if (info.getType() == 1){
            viewHolder.rb_content.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.mipmap.icon_new_round_zfb), null, null, null);
        }else if (info.getType() == 2){
            viewHolder.rb_content.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.mipmap.icon_new_bank), null, null, null);
        }else{
            viewHolder.rb_content.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.mipmap.icon_add_blue), null, null, null);
        }
        if (isCheck) {
            viewHolder.rb_content.setChecked(true);
        } else {
            viewHolder.rb_content.setChecked(false);
        }
        viewHolder.rb_content.setOnClickListener(this);
        viewHolder.rb_content.setTag(info);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_content:
                final PublicSelectInfo publicSelectInfo = (PublicSelectInfo) v.getTag();
                boolean checked = publicSelectInfo.isCheck();
                if (checked) {

                } else {
                    resetStatus();
                    int tagId = publicSelectInfo.getTagId();
                    for (int i = 0; i < data.size(); i++) {
                        int tagId2 = data.get(i).getTagId();
                        if (tagId == tagId2) {
                            data.get(i).setCheck(true);
                        } else {
                            data.get(i).setCheck(false);
                        }
                    }
                }
                notifyDataSetChanged();
                //为了看点击效果，所以做了个延迟0.1s的操作
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //execute the task
                        dataCallBack.onItemClick(publicSelectInfo.tagId, publicSelectInfo.getId(), publicSelectInfo.getType(), publicSelectInfo.getName());
                    }
                }, 100);
                break;
        }
    }

    public static class ViewHolder {

        public RadioButton rb_content;

        public ViewHolder(View rootView) {
            rb_content = (RadioButton) rootView.findViewById(R.id.rb_content);
        }

    }

    public interface DataCallBack {
        void onItemClick(int tagId, int id, int type, String values2);
    }
}
