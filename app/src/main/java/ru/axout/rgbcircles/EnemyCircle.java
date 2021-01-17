package ru.axout.rgbcircles;

import android.graphics.Color;
import java.util.Random;

public class EnemyCircle extends SimpleCircle{

    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 110;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.rgb(0, 200, 0);
    public static final int RANDOM_SPEED = 10; // значение предельной скорости
    private int dx; // фактическая скорость по Х
    private int dy; // фактическая скорость по У

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        // координаты случайного появления вражеских кругов на экране в диапазоне от 0 до высоты или ширины экрана
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        // сделаем скорость и направление перемещение вражеского круга случайными
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        return new EnemyCircle(x, y, radius, dx, dy);
    }

    // образовавшийся круг больше главного, то это вражеский.
    // Если меньше, то - еда.
    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)) {
            setColor(FOOD_COLOR);
        } else {
            setColor(ENEMY_COLOR);
        }
    }

    public boolean isSmallerThan(SimpleCircle circle) {
        return radius < circle.radius;
    }

    // метод двигающий круги на один шаг при касании пальцем экрана
    public void moveOneStep() {
        x += dx;
        y += dy;
        // отражение вражеских кругов от границ экрана
        checkBounds();
    }

    // отражение от границ экрана
    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0) {
            dx = -dx;
        }
        if (y > GameManager.getHeight() || y < 0) {
            dy = -dy;
        }
    }
}
