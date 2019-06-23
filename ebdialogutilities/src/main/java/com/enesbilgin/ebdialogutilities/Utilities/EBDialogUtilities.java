package com.enesbilgin.ebdialogutilities.Utilities;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.enesbilgin.ebdialogutilities.Constants.AlertViewType;
import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence;
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity;
import com.enesbilgin.ebdialogutilities.Fragments.EBCustomDialog;
import com.enesbilgin.ebdialogutilities.Fragments.EBLoadingDialog;
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener;
import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener;
import com.enesbilgin.ebdialogutilities.Models.EBButtonModel;
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel;
import com.enesbilgin.ebdialogutilities.R;

import java.util.ArrayList;

/**
 * Copyright 2019
 * Enes Bilgin
 */
public class EBDialogUtilities {

    public static void initialize(Context context, DialogTheme dialogTheme) {
        StyleUtilities.setDialogTheme(context, dialogTheme);
    }
    /**Info**/
    public static void showInfoBox(Context context, String header_text, String message_text, final SingleEventListener singleEventListener) {
        ArrayList<EBButtonModel> buttonModels = new ArrayList<>();

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_ok_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(singleEventListener!=null)
                    singleEventListener.onComplete();
            }
        }, R.color.md_light_blue_800));

        EBCustomDialogModel dialogModel = new EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.VERTICAL, VerticalButtonsGravity.CENTER);
        showCustomDialog(context, dialogModel);
    }

    public static void showInfoBox(Context context,String header_text, String message_text) {
       showInfoBox(context, header_text, message_text, null);
    }

    public static void showInfoBox(Context context, String message_text) {
        String header_text = context.getString(R.string.eb_information);
        showInfoBox(context, header_text, message_text);
    }

    public static void showInfoBox(Context context, String message_text, SingleEventListener singleEventListener) {
        String header_text = context.getString(R.string.eb_information);
        showInfoBox(context, header_text, message_text, singleEventListener);
    }

    public static void showNotSupportedInfoBox(Context context) {
        showInfoBox(context, context.getString(R.string.eb_not_supported_annotation));
    }

    /**Error**/
    public static void showErrorBox(Context context, String header_text, String message_text, final SingleEventListener singleEventListener) {
        ArrayList<EBButtonModel> buttonModels = new ArrayList<>();

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_ok_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(singleEventListener!=null)
                    singleEventListener.onComplete();
            }
        }, R.color.md_light_blue_800));

        EBCustomDialogModel dialogModel = new EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.VERTICAL, VerticalButtonsGravity.CENTER);
        dialogModel.setHeaderTextColor(R.color.md_red_700);
        showCustomDialog(context, dialogModel);
    }

    public static void showErrorBox(Context context,String header_text, String message_text) {
        showErrorBox(context, header_text, message_text, null);
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
    public static void showConfirmBox(Context context, String header_text, String message_text, final CompletionListener completionListener) {

        ArrayList<EBButtonModel> buttonModels = new ArrayList<>();

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_no_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(completionListener!=null)
                    completionListener.onCancel();
            }
        }, R.color.md_red_900));

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_yes_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(completionListener!=null)
                    completionListener.onSuccess();
            }
        }, R.color.md_green_600));

        EBCustomDialogModel dialogModel = new EBCustomDialogModel(header_text, message_text, buttonModels, ButtonsSequence.HORIZONTAL);
        showCustomDialog(context, dialogModel);
    }

    public static void showVoteBox(Context context, String app_name, final VoteChoiceListener voteChoiceListener) {
        String title;

        if(app_name==null)
            title = context.getString(R.string.eb_vote_header);
        else title = String.format(context.getString(R.string.eb_vote_header_custom), app_name);

        ArrayList<EBButtonModel> buttonModels = new ArrayList<>();

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_later_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(voteChoiceListener!=null)
                    voteChoiceListener.onLater();
            }
        }, R.color.md_light_blue_800));

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_no_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(voteChoiceListener!=null)
                    voteChoiceListener.onCancel();
            }
        }, R.color.md_red_900));

        buttonModels.add(new EBButtonModel(context.getString(R.string.eb_yes_big), new SingleEventListener() {
            @Override
            public void onComplete() {
                if(voteChoiceListener!=null)
                    voteChoiceListener.onRedirect();
            }
        }, R.color.md_green_600));

        EBCustomDialogModel dialogModel = new EBCustomDialogModel(title, context.getString(R.string.eb_vote_message), buttonModels, ButtonsSequence.HORIZONTAL);
        showCustomDialog(context, dialogModel);
    }

    /**Loading**/
    public static EBLoadingDialog showLoadingBox(Context context, Integer themeColor) {
        EBLoadingDialog dialog = EBLoadingDialog.newInstance(themeColor);
        show(dialog, context);
        return dialog;
    }

    public static EBLoadingDialog showLoadingBox(Context context) {
        return showLoadingBox(context, null);
    }

    /**Custom Dialog**/
    public static void showCustomDialog(Context context, EBCustomDialogModel dialogModel) {
        EBCustomDialog customDialog = EBCustomDialog.newInstance(dialogModel);
        show(customDialog, context);
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
