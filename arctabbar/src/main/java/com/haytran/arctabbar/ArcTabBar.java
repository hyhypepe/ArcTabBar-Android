package com.haytran.arctabbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by haytran on 10/09/2018.
 */

public class ArcTabBar extends View {
    private static final String TAG = "CustomView";
    private Paint mPrimaryCirclePaint;
    private Paint mSecondaryCirclePaint;
    private Circle mPrimaryCircle;
    private Circle[] mSecondaryCircles;
    private int mSecondaryCircleRadius = 32;
    private ArcTabBarListener listener;

    public ArcTabBar(Context context) {
        super(context);

        init(null);
    }

    public ArcTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public ArcTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public ArcTabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void setListener(ArcTabBarListener listener) {
        this.listener = listener;
    }

    private void init(@Nullable AttributeSet set) {
        mPrimaryCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPrimaryCirclePaint.setColor(Color.RED);
        mSecondaryCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondaryCirclePaint.setColor(Color.GREEN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPrimaryCircle = getPrimaryCircle();

        Circle firstCircle = getSecondaryCircle(252);
        Circle secondCircle = getSecondaryCircle(270);
        Circle thirdCircle = getSecondaryCircle(288);

        mSecondaryCircles = new Circle[3];
        mSecondaryCircles[0] = firstCircle;
        mSecondaryCircles[1] = secondCircle;
        mSecondaryCircles[2] = thirdCircle;

        drawCircle(canvas, mPrimaryCircle, mPrimaryCirclePaint);
        drawCircle(canvas, mSecondaryCircles[0], mSecondaryCirclePaint);
        drawCircle(canvas, mSecondaryCircles[1], mSecondaryCirclePaint);
        drawCircle(canvas, mSecondaryCircles[2], mSecondaryCirclePaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        Point touchedPoint = new Point(eventX, eventY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                int clickedPosition = getClickedCircle(touchedPoint);
                Log.d(TAG, "onTouchEvent: selectedPoint" + clickedPosition);
                if (listener != null && clickedPosition != -1) {
                    listener.didClickedCircle(clickedPosition);
                }
                break;
        }
        return true;
    }

    private Circle getPrimaryCircle() {
        float x = getWidth() / 2;
        float y = getWidth() + mSecondaryCircleRadius;
        float radius = y - mSecondaryCircleRadius*2;
        Point point = new Point(x, y);
        return new Circle(point, radius);
    }

    private Circle getSecondaryCircle(float degree) {
        Point point = mPrimaryCircle.getPointInAngle(degree);
        float radius = ViewUtils.dp2px(getResources(), mSecondaryCircleRadius);
        return new Circle(point, radius);
    }


    private void drawCircle(Canvas canvas, Circle circle, Paint paint) {
        canvas.drawCircle(circle.point.cx, circle.point.cy, circle.radius, paint);
    }

    private int getClickedCircle(Point touchedPoint) {
        for (int i = 0; i < mSecondaryCircles.length; i++) {
            if (mSecondaryCircles[i].hasPoint(touchedPoint)) {
                return i;
            }
        }
        return -1;
    }
}
