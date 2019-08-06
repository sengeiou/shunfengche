package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：管家快讯列表适配器
 * author:cyq
 * 2018-04-10
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HousekeeperAlertsAdapter extends BaseQuickAdapter<HouseKeeperDataQuickBean.ListBean,BaseViewHolder> {


    private Context context;
    private List<HouseKeeperDataQuickBean.ListBean> list;

    public HousekeeperAlertsAdapter(Context context, List<HouseKeeperDataQuickBean.ListBean> list) {
        super(R.layout.item_housekeeper_alerts,list);
        this.context = context;
        this.list = list;
    }



    @Override
    protected void convert(BaseViewHolder helper, HouseKeeperDataQuickBean.ListBean item) {
            helper.setText(R.id.tv_title, TextUtils.isEmpty(item.getTitle())?"":item.getTitle());
            helper.setText(R.id.tv_date,TextUtils.isEmpty(item.getAdd_time())?"": DateUtil.StampTimeToDate(item.getAdd_time(),"yyyy-MM-dd"));
        x.image().bind((ImageView) helper.getView(R.id.iv_pic),item.getCover_url(), ImageUtils.defaulHeadOptionsTwo());
    }
}
