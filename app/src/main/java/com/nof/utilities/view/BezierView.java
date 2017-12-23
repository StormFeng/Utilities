package com.nof.utilities.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Administrator on 2017/12/23.
 */

public class BezierView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private static final int H = 50;
    private static int W;

    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private Point p5;
    private Point c1;
    private Point c2;
    private Point c3;
    private Point c4;
    private Point b1;
    private Point b2;

    private int p;
    private Handler mHandler;
    private boolean isInit = false;
    Path path = new Path();

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        W = mWidth/4;
        setMeasuredDimension(mWidth,mHeight);
    }

    private void initParams(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(this,1);
                p+=20;
                initPoint(p);
                invalidate();
                if(p1.x==0){
                    p=0;
                }
            }
        });
        isInit = true;
    }

    private void initPoint(int offset){
        p1 = new Point(-4*W+offset,0);
        p2 = new Point(p1.x+2*W,0);
        p3 = new Point(p1.x+4*W,0);
        p4 = new Point(p1.x+6*W,0);
        p5 = new Point(p1.x+8*W,0);

        c1 = new Point(p1.x+W,-H);
        c2 = new Point(p1.x+3*W,H);
        c3 = new Point(p1.x+5*W,-H);
        c4 = new Point(p1.x+7*W,H);

        b1 = new Point(p1.x+8*W,mHeight-H);
        b2 = new Point(p1.x,mHeight-H);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,H);
        path.reset();
        path.moveTo(p1.x,p1.y);
        path.quadTo(c1.x,c1.y,p2.x,p2.y);
        path.quadTo(c2.x,c2.y,p3.x,p3.y);
        path.quadTo(c3.x,c3.y,p4.x,p4.y);
        path.quadTo(c4.x,c4.y,p5.x,p5.y);
        path.lineTo(b1.x,b1.y);
        path.lineTo(b2.x,b2.y);
        path.close();
        canvas.drawPath(path,mPaint);
    }
}
















