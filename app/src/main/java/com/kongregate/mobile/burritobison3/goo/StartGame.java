package com.kongregate.mobile.burritobison3.goo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartGame extends AppCompatActivity {
    Button btnLight;
    Button btnMed;
    Button btnHard;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        btnLight = (Button) findViewById(R.id.light);
        btnMed = (Button) findViewById(R.id.medium);
        btnHard = (Button) findViewById(R.id.hardcore);


        btnLight.setOnClickListener(
                v -> startActivity(new Intent(getApplicationContext(),
                        LightActivity.class)));

        btnMed.setOnClickListener(
                v -> startActivity(new Intent(getApplicationContext(),
                        MedActivity.class)));

        btnHard.setOnClickListener(
                v -> startActivity(new Intent(getApplicationContext(),
                        HardActivity.class)));

    }
}