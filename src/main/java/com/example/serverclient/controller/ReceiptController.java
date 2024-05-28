package com.example.serverclient.controller;

import com.example.serverclient.entity.ClientEntity;
import com.example.serverclient.entity.ReceiptEntity;
import com.example.serverclient.entity.ServiceCostEntity;
import com.example.serverclient.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class ReceiptController {
    private final ErrorAlertService alertService = new ErrorAlertService();
    private  final ClientService clientService = new ClientService();
    private final ServiceCostService sercostService = new ServiceCostService();
    //    private final ReceiptService service = new ReceiptService();////////////no sure
    private  final ReceiptService service = new ReceiptService();

    private boolean addFlag = true;

    @FXML
    private ComboBox<ClientEntity> ComboboxClient;

    @FXML
    private ComboBox<ServiceCostEntity> ComboboxServiceCost;

    @FXML
    private Button addReceipt;

    @FXML
    private Button cancelReceipt;

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textDate;
    @FXML
    void cancelAction(ActionEvent event){
        textTitle.clear();
        textDate.clear();
    }
    @FXML
    private void initialize(){
        clientService.getAll();
        sercostService.getAll();
        ComboboxClient.setItems(clientService.getData());

        ComboboxServiceCost.setItems(sercostService.getData());
    }

    @Setter
    @Getter
    private Optional<ReceiptEntity> receipt;
    public  void  start(){
        if (receipt.isPresent()){
            textTitle.setText(receipt.get().getReceipts());
            textDate.setText(receipt.get().getDate());
            ComboboxClient.setValue(receipt.get().getClients());
            ComboboxServiceCost.setValue(receipt.get().getServiceCost());
        }
    }

    @FXML
    void addAction(ActionEvent event) {

        ReceiptEntity temp = ReceiptEntity.builder()
                .receipts(textTitle.getText())
                .date(textDate.getText())
                .serviceCost(ComboboxServiceCost.getSelectionModel().getSelectedItem())
                .clients(ComboboxClient.getSelectionModel().getSelectedItem())
                .build();
        if (receipt.isEmpty()){
            receipt = Optional.of(temp);}
        else {
            temp.setId(receipt.get().getId());
            receipt = Optional.of(temp);
        }
        System.out.println(temp);
        receipt = Optional.of(temp);
        Stage stage = (Stage) addReceipt.getScene().getWindow();
        stage.close();
    }
}

