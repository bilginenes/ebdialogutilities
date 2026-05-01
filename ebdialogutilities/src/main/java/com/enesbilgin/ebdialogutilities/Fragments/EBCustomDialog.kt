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
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel
import com.enesbilgin.ebdialogutilities.R
import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities
import com.enesbilgin.ebdialogutilities.databinding.DialogDarkCustomBinding
import com.enesbilgin.ebdialogutilities.databinding.DialogLightCustomBinding
import com.enesbilgin.ebdialogutilities.databinding.EbDialogInputFieldBinding
import com.enesbilgin.ebdialogutilities.databinding.EbDialogListItemBinding

class EBCustomDialog : DialogFragment() {

    private var dialogModel: EBCustomDialogModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // retainInstance = true is deprecated, but we'll use it for now to keep callbacks
        @Suppress("DEPRECATION")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnims
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false

        val model = dialogModel ?: return null
        val dialogTheme = StyleUtilities.getDialogTheme(requireContext())
        
        return if (dialogTheme == DialogTheme.LIGHT_1) {
            val binding = DialogLightCustomBinding.inflate(inflater, container, false)
            setupUI(binding.alertViewHeader, binding.alertViewMessage, binding.buttonsLayout, binding.dynamicContentContainer, R.style.DefaultDarkButton)
            binding.root
        } else {
            val binding = DialogDarkCustomBinding.inflate(inflater, container, false)
            setupUI(binding.alertViewHeader, binding.alertViewMessage, binding.buttonsLayout, binding.dynamicContentContainer, R.style.DefaultWhiteButton)
            binding.root
        }
    }

    private fun setupUI(
        headerView: TextView,
        messageView: TextView,
        buttonsLayout: LinearLayout,
        contentContainer: android.widget.FrameLayout,
        buttonStyle: Int
    ) {
        val model = dialogModel ?: return

        // Header
        if (!model.headerText.isNullOrEmpty()) {
            headerView.text = model.headerText
            model.headerTextColor?.let { headerView.setTextColor(requireContext().getColor(it)) }
            model.headerTextSize?.let { headerView.textSize = it.toFloat() }
        } else {
            headerView.visibility = View.GONE
        }

        // Message
        if (!model.messageText.isNullOrEmpty()) {
            messageView.text = model.messageText
            model.messageTextColor?.let { messageView.setTextColor(requireContext().getColor(it)) }
            model.messageTextSize?.let { messageView.textSize = it.toFloat() }
        } else {
            messageView.visibility = View.GONE
        }

        // Dynamic Content
        setupDynamicContent(contentContainer)

        // Buttons
        buttonsLayout.orientation = if (model.buttonsSequence == ButtonsSequence.VERTICAL) LinearLayout.VERTICAL else LinearLayout.HORIZONTAL
        
        model.buttonModels?.forEach { buttonModel ->
            val button = Button(ContextThemeWrapper(requireContext(), buttonStyle), null, buttonStyle)
            button.text = buttonModel.buttonText
            button.setOnClickListener {
                if (model.isInputSupportEnabled) {
                    val editText = contentContainer.findViewById<TextView>(R.id.editText)
                    model.onInputConfirmed?.invoke(editText?.text?.toString() ?: "")
                }
                buttonModel.onButtonClick?.onComplete()
                dismiss()
            }

            buttonModel.buttonTextColor?.let { button.setTextColor(requireContext().getColor(it)) }

            val params = if (model.buttonsSequence == ButtonsSequence.HORIZONTAL) {
                button.textAlignment = View.TEXT_ALIGNMENT_CENTER
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            } else {
                when (model.verticalButtonsGravity) {
                    VerticalButtonsGravity.CENTER -> button.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    VerticalButtonsGravity.RIGHT -> button.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    VerticalButtonsGravity.LEFT -> button.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    else -> {}
                }
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            }
            button.layoutParams = params
            buttonsLayout.addView(button)
        }
    }

    private fun setupDynamicContent(container: android.widget.FrameLayout) {
        val model = dialogModel ?: return
        val inflater = LayoutInflater.from(requireContext())

        when {
            model.customViewLayoutRes != null -> {
                container.visibility = View.VISIBLE
                inflater.inflate(model.customViewLayoutRes!!, container, true)
            }
            model.isInputSupportEnabled -> {
                container.visibility = View.VISIBLE
                val inputBinding = EbDialogInputFieldBinding.inflate(inflater, container, true)
                inputBinding.textInputLayout.hint = model.inputHint
                inputBinding.editText.setText(model.inputInitialText)
            }
            model.listItems != null -> {
                container.visibility = View.VISIBLE
                val listContainer = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                }
                model.listItems?.forEachIndexed { index, item ->
                    val itemBinding = EbDialogListItemBinding.inflate(inflater, listContainer, true)
                    itemBinding.listItemText.text = item
                    itemBinding.root.setOnClickListener {
                        model.onListItemSelected?.invoke(index, item)
                        dismiss()
                    }
                }
                container.addView(listContainer)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(dialogModel: EBCustomDialogModel): EBCustomDialog {
            return EBCustomDialog().apply {
                this.dialogModel = dialogModel
            }
        }
    }
}
