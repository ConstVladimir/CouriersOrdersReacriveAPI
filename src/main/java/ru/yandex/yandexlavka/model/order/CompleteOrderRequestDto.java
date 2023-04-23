package ru.yandex.yandexlavka.model.order;

import lombok.Data;

import java.util.ArrayList;
@Data
public class CompleteOrderRequestDto {
    ArrayList<CompleteOrder> complete_info;
}

/*
"CompleteOrderRequestDto": {
        "required": [
        "complete_info"
        ]
        }*/
