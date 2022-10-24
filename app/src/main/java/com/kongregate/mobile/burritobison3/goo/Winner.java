package com.kongregate.mobile.burritobison3.goo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Winner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView scoreText = findViewById(R.id.scoreText);
        Intent intent = getIntent();
        scoreText.setText(intent.getStringExtra("Score"));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button restart = findViewById(R.id.restartBtn);

        restart.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), StartGame.class)));
    }
}