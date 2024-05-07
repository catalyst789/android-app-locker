package com.secureapplocker.nativemodules

import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.google.gson.Gson
import com.secureapplocker.MainActivity
import com.secureapplocker.core.AppListFetcher
import com.secureapplocker.core.REQ_CODE_DRAW_OVER_APPS_PERM_INTENT
import com.secureapplocker.core.REQ_CODE_USAGE_PERM_INTENT
import com.secureapplocker.core.permissionsHelper.PermissionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class HelperModule(rContext: ReactApplicationContext): ReactContextBaseJavaModule(rContext){
    override fun getName(): String {
        return "HelperModule";
    }

    @ReactMethod
    fun test(promise: Promise) {
        promise.resolve("This is Helper Native Module")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @ReactMethod
    fun getAllInstalledAppListOld(promise: Promise){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appsInformation = AppListFetcher().getAllInstalledApps(reactApplicationContext.applicationContext)
                promise.resolve(appsInformation)
            }catch (e: Exception){
                promise.reject("Exception: ", e)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @ReactMethod
    fun getAllInstalledAppList(promise: Promise){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appsInformation = AppListFetcher().getAllInstalledApps(reactApplicationContext.applicationContext).join()
                promise.resolve(appsInformation)
            }catch (e: Exception){
                promise.reject("Exception: ", e)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @ReactMethod
    fun getAppIconBase64(packageName: String, promise: Promise){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appIconBase64 = AppListFetcher().getLogoBase64(reactApplicationContext.applicationContext, packageName)
                promise.resolve(appIconBase64)
            }catch (e: Exception){
                promise.reject("Exception: ", e)
            }
        }
    }

    @ReactMethod
    fun handleDrawOverOtherAppsPermission(){
        if(!PermissionManager.canDrawOverlays(this.reactApplicationContext)){
            this.reactApplicationContext.currentActivity?.let {
                PermissionManager.requestOverlayPermission(
                    it, REQ_CODE_DRAW_OVER_APPS_PERM_INTENT)
            }
        }
    }

    @ReactMethod
    fun handleUsageAccessPermission(){
        if(!PermissionManager.hasUsageAccessPermission(this.reactApplicationContext)){
            this.currentActivity?.let {
                PermissionManager.requestUsageAccessPermission(
                    it,
                    REQ_CODE_USAGE_PERM_INTENT)
            }
        }
    }
}