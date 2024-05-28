package com.example.serverclient.controller;

import com.example.serverclient.MainApplication;
import com.example.serverclient.entity.ReceiptEntity;
import com.example.serverclient.service.ReceiptService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class MainController {


    @FXML
    private TableView<ReceiptEntity> receiptTable;

    @FXML
    private TableColumn<ReceiptEntity, String> columnClient;

    @FXML
    private TableColumn<ReceiptEntity, String> columnNumber;

    @FXML
    private TableColumn<ReceiptEntity, String> columnServiceCost;

    @FXML
    private TableColumn<ReceiptEntity, String> columnReceiptTitle;
    ReceiptService service = new ReceiptService();

    @FXML
    void addReceiptAction(ActionEvent event) {
        Optional<ReceiptEntity> receipt = Optional.empty();
        MainApplication.showReceiptDialog(receipt);
    }

    private Optional<ReceiptEntity> receipt = Optional.empty();

    public void setReceipt(Optional<ReceiptEntity> receipt){
        this.receipt = receipt;
        if (receipt.isPresent()){
            if (receipt.get().getId()!=null)
                service.update(receipt.get(),receiptTable.getSelectionModel().getSelectedItem());
            else service.add(receipt.get());
        }
    }

    @FXML
    void changeBookAction(ActionEvent event){
        try{
        Optional<ReceiptEntity> book = Optional.of(receiptTable.getSelectionModel().getSelectedItem());
        MainApplication.showReceiptDialog(book);}
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Квитанция не выбран");
            alert.setContentText("Пожалуйста, выберите квитанцию");
            alert.showAndWait();
        }
    }

    private ReceiptEntity getSelectionElement(){
        ReceiptEntity temp = receiptTable.getSelectionModel().getSelectedItem();
        return temp;
    }
    @FXML
    void addOrChangeClientAction(ActionEvent event) {
        MainApplication.showDialog("add-author-view.fxml","Работа с клиентами");
    }

    @FXML
    void addOrChangeServiceAction(ActionEvent event) {
        MainApplication.showDialog("add-city-view.fxml","Работа с услугами");
    }

    @FXML
    void addOrChangeServiceCostAction(ActionEvent event) {
        MainApplication.showDialog("add-publisher-view.fxml","Работа со стоимостью");

    }

    @FXML
    void closeAction(ActionEvent event) {
    }

    @FXML
    void deleteReceiptAction(ActionEvent event) {
        ReceiptEntity selectedBook = receiptTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            service.delete(selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Книга не выбран");
            alert.setContentText("Пожалуйста, выберите квитанцию");
            alert.showAndWait();
        }
    }

    @FXML
    private void initialize(){
        //получаем все книги с сервера
        service.getAll();
        //связываем поля таблицы со столбцами
        columnClient.setCellValueFactory(new PropertyValueFactory<ReceiptEntity, String>("clients"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<ReceiptEntity, String>("date"));
        columnReceiptTitle.setCellValueFactory(new PropertyValueFactory<ReceiptEntity, String>("receipts"));
        columnServiceCost.setCellValueFactory(new PropertyValueFactory<ReceiptEntity, String>("serviceCost"));
        receiptTable.setItems(service.getData());
    }

}
