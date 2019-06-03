package employee.ui;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import employee.parser.EmployeeData;
import employee.parser.EmployeeFactory;
import employee.parser.TeamGenerator;
import employee.parser.TeamSummary;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ResourceUtils;

public class LongestTeamUI extends Application {

    private FileChooser fileChooser;
    private static TableView<EmployeeData> employeeTable = new TableView<EmployeeData>();
    private static ObservableList<EmployeeData> employeeData = FXCollections.emptyObservableList();
    
    private static TableView<TeamSummary> summaryTable = new TableView<TeamSummary>();
    private static ObservableList<TeamSummary> summaryData = FXCollections.emptyObservableList();

    public static void main(String[] args) {
        openDefaultExample("employeesData.csv");
        launch(args);
    }

    private static void openDefaultExample(String resourceFileName) {
        
        List<EmployeeData> employeesFromResourceFile = EmployeeFactory.getEmployeesFromResourceFile(resourceFileName);
        employeeData = FXCollections.observableArrayList(employeesFromResourceFile);
        summaryData = FXCollections.observableArrayList(
                TeamGenerator.getLongestWorkingTogetherTeamFromEmployees(employeesFromResourceFile));
        
        summaryTable.setMaxHeight(65);
    }
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Longest Team");
        stage.setWidth(650);
        stage.setHeight(650);

        final Label label = new Label("Employee Data");
        label.setFont(new Font("Arial", 20));

        employeeTable.setEditable(true);

        TableColumn empIdCol = new TableColumn("Emp Id");
        empIdCol.setMinWidth(100);
        empIdCol.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("empId"));

        TableColumn projectIdCol = new TableColumn("Project Id");
        projectIdCol.setMinWidth(100);
        projectIdCol.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("projectId"));

        TableColumn dateFromCol = new TableColumn("Date From");
        dateFromCol.setMinWidth(200);
        dateFromCol.setCellValueFactory(new PropertyValueFactory<EmployeeData, LocalDate>("dateFrom"));
        
        TableColumn dateToCol = new TableColumn("Date To");
        dateToCol.setMinWidth(200);
        dateToCol.setCellValueFactory(new PropertyValueFactory<EmployeeData, LocalDate>("dateTo"));

        employeeTable.setItems(employeeData);
        employeeTable.getColumns().addAll(empIdCol, projectIdCol, dateFromCol, dateToCol);
        
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(ResourceUtils.getDefaultResourceDirectory());
        final Button openButton = new Button("Open file...");

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    selectFile(file);
                }
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        
        generateSummaryView();
        vbox.getChildren().addAll(label, employeeTable, summaryTable, openButton);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
    
    private static void generateSummaryView() {
        TableColumn empId1 = new TableColumn("Employee ID #1");
        empId1.setMinWidth(150);
        empId1.setCellValueFactory(new PropertyValueFactory<TeamSummary, Integer>("employeeId1"));

        TableColumn empId2 = new TableColumn("Employee ID #2");
        empId2.setMinWidth(150);
        empId2.setCellValueFactory(new PropertyValueFactory<TeamSummary, Integer>("employeeId2"));

        TableColumn projectId = new TableColumn("Last Project ID");
        projectId.setMinWidth(150);
        projectId.setCellValueFactory(new PropertyValueFactory<TeamSummary, Integer>("lastProject"));
        
        TableColumn daysWorked = new TableColumn("Days worked");
        daysWorked.setMinWidth(150);
        daysWorked.setCellValueFactory(new PropertyValueFactory<TeamSummary, Integer>("totalDaysTogether"));

        summaryTable.setItems(summaryData);
        summaryTable.getColumns().addAll(empId1, empId2, projectId, daysWorked);
    }
    
    private void selectFile(File file) {
        
        System.out.println("File selected: " + file.getAbsolutePath());
        
        List<EmployeeData> employeesFromFile = EmployeeFactory.getEmployeesFromFile(file);
        employeeData = FXCollections.observableArrayList(employeesFromFile);
        employeeTable.setItems(employeeData);
        
        summaryData = FXCollections.observableArrayList(
                TeamGenerator.getLongestWorkingTogetherTeamFromEmployees(employeesFromFile));
        summaryTable.setItems(summaryData);
    }
}
