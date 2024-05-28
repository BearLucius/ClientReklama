package com.example.serverclient.controller;

import com.example.serverclient.entity.ClientEntity;
import com.example.serverclient.service.ClientService;
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

public class ClientController {
    private final ClientService service = new ClientService();
    private final ErrorAlertService alertService = new ErrorAlertService();
    private boolean addFlag = true;

    @FXML
    private TextField textLastname;

    @FXML
    private Button addClient;

    @FXML
    private Button cancelClientAdd;

    @FXML
    private ListView<ClientEntity> dataList;

    @FXML
        private Button deleteAuthor;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;
    @FXML private void initialize(){
        service.getAll();
        dataList.setItems(service.getData());
        //addClient.setOnAction(event -> ((Stage) (addClient.getScene().getWindow())).close());
    }

    @FXML
    void addAction(ActionEvent event){
        try {
        ClientEntity author = new ClientEntity();
        author.setLastname(textLastname.getText());
        author.setName(textName.getText());
        author.setSurname(textSurname.getText());
        if (addFlag){
            service.add(author);
        }
        else {
            author.setId(getSelectionElement().getId());
            service.update(author,getSelectionElement());
        }

        textLastname.clear();
        textName.clear();
        textSurname.clear();
        Stage stage = (Stage) addClient.getScene().getWindow();
        stage.close();
        addClient.setText("Добавить");
        }
            catch (Exception e) {
            alertService.showError(e);
        }
    }
    @FXML
    public void cancelAction(ActionEvent event){

        addFlag = true;
    }
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        try {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                ClientEntity temp = getSelectionElement();
                textSurname.setText(temp.getSurname());
                textName.setText(temp.getName());
                textLastname.setText(temp.getLastname());
                addClient.setText("Изменить");
            }
        }
        }catch (Exception e){
            alertService.showError(e);
        }
    }
    @FXML
    void txt(MouseEvent event){
        dataList.editableProperty().setValue(false);
        textLastname.clear();
        textName.clear();
        textSurname.clear();
        addClient.setText("Добавить");
    }

    private ClientEntity getSelectionElement(){
        ClientEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }
    @FXML
    void deleteAuthor(ActionEvent event){
        ClientEntity selectedBook = dataList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            service.delete(selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Никто не выбран");
            alert.setContentText("Пожалуйста, выберите автора");
            alert.showAndWait();
        }
    }
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();


    }

}


