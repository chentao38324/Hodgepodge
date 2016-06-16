package com.ptchan.hodgepodge.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chentao on 16-6-16.
 */
public class CounterView extends View implements View.OnClickListener{
    private Paint mPaint;//画笔
    private Rect mBounds;
    private int mCount;//计数器

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mBounds);
        float textWidth = mBounds.width();
        float textHeigh = mBounds.height();
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeigh/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
