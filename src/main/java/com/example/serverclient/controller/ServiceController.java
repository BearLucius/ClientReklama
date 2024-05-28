package com.example.serverclient.controller;

import com.example.serverclient.entity.ServiceEntity;
import com.example.serverclient.service.ServiceService;
import com.example.serverclient.service.ErrorAlertService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServiceController {

    private final ErrorAlertService alertService = new ErrorAlertService();

    private final ServiceService service = new ServiceService();
    private boolean addFlag = true;

    @FXML
    private Button addCity;

    @FXML
    private ListView<ServiceEntity> dataList;

    @FXML
    private TextField textTitle;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getData());
        //addAuthor.setOnAction(event -> ((Stage) (addAuthor.getScene().getWindow())).close());
    }

    @FXML
    void addActionC(ActionEvent event) {

            ServiceEntity city = new ServiceEntity();
            city.setTitle(textTitle.getText());
            if (addFlag) {
                service.add(city);
            } else {
                city.setId(getSelectionElement().getId());
                service.update(city, getSelectionElement());
            }
            textTitle.clear();
            Stage stage = (Stage) addCity.getScene().getWindow();
            stage.close();
            addCity.setText("Добавить");

    }

    @FXML
    void cancel(ActionEvent event) {
        addFlag = true;
        textTitle.clear();
    }

    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                addFlag = false;
                ServiceEntity temp = getSelectionElement();
                textTitle.setText(temp.getTitle());
                addCity.setText("Изменить");
            }
        }
    }

    private ServiceEntity getSelectionElement() {
        ServiceEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void delete(ActionEvent event) {
        ServiceEntity selectedBook = dataList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            service.delete(selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Город не выбран");
            alert.setContentText("Пожалуйста, выберите город");
            alert.showAndWait();
        }
    }
}
