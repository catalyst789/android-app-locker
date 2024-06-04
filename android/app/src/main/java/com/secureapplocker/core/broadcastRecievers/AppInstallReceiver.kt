package com.secureapplocker.core.broadcastRecievers

import android.app.usage.UsageStatsManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AppInstallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_PACKAGE_ADDED || intent.action == Intent.ACTION_PACKAGE_REPLACED) {
            val packageName = intent.data?.schemeSpecificPart
            if (isAppLaunching(context, packageName)) {
                Log.d("MYLOGS: ", "$packageName is being launched..")
                // App is being launched
                // You can perform your checks here
            }
        }
    }

    private fun isAppLaunching(context: Context, packageName: String?): Boolean {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val currentTime = System.currentTimeMillis()
        val stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 1000 * 10, currentTime)
        stats?.let { usageStats ->
            for (us in usageStats) {
                if (us.packageName == packageName && us.lastTimeUsed >= currentTime - 1000 * 10) {
                    return true
                }
            }
        }
        return false
    }
}
