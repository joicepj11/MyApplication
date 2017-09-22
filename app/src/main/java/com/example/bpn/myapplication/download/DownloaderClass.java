package com.example.bpn.myapplication.download;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.widget.Toast;

import com.example.bpn.myapplication.BeanJsonData;
import com.example.bpn.myapplication.fragment.FragementOne;
import com.example.bpn.myapplication.fragment.FragementTwo;
import com.example.bpn.myapplication.PassData;
import com.example.bpn.myapplication.SavingDataTOSharedPrefernce;

import java.util.ArrayList;
import java.util.HashSet;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bpn on 19/09/17.
 */

public class DownloaderClass extends AsyncTask<Object, Object, Void> {

    HashSet<String> hashSet =new HashSet<>();
    BeanJsonData data  ;
    ArrayList<String> arr = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();
    Context ctx;
     PassData callback ;
    PassData callback1;
    FragementTwo fragement ;
    ArrayList<BeanJsonData> arraylistJsonData = new ArrayList<>() ;


    public DownloaderClass( FragementTwo callback ,Context ctx){
        this.ctx = ctx;
        this.callback = callback;
    }


    DownloaderClass(Context ctx){
        this.ctx = ctx;
        //this.callback = callback;
    }

    public DownloaderClass(FragementTwo fragementTwo , FragementOne fragementOne, Context ctx){
        fragement = fragementTwo;
        this.callback = fragement;
        this.callback1 = fragementOne;
        this.ctx = ctx;
    }

    void readJsonObject( JsonReader reader )throws Exception{

        reader.beginObject();

        String name , Aname =null,api =null,version=null ;

         while (reader.hasNext()){
             name = reader.nextName();
             if(name.equals("android")){
                readJsonArray(reader);
             }else if(name.equals("ver")){
                 version  = reader.nextString();
                // hashSet.add(reader.nextString());
             }else if(name.equals("name")){
                 Aname = reader.nextString();
                 //hashSet.add(reader.nextString());
             } else if(name.equals("api")){
                api =  reader.nextString();
                //hashSet.add(reader.nextString());
             }else{
                 reader.skipValue();
             }


         }

        data   = new BeanJsonData(Aname ,version,api);
        arraylistJsonData.add(data);
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
//
//        mIntent1.putExtra("data",arraylistJsonData);
//
//        //mIntent1.putStringArrayListExtra("ParsedData",arraylistJsonData);
//        LocalBroadcastManager.getInstance(ctx).sendBroadcast(mIntent1);

        //Log.d("data",arr.toString());

        SavingDataTOSharedPrefernce preference = new SavingDataTOSharedPrefernce(ctx);
        preference.storeDataInSharedPreference(arraylistJsonData ,ctx);

        callback.receiveDataFromDownloadClass();
        callback1.receiveDataFromDownloadClass();

        return null;
    }
//    public void setListner(Context ctx){
//        data = ctx;
//    }



}
