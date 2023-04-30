package ru.yandex.yandexlavka.services;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.order.*;
import ru.yandex.yandexlavka.model.order.kvv.OrderDB;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrdersService {
    OrdersRepositoryInterface2 ordersRepository2;
    OrdersService(OrdersRepositoryInterface2 ordersRepository2){
        this.ordersRepository2=ordersRepository2;
    }
    public List<OrderDto> createOrders (CreateOrderRequest createOrderRequest){
        List<OrderDB> orderDBList = createOrderRequest.getOrders().stream().map(OrderDB::getOrderDB).toList();
        ordersRepository2.saveAll(orderDBList);
        List<OrderDto> orderDtoList = orderDBList.stream().map(OrderDB::getOrderDto).toList();
        return  orderDtoList;
    }
    public List<OrderDto> getOrdersResponse (int limit, int offset){
        return ordersRepository2.findAllOffsetLimit(offset, limit)
                .stream()
                .map(OrderDB::getOrderDto)
                .toList();
    }
    public OrderDto getOrderById(long id){
        return ordersRepository2.findById(id).get().getOrderDto();
    }
    public ArrayList<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) {
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CompleteOrder completeOrder: completeOrderRequestDto.getComplete_info()){
            OrderDB existOrderDB = Hibernate.unproxy(ordersRepository2.getReferenceById(completeOrder.getOrder_id()), OrderDB.class);//existOrderDBOpt.get();
            if (existOrderDB == null
                    ||existOrderDB.getCourier_id() == null
                    || !Objects.equals(existOrderDB.getCourier_id(), completeOrder.getCourier_id())) throw new EntityNotFoundException("Another courier_id");

            existOrderDB.setCompleted_time(completeOrder.getComplete_time());
            orderDtoArrayList.add(existOrderDB.getOrderDto());
        }
        return orderDtoArrayList;
    }
    public List<OrderDB> getForCourierMetaInfo (long courier_id, LocalDate startDate, LocalDate endDate){
        return ordersRepository2.getForCourierMetaInfo(courier_id, startDate, endDate);
    }
}
