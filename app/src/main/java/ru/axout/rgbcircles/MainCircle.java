package ru.axout.rgbcircles;

import android.graphics.Color;

public class MainCircle extends SimpleCircle {
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 50;
    public int mainSpeed = MAIN_SPEED;
    public static final int OUR_COLOR = Color.BLUE;

    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(OUR_COLOR);
    }

    public void setMainSpeed(int mainSpeed) {
        this.mainSpeed = mainSpeed;
    }

    public void moveMainCircleWhenTouchAt(int x1, int y1) {
        int dx = (x1 - x) * mainSpeed / GameManager.getWidth();
        int dy = (y1 - y) * mainSpeed / GameManager.getHeight();
        x += dx;
        y += dy;
    }

    public void updateMainCircle() {
        radius = INIT_RADIUS;
        setColor(OUR_COLOR);
        this.setMainSpeed(MAIN_SPEED);
    }

    public void growRadius(SimpleCircle circle) {
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
