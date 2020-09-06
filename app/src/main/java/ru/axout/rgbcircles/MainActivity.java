package ru.axout.rgbcircles;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // отключение заголовка (верхней плашки) у окна

        setContentView(R.layout.activity_start);

        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // выбор файла с xml-разметкой происходит с помощью метода setContentView(),
                // где аргументом является константа, показывающая какой файл нужно взять из папки "res"
                // название константы совпадает с именем файла рез расширения
                setContentView(R.layout.activity_main);
            }
        });
    }
}