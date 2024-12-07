# Calculator App

A simple, user-friendly calculator application for Android with both portrait and landscape modes, designed with a robust ViewModel to handle device rotation seamlessly. This project was developed as a learning exercise during the Edge Course Mobile App Development program under the mentorship of Sourav Palit (Principal App Engineer, Getonnet) and Hasan Murad (Assistant Professor of CSE, CUET).

## Project Context

This calculator app serves as a practical implementation of ViewModel architecture and rotation handling in Android development. It was created as part of my ongoing journey in mobile app development while studying Computer Science and Engineering at Chittagong University of Engineering & Technology (CUET).

## Screenshots

### App Icon
<p align="center">
<img src="Screenshots/shortcut_calculator.jpeg" width="300" height="300" alt="Calculator App Icon"/>
</p>

### Get Started Screen
<p align="center">
<img src="Screenshots/getstarted.jpg" width="300" height="600" alt="Get Started Screen"/>
</p>

### Portrait Mode
<p align="center">
<img src="Screenshots/Portrait_mode.jpg" width="300" height="600" alt="Calculator Portrait Mode"/>
</p>

### Landscape Mode
<p align="center">
<img src="Screenshots/Landscape_mode.jpg" width="600" height="300" alt="Calculator Landscape Mode"/>
</p>

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
- **Minimum SDK Version**: 21
- **Target SDK Version**: 34

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

This project specifically focuses on implementing proper state management during device rotation using ViewModel. Key benefits include:

- Seamless state preservation
- Preventing unnecessary recalculations
- Maintaining user experience across configuration changes

### Key ViewModel Features
- Expression and result state management
- Support for complex calculations
- Error handling
- Flexible input processing

## Learning Objectives

This project demonstrates practical implementation of:
- MVVM Architecture principles
- ViewModel lifecycle management
- Screen rotation handling
- UI state preservation
- Material Design implementation

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

- **Author**: Jannatul Farzana Rashumoni
- **Email**: u2004090@student.cuet.ac.bd
- **GitHub**: [JFRashu](https://github.com/JFRashu)
- **Institution**: Chittagong University of Engineering & Technology (CUET)
- **Course**: Edge Course Mobile App Development

## Acknowledgments

Special thanks to:
- Sourav Palit (Principal App Engineer, Getonnet) - Course Mentor
- Hasan Murad (Assistant Professor of CSE, CUET) - Course Mentor

## Future Enhancements
- Scientific calculator mode
- Memory function
- Theme customization
- Accessibility improvements