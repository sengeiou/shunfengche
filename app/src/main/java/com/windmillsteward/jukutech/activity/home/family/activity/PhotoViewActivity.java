package com.windmillsteward.jukutech.activity.home.family.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.gyf.barlibrary.ImmersionBar;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.adapter.PhotoViewAdapter;
import com.windmillsteward.jukutech.utils.GraphicUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * 描述：照片查看界面
 * 时间：2018/1/24
 * 作者：xjh
 */

public class PhotoViewActivity extends FragmentActivity implements View.OnClickListener {

    public static final String PIC_URLS = "PIC_URLS";
    public static final String CURR_POSITION = "CURR_POSITION";

    private List<String> pic_urls;
    private List<PhotoView> views;
    private int currPosition;
    private ViewPager vp_image;
    private TextView tv__num;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pic_urls = extras.getStringArrayList(PIC_URLS);
            currPosition = extras.getInt(CURR_POSITION);
        }
        initView();
        mImmersionBar = ImmersionBar.with(this);//初始化
        mImmersionBar.statusBarColor(R.color.color_black).fitsSystemWindows(true).init();
        vp_image.setCurrentItem(currPosition);
        tv__num.setText((currPosition + 1) + "/" + pic_urls.size());
    }

    private void initView() {
        vp_image = (ViewPager) findViewById(R.id.vp_image);
        tv__num = (TextView) findViewById(R.id.tv__num);
        views = new ArrayList<>();
        if (pic_urls != null) {
            for (String ignored : pic_urls) {
                PhotoView imageView = new PhotoView(this);
                imageView.setOnClickListener(this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.enable();
                ViewPager.LayoutParams params = new ViewPager.LayoutParams();
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                views.add(imageView);
            }
        }
        vp_image.setAdapter(new PhotoViewAdapter(this, views, pic_urls));

        vp_image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv__num.setText((position + 1) + "/" + pic_urls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        views.clear();
        views = null;
        vp_image = null;
        tv__num = null;
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
