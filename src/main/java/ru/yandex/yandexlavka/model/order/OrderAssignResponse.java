package ru.yandex.yandexlavka.model.order;

import lombok.Data;
import ru.yandex.yandexlavka.model.courier.CouriersGroupOrders;

import java.time.LocalDate;
import java.util.ArrayList;
@Data
public class OrderAssignResponse {
    LocalDate date;
    ArrayList<CouriersGroupOrders> couriers;
}

/*
"OrderAssignResponse": {
        "required": [
        "date",
        "couriers"
        ]
        }*/
