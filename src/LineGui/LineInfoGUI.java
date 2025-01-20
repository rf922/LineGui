/**
 * File : LineInfoGUI.java
 */
package LineGui;

/**
 * A JavaFx Application to display lines and calculate the midpoint, distance,
 * determine if the line is vertical/ horizontal and additionally calculates the
 * slope of the line.
 *
 */
import javafx.application.*;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.scene.image.Image;

/**
 *
 * @author rf922
 */
public class LineInfoGUI extends Application {
    
    private static final int CIRCLE_RADIUS = 5;
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    
    private Pane pane;
    private BorderPane borderPane;
    private Circle startPoint, endPoint;
    private Line line;
    private Button distanceButton, midpointButton, vertHorzButton, slopeButton;
    private Text distanceText, midpointText, vertHorxText, timeText, slopeText;
    private LineInfoDisplayer lineInfoDisplayer;
    private final DateTimeFormatter form = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm a", Locale.US);
    
    @Override
    public void start(Stage primaryStage) {
        borderPane = new BorderPane();
        pane = new Pane();
        
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
            if (startPoint != null && endPoint != null) {
                this.lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.DISTANCE);
                this.distanceText.setText(this.lineInfoDisplayer.getInfo(line));
            } else if (startPoint != null) {
                this.distanceText.setText("Please select an EndPoint");
            } else {
                this.distanceText.setText("Please pick a start and end point");
            }
        });
        midpointText = new Text("");
        midpointButton = new Button("Calculate Midpoint");
        midpointButton.setOnMousePressed((event) -> {
            if (startPoint != null && endPoint != null) {
                this.lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.MIDPOINT);
                this.midpointText.setText(this.lineInfoDisplayer.getInfo(line));
            } else if (startPoint != null) {
                this.midpointText.setText("Please select an EndPoint");
            } else {
                this.midpointText.setText("Please pick a start and end point");
            }
        });
        vertHorxText = new Text("");
        vertHorzButton = new Button("Determine Vertical/Horizontal");
        vertHorzButton.setOnMouseClicked((event) -> {
            if (startPoint != null && endPoint != null) {
                this.lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.VERTHORZ);
                this.vertHorxText.setText(this.lineInfoDisplayer.getInfo(line));
            } else if (startPoint != null) {
                this.vertHorxText.setText("Please Select an EndPoint");
            } else {
                this.vertHorxText.setText("Please pick a start and end point");
            }
            
        });
        slopeText = new Text("");
        slopeButton = new Button("Approximate Slope");
        slopeButton.setOnMouseClicked((event) -> {
            if (startPoint != null && endPoint != null) {
                this.lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.SLOPE);
                this.slopeText.setText(this.lineInfoDisplayer.getInfo(line));
            } else if (startPoint != null) {
                this.slopeText.setText("Please Select an EndPoint");
            } else {
                this.slopeText.setText("Please pick a start and end point");
            }
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
        Scene scene = new Scene(borderPane, WINDOW_WIDTH, WINDOW_HEIGHT, Color.FLORALWHITE);
        scene.getStylesheets().add(this.getClass().getResource("/resources/css/LineGuiStyle.css").toExternalForm());
        primaryStage.setTitle("Rf922's Line Information");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/images/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
