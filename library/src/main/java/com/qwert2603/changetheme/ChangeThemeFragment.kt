package com.qwert2603.changetheme

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.chth_fragment_change_theme.*

@TargetApi(Build.VERSION_CODES.Q)
class ChangeThemeFragment : Fragment() {

    companion object {
        const val REQUEST_CHOOSE_THEME = 29
    }

    private var lastTouchX: Int = -1
    private var lastTouchY: Int = -1

    private val listener = object : AppThemeHolder.Listener {
        override fun invoke(appTheme: AppTheme) {
            theme_TextView.setText(appTheme.nameRes)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.chth_fragment_change_theme, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chth_darkTheme_LinearLayout.setOnTouchListener { _: View, event: MotionEvent ->
            lastTouchX = event.rawX.toInt()
            lastTouchY = event.rawY.toInt()
            false
        }

        chth_darkTheme_LinearLayout.setOnClickListener {
            ChooseThemeDialogFragment
                    .newInstance(AppThemeHolder.INSTANCE.appTheme.ordinal, lastTouchX, lastTouchY)
                    .also { it.setTargetFragment(this, REQUEST_CHOOSE_THEME) }
                    .show(requireFragmentManager(), null)
        }

        AppThemeHolder.INSTANCE.addListener(listener)
    }

    override fun onDestroyView() {
        AppThemeHolder.INSTANCE.removeListener(listener)
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHOOSE_THEME && data != null) {
            val selectedIndex = data.getIntExtra(ChooseThemeDialogFragment.KEY_SELECTED_INDEX, 0)
            if (selectedIndex in AppTheme.values().indices) {
                AppThemeHolder.INSTANCE.appTheme = AppTheme.values()[selectedIndex]
            }
        }
    }
}

