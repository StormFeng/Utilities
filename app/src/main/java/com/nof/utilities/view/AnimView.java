package com.nof.utilities.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/22.
 */

public class AnimView extends View {

    private Paint mPaint;
    private int mDrawCount = 0;
    private final int DISTANCE = 10;

    public AnimView(Context context) {
        this(context,null);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(200);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawCount++;
        for (int i = 0; i < mDrawCount; i++) {
            canvas.drawLine(DISTANCE*i,0,DISTANCE*(i+1),0,mPaint);
        }
        if(mDrawCount<10){
            invalidate();
        }
    }
}
