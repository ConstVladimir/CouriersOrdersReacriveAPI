package ru.yandex.yandexlavka.services;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.model.order.CreateOrderDto;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderDto;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface;

import java.util.ArrayList;
@Service
public class OrdersService {
    OrdersRepositoryInterface ordersRepository;
    OrdersService(OrdersRepositoryInterface ordersRepository){this.ordersRepository=ordersRepository;}

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
}
