package com.example.bpn.myapplication;

import android.test.ActivityInstrumentationTestCase2;
import android.util.JsonReader;
import android.util.Log;

import com.example.bpn.myapplication.activity.Main2Activity;
import com.example.bpn.myapplication.download.DownLoadAsynkTaskActivity;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by user on 25/9/17.
 */

public class DownloadAsynTaskTestCase extends ActivityInstrumentationTestCase2<Main2Activity> {

    JsonReader mReader = null;
    int mStausCode = 0;
    Exception mException = null;
    CountDownLatch signal;

    Main2Activity main2Activity;

    public DownloadAsynTaskTestCase() {
        super(com.example.bpn.myapplication.activity.Main2Activity.class);
    }

    @Override
    protected void setUp() throws Exception {

        main2Activity = getActivity();

        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }


    @Test
    public void testAsynkTaskActivity() {
        DownLoadAsynkTaskActivity taskActivity = new DownLoadAsynkTaskActivity();
        taskActivity.setCallback(new DownLoadAsynkTaskActivity.Callback() {


            @Override
            public void onComplete(JsonReader reader, int stausCode, Exception e) {
                mReader = reader;
                mStausCode = stausCode;
                mException = e;
                signal.countDown();
            }
        }).execute("https://api.learn2crack.com/android/jsonandroid/");
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(mReader);
        Log.d("mReader", mReader.toString());
        assertEquals(200, mStausCode);
        Log.d("statusCode", Integer.toString(mStausCode));
        assertNull(mException);
    }
}
