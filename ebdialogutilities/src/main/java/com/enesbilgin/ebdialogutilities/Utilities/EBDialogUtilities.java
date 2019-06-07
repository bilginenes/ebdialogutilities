package com.enesbilgin.ebdialogutilities.Utilities;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.enesbilgin.ebdialogutilities.Constants.AlertViewType;
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.Fragments.EBAlertViewDialog;
import com.enesbilgin.ebdialogutilities.Fragments.VoteAppDialog;
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener;
import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener;
import com.enesbilgin.ebdialogutilities.R;
/**
 * Copyright 2019
 * Enes Bilgin
 */
public class EBDialogUtilities {

    public static void initialize(Context context, DialogTheme dialogTheme) {
        StyleUtilities.setDialogTheme(context, dialogTheme);
    }
    /**Info**/
    public static void showInfoBox(Context context, String header_text, String message_text, SingleEventListener singleEventListener) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.INFO_BOX, header_text, message_text, singleEventListener);
        show(dialog, context);
    }

    public static void showInfoBox(Context context,String header_text, String message_text) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.INFO_BOX, header_text, message_text);
        show(dialog, context);
    }

    public static void showInfoBox(Context context, String message_text) {
        String header_text = context.getString(R.string.eb_information);
        showInfoBox(context, header_text, message_text);
    }

    public static void showInfoBox(Context context, String message_text, SingleEventListener singleEventListener) {
        String header_text = context.getString(R.string.eb_information);
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.INFO_BOX, header_text, message_text, singleEventListener);
        show(dialog, context);
    }

    public static void showNotSupportedInfoBox(Context context) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.INFO_BOX,context.getString(R.string.eb_information),context.getString(R.string.eb_not_supported_annotation));
        show(dialog, context);
    }

    /**Error**/
    public static void showErrorBox(Context context,String header_text, String message_text, SingleEventListener singleEventListener) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.ERROR_BOX,header_text,message_text, singleEventListener);
        show(dialog, context);
    }

    public static void showErrorBox(Context context,String header_text, String message_text) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.ERROR_BOX,header_text,message_text);
        show(dialog, context);
    }

    public static void showErrorBox(Context context, String message_text) {
        String header_text = context.getString(R.string.eb_error);
        showErrorBox(context, header_text, message_text);
    }

    public static void showErrorBox(Context context, String message_text, SingleEventListener singleEventListener) {
        String header_text = context.getString(R.string.eb_error);
        showErrorBox(context, header_text, message_text, singleEventListener);
    }

    /**Confirm**/
    public static void showConfirmBox(Context context, String header_text, String message_text, CompletionListener completionListener) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.CONFIRM_BOX,header_text,message_text,completionListener);
        show(dialog, context);
    }

    public static void showVoteBox(Context context,String app_name, VoteChoiceListener voteChoiceListener) {
        VoteAppDialog voteAppDialog = VoteAppDialog.newInstance(app_name, voteChoiceListener);
        show(voteAppDialog, context);
    }

    public static void showVoteBox(Context context, VoteChoiceListener voteChoiceListener) {
        VoteAppDialog voteAppDialog = VoteAppDialog.newInstance(voteChoiceListener);
        show(voteAppDialog, context);
    }

    /**Loading**/
    public static EBAlertViewDialog showLoadingBox(Context context) {
        EBAlertViewDialog dialog = EBAlertViewDialog.newInstance(AlertViewType.LOADING_BOX);
        show(dialog, context);
        return dialog;
    }

    /**Common Show**/
    public static void show(DialogFragment dialog, Context context)
    {
        if(context instanceof FragmentActivity) {
            FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
            dialog.show(fm, "dialog");
        }else {
            Toast.makeText(context,R.string.eb_debug_notation,Toast.LENGTH_SHORT).show();
        }
    }

}
