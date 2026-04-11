/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Constants;

import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities;

public enum  DialogTheme {

    DARK_1(0),
    LIGHT_1(1);

    private int code;

    DialogTheme(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public static DialogTheme getDialogThemeByCode(int code) {
        for (DialogTheme theme:DialogTheme.values())
            if(theme.code==code)
                return theme;

        return StyleUtilities.DEFAULT_DIALOG_THEME;
    }

}
