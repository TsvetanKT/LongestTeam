package employee.ui;

import java.time.LocalDate;
import employee.parser.EmployeeData;
import employee.parser.EmployeeFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewSampleWithData extends Application {

    private TableView<EmployeeData> table = new TableView<EmployeeData>();
    private final ObservableList<EmployeeData> data = FXCollections.observableArrayList(
            EmployeeFactory.getEmployeesFromResourceFile("employeesData.csv"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Longest Team");
        stage.setWidth(650);
        stage.setHeight(600);

        final Label label = new Label("Employee Data");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

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

        table.setItems(data);
        table.getColumns().addAll(empIdCol, projectIdCol, dateFromCol, dateToCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
