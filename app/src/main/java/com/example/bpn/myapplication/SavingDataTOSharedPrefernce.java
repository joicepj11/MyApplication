package com.example.bpn.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpn on 20/09/17.
 */

public class SavingDataTOSharedPrefernce {

    SharedPreferences  preference ;
    SharedPreferences.Editor editor;
    Context mContext ;

    String FIELD_NAME = "data";
    public SavingDataTOSharedPrefernce(Context ctx){
        mContext = ctx;
    }

     void storeDataInSharedPreference(ArrayList<BeanJsonData> data,Context mContext){

//
//
//         Gson gson = new Gson();
//         preference = mContext.getSharedPreferences("name",Context.MODE_PRIVATE);
//         editor = preference.edit();
//         Type listofdata = new TypeToken<ArrayList<BeanJsonData>>(){}.getType();
//         String json = gson.toJson(data,listofdata);
//
//         editor.putString(FIELD_NAME,json);
//         editor.commit();


         preference = mContext.getSharedPreferences("name", Context.MODE_PRIVATE);
         SharedPreferences.Editor prefrencesEditor = preference.edit();
         Gson gson = new Gson();
         String json = gson.toJson(data);
         prefrencesEditor.putString(FIELD_NAME, json);

         prefrencesEditor.commit();
     }
    public ArrayList<BeanJsonData> readDataFromSharedPreference(Context ctx){

       preference=mContext.getSharedPreferences("name",Context.MODE_PRIVATE);
//       String data = preference.getString(FIELD_NAME,null);
//        Gson gson = new Gson();
//        ArrayList<BeanJsonData> dataArrayList = gson.fromJson(data ,new TypeToken<ArrayList<BeanJsonData>>(){}.getType());
//        return  dataArrayList;

        Gson gson = new Gson();
        String json = preference.getString(FIELD_NAME, null);
        Type type = new TypeToken<ArrayList<BeanJsonData>>() {
        }.getType();
        ArrayList<BeanJsonData> arrayList = gson.fromJson(json, type);
        return arrayList;


    }


}
