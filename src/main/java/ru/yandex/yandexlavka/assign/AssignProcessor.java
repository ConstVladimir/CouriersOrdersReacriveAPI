package ru.yandex.yandexlavka.assign;

import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.order.kvv.OrderDB;

import java.time.LocalDate;
import java.util.List;

public class AssignProcessor {
    private final List<CourierDto> courierList;
    private final List<OrderDB> orderDBList;
    private final LocalDate date;

    public AssignProcessor(List<CourierDto> courierList, List<OrderDB> orderDBList, LocalDate date) {
        this.courierList = courierList;
        this.orderDBList = orderDBList;
        this.date = date;
    }
}
