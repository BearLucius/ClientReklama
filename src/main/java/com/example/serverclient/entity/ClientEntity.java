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
public class ClientEntity {
    private Long id;
    private String lastname;
    private String name;
    private String surname;
    private List<ReceiptEntity> clients;

    @Override
    public String toString() {
        return lastname + ' ' + name + ' ' + surname + ' ' ;

    }

}