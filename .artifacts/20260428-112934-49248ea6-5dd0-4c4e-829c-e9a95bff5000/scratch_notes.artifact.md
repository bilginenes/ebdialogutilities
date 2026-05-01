# Scratch Notes - EBDialogUtilities Refactoring

## New DSL API Idea

```kotlin
EBDialog.build(context) {
    title = "Hello"
    message = "This is a message"
    theme = DialogTheme.DARK_1
    buttons {
        positive("OK") { /* click action */ }
        negative("Cancel") { /* click action */ }
    }
}.show()
```

## Input Dialog

```kotlin
EBDialog.buildInput(context) {
    title = "Name"
    hint = "Enter your name"
    onInputConfirmed { text ->
        println(text)
    }
}.show()
```

## List Dialog

```kotlin
EBDialog.buildList(context) {
    title = "Options"
    items = listOf("Red", "Green", "Blue")
    onItemSelected { index, item ->
        println("Selected $item at $index")
    }
}.show()
```

## Loading with message

```kotlin
val loader = EBDialogUtilities.showLoadingBox(context, message = "Processing...")
// later
loader.dismiss()
```

## Internal improvements
- Use `requireContext()` instead of `context!!`.
- Use `args` for `DialogFragment` instead of setting fields directly (better for state restoration).
- Use `Lifecycle` awareness if needed.
