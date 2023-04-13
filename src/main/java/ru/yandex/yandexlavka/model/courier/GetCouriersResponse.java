package ru.yandex.yandexlavka.model.courier;

import java.util.ArrayList;

public class GetCouriersResponse {
    ArrayList<CourierDto> couriers;
    int limit;
    int offset;
}

/*
"GetCouriersResponse": {
        "required": [
        "couriers",
        "limit",
        "offset"
        ]
        }*/
