package ru.yandex.yandexlavka.model.courier;

import lombok.Data;

import java.util.ArrayList;
@Data
public class CreateCouriersResponse {
    ArrayList<CourierDto> couriers;
}

/*
"CreateCouriersResponse": {
        "required": [
        "couriers"
        ]
        }*/
