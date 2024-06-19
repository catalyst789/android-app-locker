package com.secureapplocker.nativemodules

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.secureapplocker.core.DBHelper
import com.secureapplocker.core.LOCKED_APPS_TABLES

class DBModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "DBModule"
    }

    @ReactMethod
    fun lockApp(appName:String, packageName: String, promise: Promise){
        try {
            val dbHelper = DBHelper(LOCKED_APPS_TABLES, reactApplicationContext)
            dbHelper.insertData(appName, packageName)
            promise.resolve("success")
        }catch (ex: Exception){
            promise.reject(ex)
        }
    }

    @ReactMethod
    fun unlockApp(packageName: String, promise: Promise){
        try {
            val dbHelper = DBHelper(LOCKED_APPS_TABLES, reactApplicationContext)
            dbHelper.deleteData(packageName)
            promise.resolve("success")
        }catch (ex: Exception){
            promise.reject(ex)
        }
    }

    @ReactMethod
    fun getApp(packageName: String, promise: Promise){
        try {
            val dbHelper = DBHelper(LOCKED_APPS_TABLES, reactApplicationContext)
            val data = dbHelper.getData(packageName)
            val result = mutableListOf<Map<String, String>>()
            if (data.moveToFirst()) {
                do {
                    val row = mutableMapOf<String, String>()
                    for (i in 0 until data.columnCount) {
                        row[data.getColumnName(i)] = data.getString(i)
                    }
                    result.add(row)
                } while (data.moveToNext())
            }
            data.close()
            promise.resolve(result)
        }catch (ex: Exception){
            promise.reject(ex)
        }
    }

}