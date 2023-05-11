package ru.yandex.yandexlavka.services;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.order.*;
import ru.yandex.yandexlavka.model.order.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrdersService {
    @Autowired
    OrdersRepositoryInterface2 ordersRepository2;
    @Autowired
    MappingUtils mappingUtils;
    OrdersService(){}
    public List<OrderDto> createOrders (CreateOrderRequest createOrderRequest){
        List<OrderDB> orderDBList = createOrderRequest.getOrders().stream().map(mappingUtils::mappingToOrderDB).toList();
        ordersRepository2.saveAll(orderDBList);
        return orderDBList.stream().map(mappingUtils::mappingToOrderDto).toList();
    }
    public List<OrderDto> getOrdersResponse (int limit, int offset){
        return ordersRepository2.findAllOffsetLimit(offset, limit)
                .stream()
                .map(mappingUtils::mappingToOrderDto)
                .toList();
    }
    public OrderDto getOrderById(long id){
        return mappingUtils.mappingToOrderDto(ordersRepository2.findById(id).get());
    }
    public ArrayList<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) {
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CompleteOrder completeOrder: completeOrderRequestDto.getComplete_info()){
            OrderDB existOrderDB = Hibernate.unproxy(ordersRepository2.getReferenceById(completeOrder.getOrder_id()), OrderDB.class);//existOrderDBOpt.get();
            if (existOrderDB == null
                    ||existOrderDB.getCourier_id() == null
                    || !Objects.equals(existOrderDB.getCourier_id(), completeOrder.getCourier_id())) throw new EntityNotFoundException("Another courier_id");

            existOrderDB.setCompleted_time(completeOrder.getComplete_time());
            orderDtoArrayList.add(mappingUtils.mappingToOrderDto(existOrderDB));
        }
        return orderDtoArrayList;
    }
    public List<OrderDB> getForCourierMetaInfo (long courier_id, LocalDate startDate, LocalDate endDate){
        return ordersRepository2.getForCourierMetaInfo(courier_id, startDate, endDate);
    }
}
