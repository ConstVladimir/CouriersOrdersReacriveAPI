package ru.yandex.yandexlavka.services;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.model.order.*;
import ru.yandex.yandexlavka.repositories.CompleteOrdersRepositoryInterface;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface;

import java.util.ArrayList;
@Service
public class OrdersService {
    OrdersRepositoryInterface ordersRepository;
    CompleteOrdersRepositoryInterface completeOrdersRepository;
    OrdersService(OrdersRepositoryInterface ordersRepository, CompleteOrdersRepositoryInterface completeOrdersRepository){this.ordersRepository=ordersRepository; this.completeOrdersRepository=completeOrdersRepository;}

    public ArrayList<OrderDto> createOrders (CreateOrderRequest createOrderRequest){
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CreateOrderDto createOrderDto : createOrderRequest.getOrders()){
            OrderDto orderDto = new OrderDto();
            orderDto.setCost(createOrderDto.getCost());
            orderDto.setWeight(createOrderDto.getWeight());
            orderDto.setRegions(createOrderDto.getRegions());
            orderDto.setDelivery_hours(createOrderDto.getDelivery_hours());

            ordersRepository.save(orderDto);
            orderDtoArrayList.add(orderDto);
        }
        return  orderDtoArrayList;
    }
    public ArrayList<OrderDto> getOrdersResponse (int limit, int offset){
        ArrayList<OrderDto> courierDtoArrayList = new ArrayList<>();
        courierDtoArrayList.addAll(ordersRepository.findAllOffsetLimit(offset,limit));
        return  courierDtoArrayList;
    }
    public OrderDto GetOrderById (long id){
        OrderDto needCourier = ordersRepository.findById(id);
        return  needCourier;
    }
    public ArrayList<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) {
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        for (CompleteOrder completeOrder: completeOrderRequestDto.getComplete_info()){
            CompleteOrder prevComplete = Hibernate.unproxy(completeOrdersRepository.getReferenceById(completeOrder.getOrder_id()), CompleteOrder.class);

            if (prevComplete.getCourier_id() != completeOrder.getCourier_id()) throw new EntityNotFoundException("Another courier_id");

            completeOrdersRepository.completeOrder(completeOrder.getComplete_time(), completeOrder.getOrder_id());
            orderDtoArrayList.add(this.GetOrderById(completeOrder.getOrder_id()));
        }
        return orderDtoArrayList;
    }
}
