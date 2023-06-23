package ru.yandex.yandexlavka.controllers;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import ru.yandex.yandexlavka.controllers.interfaces.CourierControllerInt;
import ru.yandex.yandexlavka.services.CourierService;
import ru.yandex.yandexlavka.services.interfaces.CourierServiceInt;
import ru.yandex.yandexlavka.services.interfaces.OrdersServiceInt;

public class CourierControllerUTest {
    @Test
    public void createCouriersMethod(){
        CourierServiceInt courierService = mock(CourierServiceInt.class);
        OrdersServiceInt ordersService = mock(OrdersServiceInt.class);

        CourierController courierController = new CourierController();
        courierController.courierService = courierService;
        courierController.ordersService = ordersService;



    }
}
