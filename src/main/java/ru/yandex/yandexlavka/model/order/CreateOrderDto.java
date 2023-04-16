package ru.yandex.yandexlavka.model.order;

import lombok.Data;

import java.util.ArrayList;
@Data
public class CreateOrderDto {
    Double weight;
    Integer regions;
    ArrayList<String> delivery_hours;
    Integer cost;
}

/*
"CreateOrderDto": {
        "required": [
        "cost",
        "delivery_hours",
        "regions",
        "weight"
        ]
        }*/