package com.github.provider.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.text.TextUtils
import com.github.provider.db.DBOpenHelper


/**
 * Created by swayangjit on 29/1/21.
 */
class TelemetryProvider : ContentProvider() {
    private var mContext: Context? = null
    private var mSqliteDatabase: SQLiteDatabase? = null

    companion object {
        const val AUTHORITY = "com.github.provider"
        const val TELEMETRY_URI_CODE = 0
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, "telemetry", TELEMETRY_URI_CODE)
        }
    }

    override fun onCreate(): Boolean {
        mContext = context
        mSqliteDatabase = DBOpenHelper(mContext).getWritableDatabase()
        mSqliteDatabase!!.execSQL("delete from " + DBOpenHelper.TABLE_TELEMETRY)
        mSqliteDatabase!!.execSQL("insert into telemetry values(3,'INTERACT', 'SAMPLE_EVENT',1,1);")
        mSqliteDatabase!!.execSQL("insert into telemetry values(4,'IMPRESSION', 'SAMPLE_EVENT_1',1,2);")
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<String?>?,
        selection: String?,
        selectionArgs: Array<String?>?,
        sortOrder: String?
    ): Cursor? {
        val table = getTableName(uri)
        require(!TextUtils.isEmpty(table)) { "Unsupported URI: $uri" }
        return mSqliteDatabase!!.query(
            table,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val table = getTableName(uri)
        require(!TextUtils.isEmpty(table)) { "Unsupported URI: $uri" }
        mSqliteDatabase!!.insert(table, null, contentValues)
        mContext!!.contentResolver.notifyChange(uri, null)
        return null
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String?>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        s: String?,
        strings: Array<String?>?
    ): Int {
        return 0
    }

    private fun getTableName(uri: Uri): String? {
        var tableName: String? = null
        when (sUriMatcher.match(uri)) {
            TELEMETRY_URI_CODE -> tableName = DBOpenHelper.TABLE_TELEMETRY
            else -> {
            }
        }
        return tableName
    }
}