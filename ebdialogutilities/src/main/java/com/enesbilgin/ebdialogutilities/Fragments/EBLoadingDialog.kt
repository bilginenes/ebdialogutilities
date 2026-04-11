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
import com.github.ybq.android.spinkit.SpinKitView

class EBLoadingDialog : DialogFragment() {

    private var themeColor: Int? = null
    private var spinKitView: SpinKitView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnims
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false

        val view = inflater.inflate(R.layout.dialog_loading_view, container, false)

        themeColor?.let {
            spinKitView = view.findViewById(R.id.spinkitView)
            spinKitView?.setColor(requireContext().getColor(it))
        }

        return view
    }

    companion object {
        // LOADING_BOX
        @JvmStatic
        fun newInstance(themeColor: Int?): EBLoadingDialog {
            val loadingDialog = EBLoadingDialog()
            loadingDialog.themeColor = themeColor
            return loadingDialog
        }
    }
}