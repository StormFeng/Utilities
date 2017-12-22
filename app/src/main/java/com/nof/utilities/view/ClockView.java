package com.nof.utilities.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ClockView extends View {

    private Paint mPaint;
    private float mHeight;
    private int mPaintSize = dp2px(2);
    private int mRectRadius = dp2px(1);
    private int mCircleRadius = dp2px(6);
    private int mWidth = mCircleRadius * 2;
    public ClockView(Context context) {
        this(context,null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mPaintSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mHeight = getMeasuredHeight();
        setMeasuredDimension(mWidth,(int)mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mTemp = mHeight - mCircleRadius;
        canvas.translate(mCircleRadius,0);
        RectF rectF = new RectF(-mPaintSize/2,0,mPaintSize/2, mTemp);
        canvas.drawRoundRect(rectF,mRectRadius,mRectRadius,mPaint);
        canvas.save();

        canvas.translate(0, mTemp);
        canvas.drawCircle(0,0,mCircleRadius,mPaint);
    }

    private int dp2px(int dp){
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }
}
