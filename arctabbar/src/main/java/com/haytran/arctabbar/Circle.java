package com.haytran.arctabbar;

public class Circle {
    private static final String TAG = "Circle";
    protected Point point;
    protected float radius;

    public Circle(Point point, float radius) {
        this.point = point;
        this.radius = radius;
    }

    public Point getPointInDegree(float degree) {
        double radian = Math.toRadians(degree);
        float x = point.cx + radius * (float) (Math.cos(radian));
        float y = point.cy + radius * (float) (Math.sin(radian));
        return new Point(x, y);
    }

    boolean hasPoint(Point forPoint) {
        if (point.calculateDistance(forPoint) < radius) {
            return true;
        } else {
            return false;
        }
    }

}
