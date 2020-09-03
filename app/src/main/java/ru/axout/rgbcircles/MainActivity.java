package ru.axout.rgbcircles;

import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // выбор файла с xml-разметкой происходит с помощью метода setContentView(),
        // где аргументом является константа, показывающая какой файл нужно взять из папки "res"
        // название константы совпадает с именем файла рез расширения
        setContentView(R.layout.activity_main);
    }
}