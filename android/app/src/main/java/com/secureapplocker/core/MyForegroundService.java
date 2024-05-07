package com.secureapplocker.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyForegroundService extends Service {

    private BroadcastReceiver appOpenedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(AppDetectorService.ACTION_APP_OPENED)) {
                // Handle the broadcast indicating that an app has been opened
                String packageName = intent.getStringExtra("package_name");
                Log.d("AppDetector", "Opened package: " + packageName);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // Register the broadcast receiver
        IntentFilter filter = new IntentFilter(AppDetectorService.ACTION_APP_OPENED);
        registerReceiver(appOpenedReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the broadcast receiver
        unregisterReceiver(appOpenedReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Other methods
}


