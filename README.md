# LineInfoGUI 
- A JavaFX Application originally given as an assignment in Jessiaca Masters Advanced Java course at City College of San Francisco. It has since been expanded to include more features and visual enhancements. This project was also
partly inspired by CSC 651 Artificial Intelligence taught by Lothar Narins where in one of our assignements we were assigned to model gradient descent using JavaScipt.

## Overview
**LineInfoGUI** is a JavaFX application that allows users to:
1. **Plot Points and Draw Lines**: Mark points on a canvas and create lines passing through them.
2. **Calculate Line Properties**: Compute the midpoint, distance, slope, and determine if the line is vertical or horizontal.
3. **Visualize Gradient Descent**: Fit linear, quadratic, and cubic models to a set of points using gradient descent.

The application is built using **JDK 20** and **JavaFX 21**.

---

## Features

### 1. **Line Plotter**
- **Plot Points**: Click on the canvas to mark points.
- **Draw Lines**: Connect two points to create a line.
- **Calculate Properties**:
  - **Distance**: Calculate the distance between two points.
  - **Midpoint**: Find the midpoint of the line.
  - **Slope**: Approximate the slope of the line.
  - **Vertical/Horizontal**: Determine if the line is vertical or horizontal.

### 2. **Gradient Descent Visualization**
- **Fit Models**:
  - **Linear Model**: Fit a line (`y = m * x + b`) to the points.
  - **Quadratic Model**: Fit a quadratic curve (`y = a * x² + b * x + c`) to the points.
  - **Cubic Model**: Fit a cubic curve (`y = a * x³ + b * x² + c * x + d`) to the points.
- **Reset Points**: Clear all points and restart the gradient descent process.

---

## Prerequisites

Before running the application, ensure you have the following installed:
- **JDK 20**: [Download JDK 20](https://www.oracle.com/java/technologies/downloads/)
- **JavaFX 21 SDK**: [Download JavaFX 21](https://gluonhq.com/products/javafx/)
- **IDE**: IntelliJ IDEA, Apache NetBeans, or any IDE that supports JavaFX.

---

## Setup Instructions

### 1. **Clone the Repository**
```bash
git clone https://github.com/rf922/LineGui.git
cd LineGui
```

### 2. **Configure JavaFX**
1. Download the JavaFX SDK from the [official website](https://gluonhq.com/products/javafx/).
2. Extract the SDK to a directory on your system.

### 3. **Run the Application**
- **From the Command Line**:
  ```bash
  java --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml -jar LineInfoGUI.jar
  ```
  Replace `/path/to/javafx-sdk-21/lib` with the actual path to your JavaFX SDK.

- **From an IDE**:
  - Ensure you include that JavaFX Libraries in your project. (How to do this will vary depending on your ide)
  - Set the VM options to include the JavaFX modules:
    ```
    --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml
    ```
  - Run the `LineInfoGUI` class.

---

## Usage

1. **Line Plotter Tab**:
   - Click on the canvas to mark points.
   - Connect two points to create a line.
   - Use the buttons to calculate the distance, midpoint, slope, and determine if the line is vertical or horizontal.

2. **Gradient Descent Tab**:
   - Click on the canvas to add points.
   - The application will automatically fit linear, quadratic, and cubic models to the points.
   - Use the "Reset Points" button to clear the canvas and start over.

---

## Screenshots

### Line Plotter
![image](https://github.com/user-attachments/assets/1c53987b-a256-4c92-9cf7-4cca033bc52a)


### Gradient Descent Visualization
![image](https://github.com/user-attachments/assets/7e444445-b10f-4ffa-879a-a645677d7ade)

---

Enjoy using **LineInfoGUI**! If you have any questions or feedback, feel free to open an issue.
