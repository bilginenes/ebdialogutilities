/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Utilities

import android.content.Context
import android.preference.PreferenceManager
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme

object StyleUtilities {

    const val KEY_DIALOG_THEME = "KEY_DIALOG_THEME"
    val DEFAULT_DIALOG_THEME = DialogTheme.DARK_1

    @JvmStatic
    fun setDialogTheme(context: Context, dialogTheme: DialogTheme) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val edit = prefs.edit()
        edit.putInt(KEY_DIALOG_THEME, dialogTheme.code)
        edit.apply()
    }

    @JvmStatic
    fun getDialogTheme(context: Context?): DialogTheme {
        if (context == null) return DEFAULT_DIALOG_THEME
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val code = prefs.getInt(KEY_DIALOG_THEME, DEFAULT_DIALOG_THEME.code)

        if (code == DEFAULT_DIALOG_THEME.code)
            return DEFAULT_DIALOG_THEME

        return DialogTheme.getDialogThemeByCode(code)
    }
}