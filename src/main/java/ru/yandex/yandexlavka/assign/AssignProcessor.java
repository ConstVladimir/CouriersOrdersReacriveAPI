package ru.yandex.yandexlavka.assign;

import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.order.kvv.OrderDB;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AssignProcessor {
    private final List<CourierDto> courierList;
    private final List<OrderDB> orderDBList;
    private final HashMap<Long, OrderDB> orderDBHashMap;
    private final HashMap<Long, CourierDto> courierDtoHashMap;
    private final HashMap<Integer, HashSet<Long>> mapOrdersRegionsAndOrdersThere;
    private final HashMap<Integer,HashSet<Long>> mapOrdersRegionsAndCouriersThere;

    public AssignProcessor(List<CourierDto> courierList, List<OrderDB> orderDBList) {
        this.courierList = courierList;
        this.orderDBList = orderDBList;
        orderDBHashMap = this.getOrdersHashMap();
        courierDtoHashMap = this.getCouriersHashMap();
        mapOrdersRegionsAndOrdersThere = this.getMapRegionsAndOrdersThere();
        mapOrdersRegionsAndCouriersThere = this.getMapOrderRegionsAndAvailableCouriersThere();

    }

    public HashMap <Long, OrderDB> getOrdersHashMap(){
        HashMap<Long, OrderDB> hashMap = new HashMap<>(orderDBList.size());
        for (OrderDB order : orderDBList) hashMap.put(order.getOrder_id(), order);
        return hashMap;
    }
    public HashMap <Long, CourierDto> getCouriersHashMap(){
        HashMap<Long, CourierDto> hashMap = new HashMap<>(courierList.size());
        for (CourierDto courier : courierList) hashMap.put(courier.getCourier_id(), courier);
        return hashMap;
    }
    public HashMap<Integer, HashSet<Long>> getMapRegionsAndOrdersThere (){
        HashMap<Integer, HashSet<Long>> hashMap = new HashMap<>();
        for (OrderDB order : orderDBList){
            HashSet<Long> hashSet = hashMap.getOrDefault(order.getRegions(), new HashSet<>());
            hashSet.add(order.getOrder_id());
            hashMap.put(order.getRegions(), hashSet);
        }
        return hashMap;
    }
    public HashMap<Integer, HashSet<Long>> getMapOrderRegionsAndAvailableCouriersThere (){
        HashMap<Integer, HashSet<Long>> hashMap = new HashMap<>();
        for (CourierDto courier : courierList){
            for (Integer reg : courier.getRegions()) {
                HashSet<Long> hashSet = hashMap.getOrDefault(reg, new HashSet<>());
                hashSet.add(courier.getCourier_id());
                hashMap.put(reg, hashSet);
            }
        }

        for (Integer reg : hashMap.keySet()){
            Set<Integer> regionsSet = mapOrdersRegionsAndOrdersThere.keySet();
            if (!regionsSet.contains(reg)) hashMap.remove(reg);
        }
        return  hashMap;
    }
}
