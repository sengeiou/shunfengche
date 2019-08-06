package com.windmillsteward.jukutech.activity.home.family.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.transition.Visibility;
import android.view.View;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;

import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：智慧家庭列表适配器
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class IntelligentFamilyAdapter extends BaseQuickAdapter<IntelligentFamilyBean.ListBean, BaseViewHolder> {


    private Context context;
    private List<IntelligentFamilyBean.ListBean> list;

    public IntelligentFamilyAdapter(Context context, List<IntelligentFamilyBean.ListBean> list) {
        super(R.layout.item_intelligent_family, list);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder helper, IntelligentFamilyBean.ListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_name, item.getClass_name())
                    .setText(R.id.tv_price, "市场指导价￥" + item.getPrice())
                    .setText(R.id.tv_desc, item.getDescription())
                    .setText(R.id.tv_location, item.getRequire_area())
                    .setText(R.id.tv_range, "距离我" + DistanceUtils.getFormatDistance(item.getDistance()))
                    .addOnClickListener(R.id.tv_get_order);

            if (item.getIs_ad() == 0){//不是广告发布，显示抢单按钮
                helper.setVisible(R.id.tv_get_order,true );
            }else {
                helper.setVisible(R.id.tv_get_order,false );
            }
            List<String> pic_urls = item.getPic_urls();
            String video_urls = item.getVideo_url();
            final String videoUrlsTemp = video_urls;
            List<String> totalList = new ArrayList<>();
            if (!TextUtils.isEmpty(video_urls)){
                totalList.add(video_urls);
            }
            if (pic_urls != null && pic_urls.size() > 0){
                totalList.addAll(pic_urls);
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
            int picLength =0;
            if (totalList.size() >4){//图片长度大于4，则只取4
                picLength = 4;
            }else{//图片长度小于4，则取pic_urls.size()
                picLength = totalList.size();
            }
            if (!TextUtils.isEmpty(video_urls)){
                helper.getView(R.id.iv_image1).setVisibility(View.VISIBLE);
                helper.getView(R.id.lay_ll_pic).setVisibility(View.VISIBLE);
                helper.getView(R.id.iv_play).setVisibility(View.VISIBLE);
                final ImageView imageView = (ImageView) helper.getView(R.id.iv_image1);
//                MediaTwoUtils.getImageForVideo(video_urls, new MediaTwoUtils.OnLoadVideoImageListener() {
//                    @Override
//                    public void onLoadImage(Bitmap bitmap) {
//                        if (bitmap == null){
//                            return;
//                        }
//                        imageView.setImageBitmap(bitmap);
//                    }
//                });
                MediaUtils.getImageForVideo(video_urls, new MediaUtils.OnLoadVideoImageListener() {
                    @Override
                    public void onLoadImage(File file) {
                        if (file == null){
                            return;
                        }
                        Uri uri = Uri.fromFile(file);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
//                MediaMetadataRetriever media = new MediaMetadataRetriever();
//                media.setDataSource(video_urls,new HashMap());//
//                Bitmap bitmap  = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC );
//                ((ImageView) helper.getView(R.id.iv_image1)).setImageBitmap(bitmap);

                for (int i = 1; i < picLength; i++){
                    views.get(i).setVisibility(View.VISIBLE);
                    x.image().bind(views.get(i), pic_urls.get(i-1), ImageUtils.defaulHeadOptionsTwo());
                }
                helper.getView(R.id.iv_image1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, videoUrlsTemp);
                        Intent intent = new Intent(context,VideoPlayActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent, bundle);
                    }
                });
            }else{
                helper.getView(R.id.iv_play).setVisibility(View.GONE);
                if (pic_urls != null ){
                    if (pic_urls.size() == 0){
                        helper.getView(R.id.lay_ll_pic).setVisibility(View.GONE);
                    }else {
                        helper.getView(R.id.lay_ll_pic).setVisibility(View.VISIBLE);
                        for (int i = 0; i < picLength; i++) {
                            views.get(i).setVisibility(View.VISIBLE);
                            x.image().bind(views.get(i), pic_urls.get(i), ImageUtils.defaulHeadOptionsTwo());
                        }
                    }
                }
            }

//            for (int i1 = 0; i1 < views.size(); i1++) {
//                if (video_urls != null && video_urls.size() > 0) {
//                    if (i1 == 0) {
//                        views.get(0).setVisibility(View.VISIBLE);
//                        x.image().bind(views.get(0), item.getVideo_cover(), ImageUtils.defaulHeadOptionsTwo());
//                    }
//                    if (i1 > 0 && pic_urls != null && pic_urls.size() > i1 - 1) {
//                        views.get(i1).setVisibility(View.VISIBLE);
//                        x.image().bind(views.get(i1), pic_urls.get(i1 - 1), ImageUtils.defaulHeadOptionsTwo());
//                    }
//                } else {
//                    if (pic_urls != null && pic_urls.size() > i1) {
//                        views.get(i1).setVisibility(View.VISIBLE);
//                        x.image().bind(views.get(i1), pic_urls.get(i1), ImageUtils.defaulHeadOptionsTwo());
//                    }
//                }
//            }

            x.image().bind((CircleImageView) helper.getView(R.id.civ_header), item.getClass_image(), ImageUtils.defaulCircleHeadOptions());
        }
    }

}
