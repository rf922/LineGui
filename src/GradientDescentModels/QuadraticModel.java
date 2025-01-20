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
public class QuadraticModel extends LearningModel {
    private final String QUADRATIC_COLOR = "#c030c0";

    
    // Quadratic model: y = a * x^2 + b * x + c
    private double quadA = Math.random() * 2 - 1;
    private double quadB = Math.random() * 2 - 1;
    private double quadC = Math.random() * 2 - 1;
    
    public QuadraticModel(GradientDescentUtil utils, Pane pane){
        super(utils, pane);
    }
    
    @Override
    public void draw() {
        Polyline quadLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = util.toModelX(screenX);
            double y = quadA * x * x + quadB * x + quadC;
            quadLine.getPoints().addAll(util.toScreenX(x), util.toScreenY(y));
        }
        quadLine.setStroke(Color.web(QUADRATIC_COLOR));
        quadLine.setStrokeWidth(3);
        pane.getChildren().add(quadLine);
    }

    @Override
    public void update(List<Point> points) {
        double gradA = 0, gradBQuad = 0, gradC = 0;
        for (Point p : points) {
            double x = util.toModelX(p.x);
            double y = util.toModelY(p.y);
            double yPred = quadA * x * x + quadB * x + quadC;
            double error = yPred - y;
            gradA += 2 * error * x * x;
            gradBQuad += 2 * error * x;
            gradC += 2 * error;
        }
        gradA /= points.size();
        gradBQuad /= points.size();
        gradC /= points.size();
        quadA -= LEARNING_RATE * gradA;
        quadB -= LEARNING_RATE * gradBQuad;
        quadC -= LEARNING_RATE * gradC;    
    }
    
}
