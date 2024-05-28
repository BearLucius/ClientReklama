package com.example.serverclient.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorAlertService {
    public void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(e.getMessage());

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Ошибка:");

        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    public void doSomething() {
        try {


        } catch (Exception e) {
            showError(e);
        }
    }

    public String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }


    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        Button button1 = new Button("Показать ошибку");

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                doSomething();
            }
        });

        root.getChildren().addAll(button1);

        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("JavaFX Error Alert (o7planning.org)");
        stage.setScene(scene);

        stage.show();

    }

}
