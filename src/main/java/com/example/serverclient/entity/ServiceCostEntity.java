package com.example.serverclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ServiceCostEntity {
    private Long id;
    private String title;
    private ServiceEntity service;
    private List<ReceiptEntity> clients;

    @Override
    public String toString() {
        return  title + ' ' + service;
    }
    }
