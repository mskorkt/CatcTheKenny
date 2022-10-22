package com.example.catcthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTxt;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTxt = findViewById(R.id.scoreTxtView2);

        sharedPreferences = this.getSharedPreferences("com.example.catcthekenny", Context.MODE_PRIVATE);
        int storedScore = sharedPreferences.getInt("highScore",0);
        scoreTxt.setText(storedScore+"\n "+"Congratulations!");
    }
}