package ru.axout.rgbcircles;

import android.graphics.Color;
import java.util.Random;

public class SuperCircle extends EnemyCircle{

    public static final int SUPER_RADIUS = 10;
    public static final int SUPER_SPEED = 30;
    public static final int SUPER_COLOR = Color.MAGENTA;

    public SuperCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius, dx, dy);
        setColor(SUPER_COLOR);
    }

    public static SuperCircle getSuperCircle() {
        // для генерации случайных координат супер-круга
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        return new SuperCircle(x, y, SUPER_RADIUS, SUPER_SPEED, SUPER_SPEED);
    }
}
