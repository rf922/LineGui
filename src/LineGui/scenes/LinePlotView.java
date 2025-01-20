/*
 * File : LinePlotView.java
 * 
 * 
 */
package LineGui.scenes;

import LineGui.handlers.ButtonHandlers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author alabaster
 */
public class LinePlotView {

    private final Pane pane;       // Pane to hold graphical elements
    private Circle startPoint, endPoint; // Start and end points for the line
    private Line line;           // Line connecting the two points
    private Button distanceButton, midpointButton, vertHorzButton, slopeButton;
    private final DateTimeFormatter form = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm a", Locale.US);
    private BorderPane borderPane;
    private Text distanceText, midpointText, vertHorxText, timeText, slopeText;
    private static final int CIRCLE_RADIUS = 5;

    public LinePlotView() {
        this.pane = new Pane();
    }

    public Pane build() {
        borderPane = new BorderPane();
        pane.setBorder(
                new Border(
                        new BorderStroke(Paint.valueOf("black"),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(0.00), BorderStroke.THICK)));

        pane.setOnMouseClicked(this::handleMouseClicks);
        pane.getStyleClass().add("pane");
        borderPane.setCenter(pane);
        distanceText = new Text("");
        distanceButton = new Button("Calculate Distance");
        distanceButton.setOnMouseClicked((event) -> {
            ButtonHandlers.handleDistanceButtonClick(line, startPoint, endPoint, distanceText);
        });
        midpointText = new Text("");
        midpointButton = new Button("Calculate Midpoint");
        midpointButton.setOnMousePressed((event) -> {
            ButtonHandlers.handleMidPointButtonClick(line, startPoint, endPoint, midpointText);
        });
        vertHorxText = new Text("");
        vertHorzButton = new Button("Determine Vertical/Horizontal");
        vertHorzButton.setOnMouseClicked((event) -> {
            ButtonHandlers.handleVertHorzButtonClick(line, startPoint, endPoint, vertHorxText);
        });
        slopeText = new Text("");
        slopeButton = new Button("Approximate Slope");
        slopeButton.setOnMouseClicked((event) -> {
            ButtonHandlers.handleSlopeButtonClick(line, startPoint, endPoint, slopeText);
        });
        timeText = new Text("");
        timeText.setText("");
        TilePane distancePane = new TilePane(distanceButton, distanceText);
        distancePane.setAlignment(Pos.CENTER);
        TilePane midpointPane = new TilePane(midpointButton, midpointText);
        midpointPane.setAlignment(Pos.CENTER);
        TilePane slopePane = new TilePane(slopeButton, slopeText);
        slopePane.setAlignment(Pos.CENTER);
        TilePane vertHorzPane = new TilePane(vertHorzButton, vertHorxText);
        vertHorzPane.setAlignment(Pos.CENTER);
        TilePane timePane = new TilePane(timeText);
        timePane.setAlignment(Pos.CENTER);
        VBox controlBox = new VBox(distancePane, midpointPane, slopePane, vertHorzPane, timePane);
        controlBox.getStyleClass().add("control-box");
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setSpacing(15);
        borderPane.setBottom(controlBox);

        return borderPane;

    }

    /**
     * Handles mouse clicks
     *
     * @param event
     */
    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (startPoint == null) {
            startPoint = new Circle(x, y, CIRCLE_RADIUS);
            startPoint.setFill(Color.CYAN);
            pane.getChildren().add(startPoint);
            createCoordinates(x, y);
            line = null;
        } else if (endPoint == null) {
            endPoint = new Circle(x, y, CIRCLE_RADIUS);
            endPoint.setFill(Color.CYAN);
            pane.getChildren().add(endPoint);
            createCoordinates(x, y);
            line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
            line.setStroke(Color.AQUA);
            line.setFill(Color.CYAN);
            pane.getChildren().add(line);
            this.timeText.setText(String.format("%-17s%s", "Line Created :", LocalDateTime.now().format(form)));
        } else {
            pane.getChildren().clear();
            endPoint = null;
            line = null;
            distanceText.setText("");
            midpointText.setText("");
            vertHorxText.setText("");
            timeText.setText("");
            slopeText.setText("");
            startPoint = new Circle(x, y, CIRCLE_RADIUS);
            startPoint.setFill(Color.CYAN);
            pane.getChildren().add(startPoint);
            createCoordinates(x, y);
        }
    }

    /**
     * Creates coordinates
     *
     * @param x
     * @param y
     */
    private void createCoordinates(double x, double y) {
        String coordinateString = "(" + x + ", " + y + ")";
        Text coordinates = new Text(x - CIRCLE_RADIUS, y - CIRCLE_RADIUS - 2, coordinateString);
        coordinates.setFill(Color.CYAN);
        pane.getChildren().add(coordinates);
    }

}
