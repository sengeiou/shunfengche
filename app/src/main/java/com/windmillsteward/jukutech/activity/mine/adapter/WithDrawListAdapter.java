package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.WithdrawBean;

import java.util.List;

/**
 * 描述：提现列表适配器
 * author:cyq
 * 2017/6/18
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WithDrawListAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<WithdrawBean> list;

    private OnItemClickCallBack onItemClickCallBack;


    public WithDrawListAdapter( OnItemClickCallBack onItemClickCallBack, Context context, List<WithdrawBean> list) {
        this.context = context;
        this.onItemClickCallBack = onItemClickCallBack;
        this.list = list;
        addTagId();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setChecked(true);
            }
        }
    }

    private void addTagId() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTagId(i);
        }
    }

    /**
     * 重置状态
     */
    private void resetStatus() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChecked(false);
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_list, null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.rb_select = (RadioButton) convertView.findViewById(R.id.rb_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_content.setText(list.get(position).getAccount());
        holder.rb_select.setOnClickListener(this);
        holder.rb_select.setTag(list.get(position));
        int account_id = list.get(position).getAccount_id();
        boolean checked = list.get(position).isChecked();
        if (checked) {
            holder.rb_select.setChecked(true);
        } else {
            holder.rb_select.setChecked(false);
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_content;//内容
        private RadioButton rb_select;//单选

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_select:
                final WithdrawBean withDrawListInfo = (WithdrawBean) v.getTag();
                boolean checked = withDrawListInfo.isChecked();
                if (checked) {

                } else {
                    resetStatus();
                    int tagId = withDrawListInfo.getTagId();
                    for (int i = 0; i < list.size(); i++) {
                        int id = list.get(i).getTagId();
                        if (tagId == id) {
                            list.get(i).setChecked(true);
                        } else {
                            list.get(i).setChecked(false);
                        }
                    }
                }
                notifyDataSetChanged();
                //为了看点击效果，所以做了个延迟0.1s的操作
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //execute the task
                        onItemClickCallBack.onItemClick(withDrawListInfo);
                    }
                }, 100);
                break;
        }
    }

    public interface OnItemClickCallBack {
        /**
         * 回调给activity
         *
         * @param withdrawBean
         */
        void onItemClick(WithdrawBean withdrawBean);
    }
}
