/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.enesbilgin.ebdialogutilities.Constants.AlertViewType;
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener;
import com.enesbilgin.ebdialogutilities.R;
import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities;
import com.github.ybq.android.spinkit.SpinKitView;

public class EBLoadingDialog extends DialogFragment {


    //LOADING_BOX
    public static EBLoadingDialog newInstance(Integer themeColor){
        EBLoadingDialog loadingDialog = new EBLoadingDialog();
        loadingDialog.themeColor = themeColor;
        return loadingDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private Integer themeColor;
    private SpinKitView spinKitView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        View view = inflater.inflate(R.layout.dialog_loading_view, container, false);

        if(themeColor!=null) {
            spinKitView = view.findViewById(R.id.spinkitView);
            spinKitView.setColor(themeColor);
        }

        return view;
    }
}
