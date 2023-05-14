package ru.yandex.yandexlavka.model.courier;

import lombok.Data;
import ru.yandex.yandexlavka.assign.HoursInterval;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class Courier extends CourierDto {
    List<HoursInterval> working_time_intervals;
    int amountRegions;
    public void setDeliveryTimeIntervals(){
        working_time_intervals = super.getWorking_hours().stream().map(HoursInterval::new).collect(Collectors.toList());
    }

    public void setAmountRegions(){
        switch (this.getCourier_type()) {
            case "FOOT" -> amountRegions = 1;
            case "BIKE" -> amountRegions = 2;
            case "AUTO" -> amountRegions = 3;
        }
    }
}
