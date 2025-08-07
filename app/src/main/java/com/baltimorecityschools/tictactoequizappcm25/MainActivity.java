package com.baltimorecityschools.tictactoequizappcm25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioAttributes; // Required for SoundPool builder
import android.media.SoundPool;     // For playing sound effects
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;

public class MainActivity extends AppCompatActivity {
//    for logging
    private static final String TAG = "MainActivity";


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
    private SoundPool soundPool;
    // Use SparseIntArray to map question index to sound ID
    private SparseIntArray correctSoundMap = new SparseIntArray();
    private SparseIntArray wrongSoundMap = new SparseIntArray();
    private boolean soundPoolLoaded = false;
    private int soundsToLoadCount = 0; // Keep track of how many sounds need to load
    private int soundsLoadedCount = 0; // Keep track of how many sounds have loaded

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
     q1 = new Question("A Tic Tac Toe board has 9 squares", true);
     q2 = new Question("Tic Tac Toe is a two player game", true);
     q3 = new Question("The game is played with numbers only", false);
     q4 = new Question("To win you must get four in a row", false);
     q5 = new Question("Tic Tac Toe is a popular game", true);
     questions = new Question[] {q1, q2, q3, q4, q5};
    currentIndex = 0;
    currentQ = q1;
    userResponse = false;
        setupSoundPool();
        loadSounds(); // Method to load all sounds
//     R stands for Resources
// 3. Do what the app needs to do!
    trueBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                msg = "WRONG";
            userResponse = true;
            int soundToPlayId = -1;
            if (currentQ.isThisCorrectAnswer(userResponse)){
                qMsg = "CORRECT";
                score++;
                Log.i(TAG, "Q" + (currentIndex + 1) + " (True) Answer is CORRECT. Score: " + score);
                soundToPlayId = correctSoundMap.get(currentIndex, -1);
            } else {
                qMsg = "INCORRECT";
                Log.w(TAG, "Q" + (currentIndex + 1) + " (True) Answer is INCORRECT.");
                soundToPlayId = wrongSoundMap.get(currentIndex, -1);
            }

            System.out.println("true msg: " + qMsg + "score: " + score);
            // Play the sound (before or after Toast, your preference)
            if (soundToPlayId != -1) {
                playSound(soundToPlayId);
            } else {
                Log.e(TAG, "Sound ID not found for Q" + (currentIndex + 1) + " (True). Index: " + currentIndex);
            }

            Toast myToast = Toast.makeText(MainActivity.this, qMsg, duration);
            myToast.show();
            // Disable buttons after answering
//            trueBtn.setEnabled(false);
//            falseBtn.setEnabled(false);
        }
    });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                msg = "WRONG";
                userResponse = false;
                int soundToPlayId = -1;
                if (currentQ.isThisCorrectAnswer(userResponse)){
                    qMsg = "CORRECT";
                    score++;
                    Log.i(TAG, "Q" + (currentIndex + 1) + " (False) Answer is CORRECT. Score: " + score);
                    soundToPlayId = correctSoundMap.get(currentIndex, -1);
                } else {
                    qMsg = "INCORRECT";
                    Log.w(TAG, "Q" + (currentIndex + 1) + " (False) Answer is INCORRECT.");
                    soundToPlayId = wrongSoundMap.get(currentIndex, -1);
                }
                System.out.println("false msg: " + qMsg + "score: " + score);
                // Play the sound
                if (soundToPlayId != -1) {
                    playSound(soundToPlayId);
                } else {
                    Log.e(TAG, "Sound ID not found for Q" + (currentIndex + 1) + " (False). Index: " + currentIndex);
                }

                Toast myToast = Toast.makeText(MainActivity.this, qMsg, duration);
            myToast.show();
                // Disable buttons after answering
//                trueBtn.setEnabled(false);
//                falseBtn.setEnabled(false);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               currentIndex++;
               Log.v(TAG, "Next button clicked. New currentIndex: " + currentIndex);
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
    private void setupSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(2, android.media.AudioManager.STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool sp, int sampleId, int status) {
                if (status == 0) {
                    soundsLoadedCount++;
                    Log.d(TAG, "Sound loaded: " + sampleId + " (" + soundsLoadedCount + "/" + soundsToLoadCount + ")");
                    if (soundsLoadedCount >= soundsToLoadCount) {
                        soundPoolLoaded = true;
                        Log.i(TAG, "All sounds loaded successfully!");
                    }
                } else {
                    Log.e(TAG, "Error loading sound " + sampleId + ", status: " + status);
                }
            }
        });
    }
    private void loadSounds() {
        // Ensure these R.raw.c1, R.raw.w1 etc. match your actual filenames in res/raw
        // The order must correspond to the question order (index 0 for Q1, 1 for Q2 etc.)
        int[] correctSoundFiles = {R.raw.c1, R.raw.c2, R.raw.c3, R.raw.c4, R.raw.c5}; // Example, replace c1 etc. with actual names
        int[] wrongSoundFiles =   {R.raw.w1, R.raw.w2, R.raw.w3, R.raw.w4, R.raw.w5}; // Example, replace w1 etc. with actual names

        soundsToLoadCount = correctSoundFiles.length + wrongSoundFiles.length;
        soundsLoadedCount = 0;

        Log.d(TAG, "Starting to load " + soundsToLoadCount + " sounds.");

        for (int i = 0; i < correctSoundFiles.length; i++) {
            if (i < questions.length) {
                int soundId = soundPool.load(this, correctSoundFiles[i], 1);
                correctSoundMap.put(i, soundId);
            }
        }

        for (int i = 0; i < wrongSoundFiles.length; i++) {
            if (i < questions.length) {
                int soundId = soundPool.load(this, wrongSoundFiles[i], 1);
                wrongSoundMap.put(i, soundId);
            }
        }
    }

    private void playSound(int soundId) {
        if (soundPoolLoaded && soundPool != null) {
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
            Log.d(TAG, "Playing sound ID: " + soundId);
        } else {
            Log.w(TAG, "SoundPool not fully loaded or null, cannot play sound ID: " + soundId + ". Loaded state: " + soundPoolLoaded);
            // Consider if you want a fallback or if no sound is acceptable if not loaded yet.
            // For many quick sounds, pre-loading and waiting is best.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}