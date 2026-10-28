# SpendWise

A small Android expense tracker written in Kotlin with Jetpack Compose.

- Add expenses with a title, amount and category
- See this month's total with a per-category breakdown
- Delete entries; data is stored on the device only (no account, no network)
- Unit tests for the summary logic and input validation (JUnit)
- GitHub Actions builds the debug APK and runs the tests on every push

## Stack

Kotlin 2.0, Jetpack Compose (Material 3), `ViewModel`, `SharedPreferences` + `org.json`, JUnit 4, Gradle Kotlin DSL. Min SDK 26.

## Structure

| File | Role |
| --- | --- |
| `Expense.kt` | Data model and categories |
| `Summary.kt` | Pure totals, monthly filter and amount validation (unit tested) |
| `ExpenseStore.kt` | On-device persistence; corrupt data falls back to an empty list |
| `ExpenseViewModel.kt` | UI state and actions |
| `MainActivity.kt` | Compose screens: summary card, list, add dialog |

## Build

Open the folder in Android Studio and run the `app` configuration, or with Gradle 8.9+ and JDK 17:

```bash
gradle testDebugUnitTest assembleDebug
```

The APK is written to `app/build/outputs/apk/debug/`. CI uploads the same APK as a build artifact.
