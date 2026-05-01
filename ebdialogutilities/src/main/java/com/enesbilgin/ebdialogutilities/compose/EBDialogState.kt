/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class EBDialogState {
    var isVisible by mutableStateOf(false)
        private set

    fun show() {
        isVisible = true
    }

    fun dismiss() {
        isVisible = false
    }
}

@Composable
fun rememberEBDialogState(): EBDialogState {
    return remember { EBDialogState() }
}
