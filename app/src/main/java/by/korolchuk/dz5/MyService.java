package by.korolchuk.dz5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

       // NotificationManagerCompat

        Log.e("loglog", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("loglog", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("loglog", "onBind()");
        return null;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("loglog", "onUnbind()");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("loglog", "onDestroy()");
    }


}
