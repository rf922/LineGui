/**
 * File : GradientDescentView.java
 *
 */
package LineGui.scenes;

import GradientDescentModels.CubicModel;
import GradientDescentModels.LearningModel;
import GradientDescentModels.LinearModel;
import GradientDescentModels.QuadraticModel;
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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * A JavaFX view for visualizing gradient descent on linear, quadratic, and
 * cubic models.
 */
public class GradientDescentView {

    private final Pane pane; // Pane to hold graphical elements
    private final List<Point> points = new ArrayList<>(); // List of points added by the user
    private final String POINT_COLOR = "#c0c0c0";
    private final List<LearningModel> models;
    
    
    private Button resetButton;
    
    public GradientDescentView() {
        this.pane = new Pane();
        this.pane.getStyleClass().add("pane"); // Set background color
        models = new ArrayList<>();
        models.add(new LinearModel(pane));
        models.add(new QuadraticModel(pane));
        models.add(new CubicModel(pane));        
    }

    public Pane build() {
        BorderPane borderPane = new BorderPane();

        pane.setOnMouseClicked(this::handleMouseClicks); // Handle mouse clicks
                
        resetButton = new Button("Reset Points");
        resetButton.setOnMouseClicked(e -> {
            points.clear();
            pane.getChildren().clear();
        });
        
        TilePane buttonPane = new TilePane(resetButton);
        
        VBox controlBox = new VBox(buttonPane);
        controlBox.getStyleClass().add("control-box");
        controlBox.setAlignment(Pos.CENTER_LEFT);
        
        

        // Animation loop for rendering and updating models
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        }.start();
        borderPane.setCenter(pane);
        borderPane.setBottom(controlBox);
        return borderPane;
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
