package com.psachdev.contentprovidersample

import android.content.ContentResolver
import android.content.ContentUris
import android.os.AsyncTask

class SampleContentReaderAsyncTask(private val contentReader: ContentReader,
                                   private val contentResolver: ContentResolver) : AsyncTask<String, Boolean, String>() {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun doInBackground(vararg params: String?): String? {
        val querySingleMessageUriBase = SampleContentProvider.CONTENT_MESSAGE_WITH_ID_URI
        val querySingleMessageUri = ContentUris.withAppendedId(querySingleMessageUriBase, 1)

        val projection = arrayOf(SampleContentProvider.MATCH_MESSAGE_BY_ID_PATH + "/1")
        val selectionArgs = arrayOf("1")
        val sortOrder = null
        val selectionClause = null
        val cursor =
            contentResolver.query(
                querySingleMessageUri, projection, selectionClause,
                selectionArgs, sortOrder
            )
        val word: String?
        if (cursor != null) {
            word = if (cursor.count > 0) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(projection[0])
                //do {
                    cursor.getString(columnIndex)
                //} while (cursor.moveToNext())
            } else {
                null
            }
            cursor.close()
        } else {
            word = null
        }
        return word
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(result == null){
            contentReader.failedToReadContent("No data returned.")
        }else{
            contentReader.onContentReadSuccessfully(result)
        }
    }

    override fun onCancelled(result: String?) {
        super.onCancelled(result)
    }

    override fun onCancelled() {
        super.onCancelled()
    }
}