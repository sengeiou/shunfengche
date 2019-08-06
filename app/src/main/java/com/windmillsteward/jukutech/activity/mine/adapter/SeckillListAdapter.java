package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MyApplication;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SeckillListBean;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.StateButton;

import org.xutils.x;

import java.util.List;

/**
 * 描述：秒杀列表适配器
 * author:lc
 * 2018-07-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SeckillListAdapter extends BaseQuickAdapter<SeckillListBean.CListBean.ListBean,BaseViewHolder> {


//    private Context context;
//    private List<SeckillListBean.CListBean.ListBean> list;
    private boolean is_current_list_seckill_start;// 当前列表是否开始秒杀 true开始秒杀  false还没开始秒杀
    public SeckillListAdapter(Context context, List<SeckillListBean.CListBean.ListBean> list) {
        super(R.layout.item_seckill_list,list);
//        this.context = context;
//        this.list = list;
    }



    @Override
    protected void convert(BaseViewHolder helper, SeckillListBean.CListBean.ListBean item) {
        x.image().bind((ImageView) helper.getView(R.id.iv_content),item.getCommodity_model_image(), ImageUtils.defaulHeadOptionsTwo());
            helper.setText(R.id.tv_title, TextUtils.isEmpty(item.getCommodity_title())?"":item.getCommodity_title());
            helper.setText(R.id.btn_discount,TextUtils.isEmpty(item.getDiscount() + "")?"": item.getDiscount() + "折");
            helper.setText(R.id.tv_price,TextUtils.isEmpty(item.getCost_price() + "")?"":item.getCost_price() + "");
        StateButton sb =  helper.getView(R.id.btn_seckill);
        if (is_current_list_seckill_start) {
            sb.setTextColor(MyApplication.getContext().getResources().getColor(R.color.color_white));
            sb.setNormalBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.color_text_94b));
        } else {
            sb.setTextColor(MyApplication.getContext().getResources().getColor(R.color.color_text_666));
            sb.setNormalBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.color_text_ccc));
        }
    }

    /**
     * 当前列表是否开始秒杀 true开始秒杀  false还没开始秒杀
     * @param is_current_list_seckill_start
     */
    public void refreshState(boolean is_current_list_seckill_start){
        this.is_current_list_seckill_start = is_current_list_seckill_start;
        notifyDataSetChanged();
    }
}
