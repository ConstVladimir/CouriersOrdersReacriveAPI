package ru.yandex.yandexlavka.services;

import jakarta.validation.constraints.NotNull;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.Courier;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.courier.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.order.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.util.ArrayList;

@Service
public class MappingUtils {
    public Courier mappingToCourier (CourierDto courierDto){
        Courier courier = new Courier();
        courier.setCourier_id(courierDto.getCourier_id());
        courier.setCourier_type(courierDto.getCourier_type());
        courier.setRegions(courierDto.getRegions());
        courier.setWorking_hours(courierDto.getWorking_hours());
        courier.setDeliveryTimeIntervals();
        return courier;
    }
    public CourierDto mappingToCourierDto (@NotNull CreateCourierDto createCourierDto){
        CourierDto courierDto = new CourierDto();
        courierDto.setCourier_type(createCourierDto.getCourier_type());
        courierDto.setRegions(createCourierDto.getRegions());
        courierDto.setWorking_hours(createCourierDto.getWorking_hours());
        return courierDto;
    }

    public OrderDB mappingToOrderDB (CreateOrderDto createOrderDto){
        OrderDB orderDB = new OrderDB();
        orderDB.setWeight(createOrderDto.getWeight());
        orderDB.setRegions(createOrderDto.getRegions());
        orderDB.setDelivery_hours(new ArrayList<>(createOrderDto.getDelivery_hours()));
        orderDB.setCost(createOrderDto.getCost());
        return orderDB;
    }
    public OrderDto mappingToOrderDto(OrderDB orderDB){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(orderDB.getOrder_id());
        orderDto.setCost(orderDB.getCost());
        orderDto.setRegions(orderDB.getRegions());
        orderDto.setDelivery_hours(orderDB.getDelivery_hours());
        orderDto.setWeight(orderDB.getWeight());
        orderDto.setCompleted_time(orderDB.getCompleted_time());
        return orderDto;
    }
}
