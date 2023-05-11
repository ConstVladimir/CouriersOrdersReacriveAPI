package ru.yandex.yandexlavka.model.courier;

import lombok.Data;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.util.ArrayList;

@Data
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
