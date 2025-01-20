/*
 * File : GradientDescentView.java
 * 
 * 
 */
package LineGui.scenes;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author rf922
 */
public class GradientDescentView {

    private Pane pane;       // Pane to hold graphical elements

    public GradientDescentView() {
        this.pane = new Pane();
    }

    public Pane build() {
        pane = new StackPane();
        Text moreText = new Text("More to come!");
        moreText.setStyle("-fx-font-size: 70px;");
        pane.getChildren().add(moreText);
        return pane;
    }
}
