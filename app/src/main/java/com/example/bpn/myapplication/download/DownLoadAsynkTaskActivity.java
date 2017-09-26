package com.example.bpn.myapplication.download;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 25/9/17.
 */

public class DownLoadAsynkTaskActivity extends AsyncTask<String, Void, JsonReader> {
    Exception exception;
    Callback callback;
    private int statusCode;

    @Override
    protected JsonReader doInBackground(String... url) {

        OkHttpClient client = new OkHttpClient();
        Response response = null;
        Request request = new Request.Builder().url(url[0]).build();
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                setStatusCode(response.code());
                JsonReader reader = new JsonReader(response.body().charStream());
                return reader;
            }
        } catch (IOException e) {
            setStatusCode(response.code());
            callback.onComplete(null, getStatusCode(), e);
            e.printStackTrace();
        }
        return null;
    }

    public DownLoadAsynkTaskActivity setCallback(Callback callback) {
        this.callback = callback;
        return this;

    }

    @Override
    protected void onPostExecute(JsonReader jsonReader) {
        super.onPostExecute(jsonReader);
        callback.onComplete(jsonReader, getStatusCode(), exception);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public interface Callback {
        void onComplete(JsonReader reader, int stausCode, Exception e);
    }
}
