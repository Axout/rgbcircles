package ru.axout.rgbcircles;

public class SimpleCircle {
    protected int x; // координаты кругов
    protected int y;
    protected int radius; // радиус круга
    private int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    // метод возвращает площадь, в которую не должны попадать вражеские круги при создании
    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x, y, radius * 3);
    }

    // метод проверяет попал ли вражеский круг в зону главного круга
    public boolean isIntersect(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
    }
    // метод проверяет попал ли вражеский круг при создании в недоступную зону главного круга
    public boolean isIntersectOnInit(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2)) - 600;
    }
}
