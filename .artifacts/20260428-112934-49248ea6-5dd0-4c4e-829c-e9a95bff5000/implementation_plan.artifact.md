# Implementation Plan - Refactor and Extend EBDialogUtilities

This plan outlines the refactoring of the `EBDialogUtilities` library to modernize the codebase, improve quality, and extend its functionality with new dialog types and a more flexible API.

## User Review Required

> [!IMPORTANT]
> - The refactoring will include renaming some fields (e.g., `header_text` to `headerText`) which might be a breaking change for existing users if they access these fields directly. However, since they are mostly used through the utility class, the impact might be minimal.
> - I will introduce a DSL-like API for building dialogs, which is more idiomatic in Kotlin.

## Proposed Changes

### Library Core & Modernization

#### [ebdialogutilities/build.gradle](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/build.gradle)

- Update `appcompat`, `kotlin-stdlib`, and test dependencies to latest stable versions.
- Enable `viewBinding`.
- Add `androidx.core:core-ktx`.

#### [EBCustomDialogModel.kt](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/java/com/enesbilgin/ebdialogutilities/Models/EBCustomDialogModel.kt)

- Refactor to use Kotlin default parameters and property naming conventions.
- Add support for custom layout resources and input/list data.

#### [EBButtonModel.kt](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/java/com/enesbilgin/ebdialogutilities/Models/EBButtonModel.kt)

- Clean up and modernize.

#### [EBCustomDialog.kt](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/java/com/enesbilgin/ebdialogutilities/Fragments/EBCustomDialog.kt)

- Migrate to `ViewBinding`.
- Implement support for new dialog types (Input, List, Custom View).

#### [EBLoadingDialog.kt](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/java/com/enesbilgin/ebdialogutilities/Fragments/EBLoadingDialog.kt)

- Migrate to `ViewBinding`.
- Add support for displaying a message.

#### [EBDialogUtilities.kt](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/java/com/enesbilgin/ebdialogutilities/Utilities/EBDialogUtilities.kt)

- Modernize existing methods.
- Add new methods for Input, List, and Custom View dialogs.
- Introduce a Builder/DSL for dialog creation.

---

### UI & Layouts

#### [dialog_light_custom.xml](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/res/layout/dialog_light_custom.xml) and [dialog_dark_custom.xml](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/res/layout/dialog_dark_custom.xml)

- Add a `FrameLayout` container for custom views or dynamic content (like lists or input fields).

#### [dialog_loading_view.xml](file:///C:/AndroidSpace/EBDialogUtilities/ebdialogutilities/src/main/res/layout/dialog_loading_view.xml)

- Add a `TextView` for the loading message.

---

### Documentation & Sample

#### [MainActivity.kt](file:///C:/AndroidSpace/EBDialogUtilities/app/src/main/java/com/enesbilgin/ebdialogutilities/MainActivity.kt)

- Update to showcase new features and the new API.

#### [README.md](file:///C:/AndroidSpace/EBDialogUtilities/README.md)

- Comprehensive update of the documentation with new examples and API descriptions.

## Verification Plan

### Automated Tests
- I will attempt to run existing tests if any (though they seem to be basic).
- Command: `./gradlew :ebdialogutilities:test`

### Manual Verification
- Deploy the sample app to a device/emulator.
- Verify all dialog types: Info, Error, Confirm, Vote, Loading (with message), Input, List, and Custom View.
- Verify theme switching (Light/Dark).
- Verify animations.
