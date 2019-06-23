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
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;

import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence;
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity;
import com.enesbilgin.ebdialogutilities.Models.EBButtonModel;
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel;
import com.enesbilgin.ebdialogutilities.R;
import com.enesbilgin.ebdialogutilities.Utilities.StyleUtilities;

public class EBCustomDialog extends DialogFragment {

    public static EBCustomDialog newInstance(EBCustomDialogModel dialogModel){
        EBCustomDialog ebCustomDialog = new EBCustomDialog();
        ebCustomDialog.dialogModel = dialogModel;
        return ebCustomDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private EBCustomDialogModel dialogModel;
    private View view;
    private TextView alert_view_header, alert_view_message;
    private LinearLayout buttonsLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        if(dialogModel==null)
            return null;

        int buttonStyle;
        DialogTheme dialogTheme = StyleUtilities.getDialogTheme(getContext());

        if(dialogTheme == DialogTheme.LIGHT_1) {
            view = inflater.inflate(R.layout.dialog_light_custom, container, false);
            buttonStyle = R.style.DefaultDarkButton;
        } else {
            view = inflater.inflate(R.layout.dialog_dark_custom, container, false);
            buttonStyle = R.style.DefaultWhiteButton;
        }

        alert_view_header = view.findViewById(R.id.alert_view_header);
        alert_view_message = view.findViewById(R.id.alert_view_message);
        buttonsLayout = view.findViewById(R.id.buttonsLayout);

        if(dialogModel.getHeader_text()!=null && !dialogModel.getHeader_text().equals("")) {
            alert_view_header.setText(dialogModel.getHeader_text());
            if(dialogModel.getHeaderTextColor()!=null)
                alert_view_header.setTextColor(getContext().getColor(dialogModel.getHeaderTextColor()));
        } else alert_view_header.setVisibility(View.GONE);

        if(dialogModel.getMessage_text()!=null && !dialogModel.getMessage_text().equals(""))
            alert_view_message.setText(dialogModel.getMessage_text());
        else alert_view_message.setVisibility(View.GONE);

        if(dialogModel.getButtonsSequence()== ButtonsSequence.VERTICAL)
            buttonsLayout.setOrientation(LinearLayout.VERTICAL);
        else buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);

        if(dialogModel.getButtonModels()!=null && dialogModel.getButtonModels().size()>0) {
            for(final EBButtonModel buttonModel: dialogModel.getButtonModels()) {

                Button button = new Button(new ContextThemeWrapper(getContext(), buttonStyle), null, buttonStyle);
                button.setText(buttonModel.getButtonText());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(buttonModel.getOnButtonClick()!=null)
                            buttonModel.getOnButtonClick().onComplete();
                        dismiss();
                    }
                });

                if(buttonModel.getButtonTextColor()!=null)
                    button.setTextColor(getContext().getColor(buttonModel.getButtonTextColor()));

                if(dialogModel.getButtonsSequence()==ButtonsSequence.HORIZONTAL) {
                    button.setTextAlignment(Button.TEXT_ALIGNMENT_CENTER);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                    button.setLayoutParams(params);
                } else if(dialogModel.getButtonsSequence()==ButtonsSequence.VERTICAL) {

                    if(dialogModel.getVerticalButtonsGravity()== VerticalButtonsGravity.CENTER)
                        button.setTextAlignment(Button.TEXT_ALIGNMENT_CENTER);
                    else if(dialogModel.getVerticalButtonsGravity()== VerticalButtonsGravity.RIGHT)
                        button.setTextAlignment(Button.TEXT_ALIGNMENT_TEXT_END);
                    else if(dialogModel.getVerticalButtonsGravity()== VerticalButtonsGravity.LEFT)
                        button.setTextAlignment(Button.TEXT_ALIGNMENT_TEXT_START);


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    button.setLayoutParams(params);
                }

                buttonsLayout.addView(button);
            }
        }

        return view;
    }
}
