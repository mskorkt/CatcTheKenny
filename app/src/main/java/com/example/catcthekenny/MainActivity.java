package com.example.catcthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView homeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeText = findViewById(R.id.homeTxtView);
        homeText.setText("Hello Welcome To  Catc The Kenny Games"); //databases

    }

    public void playGame(View view){
        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }

    public void showScore(View view){
        Intent myIntent = new Intent(this, ScoreActivity.class);
        startActivity(myIntent);
    }

}