package by.korolchuk.dz5;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String TASK_EXTRA = "TASK_EXTRA";

    public MyIntentService() {
        super("MyIntentService");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        assert intent != null;
        String task = intent.getStringExtra(TASK_EXTRA);

        try{
            Thread.sleep(50000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("loglog", "onHandleIntent()" + task);
    }
}
