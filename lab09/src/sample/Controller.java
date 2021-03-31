package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private Canvas mainCanvas;
    private GraphicsContext gc;
    private static ObservableList<StockInfo> stocks = FXCollections.observableArrayList();
    private static List<Float> googleStocks = new ArrayList<>();
    private static List<Float> appleStocks = new ArrayList<>();

    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        String googleURL = "https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000" +
                "&interval=1mo&events=history&includeAdjustedClose=true";
        String appleURL = "https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1262322000&period2=1451538000" +
                "&interval=1mo&events=history&includeAdjustedClose=true";
        googleStocks = downloadStockPrices(googleURL);
        appleStocks = downloadStockPrices(appleURL);

        drawLinePlot(googleStocks, appleStocks);
    }

    public static List<Float> downloadStockPrices(String sURL){
        String inputLine = "";
        float close = 0.0f;
        List<Float> temp = new ArrayList<>();
        try {
            URL netURL = new URL(sURL);

            URLConnection conn = netURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            InputStream inStream = conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            String headerLine = br.readLine();
            while((inputLine = br.readLine()) != null){
                String[] col = inputLine.split(",");
                close = Float.parseFloat(col[4].trim());
                temp.add(close);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    public void drawLinePlot(List<Float> stock1, List<Float> stock2){
        int x = 50;
        int y = 450;
        int width = 400;
        int height = 400;

        // Calculate Information
        float xRes = (float) width / (float) stock1.size();
        float yRes = height / Math.max(Collections.max(stock1), Collections.max(stock2));

        // Draw Axis
        gc.setStroke(Color.BLACK);
        gc.strokeLine(x, y, x+width, y);
        gc.strokeLine(x, y, x, y-height);

        // Draw Plot
        plotLine(yRes, xRes, stock1, (float) height, Color.RED);
        plotLine(yRes, xRes, stock2, (float) height, Color.BLUE);
    }

    public void plotLine(float yRes, float xRes, List<Float> stock, float height, Color c){
        gc.setStroke(c);
        for (int i = 0; i < stock.size() - 1; i++){
            float x1 = (i * xRes) + 50;
            float y1 = ((height + 50) - (stock.get(i) * yRes));
            float x2 = ((i + 1) * xRes) + 50;
            float y2 = ((height + 50) - (stock.get(i+1) * yRes));
            gc.strokeLine(x1,y1,x2,y2);
        }
    }
}
