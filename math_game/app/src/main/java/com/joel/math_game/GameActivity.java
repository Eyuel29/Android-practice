package com.joel.math_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textScore,textScoreValue,textLife,textLifeValue,textTime,textTimeValue,questionView;
    EditText answerInput;
    Button buttonSubmitAnswer, buttonNextQuestion;
    String operator;
    int result, life ,score;
    CountDownTimer timer;
    long startTimerInMill = 30000, timeLeftInMill = startTimerInMill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.operator = getIntent().getStringExtra("operatorSelected");
        switch (operator) {
            case "+":
                getSupportActionBar().setTitle("Addition");
                break;
            case "-":
                getSupportActionBar().setTitle("Subtraction");
                break;
            case "*":
                getSupportActionBar().setTitle("Division");
                break;
            default:
                getSupportActionBar().setTitle("Math-game");
                break;
        }

        textScore = findViewById(R.id.textScore);
        textScoreValue = findViewById(R.id.textValueScore);
        textLife = findViewById(R.id.textLife);
        textLifeValue = findViewById(R.id.textValueLife);
        textTime = findViewById(R.id.textTime);
        textTimeValue = findViewById(R.id.textValueTime);
        questionView = findViewById(R.id.gameQuestionView);
        answerInput = findViewById(R.id.inputAnswer);
        buttonSubmitAnswer = findViewById(R.id.buttonSubmitAnswer);
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion);

        score = 0;
        life = 3;

        buttonSubmitAnswer.setOnClickListener(v -> {
            if (!answerInput.getText().toString().equals("")){
                if (checkAnswer()){
                    youWin();
                }else{
                    gameOver();
                }
                updateTimeView();
                pauseTimer();
                updateScoreView();
                updateLifeView();
            }else{
                Toast.makeText(GameActivity.this, "Please give the answer", Toast.LENGTH_SHORT).show();
            }
        });

        buttonNextQuestion.setOnClickListener(v -> continueGame(operator));
        continueGame(operator);
    }

    public void continueGame(@NonNull String operator){

        if (life < 1){
            Intent intent = new Intent(GameActivity.this,ResultActivity.class);
            intent.putExtra("userScore",score);
            startActivity(intent);
            finish();
        }

        questionView.setBackgroundResource(R.color.orange);
        answerInput.setText("");
        buttonSubmitAnswer.setEnabled(true);

        if (timer != null) {
            pauseTimer();
            resetTimer();
            startTimer();
        }else{
            startTimer();
        }

        updateLifeView();
        updateTimeView();
        updateScoreView();


        Random random = new Random();
        int  numOne = 0, numTwo = 0;
        switch (operator) {
            case "+":
                numOne  =  random.nextInt(200);
                numTwo  =  random.nextInt(200);
                result = numOne + numTwo;
                break;
            case "-":
                numOne  =  random.nextInt(200);
                numTwo  =  random.nextInt(numOne);
                result = numOne - numTwo;
                break;
            case "*":
                numOne = random.nextInt(10);
                numTwo = random.nextInt(50);
                result = numOne * numTwo;
                break;
            default:
                break;
        }
        questionView.setText(numOne + operator + numTwo);
    }

    public void updateTimeView(){
        String timeLeft = String.format(Locale.US,"%02d",timeLeftInMill/1000);
        textTimeValue.setText(timeLeft);
    }

    public void updateScoreView(){
        textScoreValue.setText(String.valueOf(score));
    }

    public void updateLifeView(){
        textLifeValue.setText(String.valueOf(life));
    }

    public void startTimer(){
        timer = new CountDownTimer(timeLeftInMill,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMill = millisUntilFinished;
                updateTimeView();
            }

            @Override
            public void onFinish() {
                questionView.setBackgroundResource(R.color.RED);
                questionView.setText("Time is up!");
                buttonSubmitAnswer.setEnabled(false);

                pauseTimer();
                resetTimer();

                life -= 1;
                updateTimeView();
                updateScoreView();
                updateLifeView();
            }
        }.start();

    }

    public void gameOver(){
        questionView.setBackgroundResource(R.color.RED);
        questionView.setText("Wrong");
        buttonSubmitAnswer.setEnabled(false);
        life -= 1;
    }

    public void timeUp(){
        questionView.setBackgroundResource(R.color.RED);
        questionView.setText("Time is up!");
        buttonSubmitAnswer.setEnabled(false);
        life -= 1;
    }

    public void youWin(){
        questionView.setText("Correct!");
        buttonSubmitAnswer.setEnabled(false);
        score += 10;
    }

    public void pauseTimer(){
        timer.cancel();
    }

    public void resetTimer(){
        timeLeftInMill = startTimerInMill;
        updateTimeView();
    }

    public boolean checkAnswer(){
        int userAnswer = Integer.valueOf(answerInput.getText().toString());
        return userAnswer == result;
    }
}