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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utilites {

    private static Utilites utilites = new Utilites();

    private Utilites() {

    }

    public static Utilites getInstance() {
        return utilites;
    }

    public static Boolean isNull(Object o) {
        return o == null;
    }

    public static Enum isValidNum(Number number) {

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

    private static boolean isNetworkAvailable(Context context) {
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
    public static URLVALIDATION isReachable(String url, Context context) {
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

    public static boolean checkBalancedParenthesis(String check)
    {
        Stack<Character> S = new Stack<>();

        for(int a = 0; a < check.length(); a++)
        {
            char braceType = check.charAt(a);
            if( braceType == '(')
                S.push(braceType);

            else if(  braceType == ')')
            {
                if(S.empty())
                    return false;
                switch(braceType)
                {
                    case ')':
                        if (S.pop() != '(')
                            return false;
                        break;

                    default:
                        break;
                }
            }
        }

        if(S.empty())
            return true;
        return false;

    }


    public static boolean isValidPhoneNumber(String phoneNumber) {
        if(phoneNumber.contains("(")){
            if (checkBalancedParenthesis(phoneNumber)) {
                Pattern pattern = Pattern.compile("([+])?([0-9]|[a-zA-z]|[ ]+|[-]|[()]){5,32}");
                Matcher matcher = pattern.matcher(phoneNumber);
                return matcher.matches();
                //return (matcher.matches() == true)?PHONE_NUMBER.VALID :PHONE_NUMBER.INVALID;
            }
            return false;
            //return PHONE_NUMBER.PARENTHESIS_UN_MATCH;
        }else{
            Pattern pattern = Pattern.compile("([+])?([0-9]|[a-zA-z]|[ ]+|[-]|[()]){5,32}");
            Matcher matcher = pattern.matcher(phoneNumber);
            return matcher.matches();
            //return (matcher.matches() == true)?PHONE_NUMBER.VALID :PHONE_NUMBER.INVALID;
        }

    }

    public static boolean isValidZipCode(String zipCode){
        //public static ZIP_CODE_NUMBER isValidZipCode(String zipCode){

        if(zipCode.length() == 0){
            return true;
            //return ZIP_CODE_NUMBER.EMPTY;
        }else{
            Pattern pattern = Pattern.compile("([0-9]|[a-zA-z]|[-]){3,10}");
            Matcher matcher = pattern.matcher(zipCode);
            return matcher.matches();
            //return (matcher.matches()==true)?ZIP_CODE_NUMBER.VALID :ZIP_CODE_NUMBER.INVALID;
        }
    }


    public static boolean isValidateDateFormat(final String date) {
        String[] formatStrings = {"DD/MM/yyyy","MM/dd/yyyy","yyyy/mm/dd"};
        boolean isValidFormat = true;
        Date dateObj;
        for (String formatString : formatStrings) {
            try {
                SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
                sdf.applyPattern(formatString);
                sdf.setLenient(false);
                dateObj = sdf.parse(date);
                System.out.println(dateObj);
                if (date.equals(sdf.format(dateObj))) {
                    isValidFormat = true;
                    break;
                }
            } catch (ParseException e) {
                isValidFormat = false;
            }
        }
        return isValidFormat;
    }


    public static boolean isValidTime24HoursFormat(final String time){
        String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
    public static boolean isValidTime12HoursFormat(final String time){
        String TIME12HOURS_PATTERN = "(([1-9])|([1][1])|([1][2])):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME12HOURS_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
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
