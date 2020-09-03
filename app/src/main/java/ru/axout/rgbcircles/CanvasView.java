package ru.axout.rgbcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView{
    private static int width;
    private static int height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context); // метод, определяющий размер экрана
        initPaint();
        gameManager = new GameManager(this, width, height);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true); // включение сглаживания
        paint.setStyle(Paint.Style.FILL); // заполнение цветом кружочков
    }

    // определяем размеры экрана
    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        // отображение тоста по центру экрана
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    // onTouchEvent - callback-метод, который будет вызван тогда,
    // когда палец будет прикоснён к экрану
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // узнаем координаты прикосновения к экрану
        int x = (int) event.getX();
        int y = (int) event.getY();
        // если палец двигается по экрану, тогда сдвигаем наш главный круг
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            gameManager.onTouchEvent(x, y);
        }
        invalidate(); // метод, который перерисовывает экран (без него будет одна статичная картинка)
        return true;
    }
}
