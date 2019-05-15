package com.enesbilgin.ebdialogutilities.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener;
import com.enesbilgin.ebdialogutilities.R;
/**
 * Copyright 2019
 * Enes Bilgin
 */
public class VoteAppDialog extends DialogFragment {

    public static VoteAppDialog newInstance(String app_name, VoteChoiceListener voteChoiceListener){
        VoteAppDialog voteAppDialog = new VoteAppDialog();
        voteAppDialog.app_name = app_name;
        voteAppDialog.voteChoiceListener = voteChoiceListener;
        return voteAppDialog;
    }

    public static VoteAppDialog newInstance(VoteChoiceListener voteChoiceListener){
        VoteAppDialog voteAppDialog = new VoteAppDialog();
        voteAppDialog.voteChoiceListener = voteChoiceListener;
        return voteAppDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private VoteChoiceListener voteChoiceListener;
    private String app_name;

    private TextView alert_view_header, alert_view_message;
    private Button alert_view_refuse, alert_view_confirm, alert_view_okay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        View view = inflater.inflate(R.layout.dialog_dark_1_alert_view, container, false);

        alert_view_header = view.findViewById(R.id.alert_view_header);
        alert_view_message = view.findViewById(R.id.alert_view_message);

        alert_view_refuse = view.findViewById(R.id.alert_view_refuse);
        alert_view_confirm = view.findViewById(R.id.alert_view_confirm);
        alert_view_okay = view.findViewById(R.id.alert_view_okay);

        if(app_name==null) {
            alert_view_header.setText(R.string.vote_header);
        } else {
            String custom_header = String.format(getContext().getString(R.string.vote_header_custom), app_name);
            alert_view_header.setText(custom_header);
        }

        alert_view_message.setText(R.string.vote_message);

        if(voteChoiceListener==null)
            return null;

        alert_view_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                voteChoiceListener.onCancel();
            }
        });

        alert_view_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                voteChoiceListener.onRedirect();
            }
        });

        alert_view_okay.setText(R.string.later);
        alert_view_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                voteChoiceListener.onLater();
            }
        });

        return view;
    }
}
