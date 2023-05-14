package ru.yandex.yandexlavka.exception;

import ru.yandex.yandexlavka.model.order.entity.OrderDB;

public class MustNotDeliveryOrder extends RuntimeException{
    OrderDB order;
    public MustNotDeliveryOrder(OrderDB order){
        this.order = order;
    }
}
