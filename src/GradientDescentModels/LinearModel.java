/*
 * 
 * 
 * 
 */
package GradientDescentModels;

import LineGui.util.Point;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author rf922
 */
public class LinearModel extends LearningModel {
    private final String LINEAR_COLOR = "#30c030";

    // Linear model: y = w * x + b
    private double w = Math.random() * 2 - 1;
    private double b = Math.random() * 2 - 1;
    
    public LinearModel( Pane pane){
        super(pane);
    }

    @Override
    public void draw() {
        double leftX = toModelX(0);
        double rightX = toModelX(pane.getWidth());
        double leftY = w * leftX + b;
        double rightY = w * rightX + b;
        Line linearLine = new Line(toScreenX(leftX), toScreenY(leftY), toScreenX(rightX), toScreenY(rightY));
        linearLine.setStroke(Color.web(LINEAR_COLOR));
        linearLine.setStrokeWidth(3);
        pane.getChildren().add(linearLine);
    }

    @Override
    public void update(List<Point> points) {
        // Linear model update
        double gradW = 0, gradB = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = w * x + b;
            double error = yPred - y;
            gradW += 2 * error * x;
            gradB += 2 * error;
        }
        gradW /= points.size();
        gradB /= points.size();
        w -= LEARNING_RATE * gradW;
        b -= LEARNING_RATE * gradB;    }
    
}
