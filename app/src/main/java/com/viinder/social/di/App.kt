package com.viinder.social.di

import android.app.Application
import com.viinder.social.common.preference.Preferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class App : Application() {

    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate() {
        super.onCreate()

        preferences = Preferences(applicationContext)

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    authModule,
                    userModule,
                    feedModule,
                    profileModule,
                    postModule,
                    searchModule,
                    directMessageModule
                )
            )
        }
    }
}
