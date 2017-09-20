package com.example.bpn.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class Main2Activity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    String data[] = null;
    ListView mListView;
    List<String> list;
    Thread t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mListView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        DownloaderClass downloaderClass = new DownloaderClass(getApplicationContext());
        downloaderClass.execute();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageBroadcastReceiver, new IntentFilter("arrayListPassingBroadcastSender"));
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view_layout, R.id.text, list);
        mListView.setAdapter(arrayAdapter);
    }

    private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
           ArrayList<String> data = intent.getStringArrayListExtra("ParsedData");
            setData(data);
        }
    };


    public void setData(ArrayList a) {
        Log.d("abc", a.toString());

        data = new String[a.size()];
        for(int i=0; i<a.size() ;i++){
            Object o = a.get(i) +" \n" + a.get(++i) +"  \n "+ a.get(++i) ;
            list.add(o.toString());
        }
        setUIdata();
    }

    void setUIdata() {

                mListView.setAdapter(arrayAdapter);


    }
}

