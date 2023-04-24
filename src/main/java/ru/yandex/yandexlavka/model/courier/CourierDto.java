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
