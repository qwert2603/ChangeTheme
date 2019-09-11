package com.qwert2603.changetheme

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.chth_view_change_theme.view.*

class ChangeThemeCheckBox(
    context: Context,
    attrs: AttributeSet?
) : FrameLayout(context, attrs) {

    private val listener = object : AppThemeHolder.Listener {
        override fun invoke(appTheme: AppTheme) {
            chth_darkTheme_Switch.isChecked = appTheme == AppTheme.DARK
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.chth_view_change_theme, this, true)
        chth_darkTheme_Switch.isSaveEnabled = false
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            AppThemeHolder.INSTANCE.addListener(listener)
        }

        chth_darkTheme_LinearLayout.setOnClickListener {
            AppThemeHolder.INSTANCE.appTheme =
                if (!chth_darkTheme_Switch.isChecked) AppTheme.DARK else AppTheme.LIGHT
        }
    }

    override fun onDetachedFromWindow() {
        if (!isInEditMode) {
            AppThemeHolder.INSTANCE.removeListener(listener)
        }
        super.onDetachedFromWindow()
    }
}