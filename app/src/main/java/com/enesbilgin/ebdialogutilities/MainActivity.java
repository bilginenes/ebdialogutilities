/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.enesbilgin.ebdialogutilities.Constants.DialogTheme;
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener;
import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener;
import com.enesbilgin.ebdialogutilities.Utilities.EBDialogUtilities;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EBDialogUtilities.initialize(MainActivity.this,DialogTheme.DARK_1);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4= findViewById(R.id.button4);
        button5= findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBDialogUtilities.showInfoBox(MainActivity.this,"This is an information message.");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBDialogUtilities.showNotSupportedInfoBox(MainActivity.this);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBDialogUtilities.showErrorBox(MainActivity.this,"This is a error message.");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBDialogUtilities.showConfirmBox(MainActivity.this, "Confirm Process", "Do you want to proceed the operation?", new CompletionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this,"onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this,"onCancel", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBDialogUtilities.showVoteBox(MainActivity.this, getString(R.string.app_name), new VoteChoiceListener() {
                    @Override
                    public void onRedirect() {
                        Toast.makeText(MainActivity.this,"onRedirect", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this,"onCancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLater() {
                        Toast.makeText(MainActivity.this,"onLater", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
