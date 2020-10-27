package com.psachdev.contentprovidersample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SampleActivity : AppCompatActivity() {
    private val LOGGERTAG: String = SampleActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDisplayAllEntries(view: View?) {
        Log.d(LOGGERTAG, "Display all entries.")
    }

    fun onClickDisplayFirstEntry(view: View?) {
        Log.d(LOGGERTAG, "Display first entry.")
        val querySingleMessageUri = SampleContentProvider.CONTENT_MESSAGE_WITH_ID_URI
        val projection = arrayOf(SampleContentProvider.MATCH_MESSAGE_BY_ID_PATH + "/1")
        val selectionArgs = arrayOf("1")
        val sortOrder = null
        val selectionClause = null
        val cursor =
            contentResolver.query(
                querySingleMessageUri, projection, selectionClause,
                selectionArgs, sortOrder
            )

        dataFromContentProvider.text = ""
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(projection[0])
                do {
                    val word = cursor.getString(columnIndex)
                    dataFromContentProvider.append(
                        """
                    $word
                    
                    """.trimIndent()
                    )
                } while (cursor.moveToNext())
            } else {
                Log.d(LOGGERTAG, "onClickDisplayEntries " + "No data returned.")
                dataFromContentProvider.append(
                    """
                No data returned.
                
                """.trimIndent()
                )
            }
            cursor.close()
        } else {
            Log.d(LOGGERTAG, "onClickDisplayEntries " + "Cursor is null.")
            dataFromContentProvider.append(
                """
            Cursor is null.
            """.trimIndent()
            )
        }
    }
}