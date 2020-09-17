package ru.axout.rgbcircles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

public class PlayActivity extends Activity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void toScoreActivity() {
        try {
            Intent intent3 = new Intent(this, MainActivity.class);
            startActivity(intent3);
        } catch (Exception e) {
            Log.d(TAG, "Кощей", e);
            e.printStackTrace();
        }
    }
}
