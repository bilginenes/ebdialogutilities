package com.enesbilgin.ebdialogutilities.Utilities;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.enesbilgin.ebdialogutilities.Fragments.EBAlertViewDialog;
import com.enesbilgin.ebdialogutilities.Fragments.VoteAppDialog;
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.R;
/**
 * Copyright 2019
 * Enes Bilgin
 */
public class EBDialogUtilities {

    public static void showConfirmBox(Context context, String header_text, String message_text, CompletionListener completionListener) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(EBAlertViewDialog.AlertViewType.CONFIRM_BOX,header_text,message_text,completionListener);
        show(dialog, context);
    }

    public static void showInfoBox(Context context,String header_text, String message_text) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(EBAlertViewDialog.AlertViewType.INFO_BOX,header_text,message_text);
        show(dialog, context);
    }

    public static void showNotSupportedInfoBox(Context context) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(EBAlertViewDialog.AlertViewType.INFO_BOX,context.getString(R.string.information),context.getString(R.string.not_supported_annotation));
        show(dialog, context);
    }

    public static EBAlertViewDialog showLoadingBox(Context context) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(EBAlertViewDialog.AlertViewType.LOADING_BOX);
        show(dialog, context);
        return dialog;
    }

    public static void showErrorBox(Context context,String header_text, String message_text) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(EBAlertViewDialog.AlertViewType.ERROR_BOX,header_text,message_text);
        show(dialog, context);
    }

    public static void showVoteBox(Context context) {
        VoteAppDialog voteAppDialog = VoteAppDialog.newInstance();
        show(voteAppDialog, context);
    }

    public static void show(DialogFragment dialog, Context context)
    {
        if(context instanceof FragmentActivity) {
            FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
            dialog.show(fm, "dialog");
        }else {
            Toast.makeText(context,R.string.debug_notation,Toast.LENGTH_SHORT).show();
            //Crashlytics.log(Log.ERROR, "DialogUtilities", "context is not instance of FragmentActivity");
        }
    }

}
