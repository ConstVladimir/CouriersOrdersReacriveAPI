package ru.yandex.yandexlavka.assign;

import jakarta.persistence.criteria.CriteriaBuilder;
import ru.yandex.yandexlavka.model.courier.Courier;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Region {
    Integer number;
    Set<Courier> couriers;
    Set<OrderDB> orderDBs;

    HashMap <Long, HashMap<Long, List<HoursInterval>>> mapOrderAndMapCourierAndIntersectIntervals = new HashMap<>();
    Region (Integer number){this.number = number;}
    Region (Integer number, Set<Courier> couriers, Set<OrderDB> orderDBs){
        this.number = number;
        this.couriers = couriers;
        this.orderDBs = orderDBs;
    }
    private boolean isAvailableToDelivery (Courier courier, OrderDB order){
        //if (!courier.getRegions().contains(order.getRegions())) return false;
        if (courier.getCourier_type().equals("FOOT") && order.getWeight() > 10.0) return false;
        if (courier.getCourier_type().equals("BIKE") && order.getWeight() > 20.0) return false;
        if (courier.getCourier_type().equals("AUTO") && order.getWeight() > 40.0) return false;
        return true;
    }

    private void setMapOrderAndMapCourierAndIntersectIntervals (){
        for (OrderDB order : orderDBs){
            HashMap<Long, List<HoursInterval>> mapCouriersAndIntersects = new HashMap<>();
            mapOrderAndMapCourierAndIntersectIntervals.put(order.getOrder_id(), mapCouriersAndIntersects);
            for (Courier courier : couriers){
                if (isAvailableToDelivery(courier, order)){
                    List<HoursInterval> intersections = this.getHoursIntersectionCourierAndOrder(courier, order);
                    if (!intersections.isEmpty()){
                        mapCouriersAndIntersects.put(courier.getCourier_id(), intersections);
                    }
                }
            }
            if (mapCouriersAndIntersects.isEmpty()) System.out.println("Must not be delivery order Id="+ order.getOrder_id());
        }

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
