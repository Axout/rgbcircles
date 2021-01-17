package ru.axout.rgbcircles;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameManager  {
    public static final int MAX_CIRCLES = 10;
    public static final int SUPER_MAIN_SPEED = 80;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    private SuperCircle superCircle;
    private final CanvasView canvasView;
    private static int width;
    private static int height;
    private final User user;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w; // к нестатическим полям некорректно обращаться через this
        height = h; // к нестатическим полям некорректно обращаться через this

        // создаём круги
        initMainCircle();
        initEnemyCircles();
        initSuperCircle();
        user = new User();
    }

    // создаём вражеские круги
    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<>();
        for (int i = 0; i < MAX_CIRCLES; i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersectOnInit(mainCircleArea));
            circles.add(circle);
        }
        // корректируем радиусы кругов, чтобы всегда была возможность выиграть
        correctionRandomCircles(circles);
        // определим какой получился круг: враг или еда. И затем раскрасим.
        calculateAndSetCirclesColor();
    }

    // корректируем радиусы кругов, чтобы всегда была возможность выиграть
    private void correctionRandomCircles(ArrayList<EnemyCircle> circles) {
        int increasedRadius = mainCircle.radius;

        // сортируем вражеские круги по возрастанию
        Collections.sort(circles, new Comparator<EnemyCircle>() {
            @Override
            public int compare(EnemyCircle o1, EnemyCircle o2) {
                return Integer.compare(o2.radius, o1.radius);
            }
        });

        // для каждого круга проверяем меньше ли его радиус радиуса главного круга
        for (EnemyCircle circle : circles) {
            // если меньше (тупиковая ситуация), то уменьшаем радиус текущего вражеского круга
            if (increasedRadius < circle.getRadius())
                circle.setRadius(increasedRadius - 2);
            // а если нет, то идём дальше
            increasedRadius += circle.getRadius();
        }
    }

    // в зависимости от размера созданным кругам присваиваем роли: еда или враг
    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle : circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    // создаём главный круг
    private void initMainCircle() {
        // рисуем круг по-центру экрана
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    // создаём супер-круг
    private void initSuperCircle() {
        superCircle = SuperCircle.getSuperCircle();
    }

    // рисуем круги
    public void onDraw() {
        // рисуем главный круг
        canvasView.drawCircle(mainCircle);
        // рисуем вражеские круги
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
        // рисуем супер-круг
        if (superCircle != null) canvasView.drawCircle(superCircle);
    }

    // управление главным кругом по касанию
    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y); // двигаем главный круг
        checkCollision(); // проверим пересечение с другими кругами
        moveCircles(); // двигаем остальные круги
    }

    private void checkCollision() {
        // проверка вражеских кругов
        SimpleCircle circleForDel = null; // для удаления съеденного круга
        for (EnemyCircle circle : circles) {
            // если главный круг пересёк другой, то:
            if (mainCircle.isIntersect(circle)) {
                // по радиусу проверим - с каким кругом мы пересеклись
                if (circle.isSmallerThan(mainCircle)) {
                    // если круг меньшего радиуса, то "съедаем" его, увеличивая свой радиус на радиус съеденного
                    mainCircle.growRadius(circle);
                    // увеличим счёт
                    user.increaseScore(circle.radius);
                    // сохраним ссылку на тот круг, который хотим удалить
                    circleForDel = circle;
                    // вызовем переоценку всех цветов, потому, что наш размер теперь изменился
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    user.injury();
                    gameEnd("Health: " + user.getHealth());
                    return;
                }
            }
        }
        // проверка супер-круга
        if (superCircle != null) {
            if (mainCircle.isIntersect(superCircle)) {
                superCircle = null;
                mainCircle.setMainSpeed(SUPER_MAIN_SPEED);
                mainCircle.setColor(Color.CYAN);
            }
        }
        // после прохода и анализа всех кругов проверим:
        // если круг не null, то мы удалим данный круг
        if (circleForDel != null) {
            circles.remove(circleForDel);
        }
        // положительный конец игры - если все круги съедены
        if (circles.isEmpty()) {
            gameEnd("Score: " + user.getScore());
        }
    }

    // в случае наступления "конца игры", заново создаём вражеские круги
    private void gameEnd(String text) {
        mainCircle.updateMainCircle();
        initEnemyCircles();
        initSuperCircle();
        canvasView.redraw();
        if (user.getHealth() <= 0) {
            canvasView.showMessage("Your max score: " + user.getScore());
            user.setScore(0);
            user.setHealth(3);
        }
        else canvasView.showMessage(text);
    }

    // другие круги будут двигаться, но только при прикосновении к экрану
    // независимое перемещение реализуется через многопоточность
    private void moveCircles() {
        // вражеские круги
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
        // супер круг
        if (superCircle != null) superCircle.moveOneStep();
    }
}
