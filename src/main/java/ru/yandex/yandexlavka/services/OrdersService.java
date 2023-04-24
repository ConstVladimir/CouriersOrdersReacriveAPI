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

import static java.util.stream.Collectors.toCollection;

@Service
public class OrdersService {
    OrdersRepositoryInterface2 ordersRepository2;
    OrdersService(OrdersRepositoryInterface2 ordersRepository2){
        this.ordersRepository2=ordersRepository2;
    }

    public ArrayList<OrderDto> createOrders (CreateOrderRequest createOrderRequest){
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CreateOrderDto createOrderDto : createOrderRequest.getOrders()){
            OrderDB orderDb = new OrderDB();
            orderDb.setCost(createOrderDto.getCost());
            orderDb.setWeight(createOrderDto.getWeight());
            orderDb.setRegions(createOrderDto.getRegions());
            orderDb.setDelivery_hours(createOrderDto.getDelivery_hours());

            ordersRepository2.save(orderDb);
            orderDtoArrayList.add(orderDb.getOrderDto());
        }
        return  orderDtoArrayList;
    }
    public ArrayList<OrderDto> getOrdersResponse (int limit, int offset){
        return ordersRepository2.findAllOffsetLimit(offset, limit)
                .stream()
                .map(OrderDB::getOrderDto)
                .collect(toCollection(ArrayList::new));
    }
    public OrderDto GetOrderById (long id){
        OrderDto needCourier = ordersRepository2.findById(id).get().getOrderDto();
        return  needCourier;
    }
    public ArrayList<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) {
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CompleteOrder completeOrder: completeOrderRequestDto.getComplete_info()){
            OrderDB existOrderDB = Hibernate.unproxy(ordersRepository2.getReferenceById(completeOrder.getOrder_id()), OrderDB.class);//existOrderDBOpt.get();
            if (existOrderDB == null ||existOrderDB.getCourier_id() == null || existOrderDB.getCourier_id() != completeOrder.getCourier_id()) throw new EntityNotFoundException("Another courier_id");

            existOrderDB.setCompleted_time(completeOrder.getComplete_time());
            orderDtoArrayList.add(existOrderDB.getOrderDto());
        }
        return orderDtoArrayList;
    }

    public List<OrderDB> getForCourierMetaInfo (long courier_id, LocalDate startDate, LocalDate endDate){
        return ordersRepository2.getForCourierMetaInfo(courier_id, startDate, endDate);
    }
}
