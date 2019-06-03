package employee.ui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MyFilePicker extends Application implements EventHandler<ActionEvent> {

    private FileChooser fileChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Example test Proj");

        fileChooser = new FileChooser();
        final Button openButton = new Button("Open file...");

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    selectFile(file);
                }
            }
        });

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().add(openButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        primaryStage.setScene(new Scene(rootGroup));
        primaryStage.show();
    }

    private void selectFile(File file) {
        
        System.out.println("File selected: " + file.getAbsolutePath());
        
        System.out.println(loadFileString(file));
        
    }
    
    private static String loadFileString(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(MyFilePicker.class.getName()).log(
                    Level.SEVERE, "Cannot open file: " + file.getAbsolutePath(), ex);
        }
        
        return null;
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
