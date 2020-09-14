package ru.axout.rgbcircles;

/*
Данный интерфейс позволяет разделить обработку интерфейса приложения и логику обработки данных.
Например, если мы захотим изменить внешний вид приложения, мы заменим CanvasView на какую-то другую "вьюшку".
Либо захотим перенести приложение на другую ОС ил десктоп-версию.
Поэтому всё, что касается отображения из GameManager необходимо перенести в CanvasView.

Данный приём реализует паттерн MVC (Model-View-Controller)
 */
public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);

    void toScoreActivity();
}
