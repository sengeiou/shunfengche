package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.model.HomeRootModel;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;

public class MarqueeViewAdapter extends MarqueeFactory<RelativeLayout,HomeRootModel.ListNewsFlashBean> {
    private LayoutInflater inflater;

    public MarqueeViewAdapter(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(HomeRootModel.ListNewsFlashBean data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.item_marquee_view, null);
        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
        return mView;
    }
}