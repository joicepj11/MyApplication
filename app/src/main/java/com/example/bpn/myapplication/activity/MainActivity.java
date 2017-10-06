package com.example.bpn.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.bpn.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private static String PATH = "url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void abc(View view) {
        startActivity(new Intent(this, PreferenceExample.class));
    }

    public void startMainTwoActivity(View view) {
        String url;
        Utilites utilites = Utilites.getInstance();
        utilites.isReachable("https://api.learn2crack.com/android/jsonandroid/",getApplicationContext());
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("url", "https://api.learn2crack.com/android/jsonandroid/");
        editor.apply();
        editor.commit();
        url = sp.getString(PATH, null);
        if (!TextUtils.isEmpty(url)) {
            if (url.equals("https://api.learn2crack.com/android/jsonandroid/")) {
                Intent i = new Intent(this, Main2Activity.class);
                i.putExtra("url", url);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "please enter  url: https://api.learn2crack.com/android/jsonandroid/", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "please enter  url to read ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
