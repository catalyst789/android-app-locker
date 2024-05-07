package com.secureapplocker.core

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.util.Base64
import java.util.concurrent.CompletableFuture

class AppListFetcher {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllInstalledAppsOld(context: Context): ReadableArray {
        val installedApps = mutableListOf<ReadableMap>()
        val packageManager: PackageManager = context.packageManager

        // Get a list of installed packages
        val packages: List<PackageInfo> = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {
            val currAppInfo = Arguments.createMap()
            // Check if the application is not a system app
            val appName: String = packageInfo.applicationInfo.loadLabel(packageManager).toString()
            if (appName.startsWith("com.") or appName.contains("android.")) continue
            val packageName: String = packageInfo.applicationInfo.packageName
            // Check if the application is launchable
            if (
                packageManager.getLaunchIntentForPackage(packageName) != null
                ) {
                currAppInfo.putString("appName", appName)
                currAppInfo.putString("packageName", packageName)
                currAppInfo.putString("appIconBase64", getLogoBase64(context, packageName))
                installedApps.add(currAppInfo)
            }
//            val appIconBase64 = getLogoBase64(context,packageName)
//            currAppInfo.putString("appName", appName)
//            currAppInfo.putString("packageName", packageName)
//            currAppInfo.putString("appIconBase64", appIconBase64)
//            installedApps.add(currAppInfo)
        }

        // Sort the list alphabetically
        installedApps.sortBy { it.getString("appName") }
        val installedAppArray = Arguments.createArray();
        installedApps.forEach {installedAppArray.pushMap(it)}
        return installedAppArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllInstalledApps(context: Context): CompletableFuture<ReadableArray> {
        val future = CompletableFuture<ReadableArray>()
        GlobalScope.launch(Dispatchers.IO) {
            val installedApps = mutableListOf<ReadableMap>()
            val packageManager: PackageManager = context.packageManager

            // Get a list of installed packages
            val packages: List<PackageInfo> = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)

            val deferredList = packages.map { packageInfo ->
                async(Dispatchers.IO) {
                    val currAppInfo = Arguments.createMap()
                    // Check if the application is not a system app
                    val appName: String = packageInfo.applicationInfo.loadLabel(packageManager).toString()
                    if (appName.startsWith("com.") or appName.contains("android.")) return@async null
                    val packageName: String = packageInfo.applicationInfo.packageName
                    // Check if the application is launchable
                    if (packageManager.getLaunchIntentForPackage(packageName) != null) {
                        currAppInfo.putString("appName", appName)
                        currAppInfo.putString("packageName", packageName)
                        currAppInfo.putString("appIconBase64", getLogoBase64(context, packageName))
                        return@async currAppInfo
                    }
                    return@async null
                }
            }

            deferredList.awaitAll().forEach {
                if (it != null) {
                    installedApps.add(it)
                }
            }

            // Sort the list alphabetically
            installedApps.sortBy { it.getString("appName") }
            val installedAppArray = Arguments.createArray()
            installedApps.forEach { installedAppArray.pushMap(it) }
            withContext(Dispatchers.Main) {
                future.complete(installedAppArray)
            }
        }
        return future
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getLogoBase64(context: Context, packageName: String): String? {
        val packageManager: PackageManager = context.packageManager

        try {
            // Retrieve the application info
            val appInfo: ApplicationInfo = packageManager.getApplicationInfo(packageName, 0)

            // Retrieve the app icon as a drawable
            val appIconDrawable = packageManager.getApplicationIcon(appInfo)

            // Convert the drawable to a Bitmap
            val bitmap = drawableToBitmap(appIconDrawable)

            // Convert the Bitmap to a Base64 string
            val base64String = bitmapToBase64(bitmap)

            return base64String
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    // Function to convert a drawable to a Bitmap
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    // Function to convert a Bitmap to a Base64 string
    @RequiresApi(Build.VERSION_CODES.O)
    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return "data:image/*;base64," + Base64.getEncoder().encodeToString(byteArray)
    }

}


