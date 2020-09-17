package ru.axout.rgbcircles;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

//    Работа с логами:
//
//    private static final String TAG = "myLogs";
//
//    public void toScoreActivity() {
//        try {
//            Intent intent3 = new Intent(this, MainActivity.class);
//            startActivity(intent3);
//        } catch (Exception e) {
//            Log.d(TAG, "Кощей", e);
//            e.printStackTrace();
//        }
//    }
}
