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

public class ServiceEntity {
    private Long id;
    private String title;
    private List<ServiceCostEntity> serviceCost;
    @Override
    public String toString() {
        return title;
    }
}
