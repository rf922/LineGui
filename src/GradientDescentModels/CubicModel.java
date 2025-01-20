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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 *
 * @author rf922
 */
public class CubicModel extends LearningModel {
    private final String CUBIC_COLOR = "#3030c0";
    
    
    // Cubic model: y = a * x^3 + b * x^2 + c * x + d
    private double cubicA = Math.random() * 2 - 1;
    private double cubicB = Math.random() * 2 - 1;
    private double cubicC = Math.random() * 2 - 1;
    private double cubicD = Math.random() * 2 - 1;

    public CubicModel(GradientDescentUtil utils, Pane pane){
        super(utils, pane);
    }
    
    @Override
    public void draw() {
        Polyline cubicLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = util.toModelX(screenX);
            double y = cubicA * x * x * x + cubicB * x * x + cubicC * x + cubicD;
            cubicLine.getPoints().addAll(util.toScreenX(x), util.toScreenY(y));
        }
        cubicLine.setStroke(Color.web(CUBIC_COLOR));
        cubicLine.setStrokeWidth(3);
        pane.getChildren().add(cubicLine);    }

    @Override
    public void update(List<Point> points) {
        double gradACubic = 0, gradBCubic = 0, gradCCubic = 0, gradD = 0;
        for (Point p : points) {
            double x = util.toModelX(p.x);
            double y = util.toModelY(p.y);
            double yPred = cubicA * x * x * x + cubicB * x * x + cubicC * x + cubicD;
            double error = yPred - y;
            gradACubic += 2 * error * x * x * x;
            gradBCubic += 2 * error * x * x;
            gradCCubic += 2 * error * x;
            gradD += 2 * error;
        }
        gradACubic /= points.size();
        gradBCubic /= points.size();
        gradCCubic /= points.size();
        gradD /= points.size();
        cubicA -= LEARNING_RATE * gradACubic;
        cubicB -= LEARNING_RATE * gradBCubic;
        cubicC -= LEARNING_RATE * gradCCubic;
        cubicD -= LEARNING_RATE * gradD;    }
    
}
