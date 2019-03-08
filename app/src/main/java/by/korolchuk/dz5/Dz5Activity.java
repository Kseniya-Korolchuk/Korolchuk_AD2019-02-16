package by.korolchuk.dz5;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import by.korolchuk.R;
import timber.log.Timber;

public class Dz5Activity extends AppCompatActivity {

    private static final String MY_ACTION = "by.korolchuk.dz5.MY_ACTION";

    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);


    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_ACTION);
        registerReceiver(wifiReceiver, intentFilter);


        Intent intent1 = new Intent(this, MyIntentService.class);
        intent1.putExtra(MyIntentService.TASK_EXTRA, "111111");
        startService(intent1);

        Intent intent2 = new Intent(this, MyIntentService.class);
        intent2.putExtra(MyIntentService.TASK_EXTRA, "222222");
        startService(intent2);

        Intent intent3 = new Intent(this, MyIntentService.class);
        intent3.putExtra(MyIntentService.TASK_EXTRA, "333333");
        startService(intent3);

        //bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent();
        intent.setAction(MY_ACTION);
        sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("loglog", "onStop()");
        unregisterReceiver(wifiReceiver);
        unbindService(serviceConnection);
    }


    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Timber.tag("log").e("wifiReceiver");
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
