package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    // Table
    @FXML TableView tableView;
    @FXML TableColumn SIDCol;
    @FXML TableColumn assignmentsCol;
    @FXML TableColumn midtermCol;
    @FXML TableColumn finalExamCol;
    @FXML TableColumn finalMarkCol;
    @FXML TableColumn letterGradeCol;
    private ObservableList<StudentRecord> marks = DataSource.getAllMarks();

    // Text Fields
    @FXML TextField SIDField;
    @FXML TextField midtermField;
    @FXML TextField asgmtField;
    @FXML TextField examField;

    // Buttons
    @FXML Button addButton;

    // Menu
    @FXML MenuBar menu;
    @FXML MenuItem newFile;
    @FXML MenuItem openFile;
    @FXML MenuItem saveFile;
    @FXML MenuItem saveAsFile;
    @FXML MenuItem exitFile;

    // Public
    public List<String> header = new ArrayList<>();
    public String fileName = "";

    @FXML
    public void initialize(){
//        DataSource ds = new DataSource();
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(marks);

        // Add strings to header for csv
        header.add("SID");
        header.add("Assignments");
        header.add("Midterm");
        header.add("Final Exam");
    }

    @FXML private void handleAddButton(ActionEvent event){
        String sid = "";
        float midterm = 0.0f;
        float asgmt = 0.0f;
        float exam = 0.0f;

        // Check if there is input
        if (SIDField.getText().length()>0){
            sid = SIDField.getText();
        }
        // Rinse and repeat
        if (midtermField.getText().length()>0){
            midterm = Float.parseFloat(midtermField.getText());
        }
        if (asgmtField.getText().length()>0){
            asgmt = Float.parseFloat(asgmtField.getText());
        }
        if (examField.getText().length()>0){
            exam = Float.parseFloat(examField.getText());
        }
        marks.add(new StudentRecord(sid, midterm, asgmt, exam));
        tableView.setItems(marks);

        // Reset the text fields
        SIDField.setText("");
        midtermField.setText("");
        asgmtField.setText("");
        examField.setText("");
    }


//    private boolean isSID(String input){
//        String match = "(\\d{3})(\\d{3})(\\d{3})";
//        return input.matches(match);
//    }

    public void handleNew(ActionEvent actionEvent) {
        newFile();
    }

    public void handleOpen(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fileName = fc.showOpenDialog(Main.getStage()).getName();
        load();
    }

    public void handleSave(ActionEvent actionEvent) throws IOException {
//        fileName = "default.csv";
        if (fileName.equals("")) {
            try {
                FileChooser fc = new FileChooser();
                fileName = fc.showSaveDialog(Main.getStage()).getName();
                save();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            save();
        }
    }

    public void handleSaveAs(ActionEvent actionEvent) {
        try {
            FileChooser fc = new FileChooser();
            fileName = fc.showSaveDialog(Main.getStage()).getName();
            save();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void newFile(){
//        ObservableList<StudentRecord> newList = FXCollections.observableArrayList();
        marks = FXCollections.observableArrayList();
        tableView.setItems(marks);
    }

    private void load(){
        String inputLine = "";
        try {
            newFile();
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String ogHeader = header.stream().collect(Collectors.joining(", "));
            String sid = "";
            float asgmt = 0.0f;
            float midterm = 0.0f;
            float exam = 0.0f;

            while((inputLine = bf.readLine()) != null){
//                inputLine = bf.readLine();
                String[] col = inputLine.split(",");
                if (!inputLine.equals(ogHeader)){
                    sid = col[0].trim();
                    asgmt = Float.parseFloat(col[1].trim());
                    midterm = Float.parseFloat(col[2].trim());
                    exam = Float.parseFloat(col[3].trim());
                    marks.add(new StudentRecord(sid, asgmt, midterm, exam));
                }
            }
            tableView.setItems(marks);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void save() throws IOException {
        Writer writer = null;
        try {
            if (null != fileName){
                File file = new File(fileName);
                writer = new BufferedWriter(new FileWriter(file));

                // Add header
                String headerLine = header.stream().collect(Collectors.joining(", "));
                writer.write(headerLine + "\n");

                for (StudentRecord student : marks){
                    String entry = student.getSID() + "," + student.getAssignments() + ","
                            + student.getMidterm() + "," + student.getFinalExam() + "\n";
                    writer.write(entry);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }

    }
}
