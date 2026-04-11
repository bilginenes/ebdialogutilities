/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity
import com.enesbilgin.ebdialogutilities.Models.EBButtonModel
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel
import com.enesbilgin.ebdialogutilities.R
import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities

class EBCustomDialog : DialogFragment() {

    private var dialogModel: EBCustomDialogModel? = null
    private var alert_view_header: TextView? = null
    private var alert_view_message: TextView? = null
    private var buttonsLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnims
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false

        if (dialogModel == null)
            return null

        val dialogTheme = StyleUtilities.getDialogTheme(context)
        val view: View
        val buttonStyle: Int

        if (dialogTheme == DialogTheme.LIGHT_1) {
            view = inflater.inflate(R.layout.dialog_light_custom, container, false)
            buttonStyle = R.style.DefaultDarkButton
        } else {
            view = inflater.inflate(R.layout.dialog_dark_custom, container, false)
            buttonStyle = R.style.DefaultWhiteButton
        }

        alert_view_header = view.findViewById(R.id.alert_view_header)
        alert_view_message = view.findViewById(R.id.alert_view_message)
        buttonsLayout = view.findViewById(R.id.buttonsLayout)

        if (!dialogModel?.header_text.isNullOrEmpty()) {
            alert_view_header?.text = dialogModel?.header_text
            dialogModel?.headerTextColor?.let {
                alert_view_header?.setTextColor(requireContext().getColor(it))
            }
        } else {
            alert_view_header?.visibility = View.GONE
        }

        if (!dialogModel?.message_text.isNullOrEmpty()) {
            alert_view_message?.text = dialogModel?.message_text
        } else {
            alert_view_message?.visibility = View.GONE
        }

        if (dialogModel?.buttonsSequence == ButtonsSequence.VERTICAL) {
            buttonsLayout?.orientation = LinearLayout.VERTICAL
        } else {
            buttonsLayout?.orientation = LinearLayout.HORIZONTAL
        }

        dialogModel?.buttonModels?.let { models ->
            if (models.isNotEmpty()) {
                for (buttonModel in models) {
                    val button = Button(ContextThemeWrapper(context, buttonStyle), null, buttonStyle)
                    button.text = buttonModel.buttonText

                    button.setOnClickListener {
                        buttonModel.onButtonClick?.onComplete()
                        dismiss()
                    }

                    buttonModel.buttonTextColor?.let {
                        button.setTextColor(requireContext().getColor(it))
                    }

                    if (dialogModel?.buttonsSequence == ButtonsSequence.HORIZONTAL) {
                        button.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                        button.layoutParams = params
                    } else if (dialogModel?.buttonsSequence == ButtonsSequence.VERTICAL) {
                        when (dialogModel?.verticalButtonsGravity) {
                            VerticalButtonsGravity.CENTER -> button.textAlignment = View.TEXT_ALIGNMENT_CENTER
                            VerticalButtonsGravity.RIGHT -> button.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            VerticalButtonsGravity.LEFT -> button.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                            else -> {}
                        }
                        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        button.layoutParams = params
                    }
                    buttonsLayout?.addView(button)
                }
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(dialogModel: EBCustomDialogModel): EBCustomDialog {
            val ebCustomDialog = EBCustomDialog()
            ebCustomDialog.dialogModel = dialogModel
            return ebCustomDialog
        }
    }
}