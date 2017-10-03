package com.example.bpn.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.URISyntaxException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by user on 25/9/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NetworkUrlTest {
    @SuppressWarnings("unchecked")
    @Rule
    public ActivityTestRule MainActivity = new ActivityTestRule(com.example.bpn.myapplication.activity.MainActivity.class);

    @Test
    public void checkUrlTest() {

        onView(withId(R.id.button3)).perform(click());


    }

    @Test
    public void testSharedPreference() {
        // scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
        Exception exception = null;
        URI uri = null;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.getActivity().getApplicationContext());
        String url = sp.getString("url", null);
        assertNull(url);

        try {
            uri = new URI(url);


        } catch (URISyntaxException e) {
            exception = e;
        }
        assertNull(exception);
        assertEquals("https://api.learn2crack.com/android/jsonandroid/", url);
        onView(withId(R.id.button2)).perform(click());
    }

}
