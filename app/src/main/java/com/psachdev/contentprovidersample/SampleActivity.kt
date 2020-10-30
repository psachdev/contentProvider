package com.psachdev.contentprovidersample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SampleActivity : AppCompatActivity(), ContentReader {
    private val LOGGERTAG: String = SampleActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDisplayAllEntries(view: View?) {
        Log.d(LOGGERTAG, "Display all entries.")
    }

    override fun onContentReadSuccessfully(word: String) {
        dataFromContentProvider.append(
            """
                $word
                """.trimIndent()
        )
    }

    override fun failedToReadContent(error: String) {
        Log.d(LOGGERTAG, error)
        dataFromContentProvider.append(
            """
                $error
                """.trimIndent()
        )
    }

    fun onClickDisplayFirstEntry(view: View?) {
        Log.d(LOGGERTAG, "Display first entry.")
        dataFromContentProvider.text = ""
        SampleContentReaderAsyncTask(this, contentResolver).execute()
    }
}