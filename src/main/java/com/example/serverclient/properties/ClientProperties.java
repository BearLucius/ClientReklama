package com.example.serverclient.properties;


import com.example.serverclient.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Getter

public class ClientProperties {
    private final Properties properties = new Properties();

    private String allReceipt;
    private String fineByIdReceipt;
    private String saveReceipt;
    private String updateReceipt;
    private String findByClientInReceipt;
    private String findByTitleInReceipt;
    private String allService;
    private String fineByIdService;
    private String saveService;
    private String updateService;
    private String allServiceCost;
    private String fineByIdServiceCost;
    private String saveServiceCost;
    private String updateServiceCost;
    private String deleteReceipt;
    private String deleteService;
    private String deleteServiceCost;
    private String fineByIdClient;
    private String allClient;
    private String saveClient;
    private String updateClient;
    private String deleteClient;

    public ClientProperties() {
        try (
                InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(input);
            properties.load(input);
            allReceipt = properties.getProperty("receipt.getAll");
            fineByIdReceipt = properties.getProperty("receipt.fineById");
            saveReceipt = properties.getProperty("receipt.save");
            updateReceipt = properties.getProperty("receipt.update");
            findByClientInReceipt = properties.getProperty("receipt.findByClient");
            findByTitleInReceipt = properties.getProperty("receipt.findByTitle");
            allService = properties.getProperty("service.getAll");
            fineByIdService = properties.getProperty("service.fineById");
            saveService = properties.getProperty("service.save");
            updateService = properties.getProperty("service.update");
            allServiceCost = properties.getProperty("servicecost.getAll");
            fineByIdServiceCost = properties.getProperty("servicecost.findById");
            saveServiceCost = properties.getProperty("servicecost.save");
            updateServiceCost = properties.getProperty("servicecost.update");
            deleteReceipt = properties.getProperty("receipt.del");
            deleteService = properties.getProperty("service.del");
            deleteServiceCost = properties.getProperty("service.del");
            fineByIdClient = properties.getProperty("client.fineById");
            allClient = properties.getProperty("client.getAll");
            saveClient = properties.getProperty("client.save");
            deleteClient = properties.getProperty("client.delete");
            updateClient = properties.getProperty("client.update");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
