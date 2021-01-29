package com.github.provider.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by swayangjit on 29/1/21.
 */
class DBOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val CREATE_BOOK_TABLE = ("CREATE TABLE IF NOT EXISTS "
            + TABLE_TELEMETRY + "(_id INTEGER PRIMARY KEY, event_type TEXT, event TEXT, timestamp INTEGER, priority INTEGER)")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        const val TABLE_TELEMETRY = "telemetry"
        private const val DB_NAME = "sunbird_provider.db"
        private const val DB_VERSION = 1
    }
}