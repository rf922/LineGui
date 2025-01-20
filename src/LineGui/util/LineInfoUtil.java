/**
 * File : LineInfoDisplayer.java
 */
package LineGui.util;





import java.math.BigDecimal;
import java.util.function.BiFunction;
import javafx.scene.shape.Line;





@FunctionalInterface
public interface LineInfoUtil {

    String getInfo(Line line);

    public static enum InfoType {
        DISTANCE, MIDPOINT, VERTHORZ, SLOPE;
    }
    
    /**
     * Takes an enum type that determine the information getInfo will return
     * @param type
     * @return LineInfoUtil
     */
    public static LineInfoUtil createLineInfoDisplayer(InfoType type) {
        LineInfoUtil display = switch(type){
            case DISTANCE -> (Line) -> { 
                BiFunction<Double, Double, Double> distanceFormula = (x1, x2) -> {
                    Double distance = Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
                    return distance;};
                return String.format("%.2f",distanceFormula.apply(Line.getEndX()-Line.getStartX(), Line.getEndY() - Line.getStartY()));
            };
            case MIDPOINT ->(Line) -> {
                BiFunction<Double, Double, Double> midPointFormula = (x1, x2) -> (x1+x2)/2;
                Double xComponent = midPointFormula.apply(Line.getStartX(), Line.getEndX());
                Double yComponent = midPointFormula.apply(Line.getStartY(), Line.getEndY());
                String midpoint = String.format("%s%-1.2f%-1s%.2f%s","(",xComponent,",", yComponent,")");
                return midpoint;
            };
            case SLOPE -> (Line) ->{
                BiFunction<Double, Double, Double> slopeApprox = (x1, x2) -> x2/x1;
                Double xComponent = Line.getEndX() - Line.getStartX();
                Double yComponent = Line.getEndY() - Line.getStartY();
                String slopeAprx = String.format("%-5s%.2f", "Slope :",slopeApprox.apply(xComponent, yComponent));
                return slopeAprx;
            };    
            case VERTHORZ -> (Line) -> {
                boolean vertical = BigDecimal.valueOf(Line.getStartX()).compareTo(BigDecimal.valueOf(Line.getEndX())) == 0 ;
                boolean horizontal = BigDecimal.valueOf(Line.getStartY()).compareTo(BigDecimal.valueOf(Line.getEndY())) == 0 ;
                return String.format("%s%-5s%s%-5s", 
                        "Vertical?  : ", vertical," Horizontal? : ", horizontal);
            };
            default -> throw new IllegalArgumentException();
        };
        
        return display;
    }

}
