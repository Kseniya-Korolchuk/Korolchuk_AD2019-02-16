package by.korolchuk.Dz5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import by.korolchuk.R;

import static by.korolchuk.Dz5.Dz5bService.CHECK_WIFI;
import static by.korolchuk.Dz5.Dz5bService.STATE;

@SuppressLint("LogNotTimber")

public class Dz5Activity extends Activity {

    private TextView textView;
    private Dz5bService wifiService;
    private ServiceConnection serviceConnection;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);
        textView = findViewById(R.id.checkWifiTextView);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("Dz5bService", "connected()");
                wifiService = ((Dz5bService.MyBinder) iBinder).getService();
                wifiService.checkWifiState();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }


    @Override
    protected void onResume() {
        Log.e("activity", "onResume()");
        super.onResume();
        Intent intent = new Intent(this, Dz5bService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        IntentFilter intentFilter = new IntentFilter(CHECK_WIFI);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        Log.e("activity", "onPause()");
        super.onPause();
        unbindService(serviceConnection);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("activity", "onDestroy()");
        unbindService(serviceConnection);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getBooleanExtra(STATE, false)) {
                    textView.setText(getString(R.string.wifi_is_on));
                } else {
                    textView.setText(getString(R.string.wifi_is_off));
                }
            }
        }
    };
}