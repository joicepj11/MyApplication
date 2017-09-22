package com.example.bpn.myapplication.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.JsonReader;
import android.util.JsonToken;

import com.example.bpn.myapplication.BeanJsonData;
import com.example.bpn.myapplication.SqlDatabase;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 21/9/17.
 */

public class DownloadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    SqlDatabase sqlDatabase = new SqlDatabase(getBaseContext());
    public DownloadService() {
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
        }
        sqlDatabase.insert(Aname,version,api);


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
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://api.learn2crack.com/android/jsonandroid/")
                        .build();
                try{
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        JsonReader reader = new JsonReader(response.body().charStream());
                        readJsonData(reader);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                Intent intent1 = new Intent("passDataToFragement1");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
                stopSelf();
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
