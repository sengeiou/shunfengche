package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页面的viewpage,与ImageCycleView的区别在于自己重新理解,且在自己的理解之上加入一些项目里用到的方法.
 */
public class ImageCycleViewGuidance extends FrameLayout {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图片轮播视图
     */
    private ImageCycleViewPager mViewPager;
    /**
     * 数据集合
     * Map<String,String> map=new HashMap<String, String>();
     * map.put("","");
     */
    private List<ImageInfo> data = new ArrayList<>();
    /**
     * 加载图片回调函数
     */
    private LoadImageCallBack mLoadImageCallBack;

    /**
     * 图片轮播指示器容器
     */
    private LinearLayout mIndicationGroup;
    /**
     * 轮播的总数
     */
    private int mCount = 0;
    /**
     * 未获得焦点指示器资源
     */
    private Bitmap unFocusIndicationStyle;
    /**
     * 获得焦点指示器资源
     */
    private Bitmap focusIndicationStyle;
    /**
     * 指示器间距相对于自身的百分比,默认间距为指示器高度的1/2
     */
    private float indication_self_margin_percent = 0.5f;
    /**
     * 单击事件监听器
     */
    private OnPageClickListener mOnPageClickListener;
    /**
     * 图片文本提示
     */
    private TextView mText;
    private RelativeLayout relativeLayout;


    public ImageCycleViewGuidance(Context context) {
        super(context);
        init(context);
    }

    public ImageCycleViewGuidance(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化基础信息
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        unFocusIndicationStyle = drawCircle(50, Color.GRAY);
        focusIndicationStyle = drawCircle(50, Color.WHITE);
        initView();
    }

    int heightNumerical;// 控件高度

    /**
     * 设置此控件高度
     *
     * @param numerical 单位dp
     */
    public void setHeight(int numerical) {
        heightNumerical = numerical;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 脑补文章http://blog.csdn.net/cyp331203/article/details/45038329
        //int specMode = MeasureSpec.getMode(widthMeasureSpec);//得到模式
        if (heightNumerical != 0) {
            int specSize = MeasureSpec.getSize(widthMeasureSpec);//得到大小 1080
            // DP转像素
            specSize = GraphicUtil.dp2px(mContext, 200);
            // 重新赋值
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(specSize, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 初始化view控件
     *
     * @author 代凯男
     */
    private void initView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_root);


        View.inflate(mContext, R.layout.view_image_cycle_guidance, this);
        FrameLayout fl_image_cycle = (FrameLayout) findViewById(R.id.fl_image_cycle);
        mViewPager = new ImageCycleViewPager(mContext);
        mViewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        fl_image_cycle.addView(mViewPager);
        mViewPager.setOnPageChangeListener(new ImageCyclePageChangeListener());
        mIndicationGroup = (LinearLayout) findViewById(R.id.ll_indication_group);
        mText = (TextView) findViewById(R.id.tv_text);
    }


    public void hintText() {
        mText.setVisibility(View.GONE);
    }

    public void showText() {
        mText.setVisibility(View.VISIBLE);
    }

    public enum IndicationStyle {
        COLOR, IMAGE
    }

    /**
     * 设置轮播指示器样式，如果你对默认的样式不满意可以自己设置
     *
     * @param indicationStyle         资源类型,color,image,shape
     * @param unFocus                 未获得焦点指示器资源id  图片或shape或color值
     * @param focus                   获得焦点指示器资源id 图片或shape或color值
     * @param indication_self_percent 自身高度的百分比 >=0f
     */
    public void setIndicationStyle(IndicationStyle indicationStyle, int unFocus, int focus, float indication_self_percent) {
        if (indicationStyle == IndicationStyle.COLOR) {
            unFocusIndicationStyle = drawCircle(50, unFocus);
            focusIndicationStyle = drawCircle(50, focus);
        } else if (indicationStyle == IndicationStyle.IMAGE) {
            unFocusIndicationStyle = BitmapFactory.decodeResource(mContext.getResources(), unFocus);
            focusIndicationStyle = BitmapFactory.decodeResource(mContext.getResources(), focus);
        }
        indication_self_margin_percent = indication_self_percent;
        initIndication();
    }

    /**
     * 图片轮播是自动滚动状态  true 自动滚动，false 图片不能自动滚动只能手动左右滑动
     */
    private boolean isAutoCycle = true;

    /**
     * 是否无限轮播 true无限 false非无限
     */
    private boolean infiniteCycle = true;
    /**
     * 自动轮播时间间隔默认5秒
     */
    private long mCycleDelayed = 5000;

    /**
     * 设置自动轮播时间间隔
     *
     * @param delayed 毫秒
     */
    public void setCycleDelayed(long delayed) {
        mCycleDelayed = delayed;
    }

    /**
     * 设置是否自动无限轮播
     *
     * @param state
     */
    public void setAutoCycle(Boolean state) {
        isAutoCycle = state;
    }

    /**
     * 设置是否无限轮播
     *
     * @param infiniteCycle
     */
    public void setInfiniteCycle(Boolean infiniteCycle) {
        this.infiniteCycle = infiniteCycle;
    }

    /**
     * 加载显示的数据  网络图片资源及标题
     *
     * @param list     数据
     * @param callBack 如何加载图片及显示的回调方法 not null
     */
    public void loadData(List<ImageInfo> list, LoadImageCallBack callBack) {
        data = list;
        mCount = list.size();
        initIndication();
        if (callBack == null) {
            new IllegalArgumentException("LoadImageCallBack 回调函数不能为空！");
        }
        mLoadImageCallBack = callBack;
        mViewPager.setAdapter(new ImageCycleAdapter());

        if (infiniteCycle) {
            //最大值中间 的第一个
            mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % mCount));
        }
    }

    /**
     * 设置点击事件监听回调函数
     *
     * @param listener
     */
    public void setOnPageClickListener(OnPageClickListener listener) {
        mOnPageClickListener = listener;
    }

    /**
     * 轮播控件的监听事件
     */
    public interface OnPageClickListener {
        /**
         * 单击图片事件
         *
         * @param imageView 被点击的View对象
         * @param imageInfo 数据信息
         * @param position  数据位置
         */
        void onClick(View imageView, ImageInfo imageInfo, int position);
    }


    /**
     * 初始化指标器
     */
    private void initIndication() {
        mIndicationGroup.removeAllViews();
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicationGroup.getLayoutParams().height, LinearLayout.LayoutParams.MATCH_PARENT);
            params.leftMargin = (int) (mIndicationGroup.getLayoutParams().height * indication_self_margin_percent);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setImageBitmap(focusIndicationStyle);
            } else {
                imageView.setImageBitmap(unFocusIndicationStyle);
            }
            mIndicationGroup.addView(imageView);
        }
    }

    public void hideIndication(boolean isVisible){
        if (isVisible){
            mIndicationGroup.setVisibility(VISIBLE);
        }else{
            mIndicationGroup.setVisibility(GONE);
        }
    }
    /**
     * 画指示器
     *
     * @param radius 半径,或者说是宽高,看下面代码就知道了
     * @param color  颜色
     * @return
     */
    private Bitmap drawCircle(int radius, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//画笔初始化且抗锯齿
        paint.setColor(color);// 设置画笔颜色
        // 返回具有指定宽度和高度的可变位图
        Bitmap bitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        // 构造一个具有指定位图的画布
        Canvas canvas = new Canvas(bitmap);
        // 开始画圆 X中心坐标 Y中心坐标 半径 画笔
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        return bitmap;
    }

    /**
     * 图片信息
     */
    public static class ImageInfo {
        public ImageInfo(Object image, String text, Object value) {
            this.image = image;
            this.text = text;
            this.value = value;
        }

        public Object image;
        public String text = "";
        public Object value;
    }


    /**
     * 加载图片并显示回调接口
     */
    public interface LoadImageCallBack {
        /**
         * 自己如何设置加载图片
         *
         * @param imageInfo 数据信息
         */
        ImageView loadAndDisplay(ImageInfo imageInfo);
    }

    /**
     * 轮播图片监听
     *
     * @author 代凯男
     */
    private final class ImageCyclePageChangeListener implements OnPageChangeListener {

        //上次指示器指示的位置,开始为默认位置0
        private int preIndex = 0;

        @Override
        public void onPageSelected(int index) {
            index = index % mCount;
            //更新文本信息
            String text = data.get(index).text;
            mText.setText(TextUtils.isEmpty(text) ? "" : text);
            //恢复默认没有获得焦点指示器样式
            ((ImageView) (mIndicationGroup.getChildAt(preIndex))).setImageBitmap(unFocusIndicationStyle);
            // 设置当前显示图片的指示器样式
            ((ImageView) (mIndicationGroup.getChildAt(index))).setImageBitmap(focusIndicationStyle);
            preIndex = index;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
    }

    /**
     * 图片轮播适配器
     */
    private class ImageCycleAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final ImageInfo imageInfo = data.get(position % mCount);

            ImageView imageView = mLoadImageCallBack.loadAndDisplay(imageInfo);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 设置图片点击监听
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPageClickListener != null) {
                        mOnPageClickListener.onClick(v, imageInfo, position % mCount);
                    }
                }
            });

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            if (infiniteCycle) {
                return Integer.MAX_VALUE;
            } else {
                return data.size();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }


    /**
     * 开始图片轮播
     */
    private void startImageCycle() {
        handler.sendEmptyMessageDelayed(0, mCycleDelayed);
    }

    /**
     * 暂停图片轮播
     */
    private void stopImageCycle() {
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 实现自动轮播
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0, mCycleDelayed);
            }
            return false;
        }
    });

    /**
     * 触摸停止计时器，抬起启动计时器
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isAutoCycle) {
                // 开始图片滚动
                startImageCycle();
            }
        } else {
            if (isAutoCycle) {
                // 停止图片滚动
                stopImageCycle();
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 停止图片滚动
        stopImageCycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isAutoCycle) {
            startImageCycle();
        }
    }


    /**
     * 自定义ViewPager主要用于事件处理
     */
    public class ImageCycleViewPager extends ViewPager {

        public ImageCycleViewPager(Context context) {
            super(context);
        }

        public ImageCycleViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /**
         * 事件拦截
         */
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        /**
         * 事件分发
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.dispatchTouchEvent(ev);
        }

        /**
         * 事件处理
         */
        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return super.onTouchEvent(ev);
        }


    }
}