package com.example.bpn.myapplication.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bpn.myapplication.PassData;
import com.example.bpn.myapplication.R;
import com.example.bpn.myapplication.activity.Main2Activity;
import com.example.bpn.myapplication.data.BeanJsonData;
import com.example.bpn.myapplication.data.SavingDataTOSharedPrefernce;
import com.example.bpn.myapplication.data.SqlDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpn on 20/09/17.
 */

public class FragementTwo extends Fragment implements PassData {


    List list;
    ListView mListView;
    ArrayAdapter arrayAdapter;
    View view;
    Main2Activity main2Activity;
    BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

//            ArrayList<BeanJsonData> data = intent.getParcelableArrayListExtra("data");
//            for (BeanJsonData data1 : data) {
//            list.add(data1.getName() + "\n" + data1.getApi() + "\n" + data1.getVersion());
//           }
            SqlDatabase database = new SqlDatabase(context);
            ArrayList<BeanJsonData> data = new ArrayList<>();
            data = database.read();
            for (BeanJsonData data1 : data) {
                list.add(data1.getName() + "\n" + data1.getApi() + "\n" + data1.getVersion());
            }
            database.closeDB();
            //comment
            arrayAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragement_layout_two, container, false);

        mListView = view.findViewById(R.id.list_item2);
        list = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_view_layout, R.id.text, list);
        mListView.setAdapter(arrayAdapter);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mBroadCastReceiver, new IntentFilter("passDataToFragement1"));
        return view;
    }


    //    @Override
//    public void receiveDataFromDownloadClass(ArrayList a ) {
//
//        for (int i = 0; i < a.size(); i++) {
//            Object o = a.get(i) + " \n" + a.get(++i) + "  \n " + a.get(++i);
//            list.add(o.toString());
//        }
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                arrayAdapter.notifyDataSetChanged();
//            }
//        });
//
//    }
//
//
    @Override
    public void receiveDataFromDownloadClass() {


        SavingDataTOSharedPrefernce s = new SavingDataTOSharedPrefernce(getContext());
        ArrayList<BeanJsonData> beanJsonData = s.readDataFromSharedPreference(getContext());


        for (int i = 0; i < beanJsonData.size(); i++) {
            BeanJsonData data = beanJsonData.get(i);

            list.add(data.getName() + "\n" + data.getVersion() + "  \n" + data.getApi());

        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}
