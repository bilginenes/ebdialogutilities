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

import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.R;

public class EBAlertViewDialog extends DialogFragment {
    private AlertViewType type;
    private String header_text = null;
    private String message_text = null;
    private CompletionListener completionListener = null;

    public enum AlertViewType {
        CONFIRM_BOX, //Header, Message, Confirm Listener, Refuse Listener
        INFO_BOX, //Header, Message
        ERROR_BOX, //Header, Message
        LOADING_BOX, //Nothing
        LOADING_INFO_BOX, //Header, Message
    }

    //CONFIRM_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text, CompletionListener completionListener){
        EBAlertViewDialog dialog = new EBAlertViewDialog();
        dialog.type = type;
        dialog.header_text = header_text;
        dialog.message_text = message_text;
        dialog.completionListener = completionListener;
        return dialog;
    }

    //INFO_BOX ERROR_BOX LOADING_INFO_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type, String header_text, String message_text){
        return newInstance(type,header_text,message_text,null);
    }

    //LOADING_BOX
    public static EBAlertViewDialog newInstance(AlertViewType type){
        return newInstance(type,null,null,null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        /*if(savedInstanceState!=null) {
            type = (AlertViewType) savedInstanceState.get("type");
            header_text = savedInstanceState.getString("header_text");
            message_text = savedInstanceState.getString("message_text");
            completionListener = (CompletionListener) savedInstanceState.get("completionListener");
        }*/

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        if(type==AlertViewType.LOADING_BOX)
            return inflater.inflate(R.layout.dialog_loading_view, container, false);

        View view = inflater.inflate(R.layout.dialog_alert_view, container, false);

        TextView alert_view_header = view.findViewById(R.id.alert_view_header);
        TextView alert_view_message = view.findViewById(R.id.alert_view_message);

        LinearLayout alert_view_buttons = view.findViewById(R.id.alert_view_buttons);
        Button alert_view_refuse = view.findViewById(R.id.alert_view_refuse);
        Button alert_view_confirm = view.findViewById(R.id.alert_view_confirm);
        Button alert_view_confirm2 = view.findViewById(R.id.alert_view_confirm2);

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
                alert_view_confirm2.setVisibility(View.GONE);
                return view;
            case INFO_BOX:
            case ERROR_BOX:
                alert_view_header.setText(header_text);
                alert_view_message.setText(message_text);

                alert_view_confirm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EBAlertViewDialog.this.dismiss();
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

    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("type", type);
        outState.putString("header_text", header_text);
        outState.putString("message_text", message_text);
        outState.putSerializable("completionListener", completionListener);
    }*/
}
