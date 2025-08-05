package com.baltimorecityschools.tictactoequizappcm25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //  1.  All Variables including UI variables
    TextView q1TV;
    Button trueBtn, falseBtn, finishedBtn;
    String msg;
    String choice;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //      2.   Inflate UI elements and initialize all UI variables
     q1TV = (TextView) findViewById(R.id.q1TV);
     trueBtn = (Button) findViewById(R.id.trueBTN);
     falseBtn = (Button) findViewById(R.id.falseBTN);
     finishedBtn = (Button) findViewById(R.id.finishedBTN);
     msg = "";
     choice = "";

//     R stands for Resources
// 3. Do what the app needs to do!
    trueBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                msg = "WRONG";
            choice = "true";
//            int duration = Toast.LENGTH_SHORT;

//            Toast myToast = Toast.makeText(MainActivity.this, msg, duration);
//            myToast.show();
        }
    });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                msg = "WRONG";
                choice = "false";
//            int duration = Toast.LENGTH_SHORT;

//            Toast myToast = Toast.makeText(MainActivity.this, msg, duration);
//            myToast.show();
            }
        });
        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                msg = "WRONG";
               if (choice.equals("true")){
                   msg = "WRONG";
               }else{
                   msg = "CORRECT";
                   score++;
               }
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast myToast = Toast.makeText(MainActivity.this, msg, duration);
//            myToast.show();

                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                myIntent.putExtra("score", score);
               startActivity(myIntent);
            }
        });
    }
}