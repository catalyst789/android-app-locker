package com.secureapplocker.core

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.facebook.react.bridge.ReactApplicationContext

class DBHelper(private val tableName: String, context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "secured-app-locker.db"
        private const val DATABASE_VERSION = 1
        private const val COLUMN_ID = "_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create your tables here
        db.execSQL("CREATE TABLE $tableName ($COLUMN_ID INTEGER PRIMARY KEY, \"name\" TEXT, \"value\" TEXT, \"packageName\" TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    fun insertData(name: String, packageName: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("packageName", packageName)
        return db.insert(tableName, null, contentValues)
    }

    fun deleteData(packageName: String): Int {
        val db = this.writableDatabase
        return db.delete(tableName, "packageName = ?", arrayOf(packageName))
    }

    fun getData(packageName: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $tableName WHERE packageName = ?", arrayOf(packageName))
    }

}
