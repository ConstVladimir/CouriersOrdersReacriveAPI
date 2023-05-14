package ru.yandex.yandexlavka.assign;

import ru.yandex.yandexlavka.model.courier.Courier;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.util.*;

public class Region {
    Integer number;
    Set<Courier> couriers;
    Courier[] couriersMass;
    Set<OrderDB> orderDBs;
    OrderDB[] orderDBsMass;
    Set<OrderDB> mustNotDeliveryOrders = new HashSet<>();
    HashMap <Long, HashMap<Long, List<HoursInterval>>> mapOrderAndMapCourierAndIntersectIntervals = new HashMap<>();

    private int amountMembersInSGr = 1;
    ArrayList<Integer> lastSGr = new ArrayList<>();
    Region (Integer number){this.number = number;}
    public void setCouriers (Set<Courier> couriers){
        this.couriers = couriers;
        couriersMass = (Courier[]) couriers.toArray();
    }
    public void setOrderDBs (Set<OrderDB> orderDBs){
        this.orderDBs = orderDBs;
        orderDBsMass = (OrderDB[]) orderDBs.toArray();
    }
    public void setMapOrderAndMapCourierAndIntersectIntervals (){
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
                mustNotDeliveryOrders.add(order);
                orderDBs.remove(order);
            }
            else mapOrderAndMapCourierAndIntersectIntervals.put(order.getOrder_id(), mapCouriersAndIntersects);
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
    public Set<Integer> getSatisfyingCouriersGroup (){
        return new HashSet<>();
    }
    private boolean searchSGr (int amountMemInSGr, Integer startPos, LinkedList<Integer> needGr, Boolean flag ){
            for (int i = startPos; i<couriersMass.length && !flag; i++){
                needGr.addLast(i);
                if (amountMemInSGr == 1) {
                    flag = this.isSatisfyThisGroup(needGr);
                }
                else {
                    flag = searchSGr(amountMemInSGr - 1, i+1, needGr, flag);
                }
                needGr.pollLast();
            }
        return flag;
    }

    private boolean isSatisfyThisGroup (LinkedList<Integer> needGr){
        return false;
    }
}
