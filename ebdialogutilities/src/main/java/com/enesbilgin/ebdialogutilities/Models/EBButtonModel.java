/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Models;

import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener;

import javax.annotation.Nullable;

public class EBButtonModel {

    private String buttonText;
    private SingleEventListener onButtonClick;
    private Integer buttonTextColor;

    public EBButtonModel(String buttonText) {
        this.buttonText = buttonText;
    }

    public EBButtonModel(String buttonText, @Nullable  SingleEventListener onButtonClick) {
        this.buttonText = buttonText;
        this.onButtonClick = onButtonClick;
    }

    public EBButtonModel(String buttonText, @Nullable SingleEventListener onButtonClick,@Nullable  int buttonTextColor) {
        this.buttonText = buttonText;
        this.onButtonClick = onButtonClick;
        this.buttonTextColor = buttonTextColor;
    }

    public String getButtonText() {
        return buttonText;
    }

    public SingleEventListener getOnButtonClick() {
        return onButtonClick;
    }

    public Integer getButtonTextColor() {
        return buttonTextColor;
    }
}
