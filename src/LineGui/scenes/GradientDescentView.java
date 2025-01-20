/*
 * File : GradientDescentView.java
 * 
 * 
 */
package LineGui.scenes;

import java.time.LocalDateTime;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author rf922
 */
public class GradientDescentView {

    private Pane pane;       // Pane to hold graphical elements
    private static final int CIRCLE_RADIUS = 5;

    public GradientDescentView() {
        this.pane = new Pane();
    }

    public Pane build() {
        pane = new Pane();
        Text moreText = new Text(100, 200,"More to come!");
        
        moreText.setStyle("-fx-font-size: 70px;");
        pane.setOnMouseClicked(this::handleMouseClicks);
        pane.getChildren().add(moreText);
        return pane;
    }

    /**
     * Handles mouse clicks
     *
     * @param event
     */
    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        Circle point = new Circle(x, y, CIRCLE_RADIUS);
        point.setFill(Color.CYAN);
        pane.getChildren().add(point);

    }

}
