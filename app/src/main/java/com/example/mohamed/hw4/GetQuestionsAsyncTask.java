/*
Assignment #: Homework 04
File Name: GetQuestionsAsyncTask.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.mohamed.hw4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;



public class GetQuestionsAsyncTask extends AsyncTask<String,Void, ArrayList<Questions>> {

    ICommunicateWithAsync mContext;

    public GetQuestionsAsyncTask(ICommunicateWithAsync context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(ArrayList<Questions> s) {
        super.onPostExecute(s);
        mContext.sendData(s);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Questions> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }
                Log.d("demo", sb.toString());

                return  QuestionsUtil.QuestionsJSONParser.parseQuestions(sb.toString());

            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (ProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static interface ICommunicateWithAsync{
        public Context getContext();
        public void sendData(ArrayList<Questions> result);
    }

}
