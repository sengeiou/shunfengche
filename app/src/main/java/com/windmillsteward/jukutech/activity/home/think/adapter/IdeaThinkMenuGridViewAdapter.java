package com.windmillsteward.jukutech.activity.home.think.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.think.activity.WisdomFireControlListActivity;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBeanTwo;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：特产分类菜单适配器
 * author:cyq
 * 2018-06-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class IdeaThinkMenuGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<IdeaThinkClassBeanTwo> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量


    public IdeaThinkMenuGridViewAdapter(Context context, List<IdeaThinkClassBeanTwo> lists,
                                        int mIndex, int mPargerSize) {
        this.context = context;
        this.lists = lists;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
    }

    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size() > (mIndex + 1) * mPargerSize ?
                mPargerSize : (lists.size() - mIndex * mPargerSize);
    }

    @Override
    public IdeaThinkClassBeanTwo getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_idea_viewpage_gridview, null);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        holder.tv_content.setText(lists.get(pos).getName() + "");

        ViewGroup.LayoutParams layoutParams = holder.iv_pic.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 120))/5;
        int i = GraphicUtil.px2dp(context, layoutParams.width);
        layoutParams.height =  layoutParams.width;
        holder.iv_pic.setLayoutParams(layoutParams);

        GlideUtil.show(context,lists.get(pos).getImage(),holder.iv_pic,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

//        添加item监听
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                if (lists.get(pos).getType().equals("1")) {
                    Intent intent = new Intent(context, WisdomFireControlListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(WisdomFireControlListActivity.CURR_CLASS,lists.get(pos).getClass_id());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
//                } else {
//                    Intent intent = new Intent(context, SpecialtyListActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(Define.INTENT_DATA,lists.get(pos).getClass_id());
//                    bundle.putInt(Define.INTENT_DATA_TWO,0);
//                    bundle.putString(Define.INTENT_DATA_THREE,lists.get(pos).getName());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_content;
        private ImageView iv_pic;
    }

}
