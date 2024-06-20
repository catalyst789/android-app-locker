package com.secureapplocker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.secureapplocker.core.Holds
import com.secureapplocker.core.Holds.reactApplicationContext
import com.secureapplocker.core.Holds.whichAppToBeUnlocked
import com.secureapplocker.core.OPEN_APP_UNLOCK_SCREEN
import com.secureapplocker.core.REQ_CODE_DRAW_OVER_APPS_PERM_INTENT
import com.secureapplocker.core.permissionsHelper.PermissionManager

class MainActivity : ReactActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        if (intent.getStringExtra("aim").equals(OPEN_APP_UNLOCK_SCREEN)) {
            Holds.showLockScreenIntent = true
            Holds.whichAppToBeUnlocked = intent.getStringExtra("aimData").toString()
        } else {
            Holds.showLockScreenIntent = false
            Holds.whichAppToBeUnlocked = ""
        }
    }

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName(): String = "secureapplocker"

    /**
     * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
     * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
     */
    override fun createReactActivityDelegate(): ReactActivityDelegate =
            DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.getStringExtra("aim").equals(OPEN_APP_UNLOCK_SCREEN)) {
            Holds.showLockScreenIntent = true
            if (reactApplicationContext != null) {
                reactApplicationContext!!.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                        .emit(OPEN_APP_UNLOCK_SCREEN, whichAppToBeUnlocked)
            }
        } else {
            if (reactApplicationContext != null) {
                reactApplicationContext!!.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                        .emit(OPEN_APP_UNLOCK_SCREEN, null)
            }
            Holds.showLockScreenIntent = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_DRAW_OVER_APPS_PERM_INTENT) {
            Log.d("CUSTOM_LOGS", "draw over other apps result: $resultCode")
            val isDrawPermitted = PermissionManager.canDrawOverlays(this.applicationContext)
            Log.d("CUSTOM_LOGS", "isDrawPermitted: $isDrawPermitted")
        }
    }
}
