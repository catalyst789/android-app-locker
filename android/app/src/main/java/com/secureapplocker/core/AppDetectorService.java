package com.secureapplocker.core;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

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
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent2.putExtra("aim", CONSTANTSKt.OPEN_APP_UNLOCK_SCREEN);
            startActivity(intent2);
        }
    }

    @Override
    public void onInterrupt() {
        // Handle interrupt
    }

    // Other methods
}


