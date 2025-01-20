/*
 * 
 * 
 * 
 */
package GradientDescentModels;

import LineGui.util.Point;
import java.util.List;
import javafx.scene.layout.Pane;

/**
 *
 * @author rf922
 */
public abstract class LearningModel {
    protected final double LEARNING_RATE = 0.1; 
    protected final Pane pane;
    
    public LearningModel( Pane pane){
        this.pane = pane;
    }
    
    public abstract void draw();
    public abstract void update(List<Point> points);
    
      /**
     * Converts screen X coordinate to model X coordinate.
     */
    protected double toModelX(double screenX) {
        return screenX / pane.getWidth() * 2 - 1;
    }

    /**
     * Converts screen Y coordinate to model Y coordinate.
     */
    protected double toModelY(double screenY) {
        return 1 - screenY / pane.getHeight() * 2;
    }

    /**
     * Converts model X coordinate to screen X coordinate.
     */
    protected double toScreenX(double modelX) {
        return (modelX + 1) / 2 * pane.getWidth();
    }

    /**
     * Converts model Y coordinate to screen Y coordinate.
     */
    protected double toScreenY(double modelY) {
        return (1 - modelY) / 2 * pane.getHeight();
    }

}
