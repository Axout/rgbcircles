package ru.axout.rgbcircles;

import java.util.ArrayList;

public class GameManager {
    public static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w; // к нестатическим полям некорректно обращаться через this
        height = h; // к нестатическим полям некорректно обращаться через this
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < MAX_CIRCLES; i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));
            circles.add(circle);
        }
        // определим какой получился круг: враг или еда. И затем расскрасим.
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle :
                circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initMainCircle() {
        // рисуем круг поцентру экрана
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    public void onDraw() {
        // рисуем главный круг
        canvasView.drawCircle(mainCircle);
        // рисуем вражеские круги
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        // проверим пересечение с другими кругами
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null; // для удаления съеденного круга
        for (EnemyCircle circle : circles) {
            // если главный круг пересёк другой, то конец игре
            if (mainCircle.isIntersect(circle)) {
                // по радиусу проверим - с каким кругом мы пересеклись
                if (circle.isSmallerThan(mainCircle)) {
                    // если круг меньшего радиуса, то "съедаем" его, увеличивая свой радиус на радиус съеденного
                    mainCircle.growRadius(circle);
                    // сохраним ссылку на тот круг, который хотим удалить
                    circleForDel = circle;
                    // вызовем переоценку всех цветов, потому, что наш размер теперь изменился
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    gameEnd("Ааа!.. Тебя съели!");
                    return;
                }
            }
        }
        // после прохода и анализа всех кругов проверим:
        // если круг не null, то мы удалим данный круг
        if (circleForDel != null) {
            circles.remove(circleForDel);
        }
        // положительный конец игры - если все круги съедены
        if (circles.isEmpty()) {
            gameEnd("Да! Ты победил!");
        }
    }

    // в случае наступления "конца игры", заново создаём вражеские круги
    // и перерисовываем экран
    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    // другие круги будут двигаться, но только при прикосновении к экрану
    // независимое перемещение требудет многопоточности
    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}
