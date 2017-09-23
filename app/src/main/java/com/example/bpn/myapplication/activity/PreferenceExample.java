package com.example.bpn.myapplication.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.bpn.myapplication.R;

/**
 * Created by admin on 9/22/2017.
 */

public class PreferenceExample extends PreferenceActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new PrefernceExampleFragement()).commit();

    }
    public static class PrefernceExampleFragement extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_for_url);
        }
    }
}
