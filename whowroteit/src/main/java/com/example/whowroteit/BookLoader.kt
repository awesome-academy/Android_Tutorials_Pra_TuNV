package com.example.whowroteit

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, queryString: String) : AsyncTaskLoader<String>(context) {

    private val queryString by lazy { queryString }

    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(queryString)
    }

    override fun onStartLoading() {
        super.onStartLoading()

        forceLoad()
    }
}


