package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    private GraphicsContext gc;
    private Map<String, Integer> occurrences = new HashMap<>();
    private Set<String> keys;
    private Color[] pieColours = { Color.ORANGE, Color.SALMON, Color.CYAN, Color.GOLD };

    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        File file = new File("./resources/weatherwarnings-2015.csv");
        parseFile(file);
        keys = occurrences.keySet();
        drawPieGraph(gc);
        drawLegend(gc);
    }

    private void drawPieGraph(GraphicsContext gc){
        int arcs = 0;
        double startAngle = 282.0;
        double arcExtent = 0.0;

        for (String key : keys){
            arcs += occurrences.get(key);
            System.out.printf("%s = %d\n", key, occurrences.get(key));
        }

        int index = 0;
        gc.setStroke(Color.BLACK);
        for (String key : keys){
            arcExtent = 360 * ((double)occurrences.get(key) / arcs);

            gc.setFill(pieColours[index]);
            gc.strokeArc(500,150,200,200,startAngle,arcExtent, ArcType.ROUND);
            gc.fillArc(500,150,200,200,startAngle,arcExtent, ArcType.ROUND);
            startAngle += arcExtent;
            index++;

        }

    }

    private void drawLegend(GraphicsContext gc){
        // Bar Graph Legend
        gc.setFont(new Font("Calibri", 20));
        gc.setFill(Color.BLACK);

        // Pie Graph Legend
        double y = 100;
        for (String word : keys){
            gc.fillText(word, 150, y);
            y += 20;
        }

        // Pie Graph Colours
        y = 86;
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < pieColours.length; i++){
            gc.setFill(pieColours[i]);
            gc.fillRect(130,y,15,15);
            gc.strokeRect(130,y,15,15);
            y += 20;
        }

    }

    public void parseFile(File file){
        try{
            FileReader fileInput = new FileReader(file);
            BufferedReader input = new BufferedReader(fileInput);

            String header = input.readLine();

            int columnIndex = -1;
            String[] columnNames = header.split(",");

            String line;

            while((line = input.readLine()) != null){
                String[] data = line.split(",");

                if (occurrences.containsKey(data[5])){
                    int prev = occurrences.get(data[5]);
                    occurrences.put(data[5], prev + 1);
                } else {
                    occurrences.put(data[5], 1);
                }

            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
