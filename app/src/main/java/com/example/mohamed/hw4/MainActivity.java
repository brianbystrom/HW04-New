/*
Assignment #: Homework 04
File Name: MainActivity.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.mohamed.hw4;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetQuestionsAsyncTask.ICommunicateWithAsync {
    ArrayList<Questions> questionList = new ArrayList<>();
    ImageView logo;
    TextView ready;
    ProgressBar loadingTrivia;
    TextView loadingTriviaLabel, triviaReadyLabel;
    ImageView triviaReadyImage;
    Button startBtn;
    Button quitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingTrivia = (ProgressBar) findViewById(R.id.loading_progress_bar);
        loadingTriviaLabel = (TextView) findViewById(R.id.loading_trivia_label);
        triviaReadyImage = (ImageView) findViewById(R.id.trivia_image);
        triviaReadyLabel = (TextView) findViewById(R.id.trivia_ready_label);
        startBtn = (Button) findViewById(R.id.start_btn);
        quitBtn = (Button) findViewById(R.id.exit_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(v);
            }
        });

        new GetQuestionsAsyncTask(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void sendData(ArrayList<Questions> result) {

        loadingTrivia.setVisibility(GONE);
        loadingTriviaLabel.setVisibility(GONE);
        triviaReadyImage.setVisibility(VISIBLE);
        triviaReadyLabel.setVisibility(VISIBLE);
        startBtn.setEnabled(true);

        questionList = result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if(resultCode == RESULT_OK) {
                MainActivity.this.finish();
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "Unable to edit movie.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startGame(View view) {

        Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
        intent.putExtra("QUESTION", questionList);
        startActivityForResult(intent, 100);
    }


}

