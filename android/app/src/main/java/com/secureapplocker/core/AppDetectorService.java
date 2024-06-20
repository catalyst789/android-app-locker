package com.secureapplocker.core;

import static com.secureapplocker.core.CONSTANTSKt.LOCKED_APPS_TABLES;
import static com.secureapplocker.core.CONSTANTSKt.OPEN_APP_UNLOCK_SCREEN;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.secureapplocker.MainActivity;
import com.secureapplocker.TransparentActivity;

public class AppDetectorService extends AccessibilityService {

    public static final String ACTION_APP_OPENED = "com.yourapp.ACTION_APP_OPENED";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("AppDetector", event.getPackageName().toString());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // Send a broadcast indicating that an app has been opened
//            Intent intent = new Intent(ACTION_APP_OPENED);
//            intent.putExtra("package_name", event.getPackageName());
//            sendBroadcast(intent);
            // Interrupt app opening
            Holds.INSTANCE.setShowLockScreenIntent(false);
            if (!event.getPackageName().equals("com.secureapplocker")) {
                DBHelper dbHelper = new DBHelper(LOCKED_APPS_TABLES, getApplicationContext());
                Cursor dataCursor = dbHelper.getData(event.getPackageName().toString());
                if (dataCursor.moveToNext()) {
                    Holds.INSTANCE.setShowLockScreenIntent(true);
                    Holds.INSTANCE.setWhichAppToBeUnlocked(event.getPackageName().toString());
                    if (Holds.INSTANCE.getReactApplicationContext() != null) {
                        Holds.INSTANCE.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                                .emit(OPEN_APP_UNLOCK_SCREEN, Holds.INSTANCE.getWhichAppToBeUnlocked());
                    }
                    Intent intent2 = new Intent(this, MainActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtra("aim", OPEN_APP_UNLOCK_SCREEN);
                    intent2.putExtra("aimData", event.getPackageName());
                    startActivity(intent2);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        // Handle interrupt
    }

    // Other methods
}


