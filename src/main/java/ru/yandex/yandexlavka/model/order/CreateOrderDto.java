package ru.yandex.yandexlavka.model.order;

import java.util.ArrayList;

public class CreateOrderDto {
    float weight;
    int regions;
    ArrayList<String> delivery_hours;
    int cost;
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