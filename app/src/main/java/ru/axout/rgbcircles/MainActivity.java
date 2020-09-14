package ru.axout.rgbcircles;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // отключение заголовка (верхней плашки) у окна
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // выбор файла с xml-разметкой происходит с помощью метода setContentView(),
        // где аргументом является константа, показывающая какой файл нужно взять из папки "res"
        // название константы совпадает с именем файла рез расширения
        setContentView(R.layout.activity_main);
        // для переключения между экранами, а также для передачи данных между экранами нужно использовать интент
        final Intent intent = new Intent(this, PlayActivity.class);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}