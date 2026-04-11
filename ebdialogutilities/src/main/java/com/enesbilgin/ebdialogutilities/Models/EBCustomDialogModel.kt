/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Models

import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity
import java.util.ArrayList

class EBCustomDialogModel {

    var header_text: String? = null
    var message_text: String? = null
    var buttonModels: ArrayList<EBButtonModel>? = null
    var buttonsSequence: ButtonsSequence? = null
    var verticalButtonsGravity: VerticalButtonsGravity? = null
    var headerTextColor: Int? = null
    var messageTextColor: Int? = null
    var headerTextSize: Int? = null
    var messageTextSize: Int? = null

    constructor(
        header_text: String?,
        message_text: String?,
        buttonModels: ArrayList<EBButtonModel>?,
        buttonsSequence: ButtonsSequence?,
        verticalButtonsGravity: VerticalButtonsGravity?
    ) {
        this.header_text = header_text
        this.message_text = message_text
        this.buttonModels = buttonModels
        this.buttonsSequence = buttonsSequence
        this.verticalButtonsGravity = verticalButtonsGravity
    }

    constructor(
        header_text: String?,
        message_text: String?,
        buttonModels: ArrayList<EBButtonModel>?,
        buttonsSequence: ButtonsSequence?
    ) {
        this.header_text = header_text
        this.message_text = message_text
        this.buttonModels = buttonModels
        this.buttonsSequence = buttonsSequence
    }
}