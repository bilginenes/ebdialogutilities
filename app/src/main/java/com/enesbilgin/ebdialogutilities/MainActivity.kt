/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enesbilgin.ebdialogutilities.Constants.ButtonsSequence
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme
import com.enesbilgin.ebdialogutilities.Constants.VerticalButtonsGravity
import com.enesbilgin.ebdialogutilities.Interfaces.CompletionListener
import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener
import com.enesbilgin.ebdialogutilities.Interfaces.VoteChoiceListener
import com.enesbilgin.ebdialogutilities.Models.EBButtonModel
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel
import com.enesbilgin.ebdialogutilities.Utilities.EBDialogUtilities
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBDialogUtilities.initialize(this@MainActivity, DialogTheme.LIGHT_1)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)

        button1.setOnClickListener {
            EBDialogUtilities.showInfoBox(this@MainActivity, "This is an information message.")
        }

        button2.setOnClickListener {
            EBDialogUtilities.showNotSupportedInfoBox(this@MainActivity)
        }

        button3.setOnClickListener {
            EBDialogUtilities.showErrorBox(this@MainActivity, "This is a error message.")
        }

        button4.setOnClickListener {
            EBDialogUtilities.showConfirmBox(this@MainActivity, "Confirm Process", "Do you want to proceed the operation?", object : CompletionListener {
                override fun onSuccess() {
                    Toast.makeText(this@MainActivity, "onSuccess", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(this@MainActivity, "onCancel", Toast.LENGTH_SHORT).show()
                }
            })
        }

        button5.setOnClickListener {
            EBDialogUtilities.showVoteBox(this@MainActivity, getString(R.string.app_name_eb), object : VoteChoiceListener {
                override fun onRedirect() {
                    Toast.makeText(this@MainActivity, "onRedirect", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(this@MainActivity, "onCancel", Toast.LENGTH_SHORT).show()
                }

                override fun onLater() {
                    Toast.makeText(this@MainActivity, "onLater", Toast.LENGTH_SHORT).show()
                }
            })
        }

        button6.setOnClickListener {
            val buttonModels = ArrayList<EBButtonModel>()
            buttonModels.add(EBButtonModel("Button1", object : SingleEventListener {
                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "Clicked.", Toast.LENGTH_SHORT).show()
                }
            }, R.color.md_blue_700))
            buttonModels.add(EBButtonModel("Button2", object : SingleEventListener {
                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "Clicked.", Toast.LENGTH_SHORT).show()
                }
            }, R.color.md_red_700))
            buttonModels.add(EBButtonModel("Button3", object : SingleEventListener {
                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "Clicked.", Toast.LENGTH_SHORT).show()
                }
            }, R.color.md_green_700))
            val dialogModel = EBCustomDialogModel("Choose one", null, buttonModels, ButtonsSequence.VERTICAL, VerticalButtonsGravity.LEFT)
            EBDialogUtilities.showCustomDialog(this@MainActivity, dialogModel)
        }
    }
}