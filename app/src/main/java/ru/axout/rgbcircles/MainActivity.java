package ru.axout.rgbcircles;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button buttonStart;
//    Button buttonScore;

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
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
//        buttonScore = findViewById(R.id.buttonScore);
//        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonStart) {
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }

//        switch (view.getId()) {
//            case R.id.buttonStart:
//                Intent intent = new Intent(this, PlayActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.buttonScore:
//                Intent intent2 = new Intent("intent.action.score");
//                startActivity(intent2);
//                break;
//        }
    }
}