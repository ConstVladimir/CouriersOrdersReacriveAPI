package ru.yandex.yandexlavka.model.courier;

import lombok.Data;
import ru.yandex.yandexlavka.assign.HoursInterval;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class Courier extends CourierDto {
    List<HoursInterval> working_time_intervals;
    int amountRegions;
    int amountOrdersPerOne;
    int timeToFirstOrder;
    int timeToNextOrder;
    double maxWeight;
    public void setDeliveryTimeIntervals(){
        working_time_intervals = super.getWorking_hours().stream().map(HoursInterval::new).collect(Collectors.toList());
    }
    public boolean isAvailableToDelivery (OrderDB order){
        //if (!courier.getRegions().contains(order.getRegions())) return false;
        if (maxWeight < order.getWeight()) return false;
        return true;
    }
    public void setDataAboutDelivery(){
        switch (this.getCourier_type()) {
            case "FOOT" -> {
                amountRegions = 1;
                maxWeight = 10.0;
                amountOrdersPerOne = 2;
                timeToFirstOrder = 25;
                timeToNextOrder = 10;
            }
            case "BIKE" -> {
                amountRegions = 2;
                maxWeight = 20.0;
                amountOrdersPerOne = 4;
                timeToFirstOrder = 12;
                timeToNextOrder = 8;
            }
            case "AUTO" -> {
                amountRegions = 3;
                maxWeight = 40.0;
                amountOrdersPerOne = 7;
                timeToFirstOrder = 8;
                timeToNextOrder = 4;
            }
        }
    }
}
