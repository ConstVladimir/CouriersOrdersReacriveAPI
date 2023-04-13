package ru.yandex.yandexlavka.model.courier;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "couriers")
public class CourierDto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long courier_id;
    String courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;

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
