package ru.yandex.yandexlavka.assign;

import ru.yandex.yandexlavka.model.HoursInterval;
import ru.yandex.yandexlavka.model.courier.Courier;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.util.*;

public class Region {
    Integer number;
    Set<Courier> couriers;
    Courier[] couriersMass;
    Set<OrderDB> orderDBs;
    OrderDB[] orderDBsMass;
    Set<OrderDB> canNotBeSentOrders = new HashSet<>();
    Region (Integer number){this.number = number;}
    public void setCouriers (Set<Courier> couriers){
        this.couriers = couriers;
        couriersMass = (Courier[]) couriers.toArray();
    }
    public void setOrderDBs (Set<OrderDB> orderDBs){
        this.orderDBs = orderDBs;
        orderDBsMass = (OrderDB[]) orderDBs.toArray();
    }
    public HashMap <Long, HashMap<Long, List<HoursInterval>>> getMapOrderAndMapCourierAndIntersectIntervals (){
        HashMap <Long, HashMap<Long, List<HoursInterval>>> hashMap = new HashMap<>();
        for (OrderDB order : orderDBs){
            HashMap<Long, List<HoursInterval>> mapCouriersAndIntersects = new HashMap<>();
            for (Courier courier : couriers){
                if (courier.isAvailableToDelivery(order)){
                    List<HoursInterval> intersections = this.getHoursIntersectionCourierAndOrder(courier, order);
                    if (!intersections.isEmpty()){
                        mapCouriersAndIntersects.put(courier.getCourier_id(), intersections);
                    }
                }
            }
            if (mapCouriersAndIntersects.isEmpty()) {
                canNotBeSentOrders.add(order);
                orderDBs.remove(order);
            }
            else hashMap.put(order.getOrder_id(), mapCouriersAndIntersects);
        }
        return hashMap;
    }
    private boolean isIntersectCourierAndOrder (Courier courier, OrderDB order){
        for (HoursInterval orderInterval : order.getDelivery_time_intervals()){
            for (HoursInterval courierInterval : courier.getWorking_time_intervals()){
                if (orderInterval.isIntersect(courierInterval)) return true;
            }
        }
        return false;
    }
    private List<HoursInterval> getHoursIntersectionCourierAndOrder (Courier courier, OrderDB order){
        ArrayList<HoursInterval> hoursIntervalArrayList = new ArrayList<>();
        for (HoursInterval orderInterval : order.getDelivery_time_intervals()){
            for (HoursInterval courierInterval : courier.getWorking_time_intervals()){
                HoursInterval intersectInterval = orderInterval.getIntersect(courierInterval);
                if (intersectInterval!=null) hoursIntervalArrayList.add(intersectInterval);
            }
        }
        return hoursIntervalArrayList;
    }
}
