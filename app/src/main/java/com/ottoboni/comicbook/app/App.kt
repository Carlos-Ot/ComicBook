package com.ottoboni.comicbook.app

import android.app.Application

class App : Application() {

    init {
        instance = this
    }

    companion object {

        internal var instance: App? = null

        @JvmStatic internal fun getApplicationContext() = instance!!.applicationContext
    }
}