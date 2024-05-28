package com.example.serverclient;

import com.example.serverclient.controller.ReceiptController;
import com.example.serverclient.controller.MainController;
import com.example.serverclient.entity.ReceiptEntity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainApplication extends Application {
    private FXMLLoader fxmlLoader;
    private static MainController mainController;

    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader (MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,550);
        //  Image ico = new Image("C:/Users/Krapivin2021/IdeaProjects/ServerClient/src/main/resources/com/example/serverclient/logo.png");
        // stage.getIcons().add(ico);
        mainController = fxmlLoader.getController();
        stage.setTitle("Библиотека");
        stage.setScene(scene);
        stage.show();
    }

    public static void showReceiptDialog(Optional<ReceiptEntity> receipt){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-book-view.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с Квитанциями");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            ReceiptController controller = loader.getController();

            controller.setReceipt(receipt);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            receipt = controller.getReceipt();
            System.out.println(receipt.get());
            mainController.setReceipt(receipt);

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void showDialog(String nameView, String title){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить/изменить клиента");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch();
    }
}