/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Models

import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity
import java.util.ArrayList

data class EBCustomDialogModel @JvmOverloads constructor(
    var headerText: String? = null,
    var messageText: String? = null,
    var buttonModels: ArrayList<EBButtonModel>? = null,
    var buttonsSequence: ButtonsSequence? = ButtonsSequence.HORIZONTAL,
    var verticalButtonsGravity: VerticalButtonsGravity? = VerticalButtonsGravity.CENTER,
    var headerTextColor: Int? = null,
    var messageTextColor: Int? = null,
    var headerTextSize: Int? = null,
    var messageTextSize: Int? = null,
    
    // New fields for extended functionality
    var customViewLayoutRes: Int? = null,
    var isInputSupportEnabled: Boolean = false,
    var inputHint: String? = null,
    var inputInitialText: String? = null,
    var listItems: List<String>? = null,
    var onListItemSelected: ((Int, String) -> Unit)? = null,
    var onInputConfirmed: ((String) -> Unit)? = null
)
