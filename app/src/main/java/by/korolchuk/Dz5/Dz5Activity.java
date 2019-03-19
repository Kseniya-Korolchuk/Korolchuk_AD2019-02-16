package by.korolchuk.Dz5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import by.korolchuk.R;

public class Dz5Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView serviceTextView;
    private Dz5Service service;
    private BroadcastReceiver broadcastReceiver;
    private boolean bound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);

    serviceTextView = findViewById(R.id.serviceTextView);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Dz5Service.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

        IntentFilter intentFilter = new IntentFilter(Dz5Service.MY_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    boolean booleanExtra = intent.getBooleanExtra(Dz5Service.EXTRA_KEY, false);
                    serviceTextView.setText("WiFi state: " + booleanExtra);

                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        unbindService(serviceConnection);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        unbindService(serviceConnection);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((Dz5Service.MyBinder) iBinder).getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };


    @Override
    public void onClick(View v) {
        if(bound) {
            service.getState();
        }
    }


}
