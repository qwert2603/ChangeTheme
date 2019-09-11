package com.qwert2603.changetheme_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        R.layout.chth_fragment_change_theme
        R.layout.chth_view_change_theme
        R.layout.chth_include_change_theme

        R.string.chth_app_theme_dark
        R.string.chth_app_theme_light
    }
}
