package com.example.bpn.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.example.bpn.myapplication.download.DownloadService;
import com.example.bpn.myapplication.fragement.FragementOne;
import com.example.bpn.myapplication.fragement.FragementTwo;

import java.util.List;

public class Main2Activity extends AppCompatActivity  {
    ArrayAdapter<String> arrayAdapter;
    String data[] = null;
    ListView mListView;
    List list;
    PassData callback;
    PassData callback1;

    FragementOne mfragementOne;
    FragementTwo mfrFragementTwo ;
    private BeanJsonData beadJsonData;

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
          // list = new ArrayList<>();
//
//        DownloaderClass downloaderClass = new DownloaderClass(mfrFragementTwo ,mfragementOne, getApplicationContext());
//        downloaderClass.execute();
//


        startService(new Intent(Main2Activity.this,DownloadService.class));



//       callback.receiveDataFromDownloadClass();
        //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(mIntent1);

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


