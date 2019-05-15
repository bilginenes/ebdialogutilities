/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.R;

public class StyleUtilities {


    public static final String KEY_DIALOG_THEME = "KEY_DIALOG_THEME";
    public static final DialogTheme DEFAULT_DIALOG_THEME = DialogTheme.DARK_1;

    public static void setDialogTheme(Context context, DialogTheme dialogTheme) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(KEY_DIALOG_THEME, dialogTheme.getCode());
        edit.apply();
    }

    public static DialogTheme getDialogTheme(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int code = prefs.getInt(KEY_DIALOG_THEME, DEFAULT_DIALOG_THEME.getCode());

        if(code==DEFAULT_DIALOG_THEME.getCode())
            return DEFAULT_DIALOG_THEME;

        return DialogTheme.getDialogThemeByCode(code);
    }

    public static Integer getDialogViewID(DialogTheme dialogTheme) {

        switch (dialogTheme) {
            case DARK_1:
                return R.layout.dialog_dark_1_alert_view;
            case LIGHT_1:
                return R.layout.dialog_light_1_alert_view;
            default:
                return getDialogViewID(DEFAULT_DIALOG_THEME);
        }
    }

}
