package by.korolchuk.Dz5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import timber.log.Timber;

public class Dz5Service extends Service {

    public static final String MY_ACTION = "by.korolchuk.dz5.MY_ACTION";
    public static final String EXTRA_KEY = "EXTRA_KEY";
    private static final String dz5Service = "SERVICE ";
    private MyBinder myBinder = new MyBinder();

    public Dz5Service() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(dz5Service, "onCreate");
    }


    class MyBinder extends Binder {

        Dz5Service getService() {
            return Dz5Service.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(dz5Service, "onBind");
        bindWiFiMessage();
        return myBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        unbindWiFiMessage();
    }

    public void bindWiFiMessage() {
        Log.e(dz5Service, "bindWiFiMessage()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unbindWiFiMessage() {
        Log.e(dz5Service, "unbindWiFiMessage()");

        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
                if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
                    sendState(true);
                } else {
                    sendState(false);
                }
            }
        }
    };


    public void getState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isConnected = networkInfo.isConnected();
        sendState(isConnected);
    }

    public void sendState(Boolean state) {
        Intent intMes = new Intent(MY_ACTION);
        intMes.putExtra(EXTRA_KEY, state);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intMes);
    }
}
