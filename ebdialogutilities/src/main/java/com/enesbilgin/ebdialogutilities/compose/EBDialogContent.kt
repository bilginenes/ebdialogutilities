/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EBDialogScope.Title(
    text: String,
    icon: ImageVector? = null,
    iconColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun EBDialogScope.Message(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@Composable
fun EBDialogScope.Buttons(
    positiveText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    negativeText: String? = null,
    onNegativeClick: (() -> Unit)? = null,
    neutralText: String? = null,
    onNeutralClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        if (neutralText != null) {
            TextButton(onClick = { 
                onNeutralClick?.invoke()
                dismiss()
            }) {
                Text(neutralText)
            }
        }
        if (negativeText != null) {
            TextButton(onClick = { 
                onNegativeClick?.invoke()
                dismiss()
            }) {
                Text(negativeText)
            }
        }
        if (positiveText != null) {
            Button(
                onClick = { 
                    onPositiveClick?.invoke()
                    dismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(positiveText)
            }
        }
    }
}

@Composable
fun EBDialogScope.CustomContent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        content()
    }
}

@Composable
fun EBDialogScope.ListContent(
    items: List<String>,
    onItemSelected: (Int, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
    ) {
        itemsIndexed(items) { index, item ->
            ListItem(
                headlineContent = { Text(item) },
                modifier = Modifier.clickable {
                    onItemSelected(index, item)
                    dismiss()
                }
            )
        }
    }
}

@Composable
fun EBDialogScope.Loading(
    message: String? = null,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = color)
        if (message != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
