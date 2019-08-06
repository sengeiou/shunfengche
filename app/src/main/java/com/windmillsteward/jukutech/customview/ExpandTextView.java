package com.windmillsteward.jukutech.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.interfaces.OnReadMoreClickListener;
import com.windmillsteward.jukutech.utils.GraphicUtil;

/**
 * 描述：折叠textView
 * 时间：2018/1/22
 * 作者：xjh
 */

public class ExpandTextView extends LinearLayout implements View.OnClickListener {
    public static final String TAG = "ExpandTextView";

    public static final int DEFAULT_TITLE_TEXT_COLOR = 0X8A000000;
    public static final int DEFAULT_CONTENT_TEXT_COLOR = 0XDE000000;
    public static final int DEFAULT_HINT_TEXT_COLOR = 0XDE000000;
    public static final int DEFAULT_MIN_LINES = 4;
    public static final int DEFAULT_TITLE_TEXT_SIZE = 16;
    public static final int DEFAULT_CONTENT_TEXT_SIZE = 14;
    public static final int DEFAULT_HINT_TEXT_SIZE = 12;
    public static final int DEFAULT_ANIMATION_DURATION = 600;

    private Context mContext;
    //内容文字
    private String content;
    //展开后显示的文字
    private String foldHint;
    //折叠起来后显示的文字
    private String expandHint;
    //内容文字颜色
    private int contentTextColor;
    //查看更多文字颜色
    private int hintTextColor;
    //查看更多前面显示的小图标
    private Drawable indicateImage;
    //内容区域最少显示几行文字
    private int minVisibleLines;
    //内容字体大小
    private float contentTextSize;
    //提示字体大小
    private float hintTextSize;
    //展开和折叠动画持续时间
    private int animationDuration;

    private TextView mContentView;
    private TextView mHintView;
    private ImageView mIndicateImage;
    private LinearLayout mShowMore;
    private TextView copy;

    private OnReadMoreClickListener mListener;

    //      是否已经展开
    private boolean flag;

    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        content = a.getString(R.styleable.ExpandTextView_contentText);
        foldHint = a.getString(R.styleable.ExpandTextView_foldHint);
        expandHint = a.getString(R.styleable.ExpandTextView_expandHint);
        contentTextColor = a.getColor(R.styleable.ExpandTextView_textContentColor, DEFAULT_CONTENT_TEXT_COLOR);
        hintTextColor = a.getColor(R.styleable.ExpandTextView_textHintColor, DEFAULT_HINT_TEXT_COLOR);
        indicateImage = a.getDrawable(R.styleable.ExpandTextView_indicateImage);
        minVisibleLines = a.getInt(R.styleable.ExpandTextView_minVisibleLines, DEFAULT_MIN_LINES);
        contentTextSize = a.getDimension(R.styleable.ExpandTextView_contentTextSize, GraphicUtil.sp2px(mContext, DEFAULT_CONTENT_TEXT_SIZE));
        hintTextSize = a.getDimension(R.styleable.ExpandTextView_hintTextSize, GraphicUtil.sp2px(mContext, DEFAULT_HINT_TEXT_SIZE));
        animationDuration = a.getInt(R.styleable.ExpandTextView_expandAnimationDuration, DEFAULT_ANIMATION_DURATION);

        a.recycle();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View.inflate(mContext, R.layout.expand_text_view, this);
        mContentView = (TextView) findViewById(R.id.tv_content);
        mHintView = (TextView) findViewById(R.id.tv_more_hint);
        mIndicateImage = (ImageView) findViewById(R.id.iv_arrow_more);
        mShowMore = (LinearLayout) findViewById(R.id.rl_show_more);

        mContentView.setText(content);
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
        mContentView.setTextColor(contentTextColor);

        mHintView.setText(expandHint);
        mHintView.setTextSize(TypedValue.COMPLEX_UNIT_PX, hintTextSize);
        mHintView.setTextColor(hintTextColor);
        mIndicateImage.setImageResource(R.mipmap.icon_makemore);
        mShowMore.setOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.height = getMinMeasureHeight();
        mContentView.setLayoutParams(layoutParams);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        mContentView.setText(content);
        // 当前textView的高度
        int maxMeasureHeight = getMaxMeasureHeight();
        // 设置的显示的条数的高度
        int minMeasureHeight = getMinMeasureHeight();

        if (maxMeasureHeight<=minMeasureHeight) {
            mShowMore.setVisibility(GONE);
            ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
            layoutParams.height = maxMeasureHeight;
            mContentView.setLayoutParams(layoutParams);
        } else {
            mShowMore.setVisibility(VISIBLE);
            ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
            layoutParams.height = getMinMeasureHeight();
            mContentView.setLayoutParams(layoutParams);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_show_more) {
            expand();
        }
    }

    /**
     * 获取TextView最大高度
     *
     * @return int 最大测量高度
     */
    public int getMaxMeasureHeight() {
        int width = mContentView.getMeasuredWidth();
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(3000, MeasureSpec.AT_MOST);
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        return mContentView.getMeasuredHeight();
    }

    /**
     * 获取TextView最小高度
     *
     * @return int 最小测量高度
     */
    public int getMinMeasureHeight() {
        if (copy == null) {
            copy = new TextView(mContext);
            copy.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
            copy.setLineSpacing(GraphicUtil.dp2px(mContext, 6), 1.0f);
            copy.setLines(minVisibleLines);
        }
        int width = mContentView.getMeasuredWidth();
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.AT_MOST);
        copy.setLayoutParams(mContentView.getLayoutParams());
        copy.measure(widthMeasureSpec, heightMeasureSpec);
        return copy.getMeasuredHeight();
    }

    /**
     * 折叠和展开
     */
    private void expand() {
        int startHeight;
        int targetHeight;
        if (!flag) {
            flag = true;
            startHeight = getMinMeasureHeight();
            targetHeight = getMaxMeasureHeight();
            if (targetHeight < startHeight) {
                targetHeight = startHeight;
            }
            mHintView.setText(foldHint);
            ObjectAnimator.ofFloat(mIndicateImage, "rotation", 0, 180).start();
            if (mListener != null) {
                mListener.onExpand();
            }
        } else {
            flag = false;
            startHeight = getMaxMeasureHeight();
            targetHeight = getMinMeasureHeight();
            if (startHeight < targetHeight) {
                startHeight = targetHeight;
            }
            mHintView.setText(expandHint);
            ObjectAnimator.ofFloat(mIndicateImage, "rotation", -180, 0).start();
            if (mListener != null) {
                mListener.onFold();
            }
        }
        if (animationDuration < 0) {
            animationDuration = DEFAULT_ANIMATION_DURATION;
        }
        final ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, targetHeight);
        animator.setDuration(animationDuration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.height = (int) animation.getAnimatedValue();
                mContentView.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }

    public void setmListener(OnReadMoreClickListener mListener) {
        this.mListener = mListener;
    }
}
