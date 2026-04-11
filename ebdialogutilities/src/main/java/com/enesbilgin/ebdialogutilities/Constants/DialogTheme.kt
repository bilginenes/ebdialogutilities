/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Constants

import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities

enum class DialogTheme(val code: Int) {

    DARK_1(0),
    LIGHT_1(1);

    companion object {
        @JvmStatic
        fun getDialogThemeByCode(code: Int): DialogTheme {
            for (theme in values())
                if (theme.code == code)
                    return theme

            return StyleUtilities.DEFAULT_DIALOG_THEME
        }
    }
}