/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EBBottomSheet(
    state: EBDialogState,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable EBDialogScope.() -> Unit
) {
    if (state.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { state.dismiss() },
            sheetState = sheetState
        ) {
            EBDialogTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    val scope = EBDialogScope(state)
                    scope.content()
                }
            }
        }
    }
}
