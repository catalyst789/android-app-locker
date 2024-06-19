package com.secureapplocker.core

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.facebook.react.bridge.ReactApplicationContext

class DBHelper(private val tableName: String, context: ReactApplicationContext) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create your tables here
        db.execSQL("CREATE TABLE IF NOT EXISTS $tableName (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "secured-app-locker.db"
        private const val DATABASE_VERSION = 1
    }

}
