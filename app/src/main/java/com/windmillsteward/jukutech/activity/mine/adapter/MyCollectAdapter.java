package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyCollectBean;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyCollectAdapter extends BaseQuickAdapter<MyCollectBean.ListBean,BaseViewHolder> {

    private boolean isEdit;

    public MyCollectAdapter(@Nullable List<MyCollectBean.ListBean> data) {
        super(R.layout.item_mycollect, data);
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCollectBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_time, DateUtil.StampTimeToDate(String.valueOf(item.getAdd_time()),"yy/MM/dd"))
                    .addOnClickListener(R.id.iv_select);

            x.image().bind(((ImageView) helper.getView(R.id.iv_pic)),item.getPic_url(), ImageUtils.defaulPicList());
            ImageView iv_select = (ImageView) helper.getView(R.id.iv_select);
            if (isEdit) {
                iv_select.setVisibility(View.VISIBLE);
            } else {
                iv_select.setVisibility(View.GONE);
            }
            if (item.isSelect()) {
                iv_select.setImageResource(R.mipmap.icon_select);
            } else {
                iv_select.setImageResource(R.mipmap.icon_select_n);
            }
        }
    }
}
