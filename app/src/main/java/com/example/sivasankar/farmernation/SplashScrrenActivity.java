package com.example.sivasankar.farmernation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScrrenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scrren);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScrrenActivity.this, LoginActivity.class));
                finish();

            }
        };
        new Handler().postDelayed(runnable, 2000);
    }

}
