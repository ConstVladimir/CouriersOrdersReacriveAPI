package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignService {
    @Autowired
    CouriersRepositoryInterface couriersRepository;
    @Autowired
    OrdersRepositoryInterface2 ordersRepository2;

    public List<OrderAssignResponse> getOrderAssignResponse (LocalDate date){
        List<OrderAssignResponse> orderAssignResponseList = new ArrayList<>();
        List<CourierDto> courierDtoList = couriersRepository.findAll();
        List<OrderDB> orderDBList = ordersRepository2.getOrdersWhereCompleteTimeIsNULL();
        return  orderAssignResponseList;
    }
}
