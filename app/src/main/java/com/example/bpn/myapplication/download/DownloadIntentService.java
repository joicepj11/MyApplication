package com.example.bpn.myapplication.download;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.JsonReader;
import android.util.JsonToken;

import com.example.bpn.myapplication.BeanJsonData;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bpn on 21/09/17.
 */

public class DownloadIntentService extends IntentService {

    ArrayList<BeanJsonData> jsonParsedData = new ArrayList<>();
    public DownloadIntentService() {
        super("DownloadIntentService");
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

            }else if(name.equals("name")){
                Aname = reader.nextString();

            } else if(name.equals("api")){
                api =  reader.nextString();

            }else{
                reader.skipValue();
            }
            jsonParsedData.add(new BeanJsonData(Aname,version,api));

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
    protected void onHandleIntent(Intent intent) {
        Request request = new Request.Builder()
                .url("https://api.learn2crack.com/android/jsonandroid/")
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JsonReader reader = new JsonReader(response.body().charStream());
                readJsonData(reader);
            }
            Intent intent1 = new Intent("passDataToFragement1");
            intent1.putExtra("data",jsonParsedData);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);

            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
