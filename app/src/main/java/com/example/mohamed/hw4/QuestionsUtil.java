package com.example.mohamed.hw4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class QuestionsUtil {

    static public class QuestionsJSONParser{
        static ArrayList<Questions> parseQuestions(String in) throws JSONException {
            ArrayList<Questions> questionsList = new ArrayList<Questions>();

            try {
                JSONObject root = new JSONObject(in);
                JSONArray dataJSONArray = root.getJSONArray("questions");


                for (int i = 0; i<dataJSONArray.length(); i++) {
                    JSONObject dataJSONObject = dataJSONArray.getJSONObject(i);
                    Questions data = new Questions();

                    data.setId(dataJSONObject.getInt("id"));
                    data.setText(dataJSONObject.getString("text"));


                    if(dataJSONObject.has("image")) {
                        data.setImageUrl(dataJSONObject.getString("image"));
                    }

                    ArrayList<String> choiceList = new ArrayList();
                    JSONObject choices = dataJSONObject.optJSONObject("choices");
                    JSONArray choicesArray = choices.getJSONArray("choice");
                    for (int j = 0; j < choicesArray.length(); j++) {
                        choiceList.add(choicesArray.getString(j).toString());
                    }
                    data.setChoices(choiceList);
                    data.setAnswer(choices.getInt("answer"));
                    questionsList.add(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("demo", questionsList.toString());

            return questionsList;
        }
    }
}
