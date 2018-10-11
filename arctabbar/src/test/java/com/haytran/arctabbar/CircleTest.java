package com.haytran.arctabbar;

import org.junit.Before;
import org.junit.Test;

public class CircleTest {
    Circle circle;

    @Before
    public void setUp() {
        Point cPoint = new Point(30, 30);
        circle = new Circle(cPoint, 60);
    }

    @Test
    public void getDegreeFromX() {
        double degree = circle.getAngleFromX(0);
        System.out.print("degree = " + degree);
    }

}
