package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/20/020
 * 作者：xjh
 */
public class MyPublishAdapter extends BaseQuickAdapter<MyPublishBean.ListBean,BaseViewHolder> {

    private Context context;

    public MyPublishAdapter(Context context,@Nullable List<MyPublishBean.ListBean> data) {
        super(R.layout.item_mypublish, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPublishBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_name,item.getTitle())
                    .setText(R.id.tv_state,item.getStatus_name())
                    .addOnClickListener(R.id.tv_reason);
            x.image().bind(((ImageView) helper.getView(R.id.iv_pic)),item.getPic_url(), ImageUtils.defaulPicList());

            TextView tv_reason = (TextView) helper.getView(R.id.tv_reason);
            TextView tv_state = (TextView) helper.getView(R.id.tv_state);
            if (TextUtils.equals(item.getStatus_name(),"审核不通过")) {
                tv_reason.setVisibility(View.VISIBLE);
                tv_state.setTextColor(ContextCompat.getColor(context,R.color.color_price));
            } else if (TextUtils.equals(item.getStatus_name(),"审核通过")) {
                tv_reason.setVisibility(View.GONE);
                tv_state.setTextColor(ContextCompat.getColor(context,R.color.color_23abac));
            } else if (TextUtils.equals(item.getStatus_name(),"审核中")) {
                tv_reason.setVisibility(View.GONE);
                tv_state.setTextColor(ContextCompat.getColor(context,R.color.color_text_78));
            }
        }
    }
}
