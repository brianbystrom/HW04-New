/*
Assignment #: Homework 04
File Name: StatsActivity.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.mohamed.hw4;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ArrayList<Questions> questionList = new ArrayList<Questions>();
    ScrollView questionAnswers;
    int totalCorrect = 0;
    boolean color;
    ProgressBar correctProgressBar;
    TextView correctText;
    int score;
    int questions;
    Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        if (getIntent().getExtras() != null) {
            questionList = (ArrayList<Questions>) getIntent().getExtras().getSerializable("QUESTION");

            LinearLayout ll = new LinearLayout(StatsActivity.this);
            ll.setOrientation(LinearLayout.VERTICAL);

            questionAnswers = (ScrollView) findViewById(R.id.answers_scrollview);
            questionAnswers.removeAllViews();

            correctProgressBar = (ProgressBar) findViewById(R.id.correct_progressbar);
            correctProgressBar.setMax(questionList.size());

            correctText = (TextView) findViewById(R.id.textView_percent);

            finishBtn = (Button) findViewById(R.id.button_stats_finish);



            for(int i = 0; i < questionList.size(); i++) {
                TextView tv1 = new TextView(StatsActivity.this);
                TextView tv2 = new TextView(StatsActivity.this);
                TextView tv3 = new TextView(StatsActivity.this);
                String userText = "";
                String correctText = "";

                ArrayList<String> choices = new ArrayList<String>();
                choices = questionList.get(i).getChoices();

                if(questionList.get(i).getAnswer() == questionList.get(i).getUserAnswer()) {
                    totalCorrect++;
                    userText = choices.get(questionList.get(i).getUserAnswer()-1).toString();
                    correctText = choices.get(questionList.get(i).getAnswer()-1).toString();
                    color = false;
                } else if (questionList.get(i).getUserAnswer() == 0) {
                    correctText = choices.get(questionList.get(i).getAnswer()-1).toString();
                    color = true;
                    userText = "No Answer Given";

                    tv1.setText("Question: " + questionList.get(i).getText().toString());
                    tv2.setText("Your Answer: " + userText);
                    tv3.setText("Correct Answer: " + correctText + "\n");

                    if(color) {
                        tv2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }

                    ll.addView(tv1);
                    ll.addView(tv2);
                    ll.addView(tv3);
                } else {
                    color = true;
                    userText = choices.get(questionList.get(i).getUserAnswer()-1).toString();
                    correctText = choices.get(questionList.get(i).getAnswer()-1).toString();

                    tv1.setText("Question: " + questionList.get(i).getText().toString());
                    tv2.setText("Your Answer: " + userText);
                    tv3.setText("Correct Answer: " + correctText + "\n");

                    if(color) {
                        tv2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }

                    ll.addView(tv1);
                    ll.addView(tv2);
                    ll.addView(tv3);
                }



            }

            finishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quitGame(v);
                }
            });

            questionAnswers.addView(ll);
            questions = questionList.size();
            int score = (int) (((double) totalCorrect / (double) questions) * 100);
            Log.d("SCORE", questions + "/" + totalCorrect +" SCORE");
            correctProgressBar.setProgress(totalCorrect);
            correctText.setText(score+"%");

        } else {

        }
    }

    public void quitGame(View view) {
        Intent done = new Intent();
        setResult(RESULT_OK);
        StatsActivity.this.finish();
    }

}
