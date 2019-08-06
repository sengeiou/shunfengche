package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.support.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.utils.LngAndLatUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/27/027
 * 作者：xjh
 */
public class PoiIndexListAdapter extends BaseQuickAdapter<PoiInfo,BaseViewHolder> {

    private double latitude;
    private double longitude;

    public PoiIndexListAdapter(@Nullable List<PoiInfo> data) {
        super(R.layout.item_poiindex_list, data);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {
        if (item!=null) {
            String distance = LngAndLatUtil.getDistance(longitude, latitude, item.location.longitude, item.location.latitude);
            helper.setText(R.id.tv_name,item.name)
                    .setText(R.id.tv_address,item.address)
                    .setText(R.id.tv_range,distance+"km");
        }
    }
}
