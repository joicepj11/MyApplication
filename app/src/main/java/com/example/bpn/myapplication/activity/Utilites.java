package com.example.bpn.myapplication.activity;

/**
 * Created by user on 3/10/17.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Utilites {

    private static Utilites utilites;

    private Utilites() {
        if (utilites == null) {
            utilites = new Utilites();
        }
    }

    public static Utilites getInstance() {

        if (utilites == null) {
            synchronized (Utilites.class) {
                if (utilites == null) {
                    utilites = new Utilites();
                }
            }
        }
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
        ConnectivityManager connectivityManager

                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    //
    public URLVALIDATION isReachable(String url, Context context) {

        if (isNetworkAvailable(context)) {
            try {
                URL url1 = new URL(url);
                URLConnection conn = url1.openConnection();
                conn.setConnectTimeout(3000);
                conn.connect();
                return URLVALIDATION.REACHABLE;
            } catch (MalformedURLException e) {
                // the URL is not in a valid form
                return URLVALIDATION.MALFORMED_URL;
            } catch (IOException e) {
                // the connection couldn't be established
                return URLVALIDATION.UNREACHABLE;
            }
        } else {
            return URLVALIDATION.UNREACHABLE;
        }
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
