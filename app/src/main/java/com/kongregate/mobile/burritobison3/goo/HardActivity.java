package com.kongregate.mobile.burritobison3.goo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HardActivity extends AppCompatActivity {

    Random randomHard = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView timeHard = findViewById(R.id.timeHard);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView oneHard = findViewById(R.id.oneHard);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView twoHard = findViewById(R.id.twoHard);
        ImageView threeHard = findViewById(R.id.threeHard);
        LinearLayout ll_wasp = findViewById(R.id.ll_game);
        Intent i = new Intent(getApplicationContext(), Winner.class);

        oneHard.setOnClickListener(v -> {
            int counter = 0;
            ++counter;
        });
        twoHard.setOnClickListener(v -> {
            int counter = 0;
            ++counter;
        });
        threeHard.setOnClickListener(v -> {
            int counter = 0;
            ++counter;
        });
        CountDownTimer countDownTimerHard = new CountDownTimer(20 * 1000, 1000) {

            @Override
            public void onTick(long millsUntilFinished) {
                timeHard.setText("" + millsUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                startActivity(i);
                finish();
            }
        };
        countDownTimerHard.start();

        Timer timerHardOne = new Timer();
        timerHardOne.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> oneHard.animate()
                        .x(randomHard.nextFloat() * (ll_wasp.getWidth()-oneHard.getWidth()))
                        .y(randomHard.nextFloat() * (ll_wasp.getHeight()-oneHard.getHeight()))
                        .setDuration(150)
                        .start());
            }
        },0,300);

        Timer timerHardTwo = new Timer();
        timerHardTwo.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> twoHard.animate()
                        .x(randomHard.nextFloat() * (ll_wasp.getWidth()-twoHard.getWidth()))
                        .y(randomHard.nextFloat() * (ll_wasp.getHeight()-twoHard.getHeight()))
                        .setDuration(150)
                        .start());
            }
        },0,300);

        Timer timerHardThree = new Timer();
        timerHardThree.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> threeHard.animate()
                        .x(randomHard.nextFloat() * (ll_wasp.getWidth()-threeHard.getWidth()))
                        .y(randomHard.nextFloat() * (ll_wasp.getHeight()-threeHard.getHeight()))
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