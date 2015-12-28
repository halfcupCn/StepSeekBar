package com.halfcup.stepseekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

/**
 *
 * Created by HP on 2015/12/24.
 */
public class StepSeekBar extends SeekBar {
    public static final String TAG = StepSeekBar.class.getSimpleName();
    //默认竖线宽度
    private final float stepWidth = 4;
    //步数，即间隔数，默认为5
    private int step = 5;
    //竖线高度，默认为1/4控件高度?或者固定高度?
    private float stepHeight = 30;
    //在测量的时候获取实际控件的宽高
    private int parentMeasureWidth, parentMeasureHeight;
    //获取顶点位置 left,top & right,bottom
    //获取默认颜色
    public StepSeekBar(Context context) {
        super(context);
    }
    public StepSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public StepSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        setMax(step);
    }
    public void setStep(int step) {
        this.step = step;
        setMax(step);
    }
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "get size == " + parentMeasureWidth + " && " + parentMeasureHeight);
    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();
        float viewWidth = canvasWidth - getPaddingLeft() - getPaddingRight();
        //在父类draw的图形下draw
        int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();
        Log.e(TAG, "size == " + left + " & " + top + " & " + right + " & " + bottom);
        float drawX = getPaddingLeft();
        @SuppressLint("DrawAllocation") Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        getProgressDrawable()
        paint.setColor(Color.LTGRAY);
        for (int i = 0; i <= step; i++) {
            float drawL, drawR, drawT, drawB;
            drawL = drawX + i * viewWidth / step;
            drawT = canvasHeight / 2 - stepHeight;
            drawR = drawL + stepWidth;
            drawB = canvasHeight / 2;
            Log.e(TAG, "when draw size == " + drawL + " & " + drawT + " & " + drawR + " & " + drawB);
            canvas.drawRect(drawL, drawT, drawR, drawB, paint);
        }
        super.onDraw(canvas);
    }
}
