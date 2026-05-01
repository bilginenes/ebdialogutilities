# Implementation Plan - EBDialog Compose Migration & Library Design

This plan covers the conversion of the existing library to **Jetpack Compose** and the design of a comprehensive dialog library.

## Library Vision: "EBDialog Compose"

A comprehensive Compose dialog library should be **Declarative**, **State-Driven**, and **Highly Customizable**.

### Proposed Feature List (The "Comprehensive" Set)

1.  **Core Dialog Types**:
    *   `InfoDialog`: Simple icon + title + message.
    *   `ConfirmDialog`: Positive/Negative/Neutral actions.
    *   `InputDialog`: TextField with validation support.
    *   `ListDialog`: Single or Multiple selection lists.
    *   `LoadingDialog`: Circular/Horizontal indicators with text.
    *   `ProgressDialog`: Percentage-based progress tracking.
2.  **Advanced Layouts**:
    *   `BottomSheets`: Full Compose bottom sheet integration.
    *   `CustomContentDialog`: Slot API (`content: @Composable () -> Unit`) to host any UI.
3.  **UI/UX Features**:
    *   **Theming**: Seamless Material3 integration + custom EBTheme.
    *   **Animations**: Built-in transitions (Slide, Fade, Zoom).
    *   **Adaptive Design**: Proper sizing for phones, foldables, and tablets.
    *   **Accessibility**: Full TalkBack and screen reader support.
4.  **Developer Experience (DX)**:
    *   **State Management**: Simple `rememberDialogState()` hook.
    *   **Chainable Builder**: A DSL for complex dialog logic.
    *   **Previews**: Multipreview support for light/dark/large fonts.

## Proposed Architecture

```kotlin
// Example usage of the vision
@Composable
fun MyScreen() {
    val dialogState = rememberEBDialogState()

    EBDialog(state = dialogState) {
        title("Update Available")
        message("A new version is ready for download.")
        icon(Icons.Default.Update)
        positiveButton("Download") { /* start download */ }
        negativeButton("Later")
    }

    Button(onClick = { dialogState.show() }) {
        Text("Check Update")
    }
}
```

## Immediate Step: Conversion Strategy

### 1. Library Preparation
*   Update `ebdialogutilities/build.gradle` to include Compose dependencies.
*   Enable `compose = true`.

### 2. Implementation Phase
*   **[NEW]** `EBDialogTheme.kt`: Define color palettes and shapes.
*   **[NEW]** `EBBaseDialog.kt`: The core wrapper using `androidx.compose.ui.window.Dialog`.
*   **[NEW]** `EBDialogTypes.kt`: Individual composables for Info, Error, Input, etc.

---

## Ask the User (Feature Confirmation)

> [!IMPORTANT]
> **Which features should I prioritize first for the development of EBDialog Compose?**
> 1. **Material3 Design**: Should we strictly follow M3 or keep the unique EB style?
> 2. **Navigation Integration**: Do you want the dialogs to be state-based (inside composables) or integrated with `Compose Navigation`?
> 3. **Input Validation**: Should `InputDialog` have built-in validation (e.g., regex, non-empty)?
> 4. **List Type**: Standard list or Radio/Checkbox selection?
