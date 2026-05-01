/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun EBDialog(
    state: EBDialogState,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable EBDialogScope.() -> Unit
) {
    if (state.isVisible) {
        Dialog(
            onDismissRequest = { state.dismiss() },
            properties = properties
        ) {
            EBDialogTheme {
                Surface(
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    tonalElevation = 6.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        val scope = EBDialogScope(state)
                        scope.content()
                    }
                }
            }
        }
    }
}

@Composable
fun EBInfoDialog(
    state: EBDialogState,
    title: String,
    message: String,
    buttonText: String = "OK",
    icon: ImageVector? = null,
    onConfirm: () -> Unit = {}
) {
    EBDialog(state = state) {
        Title(text = title, icon = icon)
        Message(text = message)
        Buttons(
            positiveText = buttonText,
            onPositiveClick = onConfirm
        )
    }
}

@Composable
fun EBConfirmDialog(
    state: EBDialogState,
    title: String,
    message: String,
    positiveText: String = "Yes",
    negativeText: String = "No",
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {}
) {
    EBDialog(state = state) {
        Title(text = title)
        Message(text = message)
        Buttons(
            positiveText = positiveText,
            onPositiveClick = onConfirm,
            negativeText = negativeText,
            onNegativeClick = onCancel
        )
    }
}

@Composable
fun EBCustomDialog(
    state: EBDialogState,
    title: String,
    content: @Composable () -> Unit,
    confirmText: String = "OK",
    onConfirm: () -> Unit = {}
) {
    EBDialog(state = state) {
        Title(text = title)
        CustomContent(content = content)
        Buttons(
            positiveText = confirmText,
            onPositiveClick = onConfirm
        )
    }
}

@Composable
fun EBListDialog(
    state: EBDialogState,
    title: String,
    items: List<String>,
    onItemSelected: (Int, String) -> Unit
) {
    EBDialog(state = state) {
        Title(text = title)
        ListContent(items = items, onItemSelected = onItemSelected)
    }
}

@Composable
fun EBLoadingDialog(
    state: EBDialogState,
    message: String? = null
) {
    EBDialog(state = state, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
        Loading(message = message)
    }
}

class EBDialogScope(private val state: EBDialogState) {
    fun dismiss() = state.dismiss()
}
