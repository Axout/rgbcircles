package ru.axout.rgbcircles;

import android.app.Activity;
import android.view.Window;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // отключение заголовка (верхней плашки) у окна
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // выбор файла с xml-разметкой происходит с помощью метода setContentView(),
        // где аргументом является константа, показывающая какой файл нужно взять из папки "res"
        // название константы совпадает с именем файла рез расширения
        setContentView(R.layout.activity_main);
    }
}