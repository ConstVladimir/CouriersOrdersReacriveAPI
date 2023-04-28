package ru.yandex.yandexlavka.model.courier;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "couriers")
public class CourierDto implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long courier_id;
    String courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
    public static CourierDto getCourierDto (CreateCourierDto createCourierDto){
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
