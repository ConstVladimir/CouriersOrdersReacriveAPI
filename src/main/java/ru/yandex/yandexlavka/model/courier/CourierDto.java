package ru.yandex.yandexlavka.model.courier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.checkerframework.checker.regex.qual.Regex;
import ru.yandex.yandexlavka.model.HoursInterval;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "couriers")
public class CourierDto{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long courier_id;
    String courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
    @Transient
    List<HoursInterval> working_time_intervals;
    void setDeliveryTimeIntervals(){
        working_time_intervals = working_hours.stream().map(HoursInterval::new).collect(Collectors.toList());
    }
    public static CourierDto getCourierDto (@NotNull CreateCourierDto createCourierDto){
        CourierDto courierDto = new CourierDto();
        courierDto.setCourier_type(createCourierDto.getCourier_type());
        courierDto.setRegions(createCourierDto.getRegions());
        courierDto.setWorking_hours(createCourierDto.getWorking_hours());
        return courierDto;
    }
}
/*
"CourierDto": {
        "required": [
        "courier_id",
        "courier_type",
        "regions",
        "working_hours"
        ]
        }*/
