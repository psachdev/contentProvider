package com.psachdev.contentprovidersample

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import java.lang.Integer.parseInt

class SampleContentProvider : ContentProvider() {
    private var mData: Array<String>? = null
    private val MATCH_ALL_MESSAGES = 1
    private val MATCH_MESSAGE_BY_ID = 2

    private val MATCH_ALL_MESSAGES_PATH = "message"
    private val MATCH_MESSAGE_BY_ID_PATH = "all_messages"

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    val AUTHORITY = BuildConfig.APPLICATION_ID + ".SampleContentProvider"
    var CONTENT_ALL_MESSAGES_URI =
        Uri.parse("content://$AUTHORITY/$MATCH_MESSAGE_BY_ID_PATH")
    var CONTENT_MESSAGE_WITH_ID_URI =
        Uri.parse("content://$AUTHORITY/$MATCH_ALL_MESSAGES_PATH")

    override fun onCreate(): Boolean {
        uriMatcher.addURI(
            AUTHORITY,
            "message/#",
            MATCH_MESSAGE_BY_ID
        )
        uriMatcher.addURI(
            AUTHORITY,
            "all_messages",
            MATCH_ALL_MESSAGES
        )
        mData = context?.resources?.getStringArray(R.array.messages) as Array<String>
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            MATCH_ALL_MESSAGES -> {
                return getCursor()
            }
            MATCH_MESSAGE_BY_ID -> {
                val id = parseInt(uri.lastPathSegment!!)
                return getCursor(id)
            }
            else->{
                return null
            }
        }
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            MATCH_MESSAGE_BY_ID,
            MATCH_ALL_MESSAGES ->
                return "vnd.android.cursor.item/com.psachdev.contentprovidersample.message"
            else->
                throw IllegalArgumentException("Unknown URI $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    private fun getCursor(id: Int): Cursor {
        val cursor = MatrixCursor(arrayOf("$MATCH_ALL_MESSAGES_PATH/$id"))

        for (element in mData!!) {
            if(element.contains(id.toString())) {
                cursor.addRow(arrayOf<Any>(element))
            }
        }

        return cursor
    }

    private fun getCursor(): Cursor {
        val cursor = MatrixCursor(arrayOf("$MATCH_MESSAGE_BY_ID_PATH"))

        for (element in mData!!) {
            cursor.addRow(arrayOf<Any>(element))
        }

        return cursor
    }
}