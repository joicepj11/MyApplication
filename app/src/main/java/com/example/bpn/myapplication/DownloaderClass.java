package com.example.bpn.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bpn on 19/09/17.
 */

public class DownloaderClass extends AsyncTask<Object, Object, Void> {

    ArrayList<String> arr = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();
    Context ctx;
    PassData callback ;
    PassData callback1;
    FragementTwo fragement ;

    public DownloaderClass( PassData callback ,Context ctx){
        this.ctx = ctx;
        this.callback = callback;
    }


    DownloaderClass(Context ctx){
        this.ctx = ctx;
        //this.callback = callback;
    }

    public DownloaderClass(FragementTwo fragementTwo , FragementOne fragementOne,Context ctx){
        fragement = fragementTwo;
        this.callback = fragement;
        this.callback1 = fragementOne;
        this.ctx = ctx;
    }

    void readJsonObject( JsonReader reader )throws Exception{

        reader.beginObject();

        String name ;

         while (reader.hasNext()){
             name = reader.nextName();
             if(name.equals("android")){
                readJsonArray(reader);
             }else if(name.equals("ver")){
                 String data  = reader.nextString();
                 arr.add(data);
             }else if(name.equals("name")){
                 arr.add(reader.nextString());
             } else if(name.equals("api")){
                 arr.add(reader.nextString());
             }else{
                 reader.skipValue();
             }


         }

        reader.endObject();
    }

    private void readJsonArray(JsonReader reader) throws Exception{
        reader.beginArray();

        while (reader.hasNext()){
        readJsonObject(reader);
        }

        reader.endArray();
    }

    public void readJsonData(JsonReader reader) throws Exception{

        if(reader.peek() == JsonToken.BEGIN_OBJECT){
            readJsonObject(reader);
        }else if(reader.peek() == JsonToken.BEGIN_ARRAY){
            readJsonArray(reader);
        }

    }


    @Override
    protected Void doInBackground(Object... voids) {


        Request request = new Request.Builder()
                .url("https://api.learn2crack.com/android/jsonandroid/")
                .build();

        try{

            Response  response =  client.newCall(request).execute();

            if(response.isSuccessful()){
                JsonReader reader = new JsonReader(response.body().charStream());
                    readJsonData(reader);
                //testing commit
            }else{
                Toast.makeText(ctx , " Couldn't read data ",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

//        Intent mIntent = new Intent("arrayListPassingBroadcastSender");
//        mIntent.putStringArrayListExtra("ParsedData",arr);
//        LocalBroadcastManager.getInstance(ctx).sendBroadcast(mIntent);

//        Intent mIntent1 = new Intent("passDataToFragement1");
//        mIntent1.putStringArrayListExtra("ParsedData",arr);
//        LocalBroadcastManager.getInstance(ctx).sendBroadcast(mIntent1);

        //Log.d("data",arr.toString());

        callback.receiveDataFromDownloadClass(arr);
        callback1.receiveDataFromDownloadClass(arr);

        return null;
    }
//    public void setListner(Context ctx){
//        data = ctx;
//    }



}
