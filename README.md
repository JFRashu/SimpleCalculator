# Calculator App

A modern, user-friendly calculator application for Android with both portrait and landscape modes, designed with a robust ViewModel to handle device rotation seamlessly.

## Screenshots

### App Icon
![Calculator App Icon](Screenshots/shortcut_calculator.jpeg)

### Get Started Screen
![Get Started Screen](Screenshots/getstarted.jpg)

### Portrait Mode
![Calculator Portrait Mode](Screenshots/Portrait_mode.jpg)

### Landscape Mode
![Calculator Landscape Mode](Screenshots/Landscape_mode.jpg)

## Features

- **Intuitive User Interface**: Clean and modern design for easy calculation
- **Responsive Layouts**:
    - Fully functional portrait mode
    - Optimized landscape mode for wider screens
- **Comprehensive Calculation Support**:
    - Basic arithmetic operations
    - Responsive button interactions
- **State Preservation**:
    - ViewModel architecture ensures smooth state management during device rotation
- **Modern Design**:
    - Material Design principles
    - Smooth, animated button interactions

## Technical Architecture

### Core Technologies
- **Programming Language**: Kotlin
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **UI Framework**: AndroidX
- **State Management**: ViewModel for rotation handling

### Compatibility
- **Minimum SDK Version**: [Specify your minimum SDK version]
- **Target SDK Version**: [Specify your target SDK version]

### Key Components
- `CalculatorViewModel`: Manages calculation logic and app state
- `MainActivity`: Handles UI rendering and ViewModel interaction
- Custom layout files for portrait and landscape modes

## Installation

### Prerequisites
- Android Studio
- JDK 11 or higher
- Android SDK

### Setup Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/JFRashu/SimpleCalculator
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run the application on your preferred emulator or physical device

## ViewModel Rotation Handling

The app uses a ViewModel to preserve calculation state during device rotation. Key benefits include:

- Seamless state preservation
- Preventing unnecessary recalculations
- Maintaining user experience across configuration changes

### Example ViewModel Structure
```kotlin
class CalculatorViewModel : ViewModel() {

  private val _expression = MutableLiveData<String>("")
  val expression: LiveData<String> = _expression

  private val _result = MutableLiveData<String>("0")
  val result: LiveData<String> = _result

  private val sqrtFunction = object : Function("sqrt", 1) {
    override fun apply(vararg args: Double): Double {
      return sqrt(args[0])
    }
  }

  fun appendToExpression(value: String) {
    val currentText = _expression.value ?: ""
    _expression.value = currentText + value
    calculateResult(false)
  }

  fun clearAll() {
    _expression.value = ""
    _result.value = "0"
  }

  fun clearLast() {
    val currentText = _expression.value ?: ""
    if (currentText.isNotEmpty()) {
      _expression.value = currentText.substring(0, currentText.length - 1)
      calculateResult(false)
    }
  }

  fun calculateResult(isEqualPressed: Boolean) {
    try {
      val expressionText = _expression.value ?: ""
      val sanitizedExpression = expressionText
        .replace("×", "*")
        .replace("÷", "/")
        .replace("π", PI.toString())

      if (sanitizedExpression.isEmpty()) {
        _result.value = "0"
        return
      }

      val calculatedResult = evaluateExpression(sanitizedExpression)
      val formattedResult = formatResult(calculatedResult)

      if (isEqualPressed) {
        _expression.value = formattedResult
        _result.value = "0"
      } else {
        _result.value = formattedResult
      }
    } catch (e: Exception) {
      if (isEqualPressed) {
        _result.value = "Error"
      }
    }
  }

  private fun evaluateExpression(expression: String): Double {
    return ExpressionBuilder(expression)
      .function(sqrtFunction)
      .build()
      .evaluate()
  }

  private fun formatResult(result: Double): String {
    return if (result % 1 == 0.0) {
      result.toLong().toString()
    } else {
      String.format("%.8f", result).trimEnd('0').trimEnd('.')
    }
  }
}

```

## Contribution Guidelines

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Troubleshooting

- Ensure you have the latest Android Studio version
- Check Gradle sync and build configurations
- Verify SDK and dependency versions

## Contact

- **Author**: [Jannatul Farzana Rashumoni]
- **Email**: [u2004090@student.cuet.ac.bd]

---

### Future Enhancements
- Scientific calculator mode
- Memory function
- Theme customization
- Accessibility improvements