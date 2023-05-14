package ru.yandex.yandexlavka.assign;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.yandexlavka.model.courier.Courier;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;
import ru.yandex.yandexlavka.services.MappingUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AssignProcessor {
    @Autowired
    MappingUtils mappingUtils;
    private final List<Courier> courierList;
    private final List<OrderDB> orderDBList;
    private final HashMap<Long, OrderDB> orderDBHashMap;
    private final HashMap<Long, Courier> courierDtoHashMap;
    private final HashMap<Integer, HashSet<OrderDB>> mapOrdersRegionsAndOrdersThere;
    private final HashMap<Integer,HashSet<Courier>> mapOrdersRegionsAndCouriersThere;
    private final HashMap<Integer, Region> regions;

    public AssignProcessor(List<CourierDto> courierList, List<OrderDB> orderDBList) {
        this.courierList = courierList.stream().map(mappingUtils::mappingToCourier).collect(Collectors.toList());
        this.orderDBList = orderDBList;
        orderDBHashMap = this.getOrdersHashMap();
        courierDtoHashMap = this.getCouriersHashMap();
        mapOrdersRegionsAndOrdersThere = this.getMapRegionsAndOrdersThere();
        mapOrdersRegionsAndCouriersThere = this.getMapRegionsAndAvailableCouriersThere();
        regions = this.getMapRegions();
    }

    private HashMap <Long, OrderDB> getOrdersHashMap(){
        HashMap<Long, OrderDB> hashMap = new HashMap<>(orderDBList.size());
        for (OrderDB order : orderDBList) hashMap.put(order.getOrder_id(), order);
        return hashMap;
    }
    private HashMap <Long, Courier> getCouriersHashMap(){
        HashMap<Long, Courier> hashMap = new HashMap<>(courierList.size());
        for (Courier courier : courierList) hashMap.put(courier.getCourier_id(), courier);
        return hashMap;
    }
    private HashMap<Integer, HashSet<OrderDB>> getMapRegionsAndOrdersThere (){
        HashMap<Integer, HashSet<OrderDB>> hashMap = new HashMap<>();
        for (OrderDB order : orderDBList){
            HashSet<OrderDB> hashSet = hashMap.getOrDefault(order.getRegions(), new HashSet<>());
            if (hashSet.isEmpty()) {
                hashMap.put(order.getRegions(), hashSet);
            }
            hashSet.add(order);
        }
        return hashMap;
    }
    private HashMap<Integer, HashSet<Courier>> getMapRegionsAndAvailableCouriersThere (){
        HashMap<Integer, HashSet<Courier>> hashMap = new HashMap<>();
        for (Courier courier : courierList){
            for (Integer reg : courier.getRegions()) {
                HashSet<Courier> hashSet = hashMap.getOrDefault(reg, new HashSet<>());
                if (hashSet.isEmpty()) hashMap.put(reg, hashSet);
                hashSet.add(courier);
            }
        }

        for (Integer reg : hashMap.keySet()){
            Set<Integer> regionsSet = mapOrdersRegionsAndOrdersThere.keySet();
            if (!regionsSet.contains(reg)) hashMap.remove(reg);
        }
        return  hashMap;
    }
    private HashMap<Integer, Region> getMapRegions(){
        HashMap<Integer, Region> hashMap = new HashMap<>(mapOrdersRegionsAndOrdersThere.size());
        for (Map.Entry<Integer,HashSet<OrderDB>> regAndNumbOrders : mapOrdersRegionsAndOrdersThere.entrySet()){
            Region addRegion = new Region(regAndNumbOrders.getKey());
            addRegion.orderDBs = regAndNumbOrders.getValue();
            addRegion.couriers = mapOrdersRegionsAndCouriersThere.getOrDefault(regAndNumbOrders.getKey(), new HashSet<>());
            hashMap.put(regAndNumbOrders.getKey(),addRegion);
        }
        return hashMap;
    }
}
