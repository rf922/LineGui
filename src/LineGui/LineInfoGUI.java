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
import LineGui.scenes.GradientDescentView;
import LineGui.scenes.LinePlotView;
import javafx.application.*;

import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.scene.image.Image;

/**
 *
 * @author rf922
 */
public class LineInfoGUI extends Application {
    
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final String WINDOW_TITLE = "Rf922's Line Gui";

        
    @Override
    public void start(Stage primaryStage) {
        
        LinePlotView linePlotView = new LinePlotView();
        GradientDescentView gradientDescentView = new GradientDescentView();

        Tab linePlotTab = new Tab("Line Plot");
        linePlotTab.setContent(linePlotView.build());

        // Create a "More to come!" tab content
        Tab moreToComeTab = new Tab("More");
        
        moreToComeTab.setContent(gradientDescentView.build());

        // Create TabPane to hold the tabs
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("tab-pane");
        tabPane.getTabs().addAll(linePlotTab, moreToComeTab);
        
        // Set the first tab as selected
        tabPane.getSelectionModel().select(linePlotTab);

        // Create the main scene with the TabPane
        Scene scene = new Scene(tabPane, WINDOW_WIDTH, WINDOW_HEIGHT, Color.FLORALWHITE);
        scene.getStylesheets().add(this.getClass().getResource("/resources/css/LineGuiStyle.css").toExternalForm());
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/images/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
