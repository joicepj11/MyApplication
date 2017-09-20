package com.example.bpn.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpn on 20/09/17.
 */

public class FragementOne extends Fragment  implements PassData {
    List list;
    ListView mListView;
    ArrayAdapter arrayAdapter;

    public FragementOne(){

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ArrayList<String> data = intent.getStringArrayListExtra("ParsedData");
//            list.addAll(data);
//            arrayAdapter.notifyDataSetChanged();
//
//        }
//    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragement_layout_one, container,false);


       // LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageBroadcastReceiver,new IntentFilter("passDataToFragement1") );

        mListView =  view.findViewById(R.id.list_item1);
        list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_view_layout, R.id.text, list);
        mListView.setAdapter(arrayAdapter);
        return view;
    }
    @Override
    public void receiveDataFromDownloadClass(ArrayList a ) {

        for (int i = 0; i < a.size(); i++) {
            Object o = a.get(i) + " \n" + a.get(++i) + "  \n " + a.get(++i);
            list.add(o.toString());
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}
