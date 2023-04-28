package ru.yandex.yandexlavka.model.courier;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCouriersResponse {
    List<CourierDto> couriers;
}

/*
"CreateCouriersResponse": {
        "required": [
        "couriers"
        ]
        }*/
