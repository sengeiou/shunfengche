package com.windmillsteward.jukutech.customview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.GraphicUtil;


public class QuickIndexBar extends View{

	private OnLetterUpdateListener onLetterUpdateListener;
	public interface OnLetterUpdateListener {
		void onUpdate(String letter);
	}
	
	private Paint mPaint;
	
	private int mWidth;
	private int mHeight;
	private float mCellWidth;
	private float mCellHeight;
	private int colorN,colorSelect;

	private List<String> mLetters;
	
	public QuickIndexBar(Context context) {
		this(context, null);
	}
	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initView();
	}
	
	/* 初始化布局 */
	private void initView() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(GraphicUtil.sp2px(getContext(),13));
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		
		mLetters = new ArrayList<>();
		for (char i = 'A'; i <= 'Z'; i++){
			String s = String.valueOf(i);
			mLetters.add(s);
		}
		colorN = ContextCompat.getColor(getContext(), R.color.color_them);
		colorSelect = ContextCompat.getColor(getContext(), R.color.text_color_black);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();
		
		mCellWidth = mWidth;
		mCellHeight = mHeight / mLetters.size();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mLetters.size(); i++){
			
			Rect bounds = new Rect();
			mPaint.getTextBounds(mLetters.get(i), 0, 1, bounds);
			int x = (int) (mCellWidth / 2.0f - mPaint.measureText(mLetters.get(i)) / 2.0f);
			int y = (int) (mCellHeight - bounds.height() / 2.0f + i * mCellHeight);

			mPaint.setColor( currentIndex == i ? colorSelect : colorN );
			
			canvas.drawText(mLetters.get(i), x, y, mPaint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			dispathTouchAction(event);
			break;
		case MotionEvent.ACTION_MOVE:
			dispathTouchAction(event);
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		return true;
	}

	int preIndex = -1;
	int currentIndex = -1;

	private void dispathTouchAction(MotionEvent event) {
		currentIndex = (int) (event.getY() / mCellHeight);

		if (!( currentIndex >= 0 && currentIndex <= mLetters.size() - 1) ){
			return;
		}

		if (preIndex != currentIndex){
			preIndex = currentIndex;

			if (onLetterUpdateListener != null){
				onLetterUpdateListener.onUpdate(mLetters.get(currentIndex));
			}
		}

		invalidate();
		ViewCompat.postInvalidateOnAnimation(this);
	}
	public void setOnLetterUpdateListener(
			OnLetterUpdateListener onLetterUpdateListener) {
		this.onLetterUpdateListener = onLetterUpdateListener;
	}

	public void setCurrentSelectedIndex(int index){
		if (index < 0 || index > 25){
			return;
		}
		
		this.currentIndex = index;
		invalidate();
	}
}
