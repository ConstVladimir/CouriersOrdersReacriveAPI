package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface2;
import ru.yandex.yandexlavka.services.interfaces.AssignServiceInt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("assign-Service-1")
public class AssignService implements AssignServiceInt {
    @Autowired
    CouriersRepositoryInterface couriersRepository;
    @Autowired
    OrdersRepositoryInterface2 ordersRepository2;

    public Flux<OrderAssignResponse> getOrderAssignResponse (LocalDate date){
        /*List<OrderAssignResponse> orderAssignResponseList = new ArrayList<>();
        List<CourierDto> courierDtoList = couriersRepository.findAll();
        List<OrderDB> orderDBList = ordersRepository2.getOrdersWhereCompleteTimeIsNULL();*/
        return Flux.empty();
    }
}
