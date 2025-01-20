/**
 * File : GradientDescentView.java
 *
 */
package LineGui.scenes;

import GradientDescentModels.CubicModel;
import GradientDescentModels.LearningModel;
import GradientDescentModels.LinearModel;
import GradientDescentModels.QuadraticModel;
import LineGui.util.GradientDescentUtil;
import LineGui.util.Point;
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
 * A JavaFX view for visualizing gradient descent on linear, quadratic, and
 * cubic models.
 */
public class GradientDescentView {

    private final Pane pane; // Pane to hold graphical elements
    private final List<Point> points = new ArrayList<>(); // List of points added by the user
    private final String POINT_COLOR = "#c0c0c0";
    private final List<LearningModel> models;

    // Linear model: y = w * x + b
    private double w = Math.random() * 2 - 1;
    private double b = Math.random() * 2 - 1;

    // Quadratic model: y = a * x^2 + b * x + c
    private double quadA = Math.random() * 2 - 1;
    private double quadB = Math.random() * 2 - 1;
    private double quadC = Math.random() * 2 - 1;

    // Cubic model: y = a * x^3 + b * x^2 + c * x + d
    private double cubicA = Math.random() * 2 - 1;
    private double cubicB = Math.random() * 2 - 1;
    private double cubicC = Math.random() * 2 - 1;
    private double cubicD = Math.random() * 2 - 1;


    
    private GradientDescentUtil utils;
    
    
    public GradientDescentView() {
        this.pane = new Pane();
        this.pane.getStyleClass().add("pane"); // Set background color
        this.utils = new GradientDescentUtil(pane);
        models = new ArrayList<>();
        models.add(new LinearModel(utils, pane));
        models.add(new QuadraticModel(utils, pane));
        models.add(new CubicModel(utils, pane));        
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
     * Updates the models using gradient descent.
     */
    private void update() {
        if (points.isEmpty()) {
            return;
        }
        
        for(LearningModel m : models){
            m.update(points);
        }
    }

    /**
     * Renders the points and model predictions.
     */
    private void render() {
        // Clear previous model predictions
        pane.getChildren().removeIf(node -> node instanceof Line || node instanceof Polyline);
        
        for(LearningModel m : models){
            m.draw();
        }
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

}
