package com.haytran.arctabbar;

public class Point {
    private static final String TAG = "Point";
    float cx;
    float cy;

    public Point(float cx, float cy) {
        this.cx = cx;
        this.cy = cy;
    }

    double calculateDistance(Point toPoint) {
        double dx = Math.pow(toPoint.cx - this.cx, 2);
        double dy = Math.pow(toPoint.cy - this.cy, 2);
        return Math.sqrt(dx + dy);
    }

    @Override
    public String toString() {
        return "Point{" +
                "cx=" + cx +
                ", cy=" + cy +
                '}';
    }
}
