package by.korolchuk.Dz5;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

@SuppressLint("LogNotTimber")

public class Dz5bService extends Service {

    public static final String STATE = "STATE";
    public static final String CHECK_WIFI = "CHECK_WIFI";
    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.e("Dz5bService", "onCreate()");
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Dz5bService", "onBind()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(myBroadcastReceiver, intentFilter);
        return myBinder;
    }


    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        Log.e("Dz5bService", "unBind()");
        unregisterReceiver(myBroadcastReceiver);
        super.unbindService(serviceConnection);
    }

    public void checkWifiState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        sendState(wifi.isConnected());
    }

    public void sendState(boolean state) {
        Intent intent = new Intent(CHECK_WIFI);
        intent.putExtra(STATE, state);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    class MyBinder extends Binder {
        Dz5bService getService() {
            return Dz5bService.this;
        }
    }

}