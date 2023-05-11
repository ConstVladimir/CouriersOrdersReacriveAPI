package ru.yandex.yandexlavka.model.order;

import lombok.Data;
import ru.yandex.yandexlavka.model.order.dto.CreateOrderDto;

import java.util.ArrayList;

@Data
public class CreateOrderRequest {
    ArrayList<CreateOrderDto> orders;
}

/*
"CreateOrderRequest": {
        "required": [
        "orders"
        ]
        }*/
