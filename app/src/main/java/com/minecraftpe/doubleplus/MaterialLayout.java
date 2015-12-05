package com.minecraftpe.doubleplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.minecraftpe.doubleplus.R;


public class MaterialLayout extends RelativeLayout {

    private static final int DEFAULT_RADIUS = 10;
    private static final int DEFAULT_FRAME_RATE = 10;
    private static final int DEFAULT_DURATION = 200;
    private static final int DEFAULT_ALPHA = 255;
    private static final float DEFAULT_SCALE = 0.8f;
    private static final int DEFAULT_ALPHA_STEP = 5;

    private int mFrameRate = DEFAULT_FRAME_RATE;
    private int mDuration = DEFAULT_DURATION;
   
    private Paint mPaint = new Paint();
    private Point mCenterPoint = null;
    private RectF mTargetRectf;
    private int mRadius = DEFAULT_RADIUS;
    private int mMaxRadius = DEFAULT_RADIUS;

    private int mCirclelColor = Color.LTGRAY;
    private int mRadiusStep = 1;
    private int mBackupAlpha;
    private float mCircleScale = DEFAULT_SCALE;
    private int mColorAlpha = DEFAULT_ALPHA;
    private int mAlphaStep = DEFAULT_ALPHA_STEP;

    private View mTargetView;

    /**
     * @param context
     */
    public MaterialLayout(Context context) {
        this(context, null);
    }

    public MaterialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaterialLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        if (attrs != null) {
            initTypedArray(context, attrs);
        }

        initPaint();

        this.setWillNotDraw(false);
        this.setDrawingCacheEnabled(true);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
																	 R.styleable.MaterialLayout);
        mCirclelColor = typedArray.getColor(R.styleable.MaterialLayout_change_color, Color.LTGRAY);
        mDuration = typedArray.getInteger(R.styleable.MaterialLayout_duration,
										  DEFAULT_DURATION);
        mFrameRate = typedArray
			.getInteger(R.styleable.MaterialLayout_framerate, DEFAULT_FRAME_RATE);
        mColorAlpha = typedArray.getInteger(R.styleable.MaterialLayout_alpha, DEFAULT_ALPHA);
        mCircleScale = typedArray.getFloat(R.styleable.MaterialLayout_scale, DEFAULT_SCALE);

        typedArray.recycle();

    }

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCirclelColor);
        mPaint.setAlpha(mColorAlpha);

        mBackupAlpha = mColorAlpha;
    }

 
    private boolean isInFrame(View touchView, float x, float y) {
        initViewRect(touchView);
        return mTargetRectf.contains(x, y);
    }

    private void initViewRect(View touchView) {
        int[] location = new int[2];
        touchView.getLocationOnScreen(location);
        // 视图的区域
        mTargetRectf = new RectF(location[0], location[1], location[0]
								 + touchView.getWidth(), location[1] + touchView.getHeight());

    }

    private void removeExtraHeight() {
        int[] location = new int[2];
        this.getLocationOnScreen(location); 
        mTargetRectf.top -= location[1];
        mTargetRectf.bottom -= location[1];    
        int centerHorizontal = (int) (mTargetRectf.left + mTargetRectf.right) / 2;
        int centerVertical = (int) ((mTargetRectf.top + mTargetRectf.bottom) / 2);
        mCenterPoint = new Point(centerHorizontal, centerVertical);

    }

    private View findTargetView(ViewGroup viewGroup, float x, float y) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof ViewGroup) {
                return findTargetView((ViewGroup) childView, x, y);
            } else if (isInFrame(childView, x, y)) { 
			
                return childView;
            }
        }

        return null;
    }

    private boolean isAnimEnd() {
        return mRadius >= mMaxRadius;
    }

    private void calculateMaxRadius(View view) {
        int maxLength = Math.max(view.getWidth(), view.getHeight());
        mMaxRadius = (int) ((maxLength / 2) * mCircleScale);

        int redrawCount = mDuration / mFrameRate;
        mRadiusStep = (mMaxRadius - DEFAULT_RADIUS) / redrawCount;
        // 计算每次alpha递减的值
        mAlphaStep = (mColorAlpha - 100) / redrawCount;
    }

    private void deliveryTouchDownEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTargetView = findTargetView(this, event.getRawX(), event.getRawY());
            if (mTargetView != null) {
                removeExtraHeight();
                calculateMaxRadius(mTargetView);
                invalidate();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        deliveryTouchDownEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // 绘制Circle
        drawRippleIfNecessary(canvas);
    }

    private void drawRippleIfNecessary(Canvas canvas) {
        if (isFoundTouchedSubView()) {
            mRadius += mRadiusStep;
            mColorAlpha -= mAlphaStep;
			canvas.clipRect(mTargetRectf);
            mPaint.setAlpha(mColorAlpha);
            // 绘制背景圆形,也就是
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mRadius, mPaint);
        }

        if (isAnimEnd()) {
            reset();
        } else {
            invalidateDelayed();
        }
    }
    private void invalidateDelayed() {
        this.postDelayed(new Runnable() {

				@Override
				public void run() {
					invalidate();
				}
			}, mFrameRate);
    }
    private boolean isFoundTouchedSubView() {
        return mCenterPoint != null && mTargetView != null;
    }

    private void reset() {
        mCenterPoint = null;
        mTargetRectf = null;
        mRadius = DEFAULT_RADIUS;
        mColorAlpha = mBackupAlpha;
        mTargetView = null;
        invalidate();
    }

}


