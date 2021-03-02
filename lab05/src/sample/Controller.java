package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML TableView tableView;
    @FXML TableColumn SIDCol;
    @FXML TableColumn assignmentsCol;
    @FXML TableColumn midtermCol;
    @FXML TableColumn finalExamCol;
    @FXML TableColumn finalMarkCol;
    @FXML TableColumn letterGradeCol;

    private TableView<StudentRecord> marks;

    @FXML
    public void initialize(){
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(DataSource.getAllMarks());
    }
}
