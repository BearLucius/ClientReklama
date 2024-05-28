package com.example.serverclient.service;

import com.example.serverclient.entity.ClientEntity;
import com.example.serverclient.properties.ClientProperties;
import com.example.serverclient.response.BaseResponse;
import com.example.serverclient.response.DataResponse;
import com.example.serverclient.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class ClientService {
    @Getter
    private ObservableList<ClientEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<ClientEntity>>() {

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    private Type listType = new TypeToken<ListResponse<ClientEntity>>() {

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    public void getAll(){
        ListResponse<ClientEntity> data=new ListResponse<>();
        data = service.getObject(http.get(prop.getAllClient()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Получение список Авторов");
            alert.setHeaderText("Работа с авторами");
            alert.setContentText("При получении данных авторов произошла ошибка");
            alert.showAndWait();
        }
    }

    public void add(ClientEntity data){
        String temp = http.post(prop.getSaveClient(), service.getJson(data));
        DataResponse<ClientEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Добавление Автора");
            alert.setHeaderText("Добавление автора невозможно");
            alert.setContentText("Пожалуйста, используйте только кириллицу и НЕ используйте цифры");
            alert.showAndWait();
        }
    }
    private void sort() {
        data.sort(Comparator.comparing(ClientEntity::getLastname));
    }
    public void update (ClientEntity after, ClientEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateClient(),service.getJson(after));
        DataResponse<ClientEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
            sort();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Обновление Автора");
            alert.setHeaderText("Обновить автора не вышло");
            alert.setContentText("Пожалуйста, используйте только кириллицу и НЕ используйте цифры");
            alert.showAndWait();
        }
    }

    public void delete(ClientEntity data){
        String temp = http.delete(prop.getDeleteClient(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто не выбран");
            alert.setContentText("Пожалуйста, выберите автора");
            alert.showAndWait();
        }
    }


    public void findById(ClientEntity data){
        String temp = http.get(prop.getFineByIdClient() + data.getId());
        DataResponse<ClientEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }




}
