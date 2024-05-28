package com.example.serverclient.service;


import com.example.serverclient.entity.ServiceEntity;
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

public class ServiceService {
    @Getter
    private ObservableList<ServiceEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<ServiceEntity>>() {

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    private Type listType = new TypeToken<ListResponse<ServiceEntity>>() {

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    public void getAll() {
        ListResponse<ServiceEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllService()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }

    public void add(ServiceEntity data) {
        String temp = http.post(prop.getSaveService(), service.getJson(data));
        DataResponse<ServiceEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()) {
            this.data.add(respose.getData());

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }


    public void update(ServiceEntity data, ServiceEntity selectionElement) {
        String temp = http.put(prop.getUpdateService(), service.getJson(data));
        DataResponse<ServiceEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()) {
            this.data.add(respose.getData());

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }

    public void delete(ServiceEntity data) {
        String temp = http.delete(prop.getDeleteService(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }


    public void findById(ServiceEntity data) {
        String temp = http.get(prop.getFineByIdService() + data.getId());
        DataResponse<ServiceEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()) {
            this.data.add(respose.getData());

        } else {
            throw new RuntimeException(respose.getMessage());
        }

    }
}
