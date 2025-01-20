/*
 * 
 * 
 * 
 */
package LineGui.handlers;

import LineGui.LineInfoDisplayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author alabaster
 */
public class ButtonHandlers {
    
    /**
     * Handles distance button clicks 
     * @param line the current line displayed to the user
     * @param startPoint 
     * @param endPoint
     * @param distanceText
     */
    public static void handleDistanceButtonClick(Line line, Circle startPoint, Circle endPoint, Text distanceText) {
        if (startPoint != null && endPoint != null) {
            LineInfoDisplayer lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.DISTANCE);
            distanceText.setText(lineInfoDisplayer.getInfo(line));
        } else if (startPoint != null) {
            distanceText.setText("Please select an EndPoint");
        } else {
            distanceText.setText("Please pick a start and end point");
        }
    }

    /**
     *
     * @param line
     * @param startPoint
     * @param endPoint
     * @param midpointText
     */
    public static void handleMidPointButtonClick(Line line, Circle startPoint, Circle endPoint, Text midpointText) {
        if (startPoint != null && endPoint != null) {
            LineInfoDisplayer lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.MIDPOINT);
            midpointText.setText(lineInfoDisplayer.getInfo(line));
        } else if (startPoint != null) {
            midpointText.setText("Please select an EndPoint");
        } else {
            midpointText.setText("Please pick a start and end point");
        }
    }

    /**
     *
     * @param line
     * @param startPoint
     * @param endPoint
     * @param vertHorxText
     */
    public static void handleVertHorzButtonClick(Line line, Circle startPoint, Circle endPoint, Text vertHorxText) {
        if (startPoint != null && endPoint != null) {
            LineInfoDisplayer lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.VERTHORZ);
            vertHorxText.setText(lineInfoDisplayer.getInfo(line));
        } else if (startPoint != null) {
            vertHorxText.setText("Please Select an EndPoint");
        } else {
            vertHorxText.setText("Please pick a start and end point");
        }
    }

    /**
     *
     * @param line
     * @param startPoint
     * @param endPoint
     * @param slopeText
     */
    public static void handleSlopeButtonClick(Line line, Circle startPoint, Circle endPoint, Text slopeText) {
        if (startPoint != null && endPoint != null) {
            LineInfoDisplayer lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.SLOPE);
            slopeText.setText(lineInfoDisplayer.getInfo(line));
        } else if (startPoint != null) {
            slopeText.setText("Please Select an EndPoint");
        } else {
            slopeText.setText("Please pick a start and end point");
        }
    }
}
