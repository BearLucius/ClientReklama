package com.example.serverclient.entity;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptEntity {

    private Long id;
    private String receipts;
    private ClientEntity clients;
    private ServiceCostEntity serviceCost;
    private String date;

    @Override
    public String toString() {
        return "ReceiptEntity{" +
                "receipts'" + receipts + '\'' +
                '}';
    }
}