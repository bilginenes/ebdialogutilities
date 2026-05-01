/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enesbilgin.ebdialogutilities.Constants.DialogTheme
import com.enesbilgin.ebdialogutilities.Models.EBCustomDialogModel
import com.enesbilgin.ebdialogutilities.Utilities.EBDialogUtilities
import com.enesbilgin.ebdialogutilities.Utilities.buttons
import com.enesbilgin.ebdialogutilities.Utilities.positive
import com.enesbilgin.ebdialogutilities.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "EBDialog Utilities"

        EBDialogUtilities.initialize(this, DialogTheme.LIGHT_1)

        binding.button1.setOnClickListener {
            EBDialogUtilities.showInfoBox(this, "This is a modernized info message.")
        }

        binding.button2.setOnClickListener {
            // Show custom layout in legacy View-based dialog
            val dialogModel = EBCustomDialogModel(
                headerText = "Custom Layout (Legacy)",
                messageText = "This dialog hosts a custom XML layout.",
                customViewLayoutRes = com.enesbilgin.ebdialogutilities.R.layout.eb_dialog_list_item // reusing an existing layout for demo
            )
            EBDialogUtilities.showCustomDialog(this, dialogModel)
        }

        binding.button3.setOnClickListener {
            EBDialogUtilities.showErrorBox(this, "Something went wrong!")
        }

        binding.button4.setOnClickListener {
            val options = listOf("Option 1", "Option 2", "Option 3")
            EBDialogUtilities.showListBox(this, "Select an Option", options) { index, item ->
                Toast.makeText(this, "Selected: $item at index $index", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button5.setOnClickListener {
            val loader = EBDialogUtilities.showLoadingBox(this, message = "Modern Loading...")
            binding.root.postDelayed({ loader.dismiss() }, 2000)
        }

        binding.button6.setOnClickListener {
            EBDialogUtilities.build(this) {
                headerText = "DSL Builder"
                messageText = "Created using the new DSL API!"
                buttons {
                    positive("Awesome") {
                        Toast.makeText(this@MainActivity, "Positive clicked!", Toast.LENGTH_SHORT).show()
                    }
                }
            }.show(supportFragmentManager, "dsl_dialog")
        }

        binding.buttonCompose.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }
    }
}
