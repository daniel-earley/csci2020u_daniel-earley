package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Canvas mainCanvas;

    @FXML
    private GraphicsContext gc;

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1, 308431.4,
            322635.9,340253.0,363153.7};

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,
            1472362.0,1583521.9,1613246.3};

    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawBarGraph(gc);
        drawPieGraph(gc);
        drawLegend(gc);
    }

    private void drawBarGraph(GraphicsContext gc) {
        gc.setFill(Color.RED);

        double barHeight = .2f;
        double barWidth = 10.0;
        double barSpacing = 40.0;
        double y = 400.0;
        double x = 50.0;

        for (int i = 0; i < avgHousingPricesByYear.length; i++){
            barHeight = avgHousingPricesByYear[i]/10000;
            //          x, y, width, height
            gc.fillRect(x, y - barHeight, barWidth, barHeight);
            x += barSpacing;
        }

        gc.setFill(Color.BLUE);
        x = 60.0;
        for (int i = 0; i < avgCommercialPricesByYear.length; i++){
            barHeight = avgCommercialPricesByYear[i]/10000;
            //          x, y, width, height
            gc.fillRect(x, y - barHeight, barWidth, barHeight);
            x += barSpacing;
        }
    }

    private void drawPieGraph(GraphicsContext gc){
        int arcs = 0;
        double startAngle = 0.0;
        double arcExtent = 0.0;

        for (int element : purchasesByAgeGroup){
            arcs += element;
        }

        for (int i = 0; i < purchasesByAgeGroup.length; i++){

            arcExtent = 360 * ((double) purchasesByAgeGroup[i] / arcs);
            gc.setFill(pieColours[i]);

            //          x,      y,      w,      h,      startAngle, arcExtent, closure
            gc.fillArc(500, 250, 150, 150, startAngle, arcExtent, ArcType.ROUND);
            startAngle += arcExtent;
        }


    }

    private void drawLegend(GraphicsContext gc){
        // Bar Graph Legend
        gc.setFont(new Font("Calibri", 20));
        gc.setFill(Color.BLACK);
        gc.fillText("Avg House Prices", 100,100);
        gc.fillText("Avg Commercial Prices", 100,120);

        // Pie Graph Legend
        double y = 100;
        for (String word : ageGroups){
            gc.fillText(word, 550, y);
            y += 20;
        }

        // Bar Graph Colours
        gc.setFill(Color.RED);
        gc.fillRect(80,85,15,15);
        gc.setFill(Color.BLUE);
        gc.fillRect(80,105,15,15);

        // Pie Graph Colours
        y = 85;
        for (int i = 0; i < pieColours.length; i++){
            gc.setFill(pieColours[i]);
            gc.fillRect(530,y,15,15);
            y += 20;
        }

    }


}
