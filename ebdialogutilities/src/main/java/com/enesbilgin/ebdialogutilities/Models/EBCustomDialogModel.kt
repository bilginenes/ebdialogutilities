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

    private Integer headerTextColor;
    private Integer messageTextColor;

    private Integer headerTextSize;
    private Integer messageTextSize;

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

    public void setHeader_text(String header_text) {
        this.header_text = header_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public void setButtonModels(ArrayList<EBButtonModel> buttonModels) {
        this.buttonModels = buttonModels;
    }

    public void setButtonsSequence(ButtonsSequence buttonsSequence) {
        this.buttonsSequence = buttonsSequence;
    }

    public void setVerticalButtonsGravity(VerticalButtonsGravity verticalButtonsGravity) {
        this.verticalButtonsGravity = verticalButtonsGravity;
    }

    public Integer getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(Integer headerTextColor) {
        this.headerTextColor = headerTextColor;
    }

    public Integer getMessageTextColor() {
        return messageTextColor;
    }

    public void setMessageTextColor(Integer messageTextColor) {
        this.messageTextColor = messageTextColor;
    }

    public Integer getHeaderTextSize() {
        return headerTextSize;
    }

    public void setHeaderTextSize(Integer headerTextSize) {
        this.headerTextSize = headerTextSize;
    }

    public Integer getMessageTextSize() {
        return messageTextSize;
    }

    public void setMessageTextSize(Integer messageTextSize) {
        this.messageTextSize = messageTextSize;
    }
}
