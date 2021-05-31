package com.djevannn.tastyfood

import android.app.Application
import com.djevannn.core.di.databaseModule
import com.djevannn.core.di.networkModule
import com.djevannn.core.di.repositoryModule
import com.djevannn.tastyfood.di.useCaseModule
import com.djevannn.tastyfood.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}