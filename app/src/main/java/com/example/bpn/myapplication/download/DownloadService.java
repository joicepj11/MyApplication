package com.example.bpn.myapplication.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

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
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                OkHttpClient client = new OkHttpClient();
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        JsonReader reader = new JsonReader(response.body().charStream());
                        readJsonData(reader);

                        sqlDatabase.closeDB();
                        Intent intent1 = new Intent("passDataToFragement1");
                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);

                    }
                } catch (Exception e) {
                    Log.e("errorMessage", e.getMessage());

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
