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
    //final static String QUESTION_KEY = "QUESTION";
    Button start;
    ArrayList<Questions> questionList = new ArrayList<>();
    ImageView logo;
    TextView ready;
    ProgressBar loadingTrivia;
    TextView loadingTriviaLabel, triviaReadyLabel;
    ImageView triviaReadyImage;
    Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start = (Button) findViewById(R.id.button_start);
        //start.setEnabled(false);
        loadingTrivia = (ProgressBar) findViewById(R.id.loading_progress_bar);
        loadingTriviaLabel = (TextView) findViewById(R.id.loading_trivia_label);
        triviaReadyImage = (ImageView) findViewById(R.id.trivia_image);
        triviaReadyLabel = (TextView) findViewById(R.id.trivia_ready_label);
        startBtn = (Button) findViewById(R.id.start_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(v);
            }
        });

        if (isConnectedOnline()) {
            new GetQuestionsAsyncTask(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        } else {
            Toast.makeText(MainActivity.this, "No Internet Available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void sendData(ArrayList<Questions> result) {
        // TODO: in SendData method
        //1. Done - store result in variable of this activity
        //2. Set 'Trivia' image
        //3. Enable 'Start' button
        loadingTrivia.setVisibility(GONE);
        loadingTriviaLabel.setVisibility(GONE);
        triviaReadyImage.setVisibility(VISIBLE);
        triviaReadyLabel.setVisibility(VISIBLE);
        startBtn.setEnabled(true);

        questionList = result;
        //logo = (ImageView) findViewById(R.id.imageView_logo);
        //logo.setImageResource(R.drawable.trivia);
        //ready = (TextView) findViewById(R.id.textView_ready);
        //ready.setText("Trivia Ready");
        //start.setEnabled(true);


    }



    //on click on start
    //1. Use intent to pass questionList to TriviaActivity
    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
        for(int i = 0; i < questionList.size(); i++) {
            Log.d("DEMO", questionList.toString());
        }
        intent.putExtra("QUESTION", questionList);
        startActivity(intent);
    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


}

