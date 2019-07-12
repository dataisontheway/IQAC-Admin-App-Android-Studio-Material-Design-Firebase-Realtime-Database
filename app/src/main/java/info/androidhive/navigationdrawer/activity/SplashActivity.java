package info.androidhive.navigationdrawer.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.navigationdrawer.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeintent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(homeintent);

                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                finish();

            }
        },SPLASH_TIME_OUT);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}