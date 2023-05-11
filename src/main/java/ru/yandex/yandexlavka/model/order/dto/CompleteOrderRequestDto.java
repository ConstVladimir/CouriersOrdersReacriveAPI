package ru.yandex.yandexlavka.model.order.dto;

import lombok.Data;
import ru.yandex.yandexlavka.model.order.CompleteOrder;

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
