package mobile.kamheisiu.usmovientv.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import mobile.kamheisiu.usmovientv.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));

        finish();
    }
}
