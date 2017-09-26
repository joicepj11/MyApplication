package com.example.bpn.myapplication;

import android.test.ActivityInstrumentationTestCase2;
import android.util.JsonReader;

import com.example.bpn.myapplication.activity.Main2Activity;
import com.example.bpn.myapplication.data.SqlDatabase;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by user on 25/9/17.
 */

public class DatabaseTestCase extends ActivityInstrumentationTestCase2<Main2Activity> {

    JsonReader mReader = null;
    int mStausCode = 0;
    Exception mException = null;
    CountDownLatch signal;
    SqlDatabase sqLiteDatabase;
    Main2Activity main2Activity;

    public DatabaseTestCase() {
        super(com.example.bpn.myapplication.activity.Main2Activity.class);
    }

    @Override
    protected void setUp() throws Exception {

        main2Activity = getActivity();
        sqLiteDatabase = new SqlDatabase(getActivity().getApplicationContext());
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    @Test
    public void testInsertData() {
        assertNotNull(sqLiteDatabase.insertData("abc", "1", "1"));
    }


    @Test
    public void testCheckDatabaseName() {
        assertEquals("mydb.db", sqLiteDatabase.getDatabaseName());
    }

}
