package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/12
 * 作者：xjh
 */

public class MyPublishAdapter extends BaseQuickAdapter<MyPublishBean.ListBean,BaseViewHolder> {


    public MyPublishAdapter(List<MyPublishBean.ListBean> list) {
        super(R.layout.item_mypublish,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPublishBean.ListBean item) {
        helper.setText(R.id.tv_name,item.getTitle());
        int status = item.getPublish_status();
        if (status==0) {
            helper.setText(R.id.tv_state,"未审核");
        } else if (status == 1){
            helper.setText(R.id.tv_state,"已发布");
        }
        x.image().bind(((ImageView) helper.getView(R.id.iv_pic)), item.getUrl(), ImageUtils.defaulHeadOptionsTwo());
    }
}
