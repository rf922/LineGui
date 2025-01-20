/**
 * File : GradientDescentView.java
 * 
 */

package LineGui.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.List;

/**
 * A JavaFX view for visualizing gradient descent on linear, quadratic, and cubic models.
 */
public class GradientDescentView {

    private Pane pane; // Pane to hold graphical elements
    private List<Point> points = new ArrayList<>(); // List of points added by the user
    private final String POINT_COLOR = "#c0c0c0";

    // Linear model: y = w * x + b
    private double w = Math.random() * 2 - 1;
    private double b = Math.random() * 2 - 1;
    private final String LINEAR_COLOR = "#30c030";

    // Quadratic model: y = a * x^2 + b * x + c
    private double quadA = Math.random() * 2 - 1;
    private double quadB = Math.random() * 2 - 1;
    private double quadC = Math.random() * 2 - 1;
    private final String QUADRATIC_COLOR = "#c030c0";

    // Cubic model: y = a * x^3 + b * x^2 + c * x + d
    private double cubicA = Math.random() * 2 - 1;
    private double cubicB = Math.random() * 2 - 1;
    private double cubicC = Math.random() * 2 - 1;
    private double cubicD = Math.random() * 2 - 1;
    private final String CUBIC_COLOR = "#3030c0";
    
   
    
    private final double LEARNING_RATE = 0.1; // Learning rate for gradient descent

    public GradientDescentView() {
        this.pane = new Pane();
        this.pane.getStyleClass().add("pane"); // Set background color
    }

    public Pane build() {
        pane.setOnMouseClicked(this::handleMouseClicks); // Handle mouse clicks

        // Animation loop for rendering and updating models
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        }.start();

        return pane;
    }

    /**
     * Handles mouse clicks to add points.
     */
    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        points.add(new Point(x, y));

        // Draw the point
        Circle point = new Circle(x, y, 5, Color.web(POINT_COLOR));
        pane.getChildren().add(point);
    }

    /**
     * Updates the models using gradient descent.
     */
    private void update() {
        if (points.isEmpty()) return;

        // Linear model update
        double gradW = 0, gradB = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = w * x + b;
            double error = yPred - y;
            gradW += 2 * error * x;
            gradB += 2 * error;
        }
        gradW /= points.size();
        gradB /= points.size();
        w -= LEARNING_RATE * gradW;
        b -= LEARNING_RATE * gradB;

        // Quadratic model update
        double gradA = 0, gradBQuad = 0, gradC = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = quadA * x * x + quadB * x + quadC;
            double error = yPred - y;
            gradA += 2 * error * x * x;
            gradBQuad += 2 * error * x;
            gradC += 2 * error;
        }
        gradA /= points.size();
        gradBQuad /= points.size();
        gradC /= points.size();
        quadA -= LEARNING_RATE * gradA;
        quadB -= LEARNING_RATE * gradBQuad;
        quadC -= LEARNING_RATE * gradC;

        // Cubic model update
        double gradACubic = 0, gradBCubic = 0, gradCCubic = 0, gradD = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = cubicA * x * x * x + cubicB * x * x + cubicC * x + cubicD;
            double error = yPred - y;
            gradACubic += 2 * error * x * x * x;
            gradBCubic += 2 * error * x * x;
            gradCCubic += 2 * error * x;
            gradD += 2 * error;
        }
        gradACubic /= points.size();
        gradBCubic /= points.size();
        gradCCubic /= points.size();
        gradD /= points.size();
        cubicA -= LEARNING_RATE * gradACubic;
        cubicB -= LEARNING_RATE * gradBCubic;
        cubicC -= LEARNING_RATE * gradCCubic;
        cubicD -= LEARNING_RATE * gradD;
    }

    /**
     * Renders the points and model predictions.
     */
    private void render() {
        // Clear previous model predictions
        pane.getChildren().removeIf(node -> node instanceof Line || node instanceof Polyline);

        // Draw linear model prediction
        double leftX = toModelX(0);
        double rightX = toModelX(pane.getWidth());
        double leftY = w * leftX + b;
        double rightY = w * rightX + b;
        Line linearLine = new Line(toScreenX(leftX), toScreenY(leftY), toScreenX(rightX), toScreenY(rightY));
        linearLine.setStroke(Color.web(LINEAR_COLOR));
        linearLine.setStrokeWidth(3);
        pane.getChildren().add(linearLine);

        // Draw quadratic model prediction
        Polyline quadLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = toModelX(screenX);
            double y = quadA * x * x + quadB * x + quadC;
            quadLine.getPoints().addAll(toScreenX(x), toScreenY(y));
        }
        quadLine.setStroke(Color.web(QUADRATIC_COLOR));
        quadLine.setStrokeWidth(3);
        pane.getChildren().add(quadLine);

        // Draw cubic model prediction
        Polyline cubicLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = toModelX(screenX);
            double y = cubicA * x * x * x + cubicB * x * x + cubicC * x + cubicD;
            cubicLine.getPoints().addAll(toScreenX(x), toScreenY(y));
        }
        cubicLine.setStroke(Color.web(CUBIC_COLOR));
        cubicLine.setStrokeWidth(3);
        pane.getChildren().add(cubicLine);
    }

    /**
     * Converts screen X coordinate to model X coordinate.
     */
    private double toModelX(double screenX) {
        return screenX / pane.getWidth() * 2 - 1;
    }

    /**
     * Converts screen Y coordinate to model Y coordinate.
     */
    private double toModelY(double screenY) {
        return 1 - screenY / pane.getHeight() * 2;
    }

    /**
     * Converts model X coordinate to screen X coordinate.
     */
    private double toScreenX(double modelX) {
        return (modelX + 1) / 2 * pane.getWidth();
    }

    /**
     * Converts model Y coordinate to screen Y coordinate.
     */
    private double toScreenY(double modelY) {
        return (1 - modelY) / 2 * pane.getHeight();
    }

    /**
     * Represents a point on the pane.
     */
    private static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}