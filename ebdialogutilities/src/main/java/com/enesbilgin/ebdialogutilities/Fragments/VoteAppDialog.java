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

import com.enesbilgin.ebdialogutilities.R;

public class VoteAppDialog extends DialogFragment {

    public static VoteAppDialog newInstance(){
        return new VoteAppDialog();
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

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnims;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        View view = inflater.inflate(R.layout.dialog_alert_view, container, false);

        TextView alert_view_header = view.findViewById(R.id.alert_view_header);
        TextView alert_view_message = view.findViewById(R.id.alert_view_message);

        //LinearLayout alert_view_buttons = view.findViewById(R.id.alert_view_buttons);
        Button alert_view_refuse = view.findViewById(R.id.alert_view_refuse);
        Button alert_view_confirm = view.findViewById(R.id.alert_view_confirm);
        Button alert_view_confirm2 = view.findViewById(R.id.alert_view_confirm2);

        alert_view_header.setText(R.string.vote_header);
        alert_view_message.setText(R.string.vote_message);

        /*alert_view_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                FirebaseUtilities.setVoted(getContext(), FirebaseUtilities.FIELD_VOTED_CANCELED);
            }
        });

        alert_view_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //Go to Google Play Store to vote.
                FirebaseUtilities.setVoted(getContext(), FirebaseUtilities.FIELD_VOTED_REDIRECTED);
                ActionUtilities.redirectToGooglePlayStore(getContext());
            }
        });

        alert_view_confirm2.setText(R.string.later);
        alert_view_confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                FirebaseUtilities.setVoted(getContext(), FirebaseUtilities.FIELD_VOTED_LATER);
            }
        });*/

        return view;
    }
}
