package com.qwert2603.changetheme

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {
    @MainThread
    internal fun setTheme(appTheme: AppTheme) {
        Log.d("ThemeUtils", "setTheme $appTheme")
        AppCompatDelegate.setDefaultNightMode(when (appTheme) {
            AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            AppTheme.FOLLOW_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        })
    }

    fun isDarkNow(context: Context): Boolean {
        return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    internal val DEFAULT_APP_THEME: AppTheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> AppTheme.FOLLOW_SYSTEM
        else -> AppTheme.LIGHT
    }
}