package com.windmillsteward.jukutech.activity.home.houselease.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.HouseSealListBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.NumberFormatUtil;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/17/017
 * 作者：xjh
 */
public class BuyHouseAdapter extends BaseQuickAdapter<HouseSealListBean.ListBean, BaseViewHolder> {

    public BuyHouseAdapter(List<HouseSealListBean.ListBean> list) {
        super(R.layout.item_buyhouse, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseSealListBean.ListBean item) {
        if (item != null) {
            double total_price = item.getTotal_price();
            if (total_price < 10000 ){
                helper.setText(R.id.tv_price, "￥" + total_price + "元");
            }else{
                helper.setText(R.id.tv_price, "￥" + NumberFormatUtil.save2Point(total_price/10000) + "万元");
            }
            helper.setText(R.id.tv_name, item.getHouse_type_name())
                    .setText(R.id.tv_desc, item.getTitle())
                    .setText(R.id.tv_location, item.getHouse_third_name() + " " + item.getHouse_fourth_name());

            List<String> pic_urls = item.getPic_urls();
            List<String> video_urls = new ArrayList<>();
            if (!TextUtils.isEmpty(item.getVideo_url())) {
                video_urls.add(item.getVideo_url());
            }
            // 处理图片和视频
            List<ImageView> views = new ArrayList<>();
            views.add(((ImageView) helper.getView(R.id.iv_image1)));
            views.add(((ImageView) helper.getView(R.id.iv_image2)));
            views.add(((ImageView) helper.getView(R.id.iv_image3)));
            views.add(((ImageView) helper.getView(R.id.iv_image4)));
            for (ImageView imageView : views) {
                imageView.setVisibility(View.INVISIBLE);
            }
            for (int i1 = 0; i1 < views.size(); i1++) {
                if (video_urls != null && video_urls.size() > 0) {
                    if (i1 == 0) {
                        views.get(0).setVisibility(View.VISIBLE);
                        x.image().bind(views.get(0), item.getVideo_cover(), ImageUtils.defaulHeadOptionsTwo());
                    }
                    if (i1 > 0 && pic_urls != null && pic_urls.size() > i1 - 1) {
                        views.get(i1).setVisibility(View.VISIBLE);
                        x.image().bind(views.get(i1), pic_urls.get(i1 - 1), ImageUtils.defaulHeadOptionsTwo());
                    }
                } else {
                    if (pic_urls != null && pic_urls.size() > i1) {
                        views.get(i1).setVisibility(View.VISIBLE);
                        x.image().bind(views.get(i1), pic_urls.get(i1), ImageUtils.defaulHeadOptionsTwo());
                    }
                }
            }
            ImageView iv_play = helper.getView(R.id.iv_play);
            if (item.getVideo_cover() != null && item.getVideo_cover().length() > 0) {
                iv_play.setVisibility(View.VISIBLE);
            } else {
                iv_play.setVisibility(View.GONE);
            }
            x.image().bind((CircleImageView) helper.getView(R.id.civ_header), item.getUser_avatar_url(), ImageUtils.defaulHeader());
        }
    }
}
