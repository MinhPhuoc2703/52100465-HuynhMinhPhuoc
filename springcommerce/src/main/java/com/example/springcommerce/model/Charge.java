package com.example.springcommerce.model;

import lombok.Data;

@Data
public class Charge {

    public enum Currency {
         VND;
    }

    private String description;
    private int amount;
    private Currency currency;


}
