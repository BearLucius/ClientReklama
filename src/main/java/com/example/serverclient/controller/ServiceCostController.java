package com.example.serverclient.controller;

import com.example.serverclient.entity.ServiceEntity;
import com.example.serverclient.entity.ServiceCostEntity;
import com.example.serverclient.service.ServiceService;
import com.example.serverclient.service.ServiceCostService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServiceCostController {
    private final ServiceService cityService = new ServiceService();
    private final ServiceCostService publisherService = new ServiceCostService();
    private boolean addFlag = true;

    @FXML
    private ComboBox<ServiceEntity> comboBoxServiceCost;

    @FXML
    private ListView<ServiceCostEntity> dataList;

    @FXML
    private Button addServCost;
    @FXML
    private TextField textTitle;

    @FXML
    private void initialize(){
        cityService.getAll();
        publisherService.getAll();
        dataList.setItems(publisherService.getData());
        comboBoxServiceCost.setItems(cityService.getData());
    }

    @FXML
    void addAction(ActionEvent event){
        ServiceCostEntity publisher = new ServiceCostEntity();
        publisher.setTitle(textTitle.getText());
        publisher.setService(comboBoxServiceCost.getSelectionModel().getSelectedItem());
        if (addFlag){
            publisherService.add(publisher);
        }else{
            publisher.setId(getSelectionElement().getId());
            publisherService.update(publisher , getSelectionElement());
        }
        textTitle.clear();
        Stage stage = (Stage) addServCost.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void cancelAction(ActionEvent event){
        addFlag = true;
        textTitle.clear();
    }

    @FXML
    void deleteAction(ActionEvent event){
       ServiceCostEntity selectedReceipt = dataList.getSelectionModel().getSelectedItem();
        if (selectedReceipt != null) {
            publisherService.delete(selectedReceipt);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Никто не выбран");
            alert.setContentText("Пожалуйста, выберите автора");
            alert.showAndWait();
        }
    }

//    GenreEntity selectedBook = dataList.getSelectionModel().getSelectedItem();
//        if (selectedBook != null) {
//        service.delete(selectedBook);
//    } else {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Нет выбора");
//        alert.setHeaderText("Жанр не выбран");
//        alert.setContentText("Пожалуйста, выберите жанр");
//        alert.showAndWait();
//    }


    @FXML
    void ClicktoList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                ServiceCostEntity temp = getSelectionElement();
                textTitle.setText(temp.getTitle());
                comboBoxServiceCost.getSelectionModel().select(temp.getService());
                addServCost.setText("Изменить");
            }
        }
    }

    @FXML
    void txtC(MouseEvent event){
        dataList.editableProperty().setValue(false);
        textTitle.clear();
        addServCost.setText("Добавить");
    }
    private ServiceCostEntity getSelectionElement(){
        ServiceCostEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

}
