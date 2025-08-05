package com.baltimorecityschools.tictactoequizappcm25;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {
//1. Declare Vars
    int score;
    Button emailBTN;
    TextView scoreTV;
    Intent incomingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //2 Inflate UI variables
          scoreTV = (TextView) findViewById(R.id.scoreTV);
          score = 0;
          //3. Get intent
          incomingIntent = getIntent();
          score = incomingIntent.getIntExtra("score", 0);
          scoreTV.setText(score +"");
//          this is cool
        emailBTN = (Button) findViewById(R.id.emailBTN);

        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prepare email subject and body
                String subject = "My Quiz App Score!";
                String body = "I scored " + score + " points in the Tic Tac Toe Quiz App!";
                composeEmail(new String[] {"mandra@gmail.com"}, subject, body); // Pass recipient(s) as an array

                }
         });
        }



    public void composeEmail(String [] email, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}