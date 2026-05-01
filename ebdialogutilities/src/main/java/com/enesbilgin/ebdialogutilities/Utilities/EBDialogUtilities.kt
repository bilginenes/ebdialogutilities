/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Utilities

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity
import com.enesbilgin.ebdialogutilities.Fragments.EBCustomDialog
import com.enesbilgin.ebdialogutilities.Fragments.EBLoadingDialog
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener
import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener
import com.enesbilgin.ebdialogutilities.Models.EBButtonModel
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel
import com.enesbilgin.ebdialogutilities.R
import java.util.ArrayList

/**
 * Copyright 2019-2026
 * Enes Bilgin
 */
object EBDialogUtilities {

    @JvmStatic
    fun initialize(context: Context, dialogTheme: DialogTheme) {
        StyleUtilities.setDialogTheme(context, dialogTheme)
    }

    /**Info */
    @JvmStatic
    @JvmOverloads
    fun showInfoBox(context: Context, headerText: String?, messageText: String?, singleEventListener: SingleEventListener? = null) {
        val buttonModels = arrayListOf(
            EBButtonModel(context.getString(R.string.eb_ok_big), object : SingleEventListener {
                override fun onComplete() {
                    singleEventListener?.onComplete()
                }
            }, R.color.md_light_blue_800)
        )

        val dialogModel = EBCustomDialogModel(
            headerText = headerText,
            messageText = messageText,
            buttonModels = buttonModels,
            buttonsSequence = ButtonsSequence.VERTICAL,
            verticalButtonsGravity = VerticalButtonsGravity.CENTER
        )
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showInfoBox(context: Context, messageText: String?) {
        showInfoBox(context, context.getString(R.string.eb_information), messageText)
    }

    @JvmStatic
    fun showInfoBox(context: Context, messageText: String?, singleEventListener: SingleEventListener?) {
        showInfoBox(context, context.getString(R.string.eb_information), messageText, singleEventListener)
    }

    @JvmStatic
    fun showNotSupportedInfoBox(context: Context) {
        showInfoBox(context, context.getString(R.string.eb_not_supported_annotation))
    }

    /**Error */
    @JvmStatic
    @JvmOverloads
    fun showErrorBox(context: Context, headerText: String?, messageText: String?, singleEventListener: SingleEventListener? = null) {
        val buttonModels = arrayListOf(
            EBButtonModel(context.getString(R.string.eb_ok_big), object : SingleEventListener {
                override fun onComplete() {
                    singleEventListener?.onComplete()
                }
            }, R.color.md_light_blue_800)
        )

        val dialogModel = EBCustomDialogModel(
            headerText = headerText,
            messageText = messageText,
            buttonModels = buttonModels,
            buttonsSequence = ButtonsSequence.VERTICAL,
            verticalButtonsGravity = VerticalButtonsGravity.CENTER,
            headerTextColor = R.color.md_red_700
        )
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showErrorBox(context: Context, messageText: String?) {
        showErrorBox(context, context.getString(R.string.eb_error), messageText)
    }

    @JvmStatic
    fun showErrorBox(context: Context, messageText: String?, singleEventListener: SingleEventListener?) {
        showErrorBox(context, context.getString(R.string.eb_error), messageText, singleEventListener)
    }

    /**Confirm */
    @JvmStatic
    fun showConfirmBox(context: Context, headerText: String?, messageText: String?, completionListener: CompletionListener?) {
        val buttonModels = arrayListOf(
            EBButtonModel(context.getString(R.string.eb_no_big), object : SingleEventListener {
                override fun onComplete() = completionListener?.onCancel() ?: Unit
            }, R.color.md_red_900),
            EBButtonModel(context.getString(R.string.eb_yes_big), object : SingleEventListener {
                override fun onComplete() = completionListener?.onSuccess() ?: Unit
            }, R.color.md_green_600)
        )

        val dialogModel = EBCustomDialogModel(
            headerText = headerText,
            messageText = messageText,
            buttonModels = buttonModels,
            buttonsSequence = ButtonsSequence.HORIZONTAL
        )
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showVoteBox(context: Context, appName: String?, voteChoiceListener: VoteChoiceListener?) {
        val title = if (appName == null)
            context.getString(R.string.eb_vote_header)
        else String.format(context.getString(R.string.eb_vote_header_custom), appName)

        val buttonModels = arrayListOf(
            EBButtonModel(context.getString(R.string.eb_later_big), object : SingleEventListener {
                override fun onComplete() = voteChoiceListener?.onLater() ?: Unit
            }, R.color.md_light_blue_800),
            EBButtonModel(context.getString(R.string.eb_no_big), object : SingleEventListener {
                override fun onComplete() = voteChoiceListener?.onCancel() ?: Unit
            }, R.color.md_red_900),
            EBButtonModel(context.getString(R.string.eb_yes_big), object : SingleEventListener {
                override fun onComplete() = voteChoiceListener?.onRedirect() ?: Unit
            }, R.color.md_green_600)
        )

        val dialogModel = EBCustomDialogModel(
            headerText = title,
            messageText = context.getString(R.string.eb_vote_message),
            buttonModels = buttonModels,
            buttonsSequence = ButtonsSequence.HORIZONTAL
        )
        showCustomDialog(context, dialogModel)
    }

    /**Loading */
    @JvmStatic
    @JvmOverloads
    fun showLoadingBox(context: Context, themeColor: Int? = null, message: String? = null): EBLoadingDialog {
        val dialog = EBLoadingDialog.newInstance(themeColor, message)
        show(dialog, context)
        return dialog
    }

    /** Input Dialog */
    @JvmStatic
    @JvmOverloads
    fun showInputBox(
        context: Context,
        headerText: String?,
        hint: String? = null,
        initialText: String? = null,
        onConfirmed: (String) -> Unit
    ) {
        val dialogModel = EBCustomDialogModel(
            headerText = headerText,
            isInputSupportEnabled = true,
            inputHint = hint,
            inputInitialText = initialText,
            onInputConfirmed = onConfirmed,
            buttonModels = arrayListOf(
                EBButtonModel(context.getString(R.string.eb_ok_big), null, R.color.md_light_blue_800)
            )
        )
        showCustomDialog(context, dialogModel)
    }

    /** List Dialog */
    @JvmStatic
    fun showListBox(
        context: Context,
        headerText: String?,
        items: List<String>,
        onSelected: (Int, String) -> Unit
    ) {
        val dialogModel = EBCustomDialogModel(
            headerText = headerText,
            listItems = items,
            onListItemSelected = onSelected
        )
        showCustomDialog(context, dialogModel)
    }

    /**Custom Dialog */
    @JvmStatic
    fun showCustomDialog(context: Context, dialogModel: EBCustomDialogModel) {
        val customDialog = EBCustomDialog.newInstance(dialogModel)
        show(customDialog, context)
    }

    /**Common Show */
    @JvmStatic
    fun show(dialog: DialogFragment, context: Context) {
        if (context is FragmentActivity) {
            val fm = context.supportFragmentManager
            fm.beginTransaction().add(dialog, "dialog").commitAllowingStateLoss()
        } else {
            Toast.makeText(context, R.string.eb_debug_notation, Toast.LENGTH_SHORT).show()
        }
    }

    /** DSL / Builder Pattern */
    fun build(context: Context, block: EBCustomDialogModel.() -> Unit): EBCustomDialog {
        val model = EBCustomDialogModel()
        model.block()
        return EBCustomDialog.newInstance(model)
    }
}

// Extension function for easier DSL usage
fun EBCustomDialogModel.buttons(block: ArrayList<EBButtonModel>.() -> Unit) {
    if (buttonModels == null) buttonModels = ArrayList()
    buttonModels?.block()
}

fun ArrayList<EBButtonModel>.positive(text: String, color: Int? = null, onClick: () -> Unit) {
    add(EBButtonModel(text, object : SingleEventListener {
        override fun onComplete() = onClick()
    }, color))
}

fun ArrayList<EBButtonModel>.negative(text: String, color: Int? = null, onClick: () -> Unit) {
    add(EBButtonModel(text, object : SingleEventListener {
        override fun onComplete() = onClick()
    }, color))
}
