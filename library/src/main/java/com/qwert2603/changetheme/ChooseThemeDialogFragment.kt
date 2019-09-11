package com.qwert2603.changetheme

import android.annotation.TargetApi
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.qwert2603.circular_reveal_dialog.CircularRevealDialog
import com.qwert2603.circular_reveal_dialog.OnDialogButtonClickListenerAdapter
import com.qwert2603.circular_reveal_dialog.ResultListener

@TargetApi(Build.VERSION_CODES.Q)
class ChooseThemeDialogFragment : DialogFragment() {

    companion object {
        const val KEY_SELECTED_INDEX = "KEY_SELECTED_INDEX"
        private const val KEY_START_X = "KEY_START_X"
        private const val KEY_START_Y = "KEY_START_Y"

        fun newInstance(selectedIndex: Int, startX: Int, startY: Int) = ChooseThemeDialogFragment()
                .also {
                    val bundle = Bundle()
                    it.arguments = bundle
                    bundle.putInt(KEY_SELECTED_INDEX, selectedIndex)
                    bundle.putInt(KEY_START_X, startX)
                    bundle.putInt(KEY_START_Y, startY)
                }
    }


    private lateinit var resultListener: ResultListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = requireArguments()

        return AlertDialog.Builder(requireContext())
                .setTitle(R.string.chth_app_theme_dark_theme)
                .setSingleChoiceItems(
                        AppTheme.values().map { getString(it.nameRes) }.toTypedArray(),
                        arguments.getInt(KEY_SELECTED_INDEX, 0)
                ) { _, i ->
                    resultListener.onResult(Intent().putExtra(KEY_SELECTED_INDEX, i))
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .also {
                    resultListener =
                        CircularRevealDialog.initDialogForCircularReveal(
                            this,
                            it,
                            OnDialogButtonClickListenerAdapter(),
                            arguments.getInt(KEY_START_X, 0),
                            arguments.getInt(KEY_START_Y, 0),
                            400L
                        )
                }
    }
}