package com.example.serverclient.service;


import com.example.serverclient.entity.ServiceCostEntity;
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

public class ServiceCostService {
    @Getter
    private ObservableList data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<ServiceCostEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<ServiceCostEntity>>() {
    }.getType();
    public void getAll() {
        ListResponse data = new ListResponse();
        data = service.getObject(http.get(prop.getAllServiceCost()), listType);
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
    public void add(ServiceCostEntity data) {
        String temp = http.post(prop.getSaveServiceCost(), service.getJson(data));
        DataResponse response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }
    public void update(ServiceCostEntity data, ServiceCostEntity selectionElement) {
        String temp = http.put(prop.getUpdateServiceCost(), service.getJson(data));
        DataResponse response = service.getObject(temp,dataType);
        if (response.isSuccess()) {
            this.data.remove(data);
            this.data.add(response.getData());
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }
    //TODO дописать на сервере
    public void delete(ServiceCostEntity data) {
        String temp = http.delete(prop.getDeleteServiceCost(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }
    //TODO дописать
    public void findById(ServiceCostEntity data) {
        String temp = http.get(prop.getFineByIdServiceCost() + data.getId());
        DataResponse response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Низя");
            alert.setHeaderText("Никто/Ничего не выбран/но");
            alert.setContentText("Будем считать что здесь что-то расписанно про ошибку");
            alert.showAndWait();
        }
    }
}
