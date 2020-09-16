package ru.axout.rgbcircles;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

//        final Intent intent = new Intent(this, ScoreActivity.class);
//        if (CanvasView.toScoreActivity) startActivity(intent);
    }
}
