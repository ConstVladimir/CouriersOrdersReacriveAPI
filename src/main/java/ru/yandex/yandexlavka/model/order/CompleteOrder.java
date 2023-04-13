package ru.yandex.yandexlavka.model.order;

import java.time.OffsetDateTime;

public class CompleteOrder {
    long courier_id;
    long order_id;
    OffsetDateTime complete_time;
}

/*"CompleteOrder": {
        "required": [
        "complete_time",
        "courier_id",
        "order_id"
        ]
        }*/
