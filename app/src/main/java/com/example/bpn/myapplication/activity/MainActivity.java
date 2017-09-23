package com.example.bpn.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bpn.myapplication.R;

public class MainActivity extends AppCompatActivity {

    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (Button) findViewById(R.id.button2);

    }

    public void abc(View view) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
        finish();
    }
}
