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
    
    private final double LEARNING_RATE = 0.1; 
    private final String LINEAR_COLOR = "#30c030";
    private final String QUADRATIC_COLOR = "#c030c0";
    private final String CUBIC_COLOR = "#3030c0";



    private Pane pane;
    

    public GradientDescentUtil(Pane pane) {
        this.pane = pane;
    }
    
    
    public void drawLinearModel(double w, double b){
        double leftX = toModelX(0);
        double rightX = toModelX(pane.getWidth());
        double leftY = w * leftX + b;
        double rightY = w * rightX + b;
        Line linearLine = new Line(toScreenX(leftX), toScreenY(leftY), toScreenX(rightX), toScreenY(rightY));
        linearLine.setStroke(Color.web(LINEAR_COLOR));
        linearLine.setStrokeWidth(3);
        pane.getChildren().add(linearLine);
    }
    
    public void drawQuadraticModel(double a, double b, double c){
                Polyline quadLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = toModelX(screenX);
            double y = a * x * x + b * x + c;
            quadLine.getPoints().addAll(toScreenX(x), toScreenY(y));
        }
        quadLine.setStroke(Color.web(QUADRATIC_COLOR));
        quadLine.setStrokeWidth(3);
        pane.getChildren().add(quadLine);
    }
    
    public void drawCubicModel(double a, double b, double c, double d){
        Polyline cubicLine = new Polyline();
        for (int screenX = 0; screenX <= pane.getWidth(); screenX += 5) {
            double x = toModelX(screenX);
            double y = a * x * x * x + b * x * x + c * x + d;
            cubicLine.getPoints().addAll(toScreenX(x), toScreenY(y));
        }
        cubicLine.setStroke(Color.web(CUBIC_COLOR));
        cubicLine.setStrokeWidth(3);
        pane.getChildren().add(cubicLine);
    }
    
    

    public double[] updateLinearModel(List<Point> points, double w, double b) {
        double[] result = new double[]{w,b};
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
        result[0] -= LEARNING_RATE * gradW;
        result[1] -= LEARNING_RATE * gradB;
        
        return result;
    }
    
    public double[] updateQuadraticModel(List<Point> points, double a, double b, double c){
        double[] result = new double[]{a,b,c};
        double gradA = 0, gradBQuad = 0, gradC = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = a * x * x + b * x + c;
            double error = yPred - y;
            gradA += 2 * error * x * x;
            gradBQuad += 2 * error * x;
            gradC += 2 * error;
        }
        gradA /= points.size();
        gradBQuad /= points.size();
        gradC /= points.size();
        result[0] -= LEARNING_RATE * gradA;
        result[1] -= LEARNING_RATE * gradBQuad;
        result[2] -= LEARNING_RATE * gradC;
        return result;        
    }
    
    public double[] updateCubicModel(List<Point> points, double a, double b, double c, double d){
        double[] result = new double[]{a, b, c, d};
        double gradACubic = 0, gradBCubic = 0, gradCCubic = 0, gradD = 0;
        for (Point p : points) {
            double x = toModelX(p.x);
            double y = toModelY(p.y);
            double yPred = a * x * x * x + b * x * x + c * x + d;
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
        result[0] -= LEARNING_RATE * gradACubic;
        result[1] -= LEARNING_RATE * gradBCubic;
        result[2] -= LEARNING_RATE * gradCCubic;
        result[3] -= LEARNING_RATE * gradD;
        return result;
    }

    /**
     * Converts screen X coordinate to model X coordinate.
     */
    private double toModelX(double screenX) {
        return screenX / pane.getWidth() * 2 - 1;
    }

    /**
     * Converts screen Y coordinate to model Y coordinate.
     */
    private double toModelY(double screenY) {
        return 1 - screenY / pane.getHeight() * 2;
    }

    /**
     * Converts model X coordinate to screen X coordinate.
     */
    private double toScreenX(double modelX) {
        return (modelX + 1) / 2 * pane.getWidth();
    }

    /**
     * Converts model Y coordinate to screen Y coordinate.
     */
    private double toScreenY(double modelY) {
        return (1 - modelY) / 2 * pane.getHeight();
    }

    
    
}
