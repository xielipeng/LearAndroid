package org.xielipeng.appwidget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 周期性更新AppWidget的服务
 *
 * @author xielipeng 2016-08-31
 */
public class MyWidgetService extends Service {
    private static final String TAG = "MyWidgetService";

    // 更新 widget 的广播对应的action
    private final String ACTION_UPDATE_ALL = "org.xlp.widget.UPDATE_ALL";
    // 周期性更新 widget 的周期
    private static final int UPDATE_TIME = 5000;
    // 周期性更新 widget 的线程
    private UpdateThread mUpdateThread;
    private Context mContext;
    // 更新周期的计数
    private int count = 0;

    public MyWidgetService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 创建并开启线程UpdateThread
        mUpdateThread = new UpdateThread();
        mUpdateThread.start();

        mContext = this.getApplicationContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 中断线程，即结束线程。
        if (mUpdateThread != null) {
            mUpdateThread.interrupt();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    private class UpdateThread extends Thread {

        @Override
        public void run() {
            super.run();

            try {
                count = 0;
                while (true) {
                    Log.d(TAG, "run ... count:" + count);
                    count++;

                    Intent updateIntent = new Intent(ACTION_UPDATE_ALL);
                    mContext.sendBroadcast(updateIntent);

                    Thread.sleep(UPDATE_TIME);
                }
            } catch (InterruptedException e) {
                // 将 InterruptedException 定义在while循环之外，意味着抛出 InterruptedException 异常时，终止线程。
                e.printStackTrace();
            }
        }
    }
}
