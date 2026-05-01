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
import androidx.fragment.app.DialogFragment
import com.enesbilgin.ebdialogutilities.R
import com.enesbilgin.ebdialogutilities.databinding.DialogLoadingViewBinding

class EBLoadingDialog : DialogFragment() {

    private var themeColor: Int? = null
    private var message: String? = null
    private var _binding: DialogLoadingViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogLoadingViewBinding.inflate(inflater, container, false)
        
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnims
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false

        themeColor?.let {
            binding.spinkitView.setColor(requireContext().getColor(it))
        }

        if (!message.isNullOrEmpty()) {
            binding.loadingMessage.visibility = View.VISIBLE
            binding.loadingMessage.text = message
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateMessage(newMessage: String) {
        message = newMessage
        _binding?.loadingMessage?.apply {
            text = newMessage
            visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        @JvmOverloads
        fun newInstance(themeColor: Int? = null, message: String? = null): EBLoadingDialog {
            return EBLoadingDialog().apply {
                this.themeColor = themeColor
                this.message = message
            }
        }
    }
}
