package com.example.bpn.myapplication.activity;

/**
 * Created by user on 3/10/17.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Utilites {

    private static Utilites utilites = new Utilites();

    private Utilites() {

    }

    public static Utilites getInstance() {
        return utilites;
    }

    public Boolean isNull(Object o) {
        return o == null;
    }

    public Enum isValidNum(Number number) {

        if (number instanceof Integer) {

//            if ((int) number > Integer.MAX_VALUE || (int) number < Integer.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.intValue() > 0) {
                return RANGE.POSITIVE;
            } else if (number.intValue() < 0) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }

        } else if (number instanceof Double) {

//            if ((double) number > Double.MAX_VALUE || (double) number < Double.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.doubleValue() > 0d) {
                return RANGE.POSITIVE;
            } else if (number.doubleValue() < 0d) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }
        } else if (number instanceof Float) {

//            if ((float) number > Float.MAX_VALUE || (float) number < Float.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.floatValue() > 0f) {
                return RANGE.POSITIVE;
            } else if (number.floatValue() < 0f) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }
        } else if (number instanceof Long) {
//
//            if ((long) number > Long.MAX_VALUE || (long) number < Long.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.longValue() > 0) {
                return RANGE.POSITIVE;
            } else if (number.longValue() < 0) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }

        } else if (number instanceof Byte) {

//            if ((byte) number > Byte.MAX_VALUE || (byte) number < Byte.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.byteValue() > 0) {
                return RANGE.POSITIVE;
            } else if (number.byteValue() < 0) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }

        } else if (number instanceof Short) {

//            if ((short) number > Short.MAX_VALUE || (short) number < Float.MIN_VALUE) {
//                return RANGE.EXCEEDS_RANGE;
//            }

            if (number.shortValue() > 0) {
                return RANGE.POSITIVE;
            } else if (number.shortValue() < 0) {
                return RANGE.NEGATIVE;
            } else {
                return RANGE.ZERO;
            }
        }
        return null;
    }

    private boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    //
    public URLVALIDATION isReachable(String url, Context context) {
 String TAG = "isReachable";
        long lastTime;
        long startTime;
        startTime = System.currentTimeMillis();
//        if (isNetworkAvailable(context)) {

            try {
                URL url1 = new URL(url);
                URLConnection conn = url1.openConnection();
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(1000);
                conn.connect();
                lastTime = System.currentTimeMillis();
                Log.d(TAG, "isReachable: Reachable url "+(lastTime - startTime));
                return URLVALIDATION.REACHABLE;
            } catch (MalformedURLException e) {
                // the URL is not in a valid form
                lastTime = System.currentTimeMillis();
                Log.d(TAG, "isReachable: malformed url "+(lastTime - startTime));
                return URLVALIDATION.MALFORMED_URL;
            } catch (IOException e) {
                // the connection couldn't be established

                lastTime = System.currentTimeMillis();
                Log.d(TAG, "isReachable: unreachable "+(lastTime - startTime));
                return URLVALIDATION.UNREACHABLE;
            } catch (Exception e) {
                lastTime = System.currentTimeMillis();
                Log.d(TAG, "isReachable: "+(lastTime - startTime));
                return null;
            }
//        } else
//            lastTime = System.currentTimeMillis();
//        Log.d(TAG, "isReachable: "+(lastTime - startTime));
//            return URLVALIDATION.UNREACHABLE;
    }


    enum RANGE {
        POSITIVE,
        NEGATIVE,
        ZERO,
        EXCEEDS_RANGE
    }

    public enum URLVALIDATION {
        REACHABLE,
        MALFORMED_URL,
        UNREACHABLE
    }
}
