package com.example.bpn.myapplication;

import com.example.bpn.myapplication.activity.PreferenceScreenActivity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by user on 25/9/17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({PreferenceScreenActivity.class, DownloadAsynkTaskTestCase.class, DatabaseTestCase.class})

public class TestCaseSuite {
}
