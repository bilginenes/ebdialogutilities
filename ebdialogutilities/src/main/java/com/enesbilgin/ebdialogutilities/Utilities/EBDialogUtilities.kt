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
 * Copyright 2019
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
    fun showInfoBox(context: Context, header_text: String?, message_text: String?, singleEventListener: SingleEventListener? = null) {
        val buttonModels = ArrayList<EBButtonModel>()

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_ok_big), object : SingleEventListener {
            override fun onComplete() {
                singleEventListener?.onComplete()
            }
        }, R.color.md_light_blue_800))

        val dialogModel = EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.VERTICAL, VerticalButtonsGravity.CENTER)
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showInfoBox(context: Context, message_text: String?) {
        val header_text = context.getString(R.string.eb_information)
        showInfoBox(context, header_text, message_text)
    }

    @JvmStatic
    fun showInfoBox(context: Context, message_text: String?, singleEventListener: SingleEventListener?) {
        val header_text = context.getString(R.string.eb_information)
        showInfoBox(context, header_text, message_text, singleEventListener)
    }

    @JvmStatic
    fun showNotSupportedInfoBox(context: Context) {
        showInfoBox(context, context.getString(R.string.eb_not_supported_annotation))
    }

    /**Error */
    @JvmStatic
    @JvmOverloads
    fun showErrorBox(context: Context, header_text: String?, message_text: String?, singleEventListener: SingleEventListener? = null) {
        val buttonModels = ArrayList<EBButtonModel>()

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_ok_big), object : SingleEventListener {
            override fun onComplete() {
                singleEventListener?.onComplete()
            }
        }, R.color.md_light_blue_800))

        val dialogModel = EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.VERTICAL, VerticalButtonsGravity.CENTER)
        dialogModel.headerTextColor = R.color.md_red_700
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showErrorBox(context: Context, message_text: String?) {
        val header_text = context.getString(R.string.eb_error)
        showErrorBox(context, header_text, message_text)
    }

    @JvmStatic
    fun showErrorBox(context: Context, message_text: String?, singleEventListener: SingleEventListener?) {
        val header_text = context.getString(R.string.eb_error)
        showErrorBox(context, header_text, message_text, singleEventListener)
    }

    /**Confirm */
    @JvmStatic
    fun showConfirmBox(context: Context, header_text: String?, message_text: String?, completionListener: CompletionListener?) {
        val buttonModels = ArrayList<EBButtonModel>()

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_no_big), object : SingleEventListener {
            override fun onComplete() {
                completionListener?.onCancel()
            }
        }, R.color.md_red_900))

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_yes_big), object : SingleEventListener {
            override fun onComplete() {
                completionListener?.onSuccess()
            }
        }, R.color.md_green_600))

        val dialogModel = EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.HORIZONTAL)
        showCustomDialog(context, dialogModel)
    }

    @JvmStatic
    fun showVoteBox(context: Context, app_name: String?, voteChoiceListener: VoteChoiceListener?) {
        val title: String = if (app_name == null)
            context.getString(R.string.eb_vote_header)
        else String.format(context.getString(R.string.eb_vote_header_custom), app_name)

        val buttonModels = ArrayList<EBButtonModel>()

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_later_big), object : SingleEventListener {
            override fun onComplete() {
                voteChoiceListener?.onLater()
            }
        }, R.color.md_light_blue_800))

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_no_big), object : SingleEventListener {
            override fun onComplete() {
                voteChoiceListener?.onCancel()
            }
        }, R.color.md_red_900))

        buttonModels.add(EBButtonModel(context.getString(R.string.eb_yes_big), object : SingleEventListener {
            override fun onComplete() {
                voteChoiceListener?.onRedirect()
            }
        }, R.color.md_green_600))

        val dialogModel = EBCustomDialogModel(title, context.getString(R.string.eb_vote_message), buttonModels, ButtonsSequence.HORIZONTAL)
        showCustomDialog(context, dialogModel)
    }

    /**Loading */
    @JvmStatic
    @JvmOverloads
    fun showLoadingBox(context: Context, themeColor: Int? = null): EBLoadingDialog {
        val dialog = EBLoadingDialog.newInstance(themeColor)
        show(dialog, context)
        return dialog
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
}