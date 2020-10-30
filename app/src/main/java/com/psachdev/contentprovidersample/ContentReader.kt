package com.psachdev.contentprovidersample

interface ContentReader {
    fun onContentReadSuccessfully(word: String)
    fun failedToReadContent(error: String)
}