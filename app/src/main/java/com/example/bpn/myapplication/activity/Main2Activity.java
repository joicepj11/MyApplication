package com.example.bpn.myapplication.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bpn.myapplication.PassData;
import com.example.bpn.myapplication.R;
import com.example.bpn.myapplication.data.BeanJsonData;
import com.example.bpn.myapplication.download.DownloadService;
import com.example.bpn.myapplication.fragment.FragementOne;
import com.example.bpn.myapplication.fragment.FragementTwo;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;

import java.util.List;

public class Main2Activity extends AppCompatActivity  implements ProviderInstaller.ProviderInstallListener{

    private static final int ERROR_DIALOG_REQUEST_CODE = 1;

    private boolean mRetryProviderInstall;

    ArrayAdapter<String> arrayAdapter;
    String data[] = null;
    ListView mListView;
    List list;
    PassData callback;
    PassData callback1;

    FragementOne mfragementOne;
    FragementTwo mfrFragementTwo;
    private BeanJsonData beadJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        ProviderInstaller.installIfNeededAsync(this, this);


        mfragementOne = new FragementOne();
        mfrFragementTwo = new FragementTwo();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frameLayout1, mfragementOne);
        transaction.add(R.id.frameLayout2, mfrFragementTwo);
        transaction.commit();


//        mListView = (ListView) findViewById(R.id.listView);
        // list = new ArrayList<>();

//        DownloaderClass downloaderClass = new DownloaderClass(mfrFragementTwo ,mfragementOne, getApplicationContext());
//        downloaderClass.execute();

        Utilites utilites = Utilites.getInstance();
        utilites.isReachable("https://api.learn2crack.com/android/jsonandroid/",getApplicationContext());

        Intent intent = getIntent();
        Intent i = new Intent(this, DownloadService.class);
        i.putExtra("url", intent.getExtras().get("url").toString());
        startService(i);

//       callback.receiveDataFromDownloadClass();
        //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(mIntent1);

        //  LocalBroadcastManager.getInstance(this).registerReceiver(mMessageBroadcastReceiver, new IntentFilter("arrayListPassingBroadcastSender"));
//        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view_layout, R.id.text, list);
//        mListView.setAdapter(arrayAdapter);
    }
    @Override
    public void onProviderInstallFailed(int errorCode, Intent recoveryIntent) {
        if (GooglePlayServicesUtil.isUserRecoverableError(errorCode)) {
            // Recoverable error. Show a dialog prompting the user to
            // install/update/enable Google Play services.
            GooglePlayServicesUtil.showErrorDialogFragment(
                    errorCode,
                    this,
                    ERROR_DIALOG_REQUEST_CODE,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            // The user chose not to take the recovery action
                            onProviderInstallerNotAvailable();
                        }
                    });
        } else {
            // Google Play services is not available.
            onProviderInstallerNotAvailable();
        }
    }
    private void onProviderInstallerNotAvailable() {
        // This is reached if the provider cannot be updated for some reason.
        // App should consider all HTTP communication to be vulnerable, and take
        // appropriate action.
    }


    @Override
    public void onProviderInstalled() {
        // Provider is up-to-date, app can make secure network calls.
    }


    //private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {

//        @Override
//        public void onReceive(Context context, Intent intent) {
//           ArrayList<String> data = intent.getStringArrayListExtra("ParsedData");
//        }
//    };


}