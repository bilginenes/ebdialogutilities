# EB Dialog Utilities

[![](https://jitpack.io/v/bilginenes/ebdialogutilities.svg)](https://jitpack.io/#bilginenes/ebdialogutilities)

EB Dialog is a modern, lightweight Android library for easy dialog management. It offers solutions for Information, Error, Confirmation, Input, and List dialogs with a fluent Kotlin API.

## Features

- ✅ **Modern Kotlin API**: Clean and idiomatic DSL/Builder support.
- ✅ **ViewBinding**: Safe and efficient UI management.
- ✅ **Customization**: Support for Light/Dark themes and custom layouts.
- ✅ **Variety of Dialogs**:
  - Information & Error Boxes
  - Confirmation Dialogs
  - Input Dialogs (with text feedback)
  - List Dialogs (for quick selection)
  - Enhanced Loading Dialogs (with message support)
  - Google Play Vote Dialogs

## Installation

Add JitPack to your `settings.gradle` or root `build.gradle`:
```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your module `build.gradle`:
```kotlin
dependencies {
    implementation("com.github.bilginenes:ebdialogutilities:1.0.0")
}
```

## Usage

### 1. Initialization
Initialize the library with your preferred theme (usually in `Application.onCreate` or `Activity.onCreate`):
```kotlin
EBDialogUtilities.initialize(context, DialogTheme.LIGHT_1)
```

### 2. Information & Error Dialogs
```kotlin
// Simple Info
EBDialogUtilities.showInfoBox(activity, "Information", "This is an informative message.")

// Simple Error
EBDialogUtilities.showErrorBox(activity, "Oops!", "Something went wrong.")
```

### 3. Input Dialog
Easily collect user input:
```kotlin
EBDialogUtilities.showInputBox(activity, "Your Name", hint = "Enter name here") { name ->
    println("User entered: $name")
}
```

### 4. List Dialog
Quick selection from a list of options:
```kotlin
val options = listOf("Option A", "Option B", "Option C")
EBDialogUtilities.showListBox(activity, "Select One", options) { index, item ->
    println("Selected $item at position $index")
}
```

### 5. Loading Dialog (with Message)
```kotlin
val loader = EBDialogUtilities.showLoadingBox(activity, message = "Processing your request...")
// Dismiss when done
loader.dismiss()
```

### 6. DSL Builder (Advanced)
Use the powerful DSL for complex dialog configurations:
```kotlin
EBDialogUtilities.build(activity) {
    headerText = "Custom DSL"
    messageText = "Fully configured via Kotlin DSL"
    buttons {
        positive("Accept", color = R.color.md_green_700) {
            // positive action
        }
        negative("Decline") {
            // negative action
        }
    }
}.show(supportFragmentManager, "custom_dialog")
```

## License

This library is under MIT License.
