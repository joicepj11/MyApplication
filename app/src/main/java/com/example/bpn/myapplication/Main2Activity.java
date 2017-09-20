package com.example.bpn.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

public class Main2Activity extends AppCompatActivity  {
    ArrayAdapter<String> arrayAdapter;
    String data[] = null;
    ListView mListView;
    List list;
    PassData callback;
    PassData callback1;

    FragementOne mfragementOne;
    FragementTwo mfrFragementTwo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        mfragementOne = new FragementOne();
        mfrFragementTwo = new FragementTwo();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frameLayout1,mfragementOne);
        transaction.add(R.id.frameLayout2,mfrFragementTwo);
        transaction.commit();


//        mListView = (ListView) findViewById(R.id.listView);
           list = new ArrayList<>();

        DownloaderClass downloaderClass = new DownloaderClass(getApplicationContext());
       downloaderClass.execute();
      //  LocalBroadcastManager.getInstance(this).registerReceiver(mMessageBroadcastReceiver, new IntentFilter("arrayListPassingBroadcastSender"));
//        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view_layout, R.id.text, list);
//        mListView.setAdapter(arrayAdapter);
    }



    //private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {

//        @Override
//        public void onReceive(Context context, Intent intent) {
//           ArrayList<String> data = intent.getStringArrayListExtra("ParsedData");
//        }
//    };





    }


