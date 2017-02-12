/*
Assignment #: Homework 04
File Name: ImageDownloadTask.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.mohamed.hw4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    ImageData activity;

    public ImageDownloadTask(ImageData activity){
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.startProgress();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.setupData(bitmap);
        activity.stopProgress();
    }

    static public interface ImageData{
        public void setupData(Bitmap image);
        public void startProgress();
        public void stopProgress();
    }
}
