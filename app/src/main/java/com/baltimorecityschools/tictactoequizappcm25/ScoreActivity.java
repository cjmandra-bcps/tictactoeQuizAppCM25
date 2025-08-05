package com.baltimorecityschools.tictactoequizappcm25;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {
//1. Decalre Vars
    int score;
    TextView scoreTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //2 Inflate UI variables
          scoreTV = (TextView) findViewById(R.id.scoreTV);
          score = 0;

    }
}