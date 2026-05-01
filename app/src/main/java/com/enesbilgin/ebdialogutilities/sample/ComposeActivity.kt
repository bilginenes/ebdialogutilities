/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enesbilgin.ebdialogutilities.compose.*

class ComposeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            
            EBDialogTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("EBDialog Compose") },
                            actions = {
                                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                    Text("Dark Mode", style = MaterialTheme.typography.labelMedium)
                                    Switch(
                                        checked = isDarkTheme,
                                        onCheckedChange = { isDarkTheme = it },
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ComposeSampleScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeSampleScreen() {
    val infoState = rememberEBDialogState()
    val confirmState = rememberEBDialogState()
    val neutralConfirmState = rememberEBDialogState()
    val customState = rememberEBDialogState()
    val listState = rememberEBDialogState()
    val longListState = rememberEBDialogState()
    val loadingState = rememberEBDialogState()
    val bottomSheetState = rememberEBDialogState()

    var loadingMessage by remember { mutableStateOf("Processing...") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(androidx.compose.foundation.rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Basic Dialogs", style = MaterialTheme.typography.titleMedium)

        Button(onClick = { infoState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Info Dialog (Icon)")
        }

        Button(onClick = { confirmState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm Dialog (Yes/No)")
        }

        Button(onClick = { neutralConfirmState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm with Neutral Button")
        }

        Text("Lists & Collections", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))

        Button(onClick = { listState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Simple List Dialog")
        }

        Button(onClick = { longListState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Long List (Scrolling)")
        }

        Text("Advanced & Overlays", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))

        Button(onClick = { customState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Custom Content (Slot API)")
        }

        Button(onClick = { loadingState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Loading with Dynamic Message")
        }

        Button(onClick = { bottomSheetState.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Modal Bottom Sheet")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }

    // --- Dialog Definitions ---

    EBInfoDialog(
        state = infoState,
        title = "Important Information",
        message = "This is how you show useful data to the user.",
        icon = Icons.Default.Info
    )

    EBConfirmDialog(
        state = confirmState,
        title = "Unsaved Changes",
        message = "You have unsaved changes. Do you want to discard them?",
        positiveText = "Discard",
        negativeText = "Keep Editing",
        onConfirm = { /* discard */ }
    )

    // Using DSL for Neutral Button
    EBDialog(state = neutralConfirmState) {
        Title(text = "App Update")
        Message(text = "A newer version is available. Would you like to update now?")
        Buttons(
            positiveText = "Update",
            onPositiveClick = { /* update */ },
            negativeText = "Later",
            neutralText = "Remind me"
        )
    }

    EBListDialog(
        state = listState,
        title = "Selection",
        items = listOf("Option A", "Option B", "Option C"),
        onItemSelected = { _, item -> println(item) }
    )

    EBListDialog(
        state = longListState,
        title = "Pick a City",
        items = listOf("New York", "London", "Tokyo", "Paris", "Berlin", "Sydney", "Rome", "Istanbul", "Moscow", "Beijing", "Dubai"),
        onItemSelected = { _, item -> println(item) }
    )

    EBCustomDialog(
        state = customState,
        title = "Preferences",
        content = {
            Column {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Switch(checked = true, onCheckedChange = {})
                    Text("Enable Notifications", modifier = Modifier.padding(start = 8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Switch(checked = false, onCheckedChange = {})
                    Text("Biometric Login", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    )

    EBLoadingDialog(
        state = loadingState,
        message = loadingMessage
    )

    EBBottomSheet(state = bottomSheetState) {
        Title(text = "Sheet Actions", icon = Icons.Default.Info)
        ListContent(
            items = listOf("Share with friends", "Add to favorites", "Report issue"),
            onItemSelected = { _, _ -> /* handle */ }
        )
    }
    
    // Dynamic loading message simulation
    if (loadingState.isVisible) {
        LaunchedEffect(Unit) {
            loadingMessage = "Initializing..."
            kotlinx.coroutines.delay(1000)
            loadingMessage = "Downloading Assets..."
            kotlinx.coroutines.delay(1500)
            loadingMessage = "Finalizing..."
            kotlinx.coroutines.delay(1000)
            loadingState.dismiss()
        }
    }
}
