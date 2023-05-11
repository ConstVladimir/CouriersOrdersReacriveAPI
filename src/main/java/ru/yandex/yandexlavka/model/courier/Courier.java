package ru.yandex.yandexlavka.model.courier;

import ru.yandex.yandexlavka.model.HoursInterval;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.util.List;
import java.util.stream.Collectors;

public class Courier extends CourierDto {
    List<HoursInterval> working_time_intervals;
    void setDeliveryTimeIntervals(){
        working_time_intervals = super.getWorking_hours().stream().map(HoursInterval::new).collect(Collectors.toList());
    }
}
