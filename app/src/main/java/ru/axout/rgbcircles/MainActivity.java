package ru.axout.rgbcircles;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // отключение заголовка (верхней плашки) у окна
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // выбор файла с xml-разметкой
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonStart) {
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }
    }
}