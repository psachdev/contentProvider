package com.psachdev.contentprovidersample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

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
    }
}