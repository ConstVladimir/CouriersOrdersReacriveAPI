package ru.yandex.yandexlavka.model.courier;

import jakarta.validation.constraints.NotNull;
import ru.yandex.yandexlavka.model.order.GroupOrders;

import java.util.ArrayList;

public class CouriersGroupOrders {
    @NotNull
    Long courier_id;
    ArrayList<GroupOrders> orders;
}

/*
"CouriersGroupOrders": {
        "required": [
        "courier_id",
        "orders"
        ],
        }*/