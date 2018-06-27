package com.test.listfriendsapplication;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;


import com.test.listfriendsapplication.internet.NetworkStateChangeReceiver;

import io.realm.Realm;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class App extends Application {


    private static Context context;
    private static final String WIFI_STATE_CHANGE_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        // Realm
        Realm.init(context);
        initReceiver(context);

    }


    public static Context getContext() {
        return context;
    }


    @Override
    public void onTerminate() {
        //  HelperFactory.releaseHelper();
        super.onTerminate();
    }


    private static void initReceiver(final Context context) {
        NetworkStateChangeReceiver networkStateChangeReceiver = new NetworkStateChangeReceiver();
        context.registerReceiver(networkStateChangeReceiver, new IntentFilter(CONNECTIVITY_ACTION));
        context.registerReceiver(networkStateChangeReceiver, new IntentFilter(WIFI_STATE_CHANGE_ACTION));
    }

}
