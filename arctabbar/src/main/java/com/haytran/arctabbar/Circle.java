package com.haytran.arctabbar;

public class Circle {
    private static final String TAG = "Circle";
    protected Point point;
    protected float radius;

    public Circle(Point point, float radius) {
        this.point = point;
        this.radius = radius;
    }

    public Point getPointInAngle(double degree) {
        double radian = Math.toRadians(degree);
        float x = point.cx + radius * (float) (Math.cos(radian));
        float y = point.cy + radius * (float) (Math.sin(radian));
        return new Point(x, y);
    }

    public boolean hasPoint(Point forPoint) {
        if (point.calculateDistance(forPoint) < radius) {
            return true;
        } else {
            return false;
        }
    }

    public double getAngleFromX(float x) {
        double radian = Math.acos(x / radius);
        double degree = Math.toDegrees(radian);
        return degree;
    }

    public double getAngleFromY(float y) {
        double radian = Math.asin(y / radius);
        double degree = Math.toDegrees(radian);
        return degree;
    }

    public float getCX() {
        return point.cx;
    }

    public float getCY() {
        return point.cy;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "point=" + point +
                ", radius=" + radius +
                '}';
    }
}
