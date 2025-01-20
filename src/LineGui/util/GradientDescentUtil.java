/*
 * 
 * File : GradientDescentUtil.java
 * 
 */
package LineGui.util;

import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

/**
 *
 * @author rf922
 */
public class GradientDescentUtil {
    
    private Pane pane;

    
    public GradientDescentUtil(Pane pane) {
        this.pane = pane;
    }
    
    /**
     * Converts screen X coordinate to model X coordinate.
     */
    public double toModelX(double screenX) {
        return screenX / pane.getWidth() * 2 - 1;
    }

    /**
     * Converts screen Y coordinate to model Y coordinate.
     */
    public double toModelY(double screenY) {
        return 1 - screenY / pane.getHeight() * 2;
    }

    /**
     * Converts model X coordinate to screen X coordinate.
     */
    public double toScreenX(double modelX) {
        return (modelX + 1) / 2 * pane.getWidth();
    }

    /**
     * Converts model Y coordinate to screen Y coordinate.
     */
    public double toScreenY(double modelY) {
        return (1 - modelY) / 2 * pane.getHeight();
    }

}
