package com.example.mohamed.hw4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TriviaActivity extends AppCompatActivity implements ImageDownloadTask.ImageData{
    int questionIndex = 0;
    ArrayList<Questions> questionList = new ArrayList<Questions>();
    ProgressDialog progressDialog;
    ProgressBar imageLoad;
    TextView imageLoadText;
    TextView txtViewQuestionNo;
    TextView timer;
    ImageView questionImage;
    TextView question;
    //RadioGroup rdGrpOptions;
    ScrollView choicesView;
    Button prev;
    Button next;
    TextView noImageLabel;
    int tempAnswer;
    //LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        choicesView = (ScrollView) findViewById(R.id.question_scrollview);
        choicesView.removeAllViews();

        imageLoad = (ProgressBar) findViewById(R.id.image_load_progressbar);
        imageLoadText = (TextView) findViewById(R.id.image_load_label);
        noImageLabel = (TextView) findViewById(R.id.no_image_label);

        next = (Button) findViewById(R.id.button_next);
        prev = (Button) findViewById(R.id.button_prev);

        prev.setEnabled(false);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex < questionList.size() - 1) {
                    questionIndex++;
                    //RadioGroup tempGroup = (RadioGroup) findViewById(0);
                    //int tempAnswer = (Integer) tempGroup.getCheckedRadioButtonId();
                    //Log.d("DEMO", tempAnswer + " ANSWER");
                    showQuestion(v);
                } else {
                    finishGame(v);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex > 0) {
                    questionIndex--;
                    RadioGroup tempGroup = (RadioGroup) findViewById(0);
                    int tempAnswer = (Integer) tempGroup.getCheckedRadioButtonId();
                    Log.d("DEMO", tempAnswer + " ANSWER");
                    showQuestion(v);
                } else {
                    Toast.makeText(TriviaActivity.this, "You are already at the first question.", Toast.LENGTH_SHORT).show();

                }
            }
        });


        if (getIntent().getExtras() != null) {
            questionList = (ArrayList<Questions>) getIntent().getExtras().getSerializable("QUESTION");
            //Log.d("blah",questionList.size()+"");
        } else {

        }

        txtViewQuestionNo = (TextView) findViewById(R.id.text_view_question_no);
        timer = (TextView) findViewById(R.id.text_view_time_left);
        questionImage = (ImageView) findViewById(R.id.question_image);
        question = (TextView) findViewById(R.id.question);
        RadioGroup rdGrpOptions = new RadioGroup(TriviaActivity.this);
        //rdGrpOptions.setId(0);
        prev = (Button) findViewById(R.id.button_prev);
        next = (Button) findViewById(R.id.button_next);
        progressDialog = new ProgressDialog(this);

        txtViewQuestionNo.setText(getResources().getString(R.string.text_view_question_no, questionIndex + 1));

        question.setText(questionList.get(questionIndex).getText());

        //rdGrpOptions

        if(questionList.get(questionIndex).getImageUrl() == null){

        }else{
            new ImageDownloadTask(TriviaActivity.this).execute(questionList.get(questionIndex).getImageUrl());
            //questionImage.setImageBitmap(image);
        }

        ArrayList<String> choiceList = new ArrayList<String>();
        choiceList = questionList.get(questionIndex).getChoices();

        LinearLayout ll = new LinearLayout(TriviaActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.removeAllViews();
        rdGrpOptions.removeAllViews();



        for(int i = 0; i<choiceList.size(); i++){
            RadioButton rdBtnOption = new RadioButton(TriviaActivity.this);
            rdBtnOption.setText(choiceList.get(i).toString());
            rdGrpOptions.addView(rdBtnOption);
            //ll.addView(rdBtnOption);
        }

        ll.addView(rdGrpOptions);
        choicesView.addView(ll);

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(getResources().getString(R.string.text_view_time_left, millisUntilFinished));
            }

            public void onFinish() {
                Intent intent = new Intent(TriviaActivity.this, StatsActivity.class);
                //pass data?
                startActivity(intent);
            }


        }.start();

    }




    @Override
    public void setupData(Bitmap image) {
        questionImage.setImageBitmap(image);
        noImageLabel.setVisibility(GONE);
        imageLoad.setVisibility(GONE);
        imageLoadText.setVisibility(GONE);
        questionImage.setVisibility(VISIBLE);

    }

    @Override
    public void startProgress() {
        //progressDialog.setMessage("Loading Image...");
        //progressDialog.show();
    }

    @Override
    public void stopProgress() {
        progressDialog.dismiss();
    }

    public void showQuestion(View view) {

        if(questionIndex == 0) {
            prev.setEnabled(false);
        } else {
            prev.setEnabled(true);
        }

        if(questionIndex == questionList.size()-1) {
            next.setText("Finish");
        } else {
            next.setText("Next");
        }
        //if (questionIndex < questionList.size()){

            questionImage.setVisibility(GONE);
            noImageLabel.setVisibility(GONE);
            imageLoad.setVisibility(VISIBLE);
            imageLoadText.setVisibility(VISIBLE);

            if(questionList.get(questionIndex).getImageUrl() == null){
                imageLoad.setVisibility(GONE);
                imageLoadText.setVisibility(GONE);
                noImageLabel.setVisibility(VISIBLE);

            }else{
                new ImageDownloadTask(TriviaActivity.this).execute(questionList.get(questionIndex).getImageUrl());
                //questionImage.setImageBitmap(image);
            }

            txtViewQuestionNo.setText(getResources().getString(R.string.text_view_question_no, questionIndex + 1));
            question.setText(questionList.get(questionIndex).getText());

            choicesView.removeAllViews();


            /*if(questionList.get(questionIndex++).getImageUrl() == null){

            }else{
                new ImageDownloadTask(TriviaActivity.this).execute((Runnable) questionList.get(questionIndex));
                //questionImage.setImageBitmap(image);
            }*/

            ArrayList<String> choiceList = new ArrayList<String>();
            choiceList = questionList.get(questionIndex).getChoices();
            RadioGroup rdGrpOptions = (RadioGroup) new RadioGroup(TriviaActivity.this);
            rdGrpOptions.setId(0);

            LinearLayout ll = new LinearLayout(TriviaActivity.this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.removeAllViews();
            rdGrpOptions.removeAllViews();



            for(int i = 0; i<choiceList.size(); i++){
                RadioButton rdBtnOption = new RadioButton(TriviaActivity.this);
                rdBtnOption.setText(choiceList.get(i).toString());
                rdGrpOptions.addView(rdBtnOption);
                //ll.addView(rdBtnOption);
            }

            ll.addView(rdGrpOptions);
            choicesView.addView(ll);


            /*new CountDownTimer(120000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText(getResources().getString(R.string.text_view_time_left, millisUntilFinished));
                }

                public void onFinish() {
                    Intent intent = new Intent(TriviaActivity.this, StatsActivity.class);
                    //pass data?
                    startActivity(intent);
                }


            }.start();*/

        //}


    }

    public void finishGame(View view) {
        Intent intent = new Intent(TriviaActivity.this, StatsActivity.class);
        /*for(int i = 0; i < questionList.size(); i++) {
            Log.d("DEMO", questionList.toString());
        }*/
        intent.putExtra("QUESTION", questionList);
        startActivity(intent);
    }

    public void quitGame(View view) {
        TriviaActivity.this.finish();
    }
}



