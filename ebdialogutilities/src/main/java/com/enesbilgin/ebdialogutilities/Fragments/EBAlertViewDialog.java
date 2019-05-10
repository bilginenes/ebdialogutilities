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
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener;
import com.enesbilgin.ebdialogutilities.R;
/**
 * Copyright 2019
 * Enes Bilgin
 */
public class EBAlertViewDialog extends DialogFragment {
    private AlertViewType type;
    private String header_text = null;
    private String message_text = null;
    private CompletionListener completionListener = null;
    private SingleEventListener singleEventListener = null;

    //CONFIRM_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text, CompletionListener completionListener, SingleEventListener singleEventListener){
        EBAlertViewDialog dialog = new EBAlertViewDialog();
        dialog.type = type;
        dialog.header_text = header_text;
        dialog.message_text = message_text;
        dialog.completionListener = completionListener;
        dialog.singleEventListener = singleEventListener;
        return dialog;
    }

    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text, CompletionListener completionListener){
        return newInstance(type, header_text, message_text, completionListener, null);
    }

    //INFO_BOX ERROR_BOX LOADING_INFO_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text){
        return newInstance(type,header_text,message_text,null, null);
    }

    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text, SingleEventListener singleEventListener){
        return newInstance(type,header_text,message_text,null, singleEventListener);
    }

    //LOADING_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type){
        return newInstance(type,null,null,null,null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private TextView alert_view_header, alert_view_message;
    private LinearLayout alert_view_buttons;
    private Button alert_view_refuse, alert_view_confirm, alert_view_okay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        if (type == AlertViewType.LOADING_BOX)
            return inflater.inflate(R.layout.dialog_loading_view, container, false);

        View view = inflater.inflate(R.layout.dialog_alert_view, container, false);

        alert_view_header = view.findViewById(R.id.alert_view_header);
        alert_view_message = view.findViewById(R.id.alert_view_message);

        alert_view_buttons = view.findViewById(R.id.alert_view_buttons);
        alert_view_refuse = view.findViewById(R.id.alert_view_refuse);
        alert_view_confirm = view.findViewById(R.id.alert_view_confirm);
        alert_view_okay = view.findViewById(R.id.alert_view_okay);

        switch (type) {
            case CONFIRM_BOX:
                alert_view_header.setText(header_text);
                alert_view_message.setText(message_text);
                alert_view_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        completionListener.onSuccess();
                        EBAlertViewDialog.this.dismiss();
                    }
                });
                alert_view_refuse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        completionListener.onCancel();
                        EBAlertViewDialog.this.dismiss();
                    }
                });
                alert_view_okay.setVisibility(View.GONE);
                return view;
            case INFO_BOX:
                alert_view_header.setText(header_text);
                //Use default header color.
                alert_view_message.setText(message_text);

                alert_view_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EBAlertViewDialog.this.dismiss();
                        if (singleEventListener != null)
                            singleEventListener.onComplete();
                    }
                });

                alert_view_refuse.setVisibility(View.GONE);
                alert_view_confirm.setVisibility(View.GONE);
                return view;
            case ERROR_BOX:
                alert_view_header.setText(header_text);
                alert_view_header.setTextColor(getResources().getColor(R.color.md_red_900));
                alert_view_message.setText(message_text);

                alert_view_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EBAlertViewDialog.this.dismiss();
                        if (singleEventListener != null)
                            singleEventListener.onComplete();
                    }
                });

                alert_view_refuse.setVisibility(View.GONE);
                alert_view_confirm.setVisibility(View.GONE);
                return view;
            case LOADING_INFO_BOX:
                alert_view_header.setText(header_text);
                alert_view_message.setText(message_text);
                alert_view_buttons.setVisibility(View.GONE);
                return view;
            default:
                return view;
        }
    }
}
