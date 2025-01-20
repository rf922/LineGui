/*
 * 
 * 
 * 
 */
package GradientDescentModels;

import LineGui.util.GradientDescentUtil;
import LineGui.util.Point;
import java.util.List;
import javafx.scene.layout.Pane;

/**
 *
 * @author rf922
 */
public abstract class LearningModel {
    protected final double LEARNING_RATE = 0.1; 
    protected final GradientDescentUtil util;
    protected final Pane pane;
    
    public LearningModel(GradientDescentUtil util, Pane pane){
        this.util = util;
        this.pane = pane;
    }
    
    public abstract void draw();
    public abstract void update(List<Point> points);
}
