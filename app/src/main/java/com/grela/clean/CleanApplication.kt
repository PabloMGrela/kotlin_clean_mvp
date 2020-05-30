package com.grela.clean

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class CleanApplication : Application() {
    companion object {
        private var mInstance: CleanApplication? = null

        fun instance(): CleanApplication? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CleanApplication)
            modules(AppModules.modules)
            EmptyLogger()
        }
    }

}