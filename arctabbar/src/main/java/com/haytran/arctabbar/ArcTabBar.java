package com.haytran.arctabbar;

import android.content.Context;
import android.content.res.TypedArray;
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
    private int mNumberOfSecondaryCircle;
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
        if (set == null) {
            return;
        }
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.ArcTabBar);
        mNumberOfSecondaryCircle = ta.getInt(R.styleable.ArcTabBar_numberOfSecondaryIcon, 1);
        Log.d(TAG, "init: mNumberOfSecondaryCircle = " + mNumberOfSecondaryCircle);
        mPrimaryCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPrimaryCirclePaint.setColor(Color.RED);
        mSecondaryCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondaryCirclePaint.setColor(Color.GREEN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPrimaryCircle = getPrimaryCircle(mSecondaryCircleRadius);
        drawCircle(canvas, mPrimaryCircle, mPrimaryCirclePaint);

        if (mNumberOfSecondaryCircle == 0) {
            return;
        }

        mSecondaryCircles = getSecondaryCircles(mPrimaryCircle, mSecondaryCircleRadius, mNumberOfSecondaryCircle);
        for (int i = 0; i < mNumberOfSecondaryCircle; i++) {
            drawCircle(canvas, mSecondaryCircles[i], mSecondaryCirclePaint);
        }
    }

    private Circle[] getSecondaryCircles(Circle primaryCircle, int secondaryCircleRadius, int numberOfItems) {
        if (numberOfItems <= 0) {
            return null;
        }
        Circle[] secondaryCircles = new Circle[numberOfItems];
        if (numberOfItems == 1) {
            secondaryCircles[0] = getSecondaryCircleFromAngle(primaryCircle, secondaryCircleRadius, 270);
            return secondaryCircles;
        }
        float pointXCrossScreen = getWidth() - primaryCircle.getCX();
        double angleCrossScreen = primaryCircle.getAngleFromX(pointXCrossScreen);
        double totalAngle = 180;
        double leftAngle = totalAngle - (angleCrossScreen * 2);
        double anglePerItem = leftAngle / (numberOfItems + 1);
        for (int i = 0; i < numberOfItems; i++) {
            double angle = 180 + angleCrossScreen + anglePerItem * (i + 1);
            secondaryCircles[i] = getSecondaryCircleFromAngle(primaryCircle, secondaryCircleRadius, angle);
        }
        return secondaryCircles;
    }

    private Circle getPrimaryCircle(int secondaryCircleRadius) {
        float secondaryCircleRadiusDp = ViewUtils.dp2px(getResources(), secondaryCircleRadius);
        float x = getWidth() / 2;
        float y = getWidth() + secondaryCircleRadiusDp;
        float radius = getWidth();
        Point point = new Point(x, y);
        return new Circle(point, radius);
    }

    private Circle getSecondaryCircleFromAngle(Circle primaryCircle, int secondaryCircleRadius, double degree) {
        Point point = primaryCircle.getPointInAngle(degree);
        float radius = ViewUtils.dp2px(getResources(), secondaryCircleRadius);
        return new Circle(point, radius);
    }

    private void drawCircle(Canvas canvas, Circle circle, Paint paint) {
        canvas.drawCircle(circle.getCX(), circle.getCY(), circle.radius, paint);
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

    private int getClickedCircle(Point touchedPoint) {
        for (int i = 0; i < mSecondaryCircles.length; i++) {
            if (mSecondaryCircles[i].hasPoint(touchedPoint)) {
                return i;
            }
        }
        return -1;
    }


}
