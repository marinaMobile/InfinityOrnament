package com.kongregate.mobile.burritobison3.goo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MedActivity extends AppCompatActivity {
    Random randomMed = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView timeMed = findViewById(R.id.timeMed);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView one = findViewById(R.id.one);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView two = findViewById(R.id.two);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout ll_wasp = findViewById(R.id.ll_game);
        Intent i = new Intent(getApplicationContext(), Winner.class);


        one.setOnClickListener(v -> {
            int counter = 0;
            ++counter;
        });
        two.setOnClickListener(v -> {
            int counter = 0;
            ++counter;
        });
        CountDownTimer countDownTimerMed = new CountDownTimer(16 * 1000, 1000) {

            @Override
            public void onTick(long millsUntilFinished) {
                timeMed.setText("" + millsUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                startActivity(i);
                finish();
            }
        };
        countDownTimerMed.start();

        Timer timerOne = new Timer();
        timerOne.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> two.animate()
                        .x(randomMed.nextFloat() * (ll_wasp.getWidth()-one.getWidth()))
                        .y(randomMed.nextFloat() * (ll_wasp.getHeight()-one.getHeight()))
                        .setDuration(150)
                        .start());
            }
        },0,300);

        Timer timerTwo = new Timer();
        timerTwo.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> one.animate()
                        .x(randomMed.nextFloat() * (ll_wasp.getWidth()-two.getWidth()))
                        .y(randomMed.nextFloat() * (ll_wasp.getHeight()-two.getHeight()))
                        .setDuration(150)
                        .start());
            }
        },0,300);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}