package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    TextView timerTextView;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;
    ConstraintLayout gameLayout;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
            numberOfQuestions++;
        }else{
            resultTextView.setText("Wrong!!");
            numberOfQuestions++;
        }
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

        nextQuestion();
    }

    public void playAgain(View view){
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        nextQuestion();
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        resultTextView.setText("");
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                Toast.makeText(MainActivity.this, "WOW. YOU SCORED "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions)+"!", Toast.LENGTH_LONG).show();

            }
        }.start();
    }

    public void nextQuestion(){
        Random random=new Random();

        int a=random.nextInt(21);
        int b=random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();

        for(int x=0 ; x<4 ; x++) {                                                   //Showing Answer right and wrong randomly on different buttons.
            if (x == locationOfCorrectAnswer) {
                answer.add(a+b);

            } else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = random.nextInt(41);
                }

                answer.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);

        timerTextView=findViewById(R.id.timerTextView);
        sumTextView=findViewById(R.id.sumTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);
        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        gameLayout=findViewById(R.id.gameLayout);

        playAgainButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
