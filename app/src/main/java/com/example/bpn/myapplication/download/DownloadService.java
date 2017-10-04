package com.example.bpn.myapplication.download;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Toast;

import com.example.bpn.myapplication.activity.Utilites;
import com.example.bpn.myapplication.data.SqlDatabase;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 21/9/17.
 */

public class DownloadService extends Service {
    SqlDatabase sqlDatabase;
    public DownloadService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
        sqlDatabase = new SqlDatabase(getApplicationContext());
        if (Aname != null) {
            sqlDatabase.insert(Aname, version, api);
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
    public int onStartCommand(final Intent intent, int flags, int startId) {

        final String url = (String) intent.getExtras().get("url");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                Utilites utilites = Utilites.getInstance();
                Utilites.URLVALIDATION urlvalidation = utilites.isReachable(url, getApplicationContext());
                if (urlvalidation == Utilites.URLVALIDATION.REACHABLE) {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    try {
                        response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            JsonReader reader = new JsonReader(response.body().charStream());
                            if(!utilites.isNull(reader)) {
                                readJsonData(reader);
                                sqlDatabase.closeDB();
                                Intent intent1 = new Intent("passDataToFragement1");
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
                            }else {
                                Handler handler =new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "json data not available", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }


                        }
                    } catch (Exception e) {
                        Log.e("errorMessage", e.getMessage());

                    }
                } else if (urlvalidation == Utilites.URLVALIDATION.UNREACHABLE) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "On mobile data or connect to wifi", Toast.LENGTH_LONG).show();
                            }
                        });
                }
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
