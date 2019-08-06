package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;

import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class FlyBanner extends RelativeLayout {

    private static final int RMP = LayoutParams.MATCH_PARENT;
    private static final int RWP = LayoutParams.WRAP_CONTENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private static final int WHAT_AUTO_PLAY = 1000;
    //Point位置
    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private LinearLayout mPointRealContainerLl;

    private ViewPager mViewPager;
    //本地图片资源
    private List<Integer> mImages;
    //网络图片资源
    private List<String> mImageUrls;
    //是否是网络图片
    private boolean mIsImageUrl = false;
    //是否只有一张图片
    private boolean mIsOneImg = false;
    //是否可以自动播放
    private boolean mAutoPlayAble = true;
    //是否正在播放
    private boolean mIsAutoPlaying = false;
    //自动播放时间
    private int mAutoPalyTime = 5000;
    //当前页面位置
    private int mCurrentPositon;
    //指示点位置
    private int mPointPosition = CENTER;
    //指示点资源
    private int mPointDrawableResId = R.drawable.selector_bgabanner_point;
    //指示容器背景
    private Drawable mPointContainerBackgroundDrawable;
    //指示容器布局规则
    private LayoutParams mPointRealContainerLp;
    //提示语
    private TextView mTips;

    private List<String> mTipsDatas;
    //指示点是否可见
    private boolean mPointsIsVisible = true;

    private boolean haveVideoCover = false;


    private Handler mAutoPlayHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPositon++;
            mViewPager.setCurrentItem(mCurrentPositon);
            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
        }
    };
    private RelativeLayout pointContainerRl;

    public FlyBanner(Context context) {
        this(context, null);
    }

    public FlyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlyBanner);

        mPointsIsVisible = a.getBoolean(R.styleable.FlyBanner_points_visibility, true);
        mPointPosition = a.getInt(R.styleable.FlyBanner_points_position, CENTER);
        mPointContainerBackgroundDrawable
                = a.getDrawable(R.styleable.FlyBanner_points_container_background);

        a.recycle();

        setLayout(context);
    }

    private void setLayout(Context context) {
        //关闭view的OverScroll
        setOverScrollMode(OVER_SCROLL_NEVER);
        //设置指示器背景
        if (mPointContainerBackgroundDrawable == null) {
            mPointContainerBackgroundDrawable = new ColorDrawable(Color.parseColor("#00aaaaaa"));
        }
        //添加ViewPager
        mViewPager = new ViewPager(context);
        addView(mViewPager, new LayoutParams(RMP, RMP));
        //设置指示器背景容器
        pointContainerRl = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= 16) {
            pointContainerRl.setBackground(mPointContainerBackgroundDrawable);
        } else {
            pointContainerRl.setBackgroundDrawable(mPointContainerBackgroundDrawable);
        }
        //设置内边距
        pointContainerRl.setPadding(0, 10, 0, 10);
        //设定指示器容器布局及位置
        LayoutParams pointContainerLp = new LayoutParams(RMP, RWP);
        pointContainerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(pointContainerRl, pointContainerLp);
        //设置指示器容器
        mPointRealContainerLl = new LinearLayout(context);
        mPointRealContainerLl.setOrientation(LinearLayout.HORIZONTAL);
        mPointRealContainerLp = new LayoutParams(RWP, RWP);
        pointContainerRl.addView(mPointRealContainerLl, mPointRealContainerLp);
        //设置指示器容器是否可见
        if (mPointRealContainerLl != null) {
            if (mPointsIsVisible) {
                mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
        //设置指示器布局位置
        if (mPointPosition == CENTER) {
            mPointRealContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (mPointPosition == LEFT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (mPointPosition == RIGHT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    /**
     * 设置指示器位置
     */
    public void setPointPosition(int pos){
        this.mPointPosition = pos;
        //设置指示器布局位置
        if (mPointPosition == CENTER) {
            mPointRealContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (mPointPosition == LEFT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (mPointPosition == RIGHT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    /**
     * 设置指示器间距
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setPointPadding(int left,int top,int right,int bottom){
        if (pointContainerRl!= null){
            pointContainerRl.setPadding(left, top, right, bottom);
        }
    }

    /**
     * 设置本地图片
     *
     * @param images
     */
    public void setImages(List<Integer> images) {
        mIsOneImg = false;
        //加载本地图片
        mIsImageUrl = false;
        this.mImages = images;
        if (images.size() <= 1)
            mIsOneImg = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置网络图片
     *
     * @param urls
     */
    public void setImagesUrl(List<String> urls) {
        mIsOneImg = false;
        //加载网络图片
        mIsImageUrl = true;
        this.mImageUrls = urls;
        if (urls.size() <= 1)
            mIsOneImg = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置指示点是否可见
     *
     * @param isVisible
     */
    public void setPointsIsVisible(boolean isVisible) {
        if (mPointRealContainerLl != null) {
            if (isVisible) {
                mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 对应三个位置 CENTER,RIGHT,LEFT
     *
     * @param position
     */
    public void setPoinstPosition(int position) {
        //设置指示器布局位置
        if (position == CENTER) {
            mPointRealContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (position == LEFT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (position == RIGHT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    private void initViewPager() {
        //当图片多于1张时添加指示点
        if (!mIsOneImg) {
            addPoints();
        }
        //设置ViewPager
        FlyPageAdapter adapter = new FlyPageAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        //跳转到首页
        mViewPager.setCurrentItem(1, false);
        //当图片多于1张时开始轮播
        if (!mIsOneImg) {
            startAutoPlay();
        }
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return
     */
    private int toRealPosition(int position) {
        int realPosition = 0;
        if (mIsImageUrl) {
            if (!mImageUrls.isEmpty()) {
                realPosition = (position - 1) % mImageUrls.size();
                if (realPosition < 0)
                    realPosition += mImageUrls.size();
            }
        } else {
            if (!mImages.isEmpty()) {
                realPosition = (position - 1) % mImages.size();
                if (realPosition < 0)
                    realPosition += mImages.size();
            }
        }
        return realPosition;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (mIsImageUrl) {
                mCurrentPositon = position % (mImageUrls.size() + 2);
            } else {
                mCurrentPositon = position % (mImages.size() + 2);
            }
            switchToPoint(toRealPosition(mCurrentPositon));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int current = mViewPager.getCurrentItem();
                int lastReal = mViewPager.getAdapter().getCount() - 2;
                if (current == 0) {
                    mViewPager.setCurrentItem(lastReal, false);
                } else if (current == lastReal + 1) {
                    mViewPager.setCurrentItem(1, false);
                }
            }
        }
    };

    private class FlyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //当只有一张图片时返回1
            if (mIsOneImg) {
                return 1;
            }
            //当为网络图片，返回网页图片长度
            if (mIsImageUrl)
                return mImageUrls.size() + 2;
            //当为本地图片，返回本地图片长度
            return mImages.size() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            FrameLayout frameLayout = new FrameLayout(getContext());

            final ImageView imageView = new ImageView(getContext());


            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(toRealPosition(position));
                    }
                }
            });
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (mIsImageUrl) {
                if (mImageUrls != null && !mImageUrls.isEmpty())
                    x.image().bind(imageView, mImageUrls.get(toRealPosition(position)), ImageUtils.defaulHeadOptionsThree());
            } else {
                if (mImages != null && !mImages.isEmpty())
                    imageView.setImageResource(mImages.get(toRealPosition(position)));
            }
            frameLayout.addView(imageView);

            if (haveVideoCover) {
                if (toRealPosition(position) == 0) {
                    ImageView video = new ImageView(getContext());
                    video.setImageResource(R.mipmap.icon_play);
                    video.setScaleType(ImageView.ScaleType.FIT_XY);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(GraphicUtil.dp2px(getContext(), 62), GraphicUtil.dp2px(getContext(), 62), Gravity.CENTER);
                    video.setLayoutParams(params);
                    frameLayout.addView(video);

//                    MediaTwoUtils.getImageForVideo(mImageUrls.get(0), new MediaTwoUtils.OnLoadVideoImageListener() {
//                        @Override
//                        public void onLoadImage(Bitmap bitmap) {
//                            if (bitmap == null){
//                                return;
//                            }
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    });
                    MediaUtils.getImageForVideo(mImageUrls.get(0), new MediaUtils.OnLoadVideoImageListener() {
                        @Override
                        public void onLoadImage(File file) {
                            if (file == null){
                                return;
                            }
                            Uri uri = Uri.fromFile(file);
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            container.addView(frameLayout);
            return frameLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            if (object != null)
                object = null;
        }
    }

    /**
     * 添加指示点
     */
    private void addPoints() {
        mPointRealContainerLl.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LWC, LWC);
        lp.setMargins(10, 10, 10, 10);
        ImageView imageView;
        int length = mIsImageUrl ? mImageUrls.size() : mImages.size();
        for (int i = 0; i < length; i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(lp);
            imageView.setImageResource(mPointDrawableResId);
//            imageView.setImageResource(R.mipmap.lunbodianweixuan);
            mPointRealContainerLl.addView(imageView);
        }

        switchToPoint(0);
    }

    /**
     * 切换指示器
     *
     * @param currentPoint
     */
    private void switchToPoint(final int currentPoint) {
        for (int i = 0; i < mPointRealContainerLl.getChildCount(); i++) {
            mPointRealContainerLl.getChildAt(i).setEnabled(false);
        }
        mPointRealContainerLl.getChildAt(currentPoint).setEnabled(true);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mAutoPlayAble && !mIsOneImg) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置是否显示第一个为视频的图标
     *
     * @param b
     */
    public void setHaveVideoCover(boolean b) {
        haveVideoCover = b;
    }

    /**
     * 开始播放
     */
    public void startAutoPlay() {
        if (mAutoPlayAble && !mIsAutoPlaying) {
            mIsAutoPlaying = true;
            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
        }
    }

    /**
     * 停止播放
     */
    public void stopAutoPlay() {
        if (mAutoPlayAble && mIsAutoPlaying) {
            mIsAutoPlaying = false;
            mAutoPlayHandler.removeMessages(WHAT_AUTO_PLAY);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
