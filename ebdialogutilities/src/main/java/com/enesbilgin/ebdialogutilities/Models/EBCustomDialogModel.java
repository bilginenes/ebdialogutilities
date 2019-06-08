/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Models;

import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence;
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class EBCustomDialogModel {


    private String header_text;
    private String message_text;
    private ArrayList<EBButtonModel> buttonModels;

    private ButtonsSequence buttonsSequence;
    private VerticalButtonsGravity verticalButtonsGravity;

    public EBCustomDialogModel(@Nullable String header_text,@Nullable  String message_text, ArrayList<EBButtonModel> buttonModels, ButtonsSequence buttonsSequence, VerticalButtonsGravity verticalButtonsGravity) {
        this.header_text = header_text;
        this.message_text = message_text;
        this.buttonModels = buttonModels;
        this.buttonsSequence = buttonsSequence;
        this.verticalButtonsGravity = verticalButtonsGravity;
    }

    public EBCustomDialogModel(@Nullable String header_text, @Nullable String message_text, ArrayList<EBButtonModel> buttonModels, ButtonsSequence buttonsSequence) {
        this.header_text = header_text;
        this.message_text = message_text;
        this.buttonModels = buttonModels;
        this.buttonsSequence = buttonsSequence;
    }

    public String getHeader_text() {
        return header_text;
    }

    public String getMessage_text() {
        return message_text;
    }

    public ArrayList<EBButtonModel> getButtonModels() {
        return buttonModels;
    }

    public ButtonsSequence getButtonsSequence() {
        return buttonsSequence;
    }

    public VerticalButtonsGravity getVerticalButtonsGravity() {
        return verticalButtonsGravity;
    }

}
