package com.example.catcthekenny;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView myImg ;
    TextView txtScoreView, txtTimeView;
    int score, screenX, screenY, maxY;
    long remainingTime;
    Button btnRestart;
    Button btnPause;
    SharedPreferences  sharedPreferences;
    CountDownTimer countDownT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display display = getWindowManager().getDefaultDisplay();
        Point size      = new Point();
        display.getSize(size);

        screenX         =       size.x;
        screenY         =       size.y;
        score           =       0;

        myImg           =       findViewById(R.id.kennyImage);
        txtScoreView    =       findViewById(R.id.scoreView);
        txtTimeView     =       findViewById(R.id.timeView);
        btnPause        =       findViewById(R.id.pauseBtn);
        btnRestart      =       findViewById(R.id.restartGameBtn);

        startGame();

        sharedPreferences = this.getSharedPreferences("com.example.catcthekenny", Context.MODE_PRIVATE);



    }

    public void startGame(){
        txtScoreView.setText("Score: " + (score = 0));
        startCountTimer(10000);
    }
    public void startCountTimer(long time){
        countDownT=new CountDownTimer(time,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                float rndmX =   new Random().nextInt(screenX - myImg.getMeasuredWidth());
                maxY        =   screenY-2*myImg.getMeasuredHeight();
                maxY        =   maxY-170;
                float rndmY = new Random().nextInt((maxY-170)+1) + 170;
                myImg.setX(rndmX);
                myImg.setY(rndmY);

                remainingTime=millisUntilFinished;
                txtTimeView.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                txtTimeView.setText("Time is Finish!");
                myImg.setVisibility(View.INVISIBLE);

                removeImgOnclick();
                btnRestart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);

                int storedScore = sharedPreferences.getInt("highScore",0);
                if (score > storedScore){
                    sharedPreferences.edit().putInt("highScore",score).apply();
                }
            }
        };

        countDownT.start();

    }




    public void increaseScore(View view){
        score++;
        txtScoreView.setText("Score: " + score);
    }

    //kennye ait OnClick fonksiyonunu devre dışı bırakıyor.
    public void removeImgOnclick(){
            myImg.setOnClickListener(null);
    }

    //kennye ait OnClik fonksiyonunu devreye alıyor.
    public void addImgOnclick(){

        myImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                increaseScore(v);
            }
        });
    }

    public void restartGame(View view){

        score=0;
        txtScoreView.setText("Puan: "+score);

        startCountTimer(10000);

        view.setVisibility(View.INVISIBLE);

        addImgOnclick();
        myImg.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.VISIBLE);
    }

    //Duraklat/Devam Et butonunun onClick fonksiyonu
    public void pauseGame(View view){
        Button clickObject = (Button) view;
        String command= (String) clickObject.getText();


        if(command.equals("Pause")){
            countDownT.cancel();
            removeImgOnclick();
            clickObject.setText("Resume");
        }else{
            addImgOnclick();
            startCountTimer(remainingTime);
            clickObject.setText("Pause");
        }
    }

    public void goMainPage(View view){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }




}