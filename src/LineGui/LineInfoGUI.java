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
import LineGui.handlers.ButtonHandlers;
import LineGui.scenes.LinePlotView;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author rf922
 */
public class LineInfoGUI extends Application {
    
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

        
    @Override
    public void start(Stage primaryStage) {
        
        LinePlotView linePlotView = new LinePlotView(primaryStage);
        Scene scene = new Scene(linePlotView.build(), WINDOW_WIDTH, WINDOW_HEIGHT, Color.FLORALWHITE);
        scene.getStylesheets().add(this.getClass().getResource("/resources/css/LineGuiStyle.css").toExternalForm());
        primaryStage.setTitle("Rf922's Line Information");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/images/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
