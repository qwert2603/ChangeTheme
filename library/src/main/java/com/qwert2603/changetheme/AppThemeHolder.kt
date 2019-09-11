package com.qwert2603.changetheme

import android.content.Context
import android.os.Handler
import androidx.annotation.MainThread
import androidx.preference.PreferenceManager

class AppThemeHolder private constructor(appContext: Context) {
    companion object {
        lateinit var INSTANCE: AppThemeHolder

        internal fun init(appContext: Context) {
            check(!this::INSTANCE.isInitialized) { "AppThemeHolder is already initialized!" }
            INSTANCE = AppThemeHolder(appContext)
        }
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(appContext)

    private var appThemeIndex by PrefsInt(prefs, "appThemeIndex", ThemeUtils.DEFAULT_APP_THEME.ordinal)

    var appTheme: AppTheme
        get() = AppTheme.values()[appThemeIndex]
        @MainThread internal set(value) {
            appThemeIndex = value.ordinal
            listeners.forEach { it(value) }
        }

    interface Listener {
        @MainThread
        operator fun invoke(appTheme: AppTheme)
    }

    private val listeners = mutableListOf<Listener>()

    init {
        val handler = Handler()
        val runnable = Runnable {
            ThemeUtils.setTheme(appTheme)
        }

        runnable.run() // apply theme on app start.
        addListener(object : Listener {
            override fun invoke(appTheme: AppTheme) {
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 400)
            }
        })
    }

    fun addListener(listener: Listener) {
        listeners.add(listener)
        listener(appTheme)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

}