package com.qwert2603.changetheme

import androidx.annotation.StringRes

enum class AppTheme(@StringRes val nameRes: Int) {
    LIGHT(R.string.chth_app_theme_light),
    DARK(R.string.chth_app_theme_dark),
    FOLLOW_SYSTEM(R.string.chth_app_theme_follow_system)
}