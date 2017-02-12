package com.example.mohamed.hw4;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;



public  class Questions implements Serializable {

    String text, image;
    ArrayList<String> choices;
    int id;
    int answer, userAnswer;

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }



    //static public Questions createQuestions(JSONObject js) throws JSONException {
        //Questions questions = new Questions();
        //questions.setId(js.getInt("id"));
        //questions.setText(js.getString("text"));
        //Create setChoices method
        //questions.setChoices(js.getString("choices"));
        /*if (js.has("image")) {
            questions.setImageUrl(js.getString("image"));
        }*/

        //return questions;


    //  private void setChoices(Object choices) {
    //    ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();

    //  for(int i =0; i < 5; i++){
    //    RadioButton button = new RadioButton()
    //  radioButtons.add(button);
    //}
    //}



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Data{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", choices=" + choices +
                ", id=" + id +
                ", answer=" + answer +
                '}';
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Questions() {

    }


}
