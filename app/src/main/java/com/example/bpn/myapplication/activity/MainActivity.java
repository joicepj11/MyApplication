package com.example.bpn.myapplication.activity;

import android.content.Intent;
<<<<<<< 2e28d04afad8e7d74327449e620b5c5288c863fa:app/src/main/java/com/example/bpn/myapplication/MainActivity.java
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
=======
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
>>>>>>> feat: created packages and formatted code:app/src/main/java/com/example/bpn/myapplication/activity/MainActivity.java
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bpn.myapplication.R;

public class MainActivity extends AppCompatActivity {

    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abc(View view) {
<<<<<<< 2e28d04afad8e7d74327449e620b5c5288c863fa:app/src/main/java/com/example/bpn/myapplication/MainActivity.java
        startActivity(new Intent(this,PreferenceExample.class));
    }

    public void startMainTwoActivity(View view) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sp.getString("url",null);
        if(!TextUtils.isEmpty(url)){
            if(url.equals("https://api.learn2crack.com/android/jsonandroid/")) {
                Intent i = new Intent(this, Main2Activity.class);
                i.putExtra("url", url);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"please enter  url to read ",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"please enter  url to read ",Toast.LENGTH_LONG).show();
        }
=======
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
        finish();
>>>>>>> feat: created packages and formatted code:app/src/main/java/com/example/bpn/myapplication/activity/MainActivity.java
    }
}
