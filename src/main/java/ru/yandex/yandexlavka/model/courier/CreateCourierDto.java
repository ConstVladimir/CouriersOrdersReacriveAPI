package ru.yandex.yandexlavka.model.courier;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateCourierDto {
    CourierType courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
}

/*
"CreateCourierDto": {
        "required": [
        "courier_type",
        "regions",
        "working_hours"
        ]
        }*/
