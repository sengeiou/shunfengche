package com.windmillsteward.jukutech.activity.home.family.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.common.util.FileUtil;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * 描述:   查看大图的ViewPager的adapter
 * 创建人： xjh
 * 时间： 2016/8/11
 * 修改记录:
 * 时间         修改人        版本        说明
 * -----------------------------------------------------------------------------------
 * 2016/8/11      xjh         1.0
 */
public class PhotoViewAdapter extends PagerAdapter {

    private List<PhotoView> imageViews;
    private List<String> pic_urls;
    private Context mContext;

    public PhotoViewAdapter(Context context, List<PhotoView> imageViews, List<String> pic_urls) {
        this.imageViews = imageViews;
        this.pic_urls = pic_urls;
        mContext = context;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView view = imageViews.get(position);
        container.addView(view, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        if (pic_urls.get(position).startsWith("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(pic_urls.get(position))
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                        }
                    });
        } else{
            Glide.with(mContext)
                    .asBitmap()
                    .load(new File(pic_urls.get(position)))
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                        }
                    });
        }
//            GlideUtil.show(mContext, new File(pic_urls.get(position)), view);

        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(imageViews.get(position));
    }
}
