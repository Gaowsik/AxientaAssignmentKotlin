package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import android.app.Application
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.di.mRepositoryModule
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.di.retrofitNetworkModule
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.di.roomDatabaseModule
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * All the DI components will be initialized here
 */
class ApplicationCore : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationCore)
            // declare modules
            modules(
                listOf(
                    retrofitNetworkModule,mRepositoryModule, viewModelModule,roomDatabaseModule
                )


            )
        }

    }

}