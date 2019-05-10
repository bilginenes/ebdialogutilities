EB Dialog Utilities

[![](https://jitpack.io/v/bilginenes/ebdialogutilities.svg)](https://jitpack.io/#bilginenes/ebdialogutilities)

EB Dialog is a Pop-up Dailog library that offers solutions for general Info, Error, Confirm cases.

### Import Gradle

Add it in your root build.gradle at the end of repositories:
```dart
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
 Add the dependency:
```dart
dependencies {
	        implementation 'com.github.bilginenes:ebdialogutilities:Tag'
	}
```
### How To Use
Information Dialog
```dart
EBDialogUtilities.showInfoBox(MainActivity.this,"This is an information message.");
```
 <img src="https://raw.githubusercontent.com/bilginenes/ebdialogutilities/master/readme/info.png" width="240px">

Error Dialog
 ```dart
EBDialogUtilities.showErrorBox(MainActivity.this,"This is a error message.");
```
<img src="https://raw.githubusercontent.com/bilginenes/ebdialogutilities/master/readme/err.png" width="240px">

Confirmation Dialog
 ```dart
EBDialogUtilities.showConfirmBox(MainActivity.this, "Confirm Process", "Do you want to proceed the operation?", new CompletionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this,"onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this,"onCancel", Toast.LENGTH_SHORT).show();
                    }
                });
```
<img src="https://raw.githubusercontent.com/bilginenes/ebdialogutilities/master/readme/confirm.png" width="240px">

## Bugs/Requests

If you encounter any problems feel free to open an issue. If you feel the library is
missing a feature, please raise a ticket on Github and I'll look into it.

## License

This library is under MIT License.
