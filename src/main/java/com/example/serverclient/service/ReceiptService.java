package com.example.serverclient.service;



import com.example.serverclient.entity.ReceiptEntity;
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

public class ReceiptService {
    @Getter
    private ObservableList<ReceiptEntity> data = FXCollections.observableArrayList();
    private  final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<ReceiptEntity>>(){

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    private Type listType = new TypeToken<ListResponse<ReceiptEntity>>(){

    }.getType(); //фиксируем тип DataResponce<ReceiptEntity>

    public void getAll(){
        ListResponse <ReceiptEntity> data= service.getObject(http.get(prop.getAllReceipt()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(ReceiptEntity data){
        String temp = http.post(prop.getSaveReceipt(), service.getJson(data));
        DataResponse<ReceiptEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }


    public void update(ReceiptEntity after, ReceiptEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateReceipt(), service.getJson(after));
        DataResponse<ReceiptEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }

    public void delete(ReceiptEntity data){
        String temp = http.delete(prop.getDeleteReceipt(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нет выбора");
            alert.setHeaderText("Ничего не выбрано");
            alert.setContentText("Пожалуйста, выберите книгу");
            alert.showAndWait();;
        }
    }


    public void findById(ReceiptEntity data){
        String temp = http.get(prop.getFineByIdReceipt() + data.getId());
        DataResponse<ReceiptEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }
    }
