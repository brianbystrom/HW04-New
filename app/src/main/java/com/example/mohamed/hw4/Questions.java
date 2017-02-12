/*
Assignment #: Homework 04
File Name: Questions.java
Group Members: Brian Bystrom, Mohamed Salad
*/

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

    @Override
    public String toString() {
        return "Questions{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", choices=" + choices +
                ", id=" + id +
                ", answer=" + answer +
                ", userAnswer=" + userAnswer +
                '}';
    }

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
