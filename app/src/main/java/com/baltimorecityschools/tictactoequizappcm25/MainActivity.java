package com.baltimorecityschools.tictactoequizappcm25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //  1.  All Variables including UI variables
    TextView questionTV;
    Button trueBtn, falseBtn, nextBtn;
    String msg;
    String qMsg;
    String choice;
    Question q1, q2, q3, q4, q5, currentQ, nextQ, previousQ;
    int currentIndex;
    Question[] questions;
    boolean userResponse;

    int duration = Toast.LENGTH_SHORT;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //      2.   Inflate UI elements and initialize all UI variables
     questionTV = (TextView) findViewById(R.id.questionTV);
     trueBtn = (Button) findViewById(R.id.trueBTN);
     falseBtn = (Button) findViewById(R.id.falseBTN);
     nextBtn = (Button) findViewById(R.id.finishedBTN);
     msg = "";
     qMsg = "";
     choice = "";
     q1 = new Question("A Tic Tac Toe board has 8 squares", false);
     q2 = new Question("Tic Tac Toe is a two player game", true);
     q3 = new Question("The game is played with numbers only", false);
     q4 = new Question("To win you must get three in a row", true);
     q5 = new Question("Tic Tac Toe is a popular game", true);
     questions = new Question[] {q1, q2, q3, q4, q5};
    currentIndex = 0;
    currentQ = q1;
    userResponse = false;
//     R stands for Resources
// 3. Do what the app needs to do!
    trueBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                msg = "WRONG";
            userResponse = true;
            if (currentQ.isThisCorrectAnswer(userResponse)){
                qMsg = "CORRECT";
                score++;
            } else {
                qMsg = "INCORRECT";
            }

            System.out.println("true msg: " + qMsg + "score: " + score);


            Toast myToast = Toast.makeText(MainActivity.this, qMsg, duration);
            myToast.show();
        }
    });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                msg = "WRONG";
                choice = "false";
                if (currentQ.isThisCorrectAnswer(userResponse)){
                    qMsg = "CORRECT";
                    score++;
                } else {
                    qMsg = "INCORRECT";
                }
                System.out.println("false msg: " + qMsg + "score: " + score);


                Toast myToast = Toast.makeText(MainActivity.this, qMsg, duration);
            myToast.show();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               currentIndex++;

               if (currentIndex < questions.length){
               currentQ = questions[currentIndex];
               questionTV.setText(currentQ.getqPrompt());

               System.out.println("next msg index: " + currentIndex + "score: " + score);

               }else{
                   System.out.println(" post next msg index: " + currentIndex + "score: " + score);
//            Toast myToast = Toast.makeText(MainActivity.this, qMsg, duration);
//            myToast.show();

                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                myIntent.putExtra("score", score);
               startActivity(myIntent);
               }
            }
        });
    }
}