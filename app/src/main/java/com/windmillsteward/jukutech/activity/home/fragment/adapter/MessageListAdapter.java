package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * 描述：消息列表适配器
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageListAdapter extends BaseQuickAdapter<MessageBean.ListBean, BaseViewHolder> {


    private Context context;
    private List<MessageBean.ListBean> list;

    public MessageListAdapter(Context context, List<MessageBean.ListBean> list) {
        super(R.layout.item_message_list, list);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void convert(BaseViewHolder helper, MessageBean.ListBean item) {
        helper.setText(R.id.tv_title, TextUtils.isEmpty(item.getTitle()) ? "" : item.getTitle());
        helper.setText(R.id.tv_date, TextUtils.isEmpty(item.getAdd_time()) ? "" : DateUtil.StampTimeToDate(item.getAdd_time(), "yyyy-MM-dd HH:mm"));
        helper.setText(R.id.tv_content, TextUtils.isEmpty(item.getContent()) ? "" : item.getContent());
        RelativeLayout lay_rl_look = (RelativeLayout) helper.getView(R.id.lay_rl_look);
        String type = item.getType();
        switch (type){
            case "3":
            case "7":
            case "9":
            case "13":
            case "15":
            case "16":
            case "19":
            case "21":
            case "22":
            case "25":
            case "27":
            case "28":
            case "33":
            case "34":
            case "37":
            case "41":
                lay_rl_look.setVisibility(View.GONE);
                break;
                default:
                    lay_rl_look.setVisibility(View.VISIBLE);
                    break;


        }
    }
}
