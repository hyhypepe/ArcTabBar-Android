package com.haytran.arctabbar;

import org.junit.Before;
import org.junit.Test;

public class CircleTest {
    Circle circle;

    @Before
    public void setUp() {
        Point cPoint = new Point(360, 784);
        circle = new Circle(cPoint, 720);
    }

    @Test
    public void getDegreeFromX() {
        double degree = circle.getAngleFromX(360);
        System.out.print("degree = " + degree);
    }

}
